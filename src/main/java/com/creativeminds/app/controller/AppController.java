package com.creativeminds.app.controller;

import com.creativeminds.app.model.Empleado;
import com.creativeminds.app.model.Empresa;
import com.creativeminds.app.services.EmpleadoService;
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

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/inicio")
    public String Inicio(){
        return "Inicio";
    }

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
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping ({"/verEmpleados"})
    public String verEmpleado(Model model){
        List<Empleado> listaEmpleados = empleadoService.getAllEmpleado();
        model.addAttribute("listadoEmpleados",listaEmpleados);
        return "listadoEmpleados";
    }

    @GetMapping ({"/crearEmpleado"})
    public String crearEmpleado(Model model){
        //Consultar empresas
        List<Empresa> listaEmpresas = empresaService.getAllEmpresas();
        Empleado nuevoEmpleado = new Empleado();
        model.addAttribute("nuevoEmpleado",nuevoEmpleado);
        model.addAttribute("ListaEmpresas",listaEmpresas);
        return "crearEmpleado";
    }

    @PostMapping ({"/guardarEmpleado"})
    public String guardarEmpleado(Empleado nuevoEmpleado, RedirectAttributes redirectAttributes){
        if(empleadoService.saveorUpdateEmpleado(nuevoEmpleado) == true){
            return "redirect:/verEmpleados";
        }
        return "redirect:/crearEmpleado";
    }
}
