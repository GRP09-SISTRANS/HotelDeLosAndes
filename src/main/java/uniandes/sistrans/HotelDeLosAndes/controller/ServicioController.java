package uniandes.sistrans.HotelDeLosAndes.controller;

import java.util.Optional;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import uniandes.sistrans.HotelDeLosAndes.modelo.BarEntity;
import uniandes.sistrans.HotelDeLosAndes.modelo.GimnasioEntity;
import uniandes.sistrans.HotelDeLosAndes.modelo.InternetEntity;
import uniandes.sistrans.HotelDeLosAndes.modelo.LavanderiaEntity;
import uniandes.sistrans.HotelDeLosAndes.modelo.PiscinaEntity;
import uniandes.sistrans.HotelDeLosAndes.modelo.PrestamoUtensiliosEntity;
import uniandes.sistrans.HotelDeLosAndes.modelo.SalonConferenciaEntity;
import uniandes.sistrans.HotelDeLosAndes.modelo.ServicioEntity;
import uniandes.sistrans.HotelDeLosAndes.modelo.ServicioForm;
import uniandes.sistrans.HotelDeLosAndes.modelo.SpaEntity;
import uniandes.sistrans.HotelDeLosAndes.modelo.SuperMercadoEntity;
import uniandes.sistrans.HotelDeLosAndes.modelo.TiendaEntity;
import uniandes.sistrans.HotelDeLosAndes.repositorios.BarRepository;
import uniandes.sistrans.HotelDeLosAndes.repositorios.GimnasioRepository;
import uniandes.sistrans.HotelDeLosAndes.repositorios.InternetRepository;
import uniandes.sistrans.HotelDeLosAndes.repositorios.LavanderiaRepository;
import uniandes.sistrans.HotelDeLosAndes.repositorios.PiscinaRepository;
import uniandes.sistrans.HotelDeLosAndes.repositorios.PrestamosUtensiliosRepository;
import uniandes.sistrans.HotelDeLosAndes.repositorios.SalonConferenciaRepository;
import uniandes.sistrans.HotelDeLosAndes.repositorios.ServicioRepository;
import uniandes.sistrans.HotelDeLosAndes.repositorios.SpaRepository;
import uniandes.sistrans.HotelDeLosAndes.repositorios.SuperMercadoRepository;
import uniandes.sistrans.HotelDeLosAndes.repositorios.TiendaRepository;


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

    @GetMapping("/servicios")
    public String servicios(Model model, String ciudad, String tipo) {
        model.addAttribute("servicios", this.servicioRepository.findAll());
        
        return "servicios";
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