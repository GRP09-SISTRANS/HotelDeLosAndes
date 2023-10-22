package uniandes.sistrans.HotelDeLosAndes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import uniandes.sistrans.HotelDeLosAndes.models.Reserva;
import uniandes.sistrans.HotelDeLosAndes.repositories.ReservaRepository;


@Controller
public class ReservaController {
    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping(value="/reservas")
    public String reserva(Model model) {
        model.addAttribute("reservas", reservaRepository.darReservas());
        return "reservas";
    }

    @GetMapping(value="/reservas/new")
    public String crearReserva(Model model) {
        model.addAttribute("reserva", reservaRepository.darReservas());
        return "reservaNueva";
    }

    @GetMapping(value="/reservas/new/save")
    public String guardarReserva(@ModelAttribute Reserva reserva) {
        reservaRepository.insertarReserva(reserva.getCheck_in(), reserva.getCheck_out(),reserva.getFecha_inicio_reserva(), reserva.getFecha_final_reserva());
        return "redirect:/reservas";
    }

    @GetMapping(value="/reservas/{id}/edit")
    public String editarReservaForm(@PathVariable Integer id, Model model) {
        Reserva reserva = reservaRepository.darReservaPorId(id);
        if(reserva != null) {
            model.addAttribute("reserva", reserva);
            return "reservaEditar";
        }else{
            return "redirect:/reservas";
        }
    }

    @GetMapping(value="/reservas/{id}/edit/save")
    public String editarReserva(@ModelAttribute Reserva reserva, @PathVariable Integer id) {
        reservaRepository.actualizarReserva(id, reserva.getCheck_in(), reserva.getCheck_out(),reserva.getFecha_inicio_reserva(), reserva.getFecha_final_reserva());
        return "redirect:/reservas";
    }

    @GetMapping(value="/reservas/{id}/delete")
    public String eliminarReserva(@PathVariable Integer id) {
        reservaRepository.eliminarReservasPorId(id);
        return "redirect:/reservas";
    }
}
