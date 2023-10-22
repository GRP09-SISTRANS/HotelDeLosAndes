package uniandes.sistrans.HotelDeLosAndes.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="habitacion")
public class Habitacion {
    @Id
    private Integer id;
    private Integer capacidad;

    @ManyToOne
    @JoinColumn(name="tipos_habitacion_id", referencedColumnName="id")
    private Tipos_Habitacion tipos_habitacion_id;

    public Habitacion(Integer Id, Integer Capacidad, Tipos_Habitacion tipoHabitacion) {
        this.id = Id;
        this.capacidad = Capacidad;
        this.tipos_habitacion_id = tipoHabitacion;
    }

    public Habitacion() {;}

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

    public Tipos_Habitacion getTipoHabitacion() {
        return tipos_habitacion_id;
    }

    public void setTipoHabitacion(Tipos_Habitacion tipoHabitacion) {
        this.tipos_habitacion_id = tipoHabitacion;
    }

    public String toString() {
        return "Habitacion [id=" + id + ", capacidad=" + capacidad + ", tipoHabitacion=" + tipos_habitacion_id + "]";
    }
}
