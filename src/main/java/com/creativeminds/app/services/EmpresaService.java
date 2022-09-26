package com.creativeminds.app.services;

import com.creativeminds.app.model.Empresa;
import com.creativeminds.app.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaService {
    @Autowired
    EmpresaRepository empresaRepository;

    public List<Empresa> getAllEmpresas(){
        List<Empresa> list = new ArrayList<>();
        empresaRepository.findAll().forEach(empresa -> list.add(empresa));
        return list;
    }

    public Empresa getEmpresaByID(Integer id){
        return empresaRepository.findById(id).get();
    }

    //Metodo para guardar y actualizar
    public boolean saveorUpdateEmpresa (Empresa empresa){
        Empresa tmp_emp = empresaRepository.save(empresa);
        if(empresaRepository.findById(tmp_emp.getId()) != null){
            return true;
        }
        return false;
    }

    public boolean deleteEmpresa(Integer id){
        empresaRepository.deleteById(id);
        if(getEmpresaByID(id)!= null){
            return true;
        }
        return false;
    }
}