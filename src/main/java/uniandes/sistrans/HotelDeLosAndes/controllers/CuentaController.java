package uniandes.sistrans.HotelDeLosAndes.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.transaction.Transactional;
import uniandes.sistrans.HotelDeLosAndes.models.CuentaEntity;
import uniandes.sistrans.HotelDeLosAndes.repositories.CuentaRepository;
import uniandes.sistrans.HotelDeLosAndes.repositories.ProductoRepository;
import uniandes.sistrans.HotelDeLosAndes.repositories.ReservaRepository;
import uniandes.sistrans.HotelDeLosAndes.services.SuperServicio;


@Controller
public class CuentaController {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private SuperServicio superServicio;

    @GetMapping("/cuentas")
    public String cuentas(Model model) {
        model.addAttribute("cuentas", this.cuentaRepository.findAll());
        model.addAttribute("mayorOcupacion", this.superServicio.darFechaMayorOcupacion());
        model.addAttribute("menorOcupacion", this.superServicio.darFechaMayorOcupacion());
        model.addAttribute("mayorConsumo", this.superServicio.darFechaMayoresIngreso());
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
    public String cuentaGuardar(@ModelAttribute CuentaEntity cuenta) {
        this.cuentaRepository.insertarCuenta(
            cuenta.getProducto().getId(),
            cuenta.getCantidad(),
            cuenta.getReserva().getId()
        );
        return "redirect:/cuentas";
    }

    @GetMapping("/cuentas/{id}/edit")
    @Transactional
    public String cuentaEditarForm(@PathVariable("id") Long id, Model model) {
        Optional<CuentaEntity> cuenta = this.cuentaRepository.findById(id);
        if (cuenta.isPresent()) {
            CuentaEntity cuentaEntity = cuenta.get();
            model.addAttribute("cuenta", cuentaEntity);
            model.addAttribute("reservas", this.reservaRepository.findAll());
            model.addAttribute("productos", this.productoRepository.findAll());
            return "cuentaEditar";
        } else {
            return "redirect:/cuentas";
        }
    }

    @Transactional
    @PostMapping("/cuentas/{id}/edit/save")
    public String cuentaEditarGuardar(@PathVariable("id") Long idCuenta, @ModelAttribute CuentaEntity cuenta) {
        this.cuentaRepository.actualizarCuenta(
            idCuenta,
            cuenta.getProducto().getId(),
            cuenta.getCantidad(),
            cuenta.getReserva().getId()
        );
        return "redirect:/cuentas";
    }

    @Transactional
    @GetMapping("/cuentas/{id}/delete")
    public String cuentaEliminar(@PathVariable("id") long id) {
        this.cuentaRepository.deleteById(id);
        return "redirect:/cuentas";
    }
}
