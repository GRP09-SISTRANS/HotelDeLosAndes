package uniandes.sistrans.HotelDeLosAndes.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="habitacion")
public class Habitacion {


    @Id
    private Integer id;
    private Integer capacidad;

    public Habitacion(Integer Id, Integer Capacidad) {

        this.id = Id;
        this.capacidad = Capacidad;
    }

    public Habitacion()
    {;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
}
