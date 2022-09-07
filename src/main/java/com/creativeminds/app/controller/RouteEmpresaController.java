package com.creativeminds.app.controller;

import com.creativeminds.app.model.Empresa;
import com.creativeminds.app.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class RouteEmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/enterprisesRest")
    public String verEmpresas() {
        //List<Empresa> listadoEmpresas = empresaService.getAllEmpresas();
        return "crearEmpresaRest";

    }
}
