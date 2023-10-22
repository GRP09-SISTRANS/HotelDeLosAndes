package uniandes.sistrans.HotelDeLosAndes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import uniandes.sistrans.HotelDeLosAndes.models.Habitacion;
import uniandes.sistrans.HotelDeLosAndes.repositories.HabitacionRepository;
import uniandes.sistrans.HotelDeLosAndes.repositories.Tipos_HabitacionRepository;


@Controller
public class HabitacionController {
    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private Tipos_HabitacionRepository tipoHabitacionRepository;

    @GetMapping(value="/habitaciones")
    public String habitacion(Model model) {
        model.addAttribute("habitaciones", habitacionRepository.darHabitaciones());
        return "habitaciones";
    }

    @GetMapping(value="/habitaciones/new")
    public String crearHabitacion(Model model) {
        model.addAttribute("habitacion", new Habitacion());
        model.addAttribute("tipos_habitacion", tipoHabitacionRepository.darTipos_Habitacion());
        return "habitacionNueva";
    }

    @GetMapping(value="/habitaciones/new/save")
    public String guardarHabitacion(@ModelAttribute Habitacion habitacion) {
        habitacionRepository.insertarHabitacion(habitacion.getId(), habitacion.getCapacidad(), habitacion.getTipoHabitacion());
        return "redirect:/habitaciones";
    }

    @GetMapping(value="/habitaciones/{id}/edit")
    public String editarHabitacionForm(@PathVariable Integer id, Model model) {
        Habitacion habitacion = habitacionRepository.darHabitacion(id);
        if(habitacion != null) {
            model.addAttribute("habitacion", habitacion);
            return "habitacionEditar";
        }else{
            return "redirect:/habitaciones";
        }
    }

    @GetMapping(value="/habitaciones/{id}/edit/save")
    public String editarHabitacion(@ModelAttribute Habitacion habitacion, @PathVariable Integer id) {
        habitacionRepository.actualizarHabitacion(id, habitacion.getCapacidad(), habitacion.getTipoHabitacion());
        return "redirect:/habitacion";
    }

    @GetMapping(value="/habitaciones/{id}/delete")
    public String eliminarHabitacion(@PathVariable Integer id) {
        habitacionRepository.eliminarHabitacion(id);
        return "habitacion";
    } 
}
