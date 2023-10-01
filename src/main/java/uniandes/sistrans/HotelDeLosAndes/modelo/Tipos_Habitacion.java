package uniandes.sistrans.HotelDeLosAndes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tipos_habitacion")
public class Tipos_Habitacion {

    @Id
    private char id;
    private String nombre;
    private Integer costo;
    private boolean minibar;
    private boolean cafetera;
    private boolean television;

    public Tipos_Habitacion(char Id, String Nombre, Integer Costo, boolean Minibar, boolean Cafetera, boolean Television) {

        this.id = Id;
        this.nombre = Nombre;
        this.costo = Costo;
        this.minibar = Minibar;
        this.cafetera = Cafetera;
        this.television = Television;
    }

    public Tipos_Habitacion()
    {;}

    public char getId() {
        return id;
    }

    public void setId(char id) {
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

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    public boolean isCafetera() {
        return cafetera;
    }

    public void setCafetera(boolean cafetera) {
        this.cafetera = cafetera;
    }

    public boolean isTelevision() {
        return television;
    }

    public void setTelevision(boolean television) {
        this.television = television;
    }

    
}
