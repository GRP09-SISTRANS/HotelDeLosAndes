package uniandes.sistrans.HotelDeLosAndes.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "piscina")
@PrimaryKeyJoinColumn(name="servicio_id")
public class PiscinaEntity extends ServicioEntity {
    private Integer capacidad;
    private Float profundidad;
    
    
    
    public PiscinaEntity(String nombre, String tipo, Integer capacidad, Float profundidad) {
        super(nombre, tipo);
        this.capacidad = capacidad;
        this.profundidad = profundidad;
    }
    public Integer getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
    public Float getProfundidad() {
        return profundidad;
    }
    public void setProfundidad(Float profundidad) {
        this.profundidad = profundidad;
    }

    
}
