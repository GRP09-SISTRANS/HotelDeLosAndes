package uniandes.sistrans.HotelDeLosAndes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import uniandes.sistrans.HotelDeLosAndes.models.Plan_Consumo;
import uniandes.sistrans.HotelDeLosAndes.repositories.Plan_ConsumoRepository;


@Controller
public class Plan_ConsumoController {
    @Autowired
    private Plan_ConsumoRepository plan_consumoRepository;

    @GetMapping(value="/planes_consumo")
    public String plan_consumo(Model model) {
        model.addAttribute("planes_consumo", plan_consumoRepository.darPlan_Consumo());
        return "planes_consumo";
    }

    @GetMapping(value="/plan_consumo/new")
    public String crearPlan_Consumo(Model model) {
        model.addAttribute("plan_consumo", plan_consumoRepository.darPlan_Consumo());
        return "plan_consumoNueva";
    }

    @GetMapping(value="/plan_consumo/new/save")
    public String guardarPlan_Consumo(@ModelAttribute Plan_Consumo plan_consumo) {
        plan_consumoRepository.insertarPlan_Consumo(plan_consumo.getId(), plan_consumo.getNombre(), plan_consumo.getDescripcion());
        return "redirect:/plan_consumo";
    }

    @GetMapping(value="/plan_consumo/{id}/edit")
    public String editarPlan_ConsumoForm(@PathVariable Integer id, Model model) {
        Plan_Consumo plan_consumo = plan_consumoRepository.darPlan_ConsumoPorId(id);
        if(plan_consumo != null) {
            model.addAttribute("plan_consumo", plan_consumo);
            return "plan_consumoEditar";
        }else{
            return "redirect:/plan_consumo";
        }
    }

    @GetMapping(value="/plan_consumo/{id}/edit/save")
    public String editarPlan_Consumo(@ModelAttribute Plan_Consumo plan_consumo, @PathVariable Integer id) {
        plan_consumoRepository.actualizarPlan_Consumo(id, plan_consumo.getNombre(), plan_consumo.getDescripcion());
        return "redirect:/plan_consumo";
    }

    @GetMapping(value="/plan_consumo/{id}/delete")
    public String eliminarPlan_Consumo(@PathVariable Integer id) {
        plan_consumoRepository.eliminarPlan_Consumo(id);
        return "redirect:/plan_consumo";
    }
}
