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
import uniandes.sistrans.HotelDeLosAndes.model.ProductoEntity;
import uniandes.sistrans.HotelDeLosAndes.model.ServicioEntity;
import uniandes.sistrans.HotelDeLosAndes.repositories.ProductoRepository;
import uniandes.sistrans.HotelDeLosAndes.repositories.ServicioRepository;


@Controller
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @GetMapping("/productos")
    public String productos(Model model) {
        model.addAttribute("productos", this.productoRepository.findAll());
        return "productos";
    }

    @GetMapping("/productos/new")
    public String productoForm(Model model) {
        model.addAttribute("bar", new ProductoEntity());
        return "productoNuevo";
    }

    @PostMapping("/productos/new/save")
    @Transactional
    public String productoGuardar(@ModelAttribute ProductoEntity producto, @RequestParam Long servicioId) {
        Optional<ServicioEntity> servicio = this.servicioRepository.findById(servicioId);
        producto.setServicio(servicio.get());
        this.productoRepository.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/productos/{id}/edit")
    public String productoEditarForm(@PathVariable("id") Long id, Model model) {
        Optional<ProductoEntity> producto = this.productoRepository.findById(id);
        if (producto.get() != null) {
            model.addAttribute("producto", producto);
            return "productoEditar";
        } else {
            return "redirect:/productos";
        }
    }

    @PostMapping("/productos/{id}/edit/save")
    @Transactional
    public String productoEditarGuardar(@PathVariable("id") long id, @ModelAttribute ProductoEntity productoEntity) {
        this.productoRepository.save(productoEntity);
        return "redirect:/productos";
    }

    @GetMapping("/productos/{id}/delete")
    @Transactional
    public String productoEliminar(@PathVariable("id") long id) {
        this.productoRepository.deleteById(id);
        return "redirect:/productos";
    }
}
