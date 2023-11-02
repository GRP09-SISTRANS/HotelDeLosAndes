package uniandes.sistrans.HotelDeLosAndes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.transaction.Transactional;
import uniandes.sistrans.HotelDeLosAndes.models.Tipos_Habitacion;
import uniandes.sistrans.HotelDeLosAndes.repositories.Tipos_HabitacionRepository;


@Controller
public class Tipos_HabitacionController {
    @Autowired
    private Tipos_HabitacionRepository tipos_HabitacionRepository;

    @GetMapping(value="/tipos_habitacion")
    public String tipos_Habitacion(Model model) {
        model.addAttribute("tipos_habitacion", tipos_HabitacionRepository.darTipos_Habitacion());
        return "tipos_habitacion";
    }

    @GetMapping(value="/tipos_habitacion/new")
    @Transactional
    public String crearTipos_Habitacion(Model model) {
        model.addAttribute("tipos_habitacion", tipos_HabitacionRepository.darTipos_Habitacion());
        return "tipos_habitacionNueva";
    }

    @PostMapping(value="/tipos_habitacion/new/save")
    @Transactional
    public String guardarTipos_Habitacion(@RequestParam String nombre, @RequestParam Integer costo, @RequestParam String minibar, @RequestParam String cafetera, @RequestParam String television) {
        tipos_HabitacionRepository.insertarTipos_Habitacion(
            nombre,
            costo,
            minibar.equals("Tiene"),
            cafetera.equals("Tiene"),
            television.equals("Tiene")
        );
        return "redirect:/tipos_habitacion";
    }

    @GetMapping(value="/tipos_habitacion/{id}/edit")
    public String editarTipos_HabitacionForm(@PathVariable Integer id, Model model) {
        Tipos_Habitacion tipos_Habitacion = tipos_HabitacionRepository.darTipos_Habitacion(id);
        if (tipos_Habitacion != null) {
            model.addAttribute("tipos_habitacion", tipos_Habitacion);
            return "tipos_habitacionEditar";
        } else {
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
