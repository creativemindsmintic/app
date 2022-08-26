package com.creativeminds.app.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MovimientoDinero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @ManyToOne
    @JoinColumn(name = "empleado_id")
    Empleado empleado;
    String tipo_movi;
    String concepto;
    Date fecha;
    Double monto;

    public MovimientoDinero() {
    }

    public MovimientoDinero(int id, Empleado empleado, String tipo_movi, String concepto, Date fecha, Double monto) {
        this.id = id;
        this.empleado = empleado;
        this.tipo_movi = tipo_movi;
        this.concepto = concepto;
        this.fecha = fecha;
        this.monto = monto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
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


