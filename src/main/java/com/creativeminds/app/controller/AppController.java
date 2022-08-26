package com.creativeminds.app.controller;

import com.creativeminds.app.model.Empresa;
import com.creativeminds.app.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    EmpresaService empresaService;

    @GetMapping ({"/verEmpresas"})
    public String verEmpresas(Model model){
        List<Empresa> listaEmpresas = empresaService.getAllEmpresas();
        model.addAttribute("listadoEmpresas",listaEmpresas);
        return "listadoEmpresas";
    }

    @GetMapping ({"/crearEmpresa"})
    public String crearEmpresa(Model model){
        Empresa nuevaEmpresa = new Empresa();
        model.addAttribute("nuevaEmpresa",nuevaEmpresa);
        return "crearEmpresa";
    }

    @PostMapping ({"/guardarEmpresa"})
    public String guardarEmpresa(Empresa nuevaEmpresa, RedirectAttributes redirectAttributes){
        if(empresaService.saveorUpdateEmpresa(nuevaEmpresa) == true){
            return "redirect:/verEmpresas";
        }
        return "redirect:/crearEmpresa";
    }
}