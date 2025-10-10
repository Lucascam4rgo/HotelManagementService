package HotelManagementService.Hotel.service;

import HotelManagementService.Hotel.model.Quarto;
import HotelManagementService.Hotel.model.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class RoomService {

    Quarto room;

    List<Quarto> rooms = new ArrayList<>();

    public void RoomSignUp(Quarto room) {

        rooms.add(room);

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
                    (r.getStatus() == Status.LIMPANDO || r.getStatus() == Status.OCUPADO)) {
                return r;
            }
        }
        return null;
    }

    public BigDecimal totalDailyPrice(Quarto number, LocalDate checkin, LocalDate checkout) {

         long durationStay = ChronoUnit.DAYS.between(checkin, checkout);

         return number.getDailyPrice().multiply(BigDecimal.valueOf(durationStay));

    }

    public void showRooms() {
        for (Quarto q: rooms) {
            System.out.println(q);
        }
    }

}
