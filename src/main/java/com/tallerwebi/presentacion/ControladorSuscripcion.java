package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Cliente;
import com.tallerwebi.dominio.Plan;
import com.tallerwebi.dominio.SerivicioSuscripcion;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.ClienteExistente;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorSuscripcion {
    private SerivicioSuscripcion servicioSuscripcion;
    @Autowired
    public ControladorSuscripcion (SerivicioSuscripcion servicioSuscripcion){this.servicioSuscripcion = servicioSuscripcion;}


    @RequestMapping("/suscripcion")
    public ModelAndView irASuscripcion() {

        ModelMap modelo = new ModelMap();
        modelo.put("datosSuscripcion", new DatosSuscripcion());
        modelo.put("plan", Plan.values());
        return new ModelAndView("suscripcion", modelo);
    }


    @RequestMapping(path = "/suscribirme", method = RequestMethod.POST)
    public ModelAndView suscribirme(@ModelAttribute("datosSuscripcion") Cliente cliente) {
        ModelMap model = new ModelMap();
        try{
            servicioSuscripcion.guardar(cliente);

        } catch (ClienteExistente e){
            model.put("error", "El cliente ya existe");
            return new ModelAndView("suscripcion", model);
        }
        model.put("exito", "Registro exitoso");
        model.put("plan", Plan.values());
        model.put("historial", servicioSuscripcion.obtenerHistorial());
        return new ModelAndView("suscripcion", model);
    }

    @RequestMapping("/listado")
    public ModelAndView irAListadoSuscripciones() {

        ModelMap modelo = new ModelMap();
        modelo.put("datosSuscripcion", new DatosSuscripcion());
        modelo.put("plan", Plan.values());
        modelo.put("historial", servicioSuscripcion.obtenerHistorial());
        return new ModelAndView("listado-suscripciones", modelo);
    }
    @RequestMapping("/historial")
    public ModelAndView irAHistorialVacio() {

        ModelMap modelo = new ModelMap();
        modelo.put("datosSuscripcion", new DatosSuscripcion());
        return new ModelAndView("historial", modelo);
    }

    @RequestMapping(value = "/historial", method = RequestMethod.POST)
    public ModelAndView irAHistorial(@ModelAttribute("datosSuscripcion") Cliente cliente) {

        ModelMap modelo = new ModelMap();
        modelo.put("filtrado", servicioSuscripcion.listarPorPlanSolicitado(cliente.getPlan()));
        return new ModelAndView("historial", modelo);
    }
    @RequestMapping(value = "/eliminar/{dni}", method = RequestMethod.GET)
    public ModelAndView eliminar(@ModelAttribute("datosSuscripcion") Cliente cliente, @PathVariable ("dni") Long dni) {

        ModelMap modelo = new ModelMap();

        servicioSuscripcion.eliminarSuscriptor(dni);
        modelo.put("exito", "Se elimino correctamente");


        return new ModelAndView("historial", modelo);
    }

}