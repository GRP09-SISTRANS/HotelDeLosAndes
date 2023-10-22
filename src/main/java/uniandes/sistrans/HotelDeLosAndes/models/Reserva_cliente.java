package uniandes.sistrans.HotelDeLosAndes.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="reserva_cliente")
public class Reserva_cliente {
    @EmbeddedId
    private Reserva_clientePK pk;

    @ManyToOne
    @JoinColumn(name="habitacion", referencedColumnName="id")
    private Habitacion Habitacion_id;

    @ManyToOne
    @JoinColumn(name="plan_consumo", referencedColumnName="id")
    private Plan_Consumo Plan_consumo_id;

    public Reserva_cliente(Usuario Usuario_id,Reserva Reserva_id, Habitacion Habitacion_id, Plan_Consumo Plan_consumo_id) {
        this.pk = new Reserva_clientePK(Usuario_id, Reserva_id);
        this.Habitacion_id = Habitacion_id;
        this.Plan_consumo_id = Plan_consumo_id;
    }

    public Reserva_cliente() {;}

    public Reserva_clientePK getPk() {
        return pk;
    }

    public void setPk(Reserva_clientePK pk) {
        this.pk = pk;
    }

    public Habitacion getHabitacion_id() {
        return Habitacion_id;
    }

    public void setHabitacion_id(Habitacion habitacion_id) {
        Habitacion_id = habitacion_id;
    }

    public Plan_Consumo getPlan_consumo_id() {
        return Plan_consumo_id;
    }

    public void setPlan_consumo_id(Plan_Consumo plan_consumo_id) {
        Plan_consumo_id = plan_consumo_id;
    }
}
