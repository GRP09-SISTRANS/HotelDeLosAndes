package uniandes.sistrans.HotelDeLosAndes.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nombre;
    private char tipo;
    @OneToMany(mappedBy = "servicio")
    private List<ProductoEntity> productos;

    
    public ServicioEntity(String nombre, char tipo) {
        this.nombre = nombre;
        this.tipo = tipo;

    }
    public ServicioEntity() {
        ;

    }
    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public char getTipo() {
        return tipo;
    }

    
}
