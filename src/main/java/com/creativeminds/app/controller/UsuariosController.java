package com.creativeminds.app.controller;


import com.creativeminds.app.model.Empleado;
import com.creativeminds.app.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuariosController {
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping ("/users")
    public List<Empleado> verEmpleados(){
        return empleadoService.getAllEmpleado();
    }

    @PostMapping ("/users")
    public Optional <Empleado> guardarEmpleado(@RequestBody Empleado empl){
        return Optional.ofNullable(this.empleadoService.saveorUpdateEmpleado(empl));
    }
    @GetMapping(path = "users/{id}")
    public Optional <Empleado> empleadoPorID(@PathVariable("id") Integer id){
        return this.empleadoService.getEmpleadoByID(id);
    }
    @GetMapping("/enterprises/{id}/users")// Consultar empleados por empresa
    public ArrayList<Empleado> EmpleadoPorEmpresa(@PathVariable("id") Integer id){
        return this.empleadoService.obtenerPorEmpresa(id);
    }
    @PatchMapping("/users/{id}")
    public Empleado actualizarEmpleado(@PathVariable("id") Integer id, @RequestBody Empleado empleado){
        Empleado empl= empleadoService.getEmpleadoByID(id).get();
        empl.setNombre(empleado.getNombre());
        empl.setCedula(empleado.getCedula());
        empl.setCorreo(empleado.getCorreo());
        empl.setRol(empleado.getRol());
        empl.setEmpresa(empleado.getEmpresa());
        return empleadoService.saveorUpdateEmpleado(empl);

    }

    @DeleteMapping (path= "users/{id}") //Eliminar registro de la bd
    public String DeleteEmpleado(@PathVariable("id") Integer id){
        boolean respuesta=empleadoService.deleteEmpleado(id); //eliminamos usando el servicio de nuestro service
        if (respuesta){ //si la respuesta booleana es true, si se eliminó
            return "Se eliminó exitosamente el empleado con el id "+id;
        }//Si la respuesta booleana es false, no se eliminó
        return "No se puedo eliminar el empleado con el id "+id;
    }


}
