package com.creativeminds.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Entity
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String nombre;
    String correo;
    String empresa;
    String rol;
    String cedula;
    Boolean activo;

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

    //Metodo Crear Empleado
    public void crearEmp() {  //Metodo recibe modelos

    try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app", "postgres", "1234554321"); Statement stmt = conn.createStatement();) {
        String sql = "INSERT INTO empleado (nombre, correo, empresa, rol, cedula, activo) VALUES('" + this.getNombre() + "','" + this.getCorreo() + "','" + this.getEmpresa() + "','" + this.getRol() + "','" + this.getCedula() + "', '" + this.getActivo() + "')";
        stmt.executeUpdate(sql);
        System.out.println("Estudiante ingresado correctamente");
    } catch (SQLException e) {

        System.out.println("No se pudo registrar el estudiante");
        e.printStackTrace();
    }
    }


}
