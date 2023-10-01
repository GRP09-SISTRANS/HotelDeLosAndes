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
import uniandes.sistrans.HotelDeLosAndes.modelo.CuentaEntity;
import uniandes.sistrans.HotelDeLosAndes.repositorios.CuentaRepository;
import uniandes.sistrans.HotelDeLosAndes.repositorios.ProductoRepository;
import uniandes.sistrans.HotelDeLosAndes.repositorios.ServicioRepository;

@Controller

public class CuentaController {
      @Autowired
    private CuentaRepository cuentaRepository;

    @GetMapping("/cuentas")
    public String cuentas(Model model, String ciudad, String tipo) {
        model.addAttribute("cuentas", this.cuentaRepository.findAll());
        return "cuentas";
    }

    @GetMapping("/cuentas/new")
    public String cuentaForm(Model model) {
        model.addAttribute("bar", new CuentaEntity());
        return "cuentaNuevo";
    }

    @PostMapping("/cuentas/new/save")
    @Transactional
    public String cuentaGuardar(@ModelAttribute CuentaEntity cuenta, @RequestParam Long servicioId) {
        this.cuentaRepository.save(cuenta);
        return "redirect:/cuentas";
    }

    @GetMapping("/cuentas/{id}/edit")
    @Transactional
    public String cuentaEditarForm(@PathVariable("id") Long id, Model model) {
        Optional<CuentaEntity> cuenta = this.cuentaRepository.findById(id);
        if (cuenta.get() != null) {
            model.addAttribute("cuenta", cuenta);
            return "cuentaEditar";
        } else {
            return "redirect:/cuentas";
        }
    }

    @PostMapping("/cuentas/{id}/edit/save")
    public String cuentaEditarGuardar(@PathVariable("id") long id, @ModelAttribute CuentaEntity cuentaEntity) {
        this.cuentaRepository.save(cuentaEntity);
        return "redirect:/cuentas";
    }

    @GetMapping("/cuentas/{id}/delete")
    @Transactional
    public String cuentaEliminar(@PathVariable("id") long id) {
        this.cuentaRepository.deleteById(id);
        return "redirect:/cuentas";
    }
}
