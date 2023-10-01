package uniandes.sistrans.HotelDeLosAndes.modelo;

import java.sql.Date;
import java.util.Optional;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Integer id;
    private Date check_in;
    private Date check_out;
    private Date fecha_inicio_reserva;
    private Date fecha_final_reserva;

    public Reserva(Date Check_in, Date Check_out, Date Fecha_inicio_reserva, Date Fecha_final_reserva) {

        this.check_in = Check_in;
        this.check_out = Check_out;
        this.fecha_inicio_reserva = Fecha_inicio_reserva;
        this.fecha_final_reserva = Fecha_final_reserva;
    }

    public Reserva()
    {;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCheck_in() {
        return check_in;
    }

    public void setCheck_in(Date check_in) {
        this.check_in = check_in;
    }

    public Date getCheck_out() {
        return check_out;
    }

    public void setCheck_out(Date check_out) {
        this.check_out = check_out;
    }

    public Date getFecha_inicio_reserva() {
        return fecha_inicio_reserva;
    }

    public void setFecha_inicio_reserva(Date fecha_inicio_reserva) {
        this.fecha_inicio_reserva = fecha_inicio_reserva;
    }

    public Date getFecha_final_reserva() {
        return fecha_final_reserva;
    }

    public void setFecha_final_reserva(Date fecha_final_reserva) {
        this.fecha_final_reserva = fecha_final_reserva;
    }



}
