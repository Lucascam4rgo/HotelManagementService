package HotelManagementService.Hotel.view;

import HotelManagementService.Hotel.model.*;
import HotelManagementService.Hotel.service.ClientService;
import HotelManagementService.Hotel.service.ReservaService;
import HotelManagementService.Hotel.service.RoomService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Hotel {

    public static void main(String[] args) {

        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Scanner sc = new Scanner(System.in);

        ClientService clientService = new ClientService();
        RoomService rooms = new RoomService();
        ReservaService reservaService = new ReservaService();

        try {
            char op;
            do {
                System.out.println("------------------------");
                System.out.println("   HOTEL SANTO ANDRÉ    ");
                System.out.println("------------------------");
                System.out.println(" ");
                System.out.println(" [1] - Cadastro de Clientes");
                System.out.println(" [2] - Cadastro de Quartos");
                System.out.println(" [3] - Reserva de Quarto");
                System.out.println(" [4] - Cancelamento de reserva");
                System.out.println(" [5] - Listagem de Clientes");
                System.out.println(" [6] - Listagem de Quartos");
                System.out.println(" [7] - Ver todas as reservas");
                System.out.println("------------------------");
                System.out.println();

                System.out.print(" Escolha sua opção: ");
                int choice = sc.nextInt();

                System.out.println();

                switch (choice) {
                    case 1:
                        System.out.println("----------------------");
                        System.out.println(" CADASTRO DE CLIENTES ");
                        System.out.println("----------------------");

                        System.out.println();

                        System.out.print("Digite o nome do cliente: ");
                        sc.nextLine();
                        String name = sc.nextLine();

                        System.out.print("Digite o CPF: ");
                        String cpf = sc.nextLine();

                        System.out.print("Digite o email: ");
                        String email = sc.nextLine();

                        System.out.print("Digite o telefone: ");
                        String phone = sc.nextLine();

                        clientService.ClientSignup(name, cpf, email, phone);

                        break;
                    case 2:
                        System.out.println("----------------------");
                        System.out.println(" CADASTRO DE QUARTOS ");
                        System.out.println("----------------------");

                        System.out.println();
                        System.out.print("Digite o número do quarto: ");
                        Integer number = sc.nextInt();

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

                        rooms.RoomSignUp(room);

                        break;
                    case 3:
                        System.out.println("----------------------");
                        System.out.println(" RESERVA DE QUARTOS ");
                        System.out.println("----------------------");

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
                            break;
                        }

                        System.out.println();

                        System.out.print("Qual o número do quarto: ");
                        int roomNumber = sc.nextInt();

                        Quarto roomAvailable = rooms.roomStatus(roomNumber);

                        while (roomAvailable == null) {
                            System.out.println("Veja os quartos disponíveis na opção 6 ou tente novamente," +
                                    " o quarto pode estar sendo limpado ou está ocupado");

                            System.out.print("Qual o número do quarto: ");
                            roomNumber = sc.nextInt();

                            roomAvailable = rooms.roomStatus(roomNumber);

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
                            System.out.println("Checkout deve ser feito depois do dia de checkin: ");
                            checkout = LocalDate.parse(sc.next(), fmt1);
                        }

                        BigDecimal total = rooms.totalDailyPrice(roomAvailable, checkin, checkout);

                        roomAvailable.setStatus(reservaService.reservedOrOccupied(checkin));

                        Reserva reservation = new Reserva(checkin, checkout, total, clientAdressed, roomAvailable,
                                reservaStatus.ATIVA);

                        reservaService.ReservationSignup(reservation);


                        break;
                    case 4:
                        System.out.println("Qual o seu quarto? (Número): ");
                        int cancelStay = sc.nextInt();

                        System.out.println("Deseja cancelar ou finalizar? (F/C): ");
                        char option = Character.toUpperCase(sc.next().charAt(0));

                        while (option != 'F' && option != 'C') {
                            System.out.println("Valor inválido, tente novamente: ");
                            option = Character.toUpperCase(sc.next().charAt(0));
                        }
                        Quarto roomToCancel = rooms.cancelStay(cancelStay);

                        if (roomToCancel == null) {
                            System.out.println("O quarto está disponível");
                            break;
                        }
                        else {
                            roomToCancel.setStatus(Status.DISPONIVEL);

                            reservaService.searchReservation(cancelStay, option);
                            System.out.println("Quarto " + roomToCancel.getNumber() + " está disponível agora!");
                        }
                        break;
                    case 5:
                        clientService.showClients();
                        break;
                    case 6:
                        rooms.showRooms();
                        break;
                    case 7:
                        reservaService.showReservations();
                        break;
                    default:
                        System.out.println("Valor inválido.");
                }

                System.out.print("Deseja fazer outra opção? (S/N): ");
                op = Character.toUpperCase(sc.next().charAt(0));

                while (op != 'S' && op != 'N') {
                    System.out.print("Caractere inválido, tente novamente: ");
                    op = Character.toUpperCase(sc.next().charAt(0));
                }

            } while (op == 'S');
        }
        catch (NullPointerException n) {
            System.out.println("Objeto nulo: " + n.getMessage());
        }
        catch (IndexOutOfBoundsException i) {
            System.out.println("Elemento com índice fora do intervalo: " + i.getMessage());
        }
        catch (IllegalArgumentException il) {
            System.out.println("Argumento inválido: " + il.getMessage());
        }

        sc.close();

    }

}
