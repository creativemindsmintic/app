package com.creativeminds.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //Asignacion tabla segun clase
public class Empresa {
    @Id //PK
    @GeneratedValue(strategy = GenerationType.AUTO) //Autogenerado
    int id;
    String Nombre;
    String Direccion;
    String telefono;
    String nit;
    Boolean activo;

    public Empresa() {
    }

    public Empresa(String nombre, String direccion, String telefono, String nit) {
        Nombre = nombre;
        Direccion = direccion;
        this.telefono = telefono;
        this.nit = nit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
