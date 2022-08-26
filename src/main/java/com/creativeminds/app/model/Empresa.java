package com.creativeminds.app.model;

import javax.persistence.*;

@Entity //Asignacion tabla segun clase
public class Empresa {
    @Id //PK
    @GeneratedValue(strategy = GenerationType.AUTO) //Autogenerado
    int id;
    @Column(nullable = false,length = 50)
    String nombre;
    @Column(nullable = true,length = 90)
    String direccion;
    @Column(nullable = true,length = 10)
    String telefono;
    @Column(nullable = false,length = 15)
    String nit;
    @Column(name = "activo")
    Boolean activo = true;

    public Empresa() {
    }

    public Empresa(String nombre, String direccion, String telefono, String nit) {
        this.nombre = nombre;
        this.direccion = direccion;
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
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
