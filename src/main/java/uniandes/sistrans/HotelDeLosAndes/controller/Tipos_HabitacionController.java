package uniandes.sistrans.HotelDeLosAndes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import uniandes.sistrans.HotelDeLosAndes.modelo.Tipos_Habitacion;
import uniandes.sistrans.HotelDeLosAndes.repositorios.Tipos_HabitacionRepository;

@Controller
@RestController
public class Tipos_HabitacionController {

    @Autowired
    private Tipos_HabitacionRepository tipos_HabitacionRepository;

    @GetMapping(value="/tipos_habitacion")
    public String tipos_Habitacion(Model model) {
        model.addAttribute("tipos_habitacion", tipos_HabitacionRepository.darTipos_Habitacion());
        return model.toString();
    }

    @GetMapping(value="/tipos_habitacion/new")
    public String crearTipos_Habitacion(Model model) {
        model.addAttribute("tipos_habitacion", tipos_HabitacionRepository.darTipos_Habitacion());
        return "tipos_habitacionNueva";
    }

    @GetMapping(value="/tipos_habitacion/new/save")
    public String guardarTipos_Habitacion(@ModelAttribute Tipos_Habitacion tipos_Habitacion) {
        tipos_HabitacionRepository.insertarTipos_Habitacion(tipos_Habitacion.getId(), tipos_Habitacion.getNombre(), tipos_Habitacion.getCosto(), tipos_Habitacion.isMinibar(), tipos_Habitacion.isCafetera(), tipos_Habitacion.isTelevision());
        return "redirect:/tipos_habitacion";
    }

    @GetMapping(value="/tipos_habitacion/{id}/edit")
    public String editarTipos_HabitacionForm(@PathVariable Integer id, Model model) {
        Tipos_Habitacion tipos_Habitacion = tipos_HabitacionRepository.darTipos_Habitacion(id);
        if(tipos_Habitacion != null) {
            model.addAttribute("tipos_habitacion", tipos_Habitacion);
            return "tipos_habitacionEditar";
        }else{
            return "redirect:/tipos_habitacion";
        }
    }

    @GetMapping(value="/tipos_habitacion/{id}/edit/save")
    public String editarTipos_Habitacion(@ModelAttribute Tipos_Habitacion tipos_Habitacion, @PathVariable Integer id) {
        tipos_HabitacionRepository.actualizarTipos_Habitacion(id, tipos_Habitacion.getNombre(), tipos_Habitacion.getCosto(), tipos_Habitacion.isMinibar(), tipos_Habitacion.isCafetera(), tipos_Habitacion.isTelevision());
        return "redirect:/tipos_habitacion";
    }

    @GetMapping(value="/tipos_habitacion/{id}/delete")
    public String eliminarTipos_Habitacion(@PathVariable Integer id) {
        tipos_HabitacionRepository.eliminarTipos_Habitacion(id);
        return "redirect:/tipos_habitacion";
    }

    
}
