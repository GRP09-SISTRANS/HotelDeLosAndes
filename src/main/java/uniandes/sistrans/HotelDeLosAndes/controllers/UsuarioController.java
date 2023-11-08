package uniandes.sistrans.HotelDeLosAndes.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.sistrans.HotelDeLosAndes.models.Usuario;
import uniandes.sistrans.HotelDeLosAndes.repositories.UsuarioRepository;
import uniandes.sistrans.HotelDeLosAndes.services.SuperServicio;


@Controller
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SuperServicio superServicio;

    @GetMapping(value="/usuarios")
    public String usuario(Model model) {
        model.addAttribute("usuarios", usuarioRepository.darUsuarios());
        model.addAttribute("buenosClientes", this.superServicio.darBuenosClientes());
        return "usuarios";
    }

    @GetMapping(value="/usuarios/new")
    public String crearUsuario(Model model) {
        model.addAttribute("usuario", usuarioRepository.darUsuarios());
        return "usuarioNuevo";
    }

    @GetMapping(value="/usuarios/consumos")
    public String consumo(@ModelAttribute("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio
    , @RequestParam("fechaFin")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin, @ModelAttribute("usuarioId") Integer usuarioId, Model model) {

        model.addAttribute("consumosUsuario", this.superServicio.consumosUsuario(fechaInicio, fechaFin, usuarioId));
        return "usuarioConsumo";
    }

    @GetMapping(value="/usuarios/new/save")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuarioRepository.insertarUsuario(usuario.getId(), usuario.getTipo_doc(), usuario.getCorreo(), usuario.getDireccion(), usuario.getNombre(), usuario.getApellido(), usuario.getContrasenia(), usuario.getTipo_usuario());
        return "redirect:/usuarios";
    }

    @GetMapping(value="/usuarios/{id}/edit")
    public String editarUsuarioForm(@PathVariable Integer id, Model model) {
        Usuario usuario = usuarioRepository.darUsuario(id);
        if(usuario != null) {
            model.addAttribute("usuario", usuario);
            return "usuarioEditar";
        }else{
            return "redirect:/usuarios";
        }
    }

    @GetMapping(value="/usuarios/{id}/edit/save")
    public String editarUsuario(@ModelAttribute Usuario usuario, @PathVariable Integer id) {
        usuarioRepository.actualizarUsuario(id, usuario.getTipo_doc(), usuario.getCorreo(), usuario.getDireccion(), usuario.getNombre(), usuario.getApellido(), usuario.getContrasenia(), usuario.getTipo_usuario());
        return "redirect:/usuarios";
    }

    @GetMapping(value="/usuarios/{id}/delete")
    public String eliminarUsuario(@PathVariable Integer id) {
        usuarioRepository.eliminarUsuario(id);
        return "redirect:/usuarios";
    }
}
