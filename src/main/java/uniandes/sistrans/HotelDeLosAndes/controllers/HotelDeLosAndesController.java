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
}
