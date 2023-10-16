package uniandes.sistrans.HotelDeLosAndes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name = "bar")
@PrimaryKeyJoinColumn(name="servicio_id")
public class BarEntity extends ServicioEntity {
    private Integer capacidad;
    private String estilo;

    public BarEntity(String nombre, String tipo, Integer capacidad, String estilo) {
        super(nombre, tipo);
        this.capacidad = capacidad;
        this.estilo = estilo;
    }

    public BarEntity() {
        super();
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }
}
