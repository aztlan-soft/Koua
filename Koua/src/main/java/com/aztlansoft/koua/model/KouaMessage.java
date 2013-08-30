package com.aztlansoft.koua.model;
import java.math.BigDecimal;

/**
 * Class tha represents the message.
 */
public class KouaMessage
{
  private String operacion;
  private BigDecimal monto;
  private String fecha;
  private String numAutorizacion;

  public void SetAutorizacion(String value)
  {
    this.numAutorizacion = value;
  }

  public void SetMonto(BigDecimal value)
  {
    this.monto = value;
  }

  public void SetFecha(String value)
  {
    this.fecha = value;
  }

  public void SetOperacion(String value)
  {
    this.operacion = value;
  }

  public String GetOperacion()
  {
    return operacion;
  }

  public BigDecimal GetMonto()
  {
    return monto;
  }

  public String GetFecha()
  {
    return this.fecha;
  }

  public String GetAutorizacion()
  {
    return this.numAutorizacion;
  }
}
