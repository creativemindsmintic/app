package com.creativeminds.app.controller;


import com.creativeminds.app.model.Empleado;
import com.creativeminds.app.model.MovimientoDinero;
import com.creativeminds.app.services.EmpleadoService;
import com.creativeminds.app.services.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovimientosController {
    @Autowired
    MovimientosService movimientosService;

    @GetMapping ("enterprises/{id}/movements")
    public List<MovimientoDinero> verMovimientos(){
        return movimientosService.getAllMovimientos();
    }

    @PostMapping ("enterprises/{id}/movements")
    public MovimientoDinero guardarMovimiento (@RequestBody MovimientoDinero mov){
        return this.movimientosService.saveorUpdateMovimentoDinero(mov);
    }

    @GetMapping("enterprises/{id}/movements")
    public MovimientoDinero movimientoDineroporID (@PathVariable("id") Integer id){
        return this.movimientosService.getMovimientoDineroByID(id);
    }

    @PatchMapping("enterprises/{id}/movements")
    public MovimientoDinero actualizarMovimiento (@PathVariable("id") Integer id, @RequestBody MovimientoDinero movimientoDinero){
        MovimientoDinero mov = MovimientosService.getMovimientoDineroByID(id);
        mov.setEmpleado(movimientoDinero.getEmpleado());
        mov.setTipo_movi(movimientoDinero.getTipo_movi());
        mov.setConcepto(movimientoDinero.getConcepto());
        mov.setFecha(movimientoDinero.getFecha());
        mov.setMonto(movimientoDinero.getMonto());
        return movimientosService.saveorUpdateMovimentoDinero(mov);
    }

    @DeleteMapping ("enterprises/{id}/movements") //Eliminar registro de la bd
    public String DeleteMovimientoDinero(@PathVariable("id") Integer id){
        boolean respuesta=movimientosService.deleteMovimiento(id);
        if (respuesta){  //Si respuesta es true?
            return "Se elimino el movimiento con id" +id;
        }
        return "No se pudo eliminar el movimiento con id"+id;
        }
    }



}
