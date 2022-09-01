package com.creativeminds.app.services;

import com.creativeminds.app.model.Empleado;
import com.creativeminds.app.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;

    public List<Empleado> getAllEmpleado() {
        List<Empleado> list = new ArrayList<>();
        empleadoRepository.findAll().forEach(empleado -> list.add(empleado));
        return list;
    }

    public Empleado getEmpleadoByID(Integer id) {
        return empleadoRepository.findById(id).get();
    }

    //Metodo para guardar y actualizar
    public Empleado saveorUpdateEmpleado(Empleado empleado) {
        Empleado empl=empleadoRepository.save(empleado);
        return empl;

    }

    public boolean deleteEmpleado(Integer id) {
        empleadoRepository.deleteById(id);
        if (getEmpleadoByID(id) != null) {
            return false;
        }
        return true;
    }

}
