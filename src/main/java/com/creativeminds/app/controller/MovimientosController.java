package com.creativeminds.app.controller;


import com.creativeminds.app.model.Empleado;
import com.creativeminds.app.model.MovimientoDinero;
import com.creativeminds.app.services.EmpleadoService;
import com.creativeminds.app.services.MovimientoDineroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovimientosController {
    @Autowired
    MovimientoDineroService movimientoDineroService;

    @GetMapping ("enterprises/[id]/movements")
    public List<MovimientoDinero> verMovimientos(){
        return movimientoDineroService.getAllMovimientos();
    }

    @PostMapping ("enterprises/[id]/movements")
    public MovimientoDinero guardarMovimiento (@RequestBody MovimientoDinero mov){
        return this.movimientoDineroService.saveOrUpdateMovimientoDinero();
    }

    @GetMapping(path = "enterprises/[id]/movements")
    public MovimientoDinero movimientoDineroporID (@PathVariable("id") Integer id){
        return this.movimientoDineroService.getMovimientoDineroByID(id);
    }

    @PatchMapping("enterprises/[id]/movements")
    public MovimientoDinero actualizarMovimiento (@PathVariable("id") Integer id, @RequestBody MovimientoDinero movimientoDinero){
        MovimientoDinero mov = MovimientoDineroService.getMovimientoDineroByID(id);
        mov.setNombre(movimientoDinero.getNombre());
        mov.setId(movimientoDinero.getId());
        mov.setconcepto(movimientoDinero.getconcepto());
        mov.setfecha(movimientoDinero.getfecha());
        mov.setmonto(movimientoDinero.getmonto());
        return movimientoDineroService.saveorUpdateMovimientoDinero(mov);
    }

    @DeleteMapping (path= "enterprises/[id]/movements") //Eliminar registro de la bd
    public String DeleteMovimientoDinero(@PathVariable("id") Integer id){
        boolean respuesta= this.movimientoDineroService.deleteMovimientoDinero(id);
        if (respuesta){  //Si respuesta es true?
            return "Se elimino el movimiento con id" +id;
        }
        else{
            return "No se pudo eliminar el movimiento con id"+id;
        }
    }



}
