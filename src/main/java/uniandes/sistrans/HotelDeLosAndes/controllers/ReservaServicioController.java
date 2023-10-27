package uniandes.sistrans.HotelDeLosAndes.controllers;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.transaction.Transactional;
import uniandes.sistrans.HotelDeLosAndes.models.ProductoEntity;
import uniandes.sistrans.HotelDeLosAndes.models.ReservaServicio;
import uniandes.sistrans.HotelDeLosAndes.models.Usuario;
import uniandes.sistrans.HotelDeLosAndes.repositories.ProductoRepository;
import uniandes.sistrans.HotelDeLosAndes.repositories.ReservaServicioRepository;
import uniandes.sistrans.HotelDeLosAndes.repositories.UsuarioRepository;


@Controller
public class ReservaServicioController {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ReservaServicioRepository reservaServicioRepository;

    @GetMapping(value="/reservasServicios")
    public String habitacion(Model model) {
        model.addAttribute("reservasServicios", this.reservaServicioRepository.darReservasServicios());
        return "reservasServicios";
    }

    @GetMapping("/reservasServicios/new")
    public String frecuentanForm(Model model) {
        model.addAttribute("productos", this.productoRepository.findAll());
        model.addAttribute("usuarios", this.usuarioRepository.findAll());
        return "reservaServicioNuevo";
    }

    @PostMapping("/reservasServicios/new/save")
    @Transactional
    public String frecuentanGuardar(@ModelAttribute("id_producto") Long idProducto, @ModelAttribute("id_usuario") Integer idUsuario,
            @ModelAttribute("fecha") Date fecha) {
        Optional<ProductoEntity> producto = this.productoRepository.findById(idProducto);
        Optional<Usuario> usuario = this.usuarioRepository.findById(idUsuario);
        if (producto.isPresent() && usuario.isPresent()) {
            ReservaServicio reservaServicioEntity = new ReservaServicio();
            reservaServicioEntity.setFecha(fecha);
            reservaServicioEntity.setProducto(producto.get());
            reservaServicioEntity.setUsuario(usuario.get());    
            this.reservaServicioRepository.save(reservaServicioEntity);
        }

        return "redirect:/reservasServicios";
    }

    @GetMapping("/reservasServicios/{id}/edit")
    public String editReservaServicioForm(@PathVariable Long id, Model model) {
        Optional<ReservaServicio> reservaServicio = this.reservaServicioRepository.findById(id);
        if (reservaServicio.isPresent()) {
            model.addAttribute("reservaServicio", reservaServicio.get());
            model.addAttribute("productos", this.productoRepository.findAll());
            model.addAttribute("usuarios", this.usuarioRepository.findAll());
            return "reservaServicioEdit";
        } else {
            return "redirect:/reservasServicios";
        }
    }

    @PostMapping("/reservasServicios/{id}/edit/save")
    @Transactional
    public String saveEditedReservaServicio(@PathVariable Long idReservaServicio, @ModelAttribute("id_producto") Long idProducto, @ModelAttribute("id_usuario") Integer idUsuario, @ModelAttribute("fecha") Date fecha) {
        Optional<ProductoEntity> producto = this.productoRepository.findById(idProducto);
        Optional<Usuario> usuario = this.usuarioRepository.findById(idUsuario);
        Optional<ReservaServicio> reservaServicio = this.reservaServicioRepository.findById(idReservaServicio);
        if (producto.isPresent() && usuario.isPresent() && reservaServicio.isPresent()) {
            ReservaServicio reservaServicioEntity = reservaServicio.get();
            reservaServicioEntity.setFecha(fecha);
            reservaServicioEntity.setProducto(producto.get());
            reservaServicioEntity.setUsuario(usuario.get());
            this.reservaServicioRepository.save(reservaServicioEntity);
        }
        return "redirect:/reservasServicios";
    }

    @GetMapping(value="/reservasServicios/{id}/delete")
    @Transactional
    public String eliminarHabitacion(@PathVariable Long id) {
        reservaServicioRepository.deleteById(id);
        return "redirect:/reservasServicios";
    }
}
