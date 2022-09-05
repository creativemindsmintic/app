package com.creativeminds.app.services;

import com.creativeminds.app.model.Empleado;
import com.creativeminds.app.repositories.EmpleadoRepository;
import com.nimbusds.oauth2.sdk.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;

    // lista todos los empleados
    public List<Empleado> getAllEmpleado() {
        List<Empleado> empleadoList = new ArrayList<>();
        empleadoRepository.findAll().forEach(empleado -> empleadoList.add(empleado));
        return empleadoList;
    }

    // Me busca pero me permite que no solo me devuelva un empleado sino lo que encuentre
    public Optional<Empleado> getEmpleadoByID(Integer id) {
           return empleadoRepository.findById(id);
        }

        public ArrayList<Empleado> obtenerPorEmpresa(Integer id){
        return empleadoRepository.findByEmpresa(id);
    }

    //Metodo para guardar y actualizar
    public Empleado saveorUpdateEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public boolean deleteEmpleado(Integer id) {
        empleadoRepository.deleteById(id);
        if(this.empleadoRepository.findById(id).isPresent()){
            return false;
        }
    return true;

    }

}
