package com.aztlansoft.koua.model;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table KOUA_MESSAGE.
 */
public class KouaMessage
{

  private Long id;
  /**
   * Not-null value.
   */
  private String operacion;
  private String monto;
  private String autorizacion;
  private String fecha;

  public KouaMessage()
  {
  }

  public KouaMessage(Long id)
  {
    this.id = id;
  }

  public KouaMessage(Long id, String operacion, String monto, String autorizacion, String fecha)
  {
    this.id = id;
    this.operacion = operacion;
    this.monto = monto;
    this.autorizacion = autorizacion;
    this.fecha = fecha;
  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  /**
   * Not-null value.
   */
  public String getOperacion()
  {
    return operacion;
  }

  /**
   * Not-null value; ensure this value is available before it is saved to the database.
   */
  public void setOperacion(String operacion)
  {
    this.operacion = operacion;
  }

  public String getMonto()
  {
    return monto;
  }

  public void setMonto(String monto)
  {
    this.monto = monto;
  }

  public String getAutorizacion()
  {
    return autorizacion;
  }

  public void setAutorizacion(String autorizacion)
  {
    this.autorizacion = autorizacion;
  }

  public String getFecha()
  {
    return fecha;
  }

  public void setFecha(String fecha)
  {
    this.fecha = fecha;
  }

}
