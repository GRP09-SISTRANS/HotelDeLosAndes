package uniandes.sistrans.HotelDeLosAndes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name = "gimnasio")
@PrimaryKeyJoinColumn(name="servicio_id")
public class GimnasioEntity  extends ServicioEntity{
    private Integer maquinas;

    public GimnasioEntity(String nombre, String tipo, Integer maquinas) {
        super(nombre, tipo);
        this.maquinas = maquinas;
    }

    public GimnasioEntity() {
        super();
    }

    public Integer getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(Integer maquinas) {
        this.maquinas = maquinas;
    }
}
