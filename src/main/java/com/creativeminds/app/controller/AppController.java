package com.creativeminds.app.controller;


import com.creativeminds.app.model.Empleado;
import com.creativeminds.app.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class AppController {
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping ("/users")
    public List<Empleado> verEmpleados(){
        return empleadoService.getAllEmpleado();
    }

    @PostMapping ("/users")
    public Empleado guardarEmpleado(@RequestBody Empleado empl){
        return this.empleadoService.saveorUpdateEmpleado(empl);
    }




}
