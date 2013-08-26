package com.aztlansoft.koua;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * This BroadcastReceiver obtains the message body from each SMS received
 */
public class SMSReceiver extends BroadcastReceiver {

    // Get the class name to log events
    private final String TAG = this.getClass().getSimpleName();

    /**
     * Invoked each time the receiver catch an SMS_RECEIVED event
     *
     * @param context the application context
     * @param intent  the intent with the action event
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        String smsData = "";

        // ToDo: Remove Logs and Toast messages used for testing
        Log.d(TAG, intent.getAction().toString());

        Toast.makeText(context, TAG + intent.getAction().toString(),
                Toast.LENGTH_SHORT).show();

        // Get the intent extras with the PDU information
        Bundle pdusBundle = intent.getExtras();

        if (pdusBundle != null) {

            // Get the message in PDU format
            Object[] pdus = (Object[]) pdusBundle.get("pdus");

            // Create a SMS message from the PDU info
            SmsMessage message = SmsMessage.createFromPdu((byte[]) pdus[0]);

            // Extract the message body
            smsData = message.getMessageBody();

            // ToDo: Remove Logs and Toast messages used for testing
            // Log the message body
            Log.d(TAG, message.getMessageBody());

            // Display the message body
            Toast.makeText(context, smsData,
                    Toast.LENGTH_SHORT).show();

            // ToDo: Add function to parse the message body
        }
    }

}
