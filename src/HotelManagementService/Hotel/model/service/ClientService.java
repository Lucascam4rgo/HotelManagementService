package HotelManagementService.Hotel.model.service;

import HotelManagementService.Hotel.model.entities.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientService {

    private List<Cliente> clientes = new ArrayList<>();
//    private static final String FILE_PATH = "clientes.json";

    public static boolean isInteger(String name) {
        try {
            Integer.parseInt(name);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean containsLetter(String str) {
        for (char c: str.toCharArray()) {
            if (Character.isLetter(c)) {
                return true;
            }
        }
        return false;
    }

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

    public static boolean validarEmail(String email){
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void ClientSignup(String name, String cpf, String email, String phone) {

        if (name.isEmpty() || cpf.isEmpty()) {
            System.out.println("Erro: Nome e CPF são obrigatórios");
            return;
        }

        boolean existeCPF = clientes.stream().anyMatch(c ->
                c.getCpf().equals(c.formatarCpf(cpf)));

        boolean existeEmail = clientes.stream().anyMatch(e ->
                e.getEmail().equals(email));

        boolean existePhone = clientes.stream().anyMatch(t ->
                t.getPhone().equals(t.formatarPhone(phone)));

        if (existeCPF) {
            System.out.println("Esse CPF já foi cadastrado!");
            return;
        }

        if (existeEmail) {
            System.out.println("Esse email já foi cadastrado!");
            return;
        }

        if (existePhone) {
            System.out.println("Esse telefone já foi cadastrado!");
            return;
        }

        if (isInteger(name)) {
            System.out.println("O nome não pode ter números.");
            return;
        }

        if (containsLetter(cpf) || containsLetter(phone)) {
            System.out.println("O CPF/Telefone não pode conter letras");
            return;
        }

        if (!validarEmail(email)) {
            System.out.println("Email fora do padrão");
            return;
        }

        Cliente cliente = new Cliente(name, cpf, email, phone);

        clientes.add(cliente);

        System.out.println();
        System.out.println("Cliente cadastrado com sucesso!");

    }

    public Cliente hasCPF(String cpf) {

        for (Cliente c: clientes) {
            if (c.getCpf().contains(cpf)) {
                return c;
            }
        }
        return null;
    }

    public void showClients() {
        for (Cliente c: clientes) {
            System.out.println(c);
        }
    }

}
