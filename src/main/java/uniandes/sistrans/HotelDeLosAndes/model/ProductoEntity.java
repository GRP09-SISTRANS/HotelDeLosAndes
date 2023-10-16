package uniandes.sistrans.HotelDeLosAndes.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "producto")
public class ProductoEntity {
    @Id
    private Long id;
    
    private String nombre;
    private Integer costo;
    
    @ManyToOne
    @JoinColumn(name="servicio_id")
    private ServicioEntity servicio;

    @OneToMany(mappedBy = "producto")
    private List<CuentaEntity> cuentas;

    public ProductoEntity() {;}

    public ProductoEntity(String nombre, Integer costo, ServicioEntity servicio, List<CuentaEntity> cuentas) {
        this.nombre = nombre;
        this.costo = costo;
        this.servicio = servicio;
        this.cuentas = cuentas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public ServicioEntity getServicio() {
        return servicio;
    }

    public void setServicio(ServicioEntity servicio) {
        this.servicio = servicio;
    }

    public List<CuentaEntity> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<CuentaEntity> cuentas) {
        this.cuentas = cuentas;
    }
}


