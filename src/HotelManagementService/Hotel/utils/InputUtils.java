package HotelManagementService.Hotel.utils;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtils {

    public static int readInt(Scanner sc, String message) {
        while (true) {
            try {
                System.out.print(message);
                return sc.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.print("Valor inválido. Digite um número inteiro!: ");
                sc.nextLine();
            }
        }
    }

    public static BigDecimal readBigDecimal(Scanner sc, String message) {
        while (true) {
            try {
                System.out.println(message);
                return sc.nextBigDecimal();
            }
            catch (InputMismatchException e) {
                System.out.print("Valor inválido. Digite um valor numérico!: ");
                sc.nextLine();
            }
        }
    }



}
