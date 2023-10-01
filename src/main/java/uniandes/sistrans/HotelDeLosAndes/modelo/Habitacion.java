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
    private Integer tipoHabitacion;

    public Habitacion(Integer Id, Integer Capacidad, Integer tipoHabitacion) {

        this.id = Id;
        this.capacidad = Capacidad;
        this.tipoHabitacion = tipoHabitacion;
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

    public Integer getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(Integer tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    
}
