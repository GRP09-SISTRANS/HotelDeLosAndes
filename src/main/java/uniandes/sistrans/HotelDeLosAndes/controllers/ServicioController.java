package uniandes.sistrans.HotelDeLosAndes.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import uniandes.sistrans.HotelDeLosAndes.models.BarEntity;
import uniandes.sistrans.HotelDeLosAndes.models.GimnasioEntity;
import uniandes.sistrans.HotelDeLosAndes.models.InternetEntity;
import uniandes.sistrans.HotelDeLosAndes.models.LavanderiaEntity;
import uniandes.sistrans.HotelDeLosAndes.models.PiscinaEntity;
import uniandes.sistrans.HotelDeLosAndes.models.PrestamoUtensiliosEntity;
import uniandes.sistrans.HotelDeLosAndes.models.SalonConferenciaEntity;
import uniandes.sistrans.HotelDeLosAndes.models.SuperObjeto;
import uniandes.sistrans.HotelDeLosAndes.models.ServicioEntity;
import uniandes.sistrans.HotelDeLosAndes.models.ServicioForm;
import uniandes.sistrans.HotelDeLosAndes.models.SpaEntity;
import uniandes.sistrans.HotelDeLosAndes.models.SuperMercadoEntity;
import uniandes.sistrans.HotelDeLosAndes.models.TiendaEntity;
import uniandes.sistrans.HotelDeLosAndes.repositories.BarRepository;
import uniandes.sistrans.HotelDeLosAndes.repositories.GimnasioRepository;
import uniandes.sistrans.HotelDeLosAndes.repositories.InternetRepository;
import uniandes.sistrans.HotelDeLosAndes.repositories.LavanderiaRepository;
import uniandes.sistrans.HotelDeLosAndes.repositories.PiscinaRepository;
import uniandes.sistrans.HotelDeLosAndes.repositories.PrestamosUtensiliosRepository;
import uniandes.sistrans.HotelDeLosAndes.repositories.SalonConferenciaRepository;
import uniandes.sistrans.HotelDeLosAndes.repositories.ServicioRepository;
import uniandes.sistrans.HotelDeLosAndes.repositories.SpaRepository;
import uniandes.sistrans.HotelDeLosAndes.repositories.SuperMercadoRepository;
import uniandes.sistrans.HotelDeLosAndes.repositories.TiendaRepository;
import uniandes.sistrans.HotelDeLosAndes.services.SuperServicio;


@Controller
public class ServicioController {
    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private SpaRepository spaRepository;

    @Autowired
    private BarRepository barRepository;

    @Autowired
    private PrestamosUtensiliosRepository prestamosUtensiliosRepositoryRepository;

    @Autowired
    private SalonConferenciaRepository salonConferenciaRepositoryRepository;

    @Autowired
    private TiendaRepository tiendaRepositoryRepository;

    @Autowired
    private GimnasioRepository gimnasioRepository;

    @Autowired
    private InternetRepository internetRepository;

    @Autowired
    private SuperMercadoRepository superMercadoRepositoryRepository;

    @Autowired
    private PiscinaRepository piscinaRepository;

    @Autowired
    private LavanderiaRepository lavanderiaRepository;

    @Autowired
    private SuperServicio superServicio;

    @GetMapping("/servicios")
    public String servicios(Model model, String ciudad, String tipo) {
        model.addAttribute("servicios", this.servicioRepository.findAll());
        List<SuperObjeto> pepe = this.superServicio.dineroRecolectado();
        for (SuperObjeto servicioDineroHabitacion : pepe) {
            System.out.println(servicioDineroHabitacion.getNombre());
        }
        model.addAttribute("servicioHabDinero", this.superServicio.dineroRecolectado());
       // model.addAttribute("servicioHabDinero", this.servicioRepository.dineroRecolectado());
        
        return "servicios";
    }
    @GetMapping("/servicios/populares")
    public String getServiciosPopulares(@ModelAttribute("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio
    , @RequestParam("fechaFin")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin, Model model){
        List<SuperObjeto>serviciosPopulares = this.superServicio.darServiciosMasPopulares(fechaInicio, fechaFin);
        model.addAttribute("serviciosPopulares", serviciosPopulares);
        return "serviciosPopulares";
    }

    @GetMapping("/servicios/usuariosServiciosConsumos")
    public String getusuariosConsumos(@ModelAttribute("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio
    , @ModelAttribute("fechaFin")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate fechaFin,  
     @ModelAttribute("servicioId") Integer servicioId, @ModelAttribute("tipoAgrupamiento") String tipoAgrupamiento, 
     @RequestParam(value = "consumido", defaultValue = "off") String consumido, Model model){
        System.out.println(consumido);
        boolean esConsumido = "on".equals(consumido);
        if(esConsumido){
             List<SuperObjeto>consumosUsuario = this.superServicio.darConsumosServiciosUsuario(fechaInicio, fechaFin, servicioId, tipoAgrupamiento);
            model.addAttribute("consumosUsuario", consumosUsuario);
        }
        else{
            List<SuperObjeto>usuariosServicios = this.superServicio.darNoConsumosServiciosUsuario(fechaInicio, fechaFin, servicioId, tipoAgrupamiento);
            System.out.println("hola");
            model.addAttribute("consumosUsuario", usuariosServicios);
        }
       
        model.addAttribute("tipoAgrupamiento", tipoAgrupamiento);

        return "usuariosServiciosConsumos";
    }

    @GetMapping("/servicios/new")
    public String barForm(Model model) {
        model.addAttribute("servicioForm", new ServicioForm());
        return "servicioNuevo";
    }

    

    @PostMapping("/servicios/new/save")
    @Transactional
    public String barGuardar(@ModelAttribute ServicioForm servicioForm) {
        if (servicioForm.getTipoServicio().equals("Spa")){
            SpaEntity spa = new SpaEntity(servicioForm.getNombre(),servicioForm.getTipo());
            this.spaRepository.save(spa);
        }
        
        else if (servicioForm.getTipoServicio().equals("Bar")){
            BarEntity bar = new BarEntity(servicioForm.getNombre(),servicioForm.getTipo(), servicioForm.getCapacidad(), servicioForm.getEstilo());
            this.barRepository.save(bar);
            
        }
        else if (servicioForm.getTipoServicio().equals("Prestamo")){
            PrestamoUtensiliosEntity prestamoUtensilios = new PrestamoUtensiliosEntity(servicioForm.getNombre(),servicioForm.getTipo());
            this.prestamosUtensiliosRepositoryRepository.save(prestamoUtensilios);
            
        }
        else if (servicioForm.getTipoServicio().equals("Salon")){
            SalonConferenciaEntity salonConferencia = new SalonConferenciaEntity(servicioForm.getNombre(),servicioForm.getTipo(), servicioForm.getCapacidad());
            this.salonConferenciaRepositoryRepository.save(salonConferencia);
            
        }
        else if (servicioForm.getTipoServicio().equals("Tienda")){
            TiendaEntity tienda = new TiendaEntity(servicioForm.getNombre(),servicioForm.getTipo());
            this.tiendaRepositoryRepository.save(tienda);
        }
        else if (servicioForm.getTipoServicio().equals("Gimnasio")){
            GimnasioEntity gimnasio = new GimnasioEntity(servicioForm.getNombre(),servicioForm.getTipo(), servicioForm.getMaquinas());
            this.gimnasioRepository.save(gimnasio);
            
        }
        else if (servicioForm.getTipoServicio().equals("Internet")){
            InternetEntity internet = new InternetEntity(servicioForm.getNombre(),servicioForm.getTipo());
            this.internetRepository.save(internet);
            
        }
        else if (servicioForm.getTipoServicio().equals("SuperMercado")){
            SuperMercadoEntity superMercado = new SuperMercadoEntity(servicioForm.getNombre(),servicioForm.getTipo());
            this.superMercadoRepositoryRepository.save(superMercado);
            
        }
        else if (servicioForm.getTipoServicio().equals("Piscina")){
            PiscinaEntity piscina = new PiscinaEntity(servicioForm.getNombre(),servicioForm.getTipo(), servicioForm.getCapacidad(), servicioForm.getProfundidad());
            this.piscinaRepository.save(piscina);
            
        }
        else if (servicioForm.getTipoServicio().equals("Lavanderia")){
            LavanderiaEntity lavanderia = new LavanderiaEntity(servicioForm.getNombre(),servicioForm.getTipo());
            this.lavanderiaRepository.save(lavanderia);
            
        }
        return "redirect:/servicios";

    }

    @GetMapping("/servicios/{id}/edit")
    public String serviciosEditarForm(@PathVariable("id") Long id, Model model) {
        Optional<ServicioEntity> servicioEntity = this.servicioRepository.findById(id);
        if (servicioEntity.get() != null){
            model.addAttribute("servicioForm", new ServicioForm(id));
            return "servicioEditar";   
        }
        else{
            return "redirect:/servicios";
        }
    }

    @PostMapping("/servicios/{id}/edit/save")
    @Transactional
    public String servicioEditarForm(@PathVariable("id") long id, @ModelAttribute ServicioForm servicioForm) {
        if (servicioForm.getTipoServicio().equals("Spa")){
            SpaEntity spa = new SpaEntity(servicioForm.getNombre(),servicioForm.getTipo());
            this.spaRepository.save(spa);
        }
        
        else if (servicioForm.getTipoServicio().equals("Bar")){
            BarEntity bar = new BarEntity(servicioForm.getNombre(),servicioForm.getTipo(), servicioForm.getCapacidad(), servicioForm.getEstilo());
            this.barRepository.save(bar);
            
        }
        else if (servicioForm.getTipoServicio().equals("Prestamo")){
            PrestamoUtensiliosEntity prestamoUtensilios = new PrestamoUtensiliosEntity(servicioForm.getNombre(),servicioForm.getTipo());
            this.prestamosUtensiliosRepositoryRepository.save(prestamoUtensilios);
            
        }
        else if (servicioForm.getTipoServicio().equals("Salon")){
            SalonConferenciaEntity salonConferencia = new SalonConferenciaEntity(servicioForm.getNombre(),servicioForm.getTipo(), servicioForm.getCapacidad());
            this.salonConferenciaRepositoryRepository.save(salonConferencia);
            
        }
        else if (servicioForm.getTipoServicio().equals("Tienda")){
            TiendaEntity tienda = new TiendaEntity(servicioForm.getNombre(),servicioForm.getTipo());
            this.tiendaRepositoryRepository.save(tienda);
        }
        else if (servicioForm.getTipoServicio().equals("Gimnasio")){
            GimnasioEntity gimnasio = new GimnasioEntity(servicioForm.getNombre(),servicioForm.getTipo(), servicioForm.getMaquinas());
            this.gimnasioRepository.save(gimnasio);
            
        }
        else if (servicioForm.getTipoServicio().equals("Internet")){
            InternetEntity internet = new InternetEntity(servicioForm.getNombre(),servicioForm.getTipo());
            this.internetRepository.save(internet);
            
        }
        else if (servicioForm.getTipoServicio().equals("SuperMercado")){
            SuperMercadoEntity superMercado = new SuperMercadoEntity(servicioForm.getNombre(),servicioForm.getTipo());
            this.superMercadoRepositoryRepository.save(superMercado);
            
        }
        else if (servicioForm.getTipoServicio().equals("Piscina")){
            PiscinaEntity piscina = new PiscinaEntity(servicioForm.getNombre(),servicioForm.getTipo(), servicioForm.getCapacidad(), servicioForm.getProfundidad());
            this.piscinaRepository.save(piscina);
            
        }
        else if (servicioForm.getTipoServicio().equals("Lavanderia")){
            LavanderiaEntity lavanderia = new LavanderiaEntity(servicioForm.getNombre(),servicioForm.getTipo());
            this.lavanderiaRepository.save(lavanderia);
            
        }
        return "redirect:/servicios";
    }

    @GetMapping("/servicios/{id}/delete")
    @Transactional
    public String bebedorBorrar(@PathVariable("id") long id) {
        this.servicioRepository.deleteById(id);
        return "redirect:/servicios";
    }
}