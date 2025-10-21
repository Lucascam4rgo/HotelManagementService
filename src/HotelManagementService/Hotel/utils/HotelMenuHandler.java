package HotelManagementService.Hotel.utils;

import HotelManagementService.Hotel.model.entities.Cliente;
import HotelManagementService.Hotel.model.entities.Quarto;
import HotelManagementService.Hotel.model.entities.Reserva;
import HotelManagementService.Hotel.model.enums.Status;
import HotelManagementService.Hotel.model.enums.Type;
import HotelManagementService.Hotel.model.enums.reservaStatus;
import HotelManagementService.Hotel.model.service.ClientService;
import HotelManagementService.Hotel.model.service.ReservaService;
import HotelManagementService.Hotel.model.service.RoomService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class HotelMenuHandler {

    DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final ReservaService reservaService;
    private final ClientService clientService;
    private final RoomService roomService;
    private final Scanner sc;

    public HotelMenuHandler(ReservaService reservaService, ClientService clientService, RoomService roomService, Scanner sc) {
        this.reservaService = reservaService;
        this.clientService = clientService;
        this.roomService = roomService;
        this.sc = sc;
    }

    public void cadastrarCliente() {
        System.out.println("----------------------");
        System.out.println(" CADASTRO DE CLIENTES ");
        System.out.println("----------------------");

        System.out.println();

        System.out.print("Digite o nome do cliente: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Digite o CPF (somente números): ");
        String cpf = sc.nextLine();

        System.out.print("Digite o email: ");
        String email = sc.nextLine();

        System.out.print("Digite o telefone: ");
        String phone = sc.nextLine();

        clientService.ClientSignup(name, cpf, email, phone);
    }

    public void cadastrarQuarto() {
        System.out.println("----------------------");
        System.out.println(" CADASTRO DE QUARTOS ");
        System.out.println("----------------------");

        System.out.println();

        int number = InputUtils.readInt(sc, "Digite o número do quarto: ");

        while (roomService.searchRoom(number)) {
            number = InputUtils.readInt(sc, "Esse quarto já existe, tente outro:  ");
        }

        sc.nextLine();

        Type tipoRoom = null;
        while (tipoRoom == null) {
            System.out.print("Digite qual o tipo do quarto (SOLTEIRO, CASAL, SUITE): ");
            String quarto = sc.nextLine().toUpperCase();

            try {
                tipoRoom = Type.valueOf(quarto);
            }
            catch (IllegalArgumentException e) {
                System.out.println("Argumento inválido! Por favor," +
                        " digite alguma das seguintes opções: SOLTEIRO/CASAL/SUITE");
            }
        }

        System.out.print("Tipo do quarto selecionado: " + tipoRoom);
        System.out.println();

        System.out.print("Digite o preço da diária: ");
        BigDecimal dailyPrice = sc.nextBigDecimal();

        Status status = null;

        sc.nextLine();

        while (status == null) {
            System.out.print("Digite o status (DISPONIVEL/LIMPANDO): ");
            String statusS = sc.nextLine().toUpperCase();

            try {
                status = Status.valueOf(statusS);
            }catch (IllegalArgumentException e) {
                System.out.println("Argumento inválido! Por favor," +
                        " digite alguma das seguintes opções: DISPONIVEL/LIMPANDO");
            }
        }

        System.out.print("Status selecionado: " + status);

        System.out.println();

        Quarto room = new Quarto(number, tipoRoom, dailyPrice, status);

        roomService.RoomSignUp(room);
    }

    public void reservarQuarto() {
        System.out.println("----------------------");
        System.out.println(" RESERVA DE QUARTOS ");
        System.out.println("----------------------");

        if (roomService.allFull()) {
            System.out.println("Todos os quartos estão cheios :/");
            return;
        }

        System.out.print("Qual o CPF do cliente que irá se cadastrar? (xxx.xxx.xxx-xx): ");
        sc.nextLine();
        String cpfCliente = sc.nextLine();

        Cliente clientAdressed = clientService.hasCPF(cpfCliente);

        while (clientAdressed == null) {
            System.out.print("Esse cpf não existe na nossa lista - " + cpfCliente +
                    ", tente novamente: ");
            cpfCliente = sc.nextLine();
            clientAdressed = clientService.hasCPF(cpfCliente);
        }

        if (reservaService.hasReserved(cpfCliente)) {
            return;
        }

        System.out.println();

        int roomNumber = InputUtils.readInt(sc, "Qual o número do quarto? : ");

        Quarto roomAvailable = roomService.roomStatus(roomNumber);

        while (roomAvailable == null) {
            System.out.println("Veja os quartos disponíveis na opção 6 ou tente novamente," +
                    " o quarto pode estar sendo limpado ou está ocupado");

            roomNumber = InputUtils.readInt(sc, "Qual o número do quarto? : ");

            roomAvailable = roomService.roomStatus(roomNumber);

        }

        System.out.print("Seleciona a data de check-in: ");
        LocalDate checkin = LocalDate.parse(sc.next(), fmt1);

        while (checkin.isBefore(LocalDate.now())) {
            System.out.print("A data deve ser a partir de hoje: ");
            checkin = LocalDate.parse(sc.next(), fmt1);
        }

        System.out.print("Seleciona a data de check-out: ");
        LocalDate checkout = LocalDate.parse(sc.next(), fmt1);

        while (checkout.isBefore(checkin)) {
            System.out.print("Checkout deve ser feito depois do dia de checkin: ");
            checkout = LocalDate.parse(sc.next(), fmt1);
        }

        BigDecimal total = roomService.totalDailyPrice(roomAvailable, checkin, checkout);

        roomAvailable.setStatus(reservaService.reservedOrOccupied(checkin));

        Reserva reservation = new Reserva(checkin, checkout, total, clientAdressed, roomAvailable,
                reservaStatus.ATIVA);

        reservaService.ReservationSignup(reservation);
    }

    public void cancelarOuFinalizar() {
        int cancelStay = InputUtils.readInt(sc, "Qual o seu quarto? (Número): ");

        Quarto roomToCancel = roomService.cancelStay(cancelStay);

        if (roomToCancel == null) {
            System.out.println("O quarto está disponível ou sendo limpado");
            return;
        }
        else {
            reservaService.searchReservation(cancelStay);

            roomToCancel.setStatus(Status.DISPONIVEL);

            System.out.println("Quarto " + roomToCancel.getNumber() + " está disponível agora!");
        }
    }

    public void finalizarLimpeza() {
        int roomCleaning = InputUtils.readInt(sc, "Qual o quarto que finalizou a limpeza? : ");

        roomService.finalizarLimpeza(roomCleaning);
    }


}
