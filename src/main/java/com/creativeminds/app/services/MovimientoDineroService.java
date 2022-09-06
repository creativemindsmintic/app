package com.creativeminds.app.services;

import com.creativeminds.app.model.Empleado;
import com.creativeminds.app.model.MovimientoDinero;
import com.creativeminds.app.repositories.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientoDineroService {
    @Autowired
    MovimientoRepository movimientoRepository;

    public List<MovimientoDinero> getAllMovimientos() {
        List<MovimientoDinero> movimientoDineroList = new ArrayList<>();
        MovimientoRepository.findAll().forEach(movimientoDinero-> movimientoDineroList.add(movimientoDinero));
        return movimientoDineroList;
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
        if (this.movimientoRepository.findById(id).isPresent()) {
            return false;
        }
        return true;
    }
    public ArrayList<MovimientoDinero>obtenerEmpleado (Integer id){
        return .movimientoRepository.findByEmpleado();
    }

    public ArrayList<MovimientoDinero>obtenerEmpresa (Integer id){
        return movimientoRepository.findByEmpresa();
    }
}