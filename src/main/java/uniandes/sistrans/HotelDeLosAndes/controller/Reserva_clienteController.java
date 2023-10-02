package uniandes.sistrans.HotelDeLosAndes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.sistrans.HotelDeLosAndes.modelo.Habitacion;
import uniandes.sistrans.HotelDeLosAndes.modelo.Plan_Consumo;
import uniandes.sistrans.HotelDeLosAndes.modelo.Reserva;
import uniandes.sistrans.HotelDeLosAndes.modelo.Usuario;
import uniandes.sistrans.HotelDeLosAndes.repositorios.HabitacionRepository;
import uniandes.sistrans.HotelDeLosAndes.repositorios.Plan_ConsumoRepository;
import uniandes.sistrans.HotelDeLosAndes.repositorios.ReservaRepository;
import uniandes.sistrans.HotelDeLosAndes.repositorios.Reserva_clienteRepository;
import uniandes.sistrans.HotelDeLosAndes.repositorios.UsuarioRepository;

@Controller

public class Reserva_clienteController {

    @Autowired
    private Reserva_clienteRepository reserva_clienteRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private Plan_ConsumoRepository planConsumoRepository;

    @GetMapping(value="/reserva_cliente/new")
    public String reserva_cliente(Model model) {
        model.addAttribute("habitaciones", habitacionRepository.darHabitaciones());
        model.addAttribute("reserva_cliente", reservaRepository.darReservas());
        model.addAttribute("plan_consumo", planConsumoRepository.darPlan_Consumo());
        return "reserva_clienteNueva";
    }

    @PostMapping(value="/reserva_cliente/new/save")
    public String crearReserva_cliente(@ModelAttribute("reserva_id") Integer idReserva, @ModelAttribute("usuario_id") Integer idUsuario, @ModelAttribute("habitacion_id") Integer idHabitacion, @ModelAttribute("plan_consumo_id") Integer idPlanConsumo) {
        Reserva reserva = reservaRepository.darReservaPorId(idReserva);
        Usuario usuario = usuarioRepository.darUsuario(idUsuario);
        Habitacion habitacion = habitacionRepository.darHabitacion(idHabitacion);
        Plan_Consumo planConsumo = planConsumoRepository.darPlan_ConsumoPorId(idPlanConsumo);
        reserva_clienteRepository.insertarReserva_cliente(reserva.getId(), usuario.getId(), habitacion.getId(), planConsumo.getId());
        return "redirect:/reserva_cliente";
    }
}
