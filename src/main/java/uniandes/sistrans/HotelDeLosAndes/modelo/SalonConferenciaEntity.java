package uniandes.sistrans.HotelDeLosAndes.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "salon_conferencia")
@PrimaryKeyJoinColumn(name="servicio_id")
public class SalonConferenciaEntity extends ServicioEntity{
    private Integer capacidad;




    
    public SalonConferenciaEntity(String nombre, char tipo, Integer capacidad) {
        super(nombre, tipo);
        this.capacidad = capacidad;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }


    
    
}
