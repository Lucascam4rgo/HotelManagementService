package HotelManagementService.Hotel.model.entities;

import HotelManagementService.Hotel.model.enums.reservaStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reserva {

    private static int contador = 0;
    private Integer id;
    private LocalDate checkin_date;
    private LocalDate checkout_date;
    private BigDecimal total_value;
    private reservaStatus reservaStatus;

    private Cliente client;
    private Quarto room;

    public Reserva(LocalDate checkin_date, LocalDate checkout_date, BigDecimal total_value,
                   Cliente client, Quarto room, reservaStatus reservaStatus) {
        this.id = contador++;
        this.checkin_date = checkin_date;
        this.checkout_date = checkout_date;
        this.total_value = total_value;
        this.client = client;
        this.room = room;
        setReservaStatus(reservaStatus);
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getCheckin_date() {
        return checkin_date;
    }

    public void setCheckin_date(LocalDate checkin_date) {
        this.checkin_date = checkin_date;
    }

    public LocalDate getCheckout_date() {
        return checkout_date;
    }

    public void setCheckout_date(LocalDate checkout_date) {
        this.checkout_date = checkout_date;
    }

    public BigDecimal getTotal_value() {
        return total_value;
    }

    public Cliente getClient() {
        return client;
    }

    public void setClient(Cliente client) {
        this.client = client;
    }

    public Quarto getRoom() {
        return room;
    }

    public void setRoom(Quarto room) {
        this.room = room;
    }

    public reservaStatus getReservaStatus() {
        return reservaStatus;
    }

    public void setReservaStatus(reservaStatus reservaStatus) {
        this.reservaStatus = reservaStatus;
    }

    @Override
    public String toString() {
        return "RESERVA: ID - " + id + ", DATA DE CHECK-IN - " + checkin_date +
                ", DATA DE CHECKOUT - "
                + checkout_date + ", VALOR TOTAL - " + total_value + "\n" + client.toString()
                + ", " +
                room.toString() + ", STATUS - " + reservaStatus;
    }
}
