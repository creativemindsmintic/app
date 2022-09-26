package com.creativeminds.app.services;

import com.creativeminds.app.model.Empleado;
import com.creativeminds.app.model.MovimientoDinero;
import com.creativeminds.app.repositories.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientosService {

    @Autowired
    MovimientoRepository movimientoRepository;

    public List<MovimientoDinero> getAllMovimientos(){
        List<MovimientoDinero> movimientosList = new ArrayList<>();
        movimientoRepository.findAll().forEach(movimientoDinero -> movimientosList.add(movimientoDinero));
        return movimientosList;
    }

    public MovimientoDinero getMovimientoById(Integer id){ //Ver movimientos por id
        return movimientoRepository.findById(id).get();
    }

    public boolean saveorUpdateMovimiento(MovimientoDinero movimientoDinero) {
        MovimientoDinero tmp_mov = movimientoRepository.save(movimientoDinero);
        if (movimientoRepository.findById(tmp_mov.getId()) != null) {
            return true;
        }
        return false;
    }

    public boolean deleteMovimiento(Integer id){ //Eliminar movimiento por id
        movimientoRepository.deleteById(id); //Eliminar usando el metodo que nos ofrece el repositorio
        if(this.movimientoRepository.findById(id).isPresent()){ //Si al buscar el movimiento lo encontramos, no se eliminó (false)
            return false;
        }
        return true; //Si al buscar el movimiento no lo encontramos, si lo eliminò (true)
    }

    public ArrayList<MovimientoDinero> obtenerPorEmpleado(Integer id) { //Obterner teniendo en cuenta el id del empleado
        return movimientoRepository.findByEmpleado(id);
    }

    public ArrayList<MovimientoDinero> obtenerPorEmpresa(Integer id) { //Obtener movimientos teniendo en cuenta el id de la empresa a la que pertencen los empleados que la registraron

        return movimientoRepository.findByEmpresa(id);
    }
    //Servicio para ver la suma de todos los montos
    public Long obtenerSumaMontos(){
        return movimientoRepository.SumarMonto();
    }

    //Servicio para ver la suma de los montos por empleado
    public Long MontosPorEmpleado(Integer id){
        return movimientoRepository.MontosPorEmpleado(id);
    }

    //Servicio para ver la suma de los montos por empresa
    public Long MontosPorEmpresa(Integer id){
        return movimientoRepository.MontosPorEmpresa(id);
    }

    //servicio que nos deja conseguir el id de un empleado si tenemos su correo
    public Integer IdPorCorreo(String Correo){
        return movimientoRepository.IdPorCorreo(Correo);
    }
}