package HotelManagementService.Hotel.model.service;

import HotelManagementService.Hotel.model.entities.Reserva;
import HotelManagementService.Hotel.model.enums.Status;
import HotelManagementService.Hotel.model.enums.reservaStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class ReservaService {

    Reserva reserva;
    private static final Logger logger = Logger.getLogger(ReservaService.class.getName());

    List<Reserva> reservas = new ArrayList<>();

    public void ReservationSignup(Reserva reservation) {
        reservas.add(reservation);
    }

    public void showReservations() {
        for (Reserva r: reservas) {
            System.out.println(r);
        }
    }

    public boolean hasReserved(String cpf) {
        for (Reserva r: reservas) {
            if (r.getClient().getCpf().contains(cpf) && r.getReservaStatus() == reservaStatus.ATIVA) {
                System.out.println("Cliente já tem reserva ativa nesse quarto: " + r.getRoom().toString());
                return true;
            }
        }
        System.out.println("Cliente ainda não hospedado, continuando...");
        return false;
    }

    public void searchReservation(int roomNumber) {
        for (Reserva r: reservas) {
            if (r.getRoom().getNumber() == roomNumber) {
                if (r.getRoom().getStatus() == Status.RESERVADO) {
                    r.setReservaStatus(reservaStatus.CANCELADA);
                    System.out.println("Reserva cancelada");
                }
                else {
                    r.setReservaStatus(reservaStatus.FINALIZADA);
                    System.out.println("Reserva finalizada");
                }
            }
        }
    }

    public Status reservedOrOccupied(LocalDate checkin) {
        if (checkin.isAfter(LocalDate.now())) {
            return Status.RESERVADO;
        }
        else {
            return Status.OCUPADO;
        }
    }

}
