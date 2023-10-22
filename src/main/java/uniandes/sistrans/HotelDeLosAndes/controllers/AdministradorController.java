package uniandes.sistrans.HotelDeLosAndes.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*")
public class AdministradorController {

    @GetMapping("/admin")
    public String helloAdminController() {
        return "administrador";
    }
}
