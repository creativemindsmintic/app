package com.creativeminds.app.controller;


import com.creativeminds.app.model.Empleado;
import com.creativeminds.app.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class UsuariosController {
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
    @GetMapping(path = "users/[id]")
    public Empleado empleadoPorID(@PathVariable("id") Integer id){
        return this.empleadoService.getEmpleadoByID(id);
    }

    @PatchMapping("/users/[id]")
    public Empleado actualizarEmpleado(@PathVariable("id") Integer id, @RequestBody Empleado empleado){
        Empleado empl= empleadoService.getEmpleadoByID(id);
        empl.setNombre(empleado.getNombre());
        empl.setCedula(empleado.getCedula());
        empl.setCorreo(empleado.getCorreo());
        empl.setRol(empleado.getRol());
        empl.setEmpresa(empleado.getEmpresa());
        return empleadoService.saveorUpdateEmpleado(empl);

    }

    @DeleteMapping (path= "users/[id]") //Eliminar registro de la bd
    public String DeleteEmpleado(@PathVariable("id") Integer id){
        boolean respuesta= this.empleadoService.deleteEmpleado(id);
        if (respuesta){  //Si respuesta es true?
            return "Se elimino el empleado con id" +id;
        }
        else{
            return "No se pudo eliminar el empleado con id"+id;
        }
    }



}
