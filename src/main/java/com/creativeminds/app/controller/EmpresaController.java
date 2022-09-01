package com.creativeminds.app.controller;

import com.creativeminds.app.model.Empresa;
import com.creativeminds.app.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/enterprises")
    public List<Empresa> verEmpresas(){
        List<Empresa> listadoEmpresas = empresaService.getAllEmpresas();
        return listadoEmpresas;
    }

    @PostMapping("/enterprises")
    public Empresa crearEmpresa(Empresa nuevaEmpresa){
        return empresaService.saveorUpdateEmpresa(nuevaEmpresa);
    }

}