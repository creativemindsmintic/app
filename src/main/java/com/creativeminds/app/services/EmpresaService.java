package com.creativeminds.app.services;

import com.creativeminds.app.model.Empresa;
import com.creativeminds.app.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    @Autowired
    EmpresaRepository empresaRepository;

    public List<Empresa> getAllEmpresas(){
        List<Empresa> list = new ArrayList<>();
        empresaRepository.findAll().forEach(empresa -> list.add(empresa));
        return list;
    }

    public Optional<Empresa> getEmpresaByID(Integer id){
        return empresaRepository.findById(id);
    }

    //Metodo para guardar y actualizar
    public Optional<Empresa> saveorUpdateEmpresa (Empresa empresa){
        Empresa tmp_emp = empresaRepository.save(empresa);
        return empresaRepository.findById(tmp_emp.getId());
    }

    public boolean deleteEmpresa(Integer id){
        if(getEmpresaByID(id).isPresent()){
            empresaRepository.deleteById(id);
            if(getEmpresaByID(id).isPresent()){
                return false;
            }else{
                return true;
            }
        }else {
            return false;
        }
    }
}