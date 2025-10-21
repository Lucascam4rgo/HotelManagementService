package HotelManagementService.Hotel.model.service;

import HotelManagementService.Hotel.model.entities.Quarto;
import HotelManagementService.Hotel.model.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomService {

    Quarto room;

    List<Quarto> rooms = new ArrayList<>();

    public void RoomSignUp(Quarto room) {

        rooms.add(room);

    }

    public boolean searchRoom(int roomNumber) {
        return rooms.stream().anyMatch(n -> n.getNumber().equals(roomNumber));
    }

    public Quarto roomStatus(Integer roomNumber) {

        for (Quarto r : rooms) {
                if (r.getNumber().equals(roomNumber) &&  r.getStatus() == Status.DISPONIVEL) {
                    return r;
                }
        }
        return null;
    }

    public Quarto cancelStay(Integer roomNumber) {

        for (Quarto r : rooms) {
            if (r.getNumber().equals(roomNumber) &&
                    (r.getStatus() == Status.RESERVADO || r.getStatus() == Status.OCUPADO)) {
                return r;
            }
        }
        return null;
    }

    public BigDecimal totalDailyPrice(Quarto number, LocalDate checkin, LocalDate checkout) {

         long durationStay = ChronoUnit.DAYS.between(checkin, checkout);

         return number.getDailyPrice().multiply(BigDecimal.valueOf(durationStay));

    }

    public boolean allFull() {
        return rooms.stream().allMatch(r -> r.getStatus() != Status.DISPONIVEL);
    }

    public void finalizarLimpeza(int roomNumber) {

            Optional<Quarto> quarto = rooms.stream().filter(q -> q.getNumber().equals(roomNumber))
                    .findFirst();

            if (quarto.isEmpty()) {
                System.out.println("Quarto não encontrado");
                return;
            }

            if (quarto.get().getStatus() == Status.LIMPANDO) {
                System.out.println("Quarto agora está disponível");
                quarto.get().setStatus(Status.DISPONIVEL);
            } else {
                System.out.println("O quarto não está sendo limpado");
            }
    }

    public void showRooms() {
        for (Quarto q: rooms) {
            System.out.println(q);
        }
    }

}
