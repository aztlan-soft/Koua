package com.aztlansoft.koua;

import android.app.Application;

/**
 * Class to pass the Application Context.
 */
public class Koua extends Application
{
  private static Koua instance;

  public Koua()
  {
    instance = this;
  }

  public static Koua getInstance()
  {
    return instance;
  }
}
