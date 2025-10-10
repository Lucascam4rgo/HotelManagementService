package HotelManagementService.Hotel.model;

public class Cliente {

    private String name;
    private String cpf;
    private String email;
    private String phone;

    public Cliente(String name, String cpf, String email, String phone) {
        this.name = name;
        setCpf(cpf);
        this.email = email;
        setPhone(phone);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null) {
            throw new IllegalArgumentException("CPF não pode ser nulo");
        }

        //Remove qualquer caractere que não seja dígito
        String apenasDigitos = cpf.replaceAll("\\D", "");

        //verifica o tamanho
        if (apenasDigitos.length() != 11) {
            throw new IllegalArgumentException("O tamanho do cpf deve ser de 11 dígitos numéricos.");
        }

        if (!cpfValido(apenasDigitos)) {
            throw new IllegalArgumentException("CPF inválido.");
        }

        this.cpf = formatarCpf(apenasDigitos);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null) throw new IllegalArgumentException("O telefone não pode ser nulo.");

        //Remove tudo que não for número
        String apenasDigitos = phone.replaceAll("\\D", "");

        // Verifica tamanho (10 ou 11 dígitos)
        if (apenasDigitos.length() < 10 || apenasDigitos.length() > 11) {
            throw new IllegalArgumentException("Telefone inválido. Deve ter 10 ou 11 dígitos.");
        }

        int ddd = Integer.parseInt(apenasDigitos.substring(0, 2));
        if (ddd < 11 || ddd > 99) {
            throw new IllegalArgumentException("DDD inválido.");
        }

        this.phone = formatarPhone(apenasDigitos);
    }

    private boolean cpfValido(String cpf) {
        //rejeita sequencias repetidas
        if (cpf.chars().distinct().count() == 1) return false;

        try {
            int[] nums = new int[11];

            for (int x = 0; x < 11; x++) {
                nums[x] = Character.getNumericValue(cpf.charAt(x));
            }

            // primeiro dígito verificador
            int soma = 0;
            for (int i = 0; i < 9; i++) soma += nums[i] * (10 - i);

            int resto = soma % 11;
            int dv1 = (resto < 2) ? 0 : 11 - resto;
            if (nums[9] != dv1) return false;

            // Segundo dígito verificador
            soma = 0;
            for (int i = 0; i < 10; i++) soma += nums[i] * (11 - i);

            resto = soma % 11;
            int dv2 = (resto < 2) ? 0 : 11 - resto;
            return nums[10] == dv2;
        }
        catch (Exception e) {
            return false;
        }
    }

    private String formatarCpf(String cpf) {
        return cpf.substring(0, 3) + "." +
                cpf.substring(3, 6) + "." +
                cpf.substring(6, 9) + "-" +
                cpf.substring(9, 11);
    }

    private String formatarPhone(String phone) {
        return "(" + phone.substring(0, 2) + ")" +
                phone.substring(2, 7) + "-" +
                phone.substring(7);
    }

    @Override
    public String toString() {
        return "CLIENTE: " + name + ", " + cpf + ", " + email +
                ", " + phone;
    }
}
