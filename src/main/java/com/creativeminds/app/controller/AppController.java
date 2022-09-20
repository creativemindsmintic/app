package com.creativeminds.app.controller;

import com.creativeminds.app.model.Empleado;
import com.creativeminds.app.model.Empresa;
import com.creativeminds.app.model.MovimientoDinero;
import com.creativeminds.app.services.EmpleadoService;
import com.creativeminds.app.services.EmpresaService;
import com.creativeminds.app.services.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AppController {

    //    @GetMapping("/")
//    public String index() {
//        return "index";
//    }
    @GetMapping("/")
    public String plantilla() {
        return "Plantilla";
    }

    @GetMapping("/inicio")
    public String Inicio() {
        return "Inicio";
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
        if (empleadoService.deleteEmpleado(id)==true){
            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
            return "redirect:/VerEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/VerEmpleados";
    }

    @RequestMapping ("/VerMovimientos")// Controlador que nos lleva al template donde veremos todos los movimientos
    public String viewMovimientos(@RequestParam(value="pagina", required=false, defaultValue = "1") int NumeroPagina,
                                  @RequestParam(value="medida", required=false, defaultValue = "5") int medida,
                                  Model model, @ModelAttribute("mensaje") String mensaje){
        Page<MovimientoDinero> paginaMovimientos= movimientosRepositor.findAll(PageRequest.of(NumeroPagina,medida));
        model.addAttribute("movlist",paginaMovimientos.getContent());
        model.addAttribute("paginas",new int[paginaMovimientos.getTotalPages()]);
        model.addAttribute("paginaActual", NumeroPagina);
        model.addAttribute("mensaje",mensaje);
        Long sumaMonto=movimientosService.obtenerSumaMontos();
        model.addAttribute("SumaMontos",sumaMonto);//Mandamos la suma de todos los montos a la plantilla
        return "verMovimientos"; //Llamamos al HTML
    }

    @GetMapping("/AgregarMovimiento") //Controlador que nos lleva al template donde podremos crear un nuevo movimiento
    public String nuevoMovimiento(Model model, @ModelAttribute("mensaje") String mensaje){
        MovimientoDinero movimiento= new MovimientoDinero();
        model.addAttribute("mov",movimiento);
        model.addAttribute("mensaje",mensaje);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String correo=auth.getName();
        Integer idEmpleado=movimientosService.IdPorCorreo(correo);
        model.addAttribute("idEmpleado",idEmpleado);
        return "agregarMovimiento"; //Llamar HTML
    }

    @PostMapping("/GuardarMovimiento")
    public String guardarMovimiento(MovimientoDinero mov, RedirectAttributes redirectAttributes){
        if(movimientosService.saveOrUpdateMovimiento(mov)){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/AgregarMovimiento";
    }

    @GetMapping("/EditarMovimiento/{id}")
    public String editarMovimento(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        MovimientoDinero mov=movimientosService.getMovimientoById(id);
        //Creamos un atributo para el modelo, que se llame igualmente empl y es el que ira al html para llenar o alimentar campos
        model.addAttribute("mov",mov);
        model.addAttribute("mensaje", mensaje);
        List<Empleado> listaEmpleados= empleadoService.getAllEmpleado();
        model.addAttribute("emplelist",listaEmpleados);
        return "editarMovimiento";
    }

    @PostMapping("/ActualizarMovimiento")
    public String updateMovimiento(@ModelAttribute("mov") MovimientoDinero mov, RedirectAttributes redirectAttributes){
        if(movimientosService.saveOrUpdateMovimiento(mov)){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje","updateError");
        return "redirect:/EditarMovimiemto/"+mov.getId();

    }

    @GetMapping("/EliminarMovimiento/{id}")
    public String eliminarMovimiento(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if (movimientosService.deleteMovimiento(id)){
            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/VerMovimientos";
    }

    @GetMapping("/Empleado/{id}/Movimientos") //Filtro de movimientos por empleados
    public String movimientosPorEmpleado(@PathVariable("id")Integer id, Model model){
        List<MovimientoDinero> movlist = movimientosService.obtenerPorEmpleado(id);
        model.addAttribute("movlist",movlist);
        Long sumaMonto=movimientosService.MontosPorEmpleado(id);
        model.addAttribute("SumaMontos",sumaMonto);
        return "verMovimientos"; //Llamamos al HTML
    }

    @GetMapping("/Empresa/{id}/Movimientos") //Filtro de movimientos por empresa
    public String movimientosPorEmpresa(@PathVariable("id")Integer id, Model model){
        List<MovimientoDinero> movlist = movimientosService.obtenerPorEmpresa(id);
        model.addAttribute("movlist",movlist);
        Long sumaMonto=movimientosService.MontosPorEmpresa(id);
        model.addAttribute("SumaMontos",sumaMonto);
        return "verMovimientos"; //Llamamos al HTML
    }
}


