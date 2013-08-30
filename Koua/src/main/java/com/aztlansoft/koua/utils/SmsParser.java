package com.aztlansoft.koua.utils;
import com.aztlansoft.koua.model.*;

public interface SmsParser
{
  public Boolean ValidMessage(String message);
  public KouaMessage Process(String message);
}
