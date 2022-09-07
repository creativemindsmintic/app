package com.creativeminds.app.controller;

import com.creativeminds.app.model.Empresa;
import com.creativeminds.app.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<Empresa> crearEmpresa(@RequestBody Empresa nuevaEmpresa){
        return empresaService.saveorUpdateEmpresa(nuevaEmpresa);
    }

    @GetMapping("/enterprises/{id}")
    public Optional<Empresa> verEmpresaById(@PathVariable(value="id") Integer id){
        return empresaService.getEmpresaByID(id);
    }

    @PatchMapping("/enterprises/{id}")
    public Optional<Empresa> actualizarEmpresa(@RequestBody Empresa edisionEmpresa){
        return empresaService.saveorUpdateEmpresa(edisionEmpresa);
    }

    @DeleteMapping("enterprises/{id}")
    public Boolean removerEmpresa(@PathVariable(value="id") Integer id){
        return empresaService.deleteEmpresa(id);
    }

}
