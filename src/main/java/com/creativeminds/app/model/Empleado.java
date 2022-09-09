package com.creativeminds.app.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="empleado")
public class Empleado {
    @OneToMany(mappedBy = "empleado")
    private Set<MovimientoDinero> movimientoDinero;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "nombre", nullable = false, length = 80)
    private String nombre;
    @Column(name = "correo", nullable = false, length = 50)
    private String correo;
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
    @Column(name = "rol", nullable = false, length = 20)
    private String rol;

    @Column(name = "cedula", nullable = false, length = 20)
    private String cedula;
    public Empleado(){

    }

    public Empleado(String nombre, String correo, Empresa empresa, String rol, String cedula) {
        this.nombre = nombre;
        this.correo = correo;
        this.empresa = empresa;
        this.rol = rol;
        this.cedula = cedula;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
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
}
