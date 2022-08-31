package com.creativeminds.app.services;

import com.creativeminds.app.model.Empleado;
import com.creativeminds.app.model.MovimientoDinero;
import com.creativeminds.app.repositories.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientoDineroService {
    @Autowired
    MovimientoDineroService movimientoDineroService;

    public List<MovimientoDineros> getAllMovimientos() {
        List<MovimientoDinero> list = new ArrayList<>();
        MovimientoRepository.findAll().forEach(movimientoDinero -> list.add(movimientoDinero));
        return list;
    }

    public MovimientoDinero getMovimientoDineroByID (Integer id) {
        return MovimientoRepository.findById(id).get();
    }

    //Metodo para guardar y actualizar
    public MovimientoDinero saveorUpdateMovimentoDinero(MovimientoDinero movimientoDinero) {
        MovimientoDinero mov=MovimientoRepository.save(movimientoDinero);
        return mov;

    }

    public boolean deleteMovimiento(Integer id) {
        MovimientoRepository.deleteById(id);
        if (getMovimientoDineroByID(id) != null) {
            return false;
        }
        return true;
    }

}