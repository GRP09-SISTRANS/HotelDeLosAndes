package uniandes.sistrans.HotelDeLosAndes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HotelDeLosAndesController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/docs")
    public String documentation() {
        return "docs";
    }

    @RequestMapping("/roles")
    public String login() {
        return "roles";
    }

    @RequestMapping("/roles/cliente")
    public String cliente() {
        return "cliente";
    }

    @RequestMapping("/roles/recepcionista")
    public String recepcionista() {
        return "recepcionista";
    }

    @RequestMapping("/roles/empleado")
    public String empleado() {
        return "empleado";
    }

    @RequestMapping("/roles/administrador")
    public String administrador() {
        return "administrador";
    }

    @RequestMapping("/roles/gerente")
    public String gerente() {
        return "gerente";
    }
}
