package HotelManagementService.Hotel.service;

import HotelManagementService.Hotel.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClientService {

    private List<Cliente> clientes = new ArrayList<>();
    private static final String FILE_PATH = "clientes.json";

    public void ClientSignup(String name, String cpf, String email, String phone) {

        if (name.isEmpty() || cpf.isEmpty()) {
            System.out.println("Erro: Nome e CPF são obrigatórios");
            return;
        }

        boolean existe = clientes.stream().anyMatch(c ->
                c.getCpf().equals(cpf));

        if (existe) {
            System.out.println("Esse CPF já foi cadastrado!");
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
