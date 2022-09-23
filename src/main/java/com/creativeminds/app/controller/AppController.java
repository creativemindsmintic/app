package com.creativeminds.app.controller;

import com.creativeminds.app.model.*;
import com.creativeminds.app.services.EmpleadoService;
import com.creativeminds.app.services.EmpresaService;
import com.creativeminds.app.services.MovimientosService;
import com.creativeminds.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AppController {

    UserService userService;

    public AppController(EmpresaService empresaService, EmpleadoService empleadoService, MovimientosService movimientosService, UserService userService) {
        this.empresaService = empresaService;
        this.empleadoService = empleadoService;
        this.movimientosService = movimientosService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal OidcUser principal) {
            if(principal!=null){
                User user = this.userService.gerOrCreateuser(principal.getClaims());
                model.addAttribute("user",user);
            }
            return "index";
    }


    @GetMapping("/inicio")
    public String Inicio() {
        return "inicio";
    }

    @Autowired
    EmpresaService empresaService;

    @GetMapping({"/verEmpresas"})
    public String verEmpresas(Model model) {
        List<Empresa> listaEmpresas = empresaService.getAllEmpresas();
        model.addAttribute("listadoEmpresas", listaEmpresas);
        return "listadoEmpresas";
    }

    @GetMapping({"/crearEmpresa"})
    public String crearEmpresa(Model model) {
        Empresa nuevaEmpresa = new Empresa();
        model.addAttribute("nuevaEmpresa", nuevaEmpresa);
        return "crearEmpresa";
    }

    @PostMapping({"/guardarEmpresa"})
    public String guardarEmpresa(Empresa nuevaEmpresa, RedirectAttributes redirectAttributes) {
        if (empresaService.saveorUpdateEmpresa(nuevaEmpresa) == true) {
            return "redirect:/verEmpresas";
        }
        return "redirect:/crearEmpresa";
    }

    @GetMapping("/editarEmpresa/{id}")
    public String editarEmpresa(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        //Buscar la empresa a editar
        Empresa empEditar = empresaService.getEmpresaByID(id);
        model.addAttribute("empresaEditada",empEditar);

        return "editarEmpresa";
    }
//    @GetMapping("/EliminarEmpresa/{id}")
//    public String eliminarEmpresa(@PathVariable Integer id, RedirectAttributes redirectAttributes){
//        if (empresaService.deleteEmpresa(id)==true){
//            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
//            return "redirect:/VerEmpresas";
//        }
//        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
//        return "redirect:/VerEmpresas";
//    }

    @Autowired
    EmpleadoService empleadoService;

    @GetMapping({"/verEmpleados"})
    public String verEmpleado(Model model) {
        List<Empleado> listaEmpleados = empleadoService.getAllEmpleado();
        model.addAttribute("listadoEmpleados", listaEmpleados);
        return "listadoEmpleados";
    }

    @GetMapping({"/crearEmpleado"})
    public String crearEmpleado(Model model) {
        //Consultar empresas
        List<Empresa> listaEmpresas = empresaService.getAllEmpresas();
        Empleado nuevoEmpleado = new Empleado();
        model.addAttribute("nuevoEmpleado", nuevoEmpleado);
        model.addAttribute("ListaEmpresas", listaEmpresas);
        return "crearEmpleado";
    }

    @PostMapping({"/guardarEmpleado"})
    public String guardarEmpleado(Empleado nuevoEmpleado, RedirectAttributes redirectAttributes) {
        if (empleadoService.saveorUpdateEmpleado(nuevoEmpleado) == true) {
            return "redirect:/verEmpleados";
        }
        return "redirect:/crearEmpleado";
    }
    @GetMapping("/editarEmpleado/{id}")
    public String editarEmpleado(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        //Buscar empleado a editar
        Empleado empleadoEditar = empleadoService.getEmpleadoByID(id);
        model.addAttribute("empleadoEditado",empleadoEditar);

        return "editarEmpleado";
    }

    @GetMapping("/eliminarEmpleado/{id}")
    public String eliminarEmpleado(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if (empleadoService.deleteEmpleado(id)){
            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
            return "redirect:/VerEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/VerEmpleados";
    }

    @Autowired
    MovimientosService movimientosService;

    @GetMapping({"/verMovimientos"})
    public String verMovimientos(Model model) {
        List<MovimientoDinero> ListadoMovimientos = movimientosService.getAllMovimientos();
        Double total = 0.0;
        for (MovimientoDinero movim : ListadoMovimientos) {
            if(movim.getTipo_movi()== TipoMov.Ingreso){
                total = total + movim.getMonto();
            }
            if(movim.getTipo_movi()== TipoMov.Egreso){
                total = total - movim.getMonto();
            }

        }
        model.addAttribute("totalMovimientos", total);
        model.addAttribute("listadoMovimientos", ListadoMovimientos);
        return "listadoMovimientos";
    }

    @GetMapping({"/crearMovimiento"})
    public String crearMovimiento(Model model) {
        List<Empleado> listaEmpleado = empleadoService.getAllEmpleado();
        MovimientoDinero nuevoMovimiento = new MovimientoDinero();
        model.addAttribute("nuevoMovimiento", nuevoMovimiento);
        model.addAttribute("ListaEmpleado", listaEmpleado);
        return "crearMovimiento";
    }

    @PostMapping({"/guardarMovimiento"})
    public String guardarMovimiento(MovimientoDinero nuevoMovimientoDinero, RedirectAttributes redirectAttributes) {
        if (movimientosService.saveorUpdateMovimiento(nuevoMovimientoDinero) == true) {
            return "redirect:/verMovimientos";
        }
        return "redirect:/crearMovimiento";
    }

    @GetMapping({"/editarMovimiento/{id}"})
    public String editarMovimento(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje) {
        MovimientoDinero mov = movimientosService.getMovimientoById(id);
        model.addAttribute("mov", mov);
        model.addAttribute("mensaje", mensaje);
        List<Empleado> listaEmpleados = empleadoService.getAllEmpleado();
        model.addAttribute("emplelist", listaEmpleados);
        return "editarMovimiento";
    }
    @GetMapping("/eliminarMovimiento/{id}")
    public String eliminarMovimiento(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if (movimientosService.deleteMovimiento(id)){
            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
            return "redirect:/verMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/verMovimientos";
    }

}