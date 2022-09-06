package com.creativeminds.app.controller;


import com.creativeminds.app.model.Empleado;
import com.creativeminds.app.model.MovimientoDinero;
import com.creativeminds.app.services.EmpleadoService;
import com.creativeminds.app.services.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovimientosController {
    @Autowired
    MovimientosService movimientosService;

    @GetMapping ("movements/{id}")
    public List<MovimientoDinero> verMovimientos(){
        return movimientosService.getAllMovimientos();
    }

    @PostMapping ("movements/{id}")
    public MovimientoDinero guardarMovimiento (@RequestBody MovimientoDinero mov){
        return movimientosService.saveOrUpdateMovimiento(mov);
    }

    @GetMapping("movements/{id}")
    public MovimientoDinero movimientoDineroporID (@PathVariable("id") Integer id){
        return movimientosService.getMovimientoById(id);
    }

    @PatchMapping("movements/{id}")
    public MovimientoDinero actualizarMovimiento (@PathVariable("id") Integer id, @RequestBody MovimientoDinero movimientoDinero){
        MovimientoDinero mov = movimientosService.getMovimientoById(id);
        mov.setEmpleado(movimientoDinero.getEmpleado());
        mov.setTipo_movi(movimientoDinero.getTipo_movi());
        mov.setConcepto(movimientoDinero.getConcepto());
        mov.setFecha(movimientoDinero.getFecha());
        mov.setMonto(movimientoDinero.getMonto());
        return movimientosService.saveOrUpdateMovimiento(mov);
    }

    @DeleteMapping ("movements/{id}") //Eliminar registro de la bd
    public String DeleteMovimientoDinero(@PathVariable("id") Integer id){
        boolean respuesta=movimientosService.deleteMovimiento(id);
        if (respuesta){  //Si respuesta es true?
            return "Se elimino el movimiento con id" +id;
        }else{
        return "No se pudo eliminar el movimiento con id"+id;
        }
    }

        @GetMapping("/users/{id}/movements") //Consultar movimientos por id del empleado
        public ArrayList<MovimientoDinero> movimientosPorEmpleado(@PathVariable("id") Integer id){
                return movimientosService.obtenerPorEmpleado(id);
        }

        @GetMapping("/enterprises/{id}/movements") //Consultar movimientos que pertenecen a una empresa por el id de la empresa
        public ArrayList<MovimientoDinero> movimientosPorEmpresa(@PathVariable("id") Integer id){
                return movimientosService.obtenerPorEmpresa(id);
        }

}
