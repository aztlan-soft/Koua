package com.aztlansoft.koua;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.aztlansoft.koua.dao.DaoMaster;
import com.aztlansoft.koua.dao.DaoMaster.DevOpenHelper;
import com.aztlansoft.koua.dao.DaoSession;
import com.aztlansoft.koua.dao.KouaMessageDao;
import com.aztlansoft.koua.model.KouaMessage;
import com.aztlansoft.koua.utils.SmsParserImpl;

/**
 * This BroadcastReceiver obtains the message body from each SMS received
 */
public class SMSReceiver extends BroadcastReceiver
{
  private SQLiteDatabase db;
  private DevOpenHelper helper;
  private DaoSession daoSession;
  private DaoMaster daoMaster;
  private KouaMessageDao kouaMessageDao;

  // Get the class name to log events
  private final String TAG = this.getClass().getSimpleName();

  /**
   * Invoked each time the receiver catch an SMS_RECEIVED event
   *
   * @param context the application context
   * @param intent  the intent with the action event
   */
  @Override
  public void onReceive(Context context, Intent intent)
  {
    helper = new DaoMaster.DevOpenHelper(Koua.getInstance(), "koua-db", null);
    db = helper.getWritableDatabase();
    daoMaster = new DaoMaster(db);
    daoSession = daoMaster.newSession();
    kouaMessageDao = daoSession.getKouaMessageDao();

    String smsData;

    // Get the intent extras with the PDU information
    Bundle pdusBundle = intent.getExtras();

    if (pdusBundle != null)
    {
      // Get the message in PDU format
      Object[] pdus = (Object[]) pdusBundle.get("pdus");

      // Create a SMS message from the PDU info
      SmsMessage message = SmsMessage.createFromPdu((byte[]) pdus[0]);

      // Extract the message body
      smsData = message.getMessageBody();

      SmsParserImpl parser = new SmsParserImpl();
      Boolean valid = parser.ValidMessage(smsData);

      if (valid)
      {
        KouaMessage kouamsg;

        kouamsg = parser.Process(smsData);

        if (kouamsg != null)
        {
          kouaMessageDao.insert(kouamsg);
          Toast.makeText(context, "New KouaMessage ID:" + kouamsg.getId(), Toast.LENGTH_LONG).show();
        }
      }

      // ToDo: Remove Logs and Toast messages used for testing
      // Log the message body
      Log.d(TAG, message.getMessageBody());

      // Display the message body
      Toast.makeText(context, smsData, Toast.LENGTH_SHORT).show();

      // ToDo: Add function to parse the message body
    }
  }
}
