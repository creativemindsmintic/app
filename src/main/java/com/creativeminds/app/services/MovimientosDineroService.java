package com.creativeminds.app.services;

import com.creativeminds.app.model.Empleado;
import com.creativeminds.app.model.MovimientoDinero;
import com.creativeminds.app.repositories.EmpleadoRepository;
import com.creativeminds.app.repositories.MovimientoDineroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientoDineroService {
    @Autowired
    MovimientoDineroService movimientoDineroService;

    @GetMapping ("/")

    public List<MovimientoDineroS> getAllEmpleado() {
        List<MovimientoDinero> list = new ArrayList<>();
        MovimientoDineroRepository.findAll().forEach(movimientoDinero -> list.add(movimientoDinero));
        return list;
    }

    public MovimientoDinero getMovimientoDineroByID (Integer id) {
        return MovimientoDineroRepository.findById(id).get();
    }

    //Metodo para guardar y actualizar
    public MovimientoDinero saveorUpdateMovimentoDinero(MovimientoDinero movimientoDinero) {
        MovimientoDinero mov=MovimientoDineroRepository.save(movimientoDinero);
        return mov;

    }

    public boolean deleteMovimiento(Integer id) {
        MovimientoDineroRepository.deleteById(id);
        if (getMovimientoDineroByID(id) != null) {
            return false;
        }
        return true;
    }

}
