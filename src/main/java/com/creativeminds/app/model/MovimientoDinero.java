package com.creativeminds.app.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class MovimientoDinero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    int user_id;
    String tipo_movi;
    String concepto;
    Date fecha;
    Double monto;

    public MovimientoDinero(int user_id, String tipo_movi, String concepto, Date fecha, Double monto) {
        this.user_id = user_id;
        this.tipo_movi = tipo_movi;
        this.concepto = concepto;
        this.fecha = fecha;
        this.monto = monto;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTipo_movi() {
        return tipo_movi;
    }

    public void setTipo_movi(String tipo_movi) {
        this.tipo_movi = tipo_movi;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
}


