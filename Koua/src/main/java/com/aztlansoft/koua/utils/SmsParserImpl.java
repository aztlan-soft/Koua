package com.aztlansoft.koua.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.math.BigDecimal;

import com.aztlansoft.koua.model.*;

import org.apache.commons.lang3.StringUtils;

public class SmsParserImpl implements SmsParser
{
  public Boolean ValidMessage(String message)
  {
    if (message != null)
    {
      List<String> tokens = new ArrayList<String>();
      tokens.add("Retiro/Compra");
      tokens.add("Deposito");

      String patternString = "\\b(" + StringUtils.join(tokens, "|") + ")\\b";
      Pattern pattern = Pattern.compile(patternString);
      Matcher matcher = pattern.matcher(message);

      while (matcher.find())
      {
        return true;
      }

      return false;
    } else
    {
      return false;
    }
  }

  public KouaMessage Process(String message)
  {
//    try
//    {
    String firstDatePart = null;
    KouaMessage msg = new KouaMessage();

    String[] words = message.split("\\s+");
    List<String> wordList = Arrays.asList(words);

    for (String eachString : wordList)
    {
      if (eachString.equals("Retiro/Compra") || eachString.equals("Deposito"))
      {
        msg.SetOperacion(eachString);
      } else if (eachString.startsWith("$"))
      {
        String montoStr = eachString.substring(1);
        BigDecimal monto = new BigDecimal(montoStr);

        msg.SetMonto(monto);
      }

      if (firstDatePart == null)
      {
        Boolean validDate = TryToParse(eachString, "dd/MM/yy");

        if (validDate)
          firstDatePart = eachString;

      } else
      {
        String fullDate = firstDatePart + " " + eachString;

        if (TryToParse(fullDate, "dd/MM/yy HH:mm:ss"))
        {
          msg.SetFecha(fullDate);
        }
      }
    }

    Pattern p = Pattern.compile("Auto.(.*)");
    Matcher m = p.matcher(message);
    if (m.find())
    {
      msg.SetAutorizacion(m.group(1));
    }

    return msg;
//    } catch (Exception ex)
//    {
//      return null;
//    }
  }

  private Boolean TryToParse(String value, String pattern)
  {
    try
    {
      SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
      dateFormat.parse(value);
      return true;
    } catch (Exception ex)
    {
      return false;
    }
  }
}
