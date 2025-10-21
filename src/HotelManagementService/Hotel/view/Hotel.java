package HotelManagementService.Hotel.view;

import HotelManagementService.Hotel.model.service.ClientService;
import HotelManagementService.Hotel.model.service.ReservaService;
import HotelManagementService.Hotel.model.service.RoomService;
import HotelManagementService.Hotel.utils.HotelMenuHandler;
import HotelManagementService.Hotel.utils.InputUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Hotel {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ClientService clientService = new ClientService();
        RoomService rooms = new RoomService();
        ReservaService reservaService = new ReservaService();

        HotelMenuHandler hotelMenuHandler = new HotelMenuHandler(reservaService, clientService, rooms, sc);

        try {
            char op;
            do {

                switch (menu(sc)) {
                    case 1:
                        hotelMenuHandler.cadastrarCliente();
                        break;
                    case 2:
                        hotelMenuHandler.cadastrarQuarto();
                        break;
                    case 3:
                        hotelMenuHandler.reservarQuarto();
                        break;
                    case 4:
                        hotelMenuHandler.cancelarOuFinalizar();
                        break;
                    case 5:
                        hotelMenuHandler.finalizarLimpeza();
                        break;
                    case 6:
                        clientService.showClients();
                        break;
                    case 7:
                        rooms.showRooms();
                        break;
                    case 8:
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
        catch (InputMismatchException in) {
            System.out.println("Tipo digitado errado: " + in.getMessage());
        }
        finally {
            sc.close();
        }

    }

    public static int menu(Scanner sc) {
        System.out.println("------------------------");
        System.out.println("   HOTEL   ");
        System.out.println("------------------------");
        System.out.println(" ");
        System.out.println(" [1] - Cadastro de Clientes");
        System.out.println(" [2] - Cadastro de Quartos");
        System.out.println(" [3] - Reserva de Quarto");
        System.out.println(" [4] - Cancelamento de reserva");
        System.out.println(" [5] - Terminar limpeza no quarto");
        System.out.println(" [6] - Listagem de Clientes");
        System.out.println(" [7] - Listagem de Quartos");
        System.out.println(" [8] - Ver todas as reservas");
        System.out.println("------------------------");
        System.out.println();

        int choice = InputUtils.readInt(sc, "Escolha uma opção (número do menu): ");

        System.out.println();

        return choice;
    }

}

