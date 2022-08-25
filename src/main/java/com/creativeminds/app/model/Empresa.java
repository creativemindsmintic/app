package com.creativeminds.app.model;

import javax.persistence.*;

@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(nullable = false,length = 50)
    String Nombre;
    @Column(nullable = true,length = 90)
    String Direccion;
    @Column(nullable = true,length = 10)
    String telefono;
    @Column(nullable = false,length = 15)
    String nit;
    @Column(name = "activo")
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
