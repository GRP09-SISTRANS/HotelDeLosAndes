package uniandes.sistrans.HotelDeLosAndes.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Inheritance(strategy =  InheritanceType.JOINED)
@Table(name = "servicio")
public class ServicioEntity {
    @Id
    private Long id;
    private String nombre;
    private String tipo;
    @OneToMany(mappedBy = "servicio")
    private List<ProductoEntity> productos;
    
    public ServicioEntity(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;

    }

    public ServicioEntity() {;}

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }   
}
