package uniandes.sistrans.HotelDeLosAndes.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.transaction.Transactional;
import uniandes.sistrans.HotelDeLosAndes.modelo.CuentaEntity;
import uniandes.sistrans.HotelDeLosAndes.modelo.ProductoEntity;
import uniandes.sistrans.HotelDeLosAndes.modelo.Reserva;
import uniandes.sistrans.HotelDeLosAndes.repositorios.CuentaRepository;
import uniandes.sistrans.HotelDeLosAndes.repositorios.ProductoRepository;
import uniandes.sistrans.HotelDeLosAndes.repositorios.ReservaRepository;


@Controller

public class CuentaController {
      @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/cuentas")
    public String cuentas(Model model, String ciudad, String tipo) {
        model.addAttribute("cuentas", this.cuentaRepository.findAll());
        return "cuentas";
    }

    @GetMapping("/cuentas/new")
    public String cuentaForm(Model model) {
        model.addAttribute("cuenta", new CuentaEntity());
        model.addAttribute("reservas", this.reservaRepository.findAll());
        model.addAttribute("productos", this.productoRepository.findAll());
        return "cuentaNuevo";
    }

    @PostMapping("/cuentas/new/save")
    @Transactional
    public String cuentaGuardar(@ModelAttribute CuentaEntity cuenta, @RequestParam Integer reservaId, @RequestParam Long productoId) {
        Optional<Reserva> reserva = this.reservaRepository.findById(reservaId);
        Optional<ProductoEntity> producto = this.productoRepository.findById(productoId);
        if (reserva.isPresent() && producto.isPresent()) {
            cuenta.setReserva(reserva.get());
            cuenta.setProducto(producto.get());
            this.cuentaRepository.save(cuenta);
        }
        
        return "redirect:/cuentas";
    }

    @GetMapping("/cuentas/{id}/edit")
    @Transactional
    public String cuentaEditarForm(@PathVariable("id") Long id, Model model) {
        Optional<CuentaEntity> cuenta = this.cuentaRepository.findById(id);
        if (cuenta.isPresent()) {
            model.addAttribute("reserva", this.reservaRepository.findAll());
            model.addAttribute("cuenta", cuenta);
            model.addAttribute("producto", this.productoRepository.findAll());  
            return "cuentaEditar";
        } else {
            return "redirect:/cuentas";
        }
    }

    @Transactional
    @PostMapping("/cuentas/{id}/edit/save")
    public String cuentaEditarGuardar(@PathVariable long idCuenta, @ModelAttribute ("id_reserva") Integer idReserva, @ModelAttribute ("id_producto") Long idProducto) {
        Optional<CuentaEntity> cuenta = this.cuentaRepository.findById(idCuenta);
        Optional<Reserva> reserva = this.reservaRepository.findById(idReserva);
        Optional<ProductoEntity> producto = this.productoRepository.findById(idProducto);
        if (cuenta.isPresent() && reserva.isPresent() && producto.isPresent()) {
            CuentaEntity cuentaEntity = cuenta.get();
            cuentaEntity.setReserva(reserva.get());
            cuentaEntity.setProducto(producto.get());
            this.cuentaRepository.save(cuentaEntity);
        }
        return "redirect:/cuentas";
    }

    @Transactional
    @GetMapping("/cuentas/{id}/delete")
    public String cuentaEliminar(@PathVariable("id") long id) {
        this.cuentaRepository.deleteById(id);
        return "redirect:/cuentas";
    }
}
