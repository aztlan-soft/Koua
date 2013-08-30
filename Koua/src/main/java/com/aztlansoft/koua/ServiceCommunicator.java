package com.aztlansoft.koua;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentFilter;


/**
 * This service intercepts each SMS received
 */
public class ServiceCommunicator extends IntentService
{
  SMSReceiver mSMSReceiver;
  IntentFilter mIntentFilter;

  /**
   * Default constructor
   *
   * @param name Class name
   */
  public ServiceCommunicator(String name)
  {
    super(name);
  }

  /**
   * In an IntentService, onHandleIntent is run on a background thread.  As it
   * runs, it broadcasts its current status using the LocalBroadcastManager.
   *
   * @param intent The Intent that starts the IntentService.
   */
  @Override
  protected void onHandleIntent(Intent intent)
  {
  }

  /**
   * Action invoked on create
   */
  @Override
  public void onCreate()
  {
    super.onCreate();

    // SMS event receiver
    mSMSReceiver = new SMSReceiver();

    // Create the intent filter
    mIntentFilter = new IntentFilter();

    // Add the action for the intent filter
    // ToDo: Replace magic string
    mIntentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");

    // Register the receiver
    registerReceiver(mSMSReceiver, mIntentFilter);
  }

  /**
   * Action invoked on destroy
   */
  @Override
  public void onDestroy()
  {
    super.onDestroy();

    // Unregister the SMS receiver
    unregisterReceiver(mSMSReceiver);
  }
}
