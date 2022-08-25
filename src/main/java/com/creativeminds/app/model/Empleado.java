package com.creativeminds.app.model;

import javax.persistence.*;

@Entity
@Table(name="empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    @Column(name = "nombre", nullable = false, length = 80)
    private String correo;
    @Column(name = "correo", nullable = false, length = 50)
    private String empresa;
    @Column(name = "empresa", nullable = false, length = 90)
    private String rol;
    @Column(name = "rol", nullable = false, length = 20)
    private String cedula;
    @Column(name = "cedula", nullable = false, length = 20)
    private Boolean activo;

    public Empleado(){

    }

    public Empleado(String nombre, String correo, String empresa, String rol, String cedula, Boolean activo) {
        this.nombre = nombre;
        this.correo = correo;
        this.empresa = empresa;
        this.rol = rol;
        this.cedula = cedula;
        this.activo = activo;
    }

        public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
