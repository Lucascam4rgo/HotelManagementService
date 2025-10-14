🏨 Hotel Management Service

O Hotel Management Service é um sistema em Java para gerenciamento de hotéis, permitindo o controle de clientes, quartos e reservas de forma simples e organizada.
O projeto foi desenvolvido com foco em boas práticas de programação orientada a objetos e separação de responsabilidades em camadas.

🚀 Funcionalidades principais

Cadastro, listagem, atualização e exclusão de clientes

Gerenciamento de quartos (tipos, status, disponibilidade)

Registro e controle de reservas

Exibição de informações no console via menus interativos

Validação de entradas e tratamento de erros

🧩 Estrutura do projeto
HotelManagementService/
│
├── model/
│   ├── Cliente.java
│   ├── Quarto.java
│   ├── Reserva.java
│   ├── Status.java
│   ├── Type.java
│   └── reservaStatus.java
│
├── service/
│   ├── ClientService.java
│   ├── RoomService.java
│   └── ReservaService.java
│
├── utils/
│   ├── HotelMenuHandler.java
│   └── InputUtils.java
│
└── view/
    └── Hotel.java   ← Ponto de entrada principal do sistema


model/ → contém as classes de domínio (entidades principais do sistema)

service/ → camada de lógica de negócio

utils/ → utilitários para entrada de dados e gerenciamento de menus

view/ → interface de execução (CLI / console)

🛠️ Tecnologias utilizadas

Java SE 8+

Paradigma Orientado a Objetos

Coleções e Enums

Entrada/Saída via Console

▶️ Como executar o projeto
1. Clonar o repositório
git clone https://github.com/seu-usuario/HotelManagementService.git
cd HotelManagementService

2. Compilar o código

Se estiver usando o terminal:

javac -d bin src/**/*.java

3. Executar o programa
java -cp bin view.Hotel


💡 Dica: se estiver usando IntelliJ IDEA ou Eclipse, basta abrir o projeto e executar a classe Hotel.java.

🧠 Conceitos aplicados

Encapsulamento e abstração

Enumerações para status e tipos de quartos

Manipulação de listas e objetos

Separação de camadas (Model–Service–View–Utils)

Tratamento de erros de entrada

🔮 Possíveis melhorias futuras

Persistência de dados (ex: salvar em arquivo ou banco de dados)

Interface gráfica (JavaFX ou Swing)

Autenticação de usuários (ex: administradores e recepcionistas)

API REST para integração externa

👨‍💻 Autor

Lucas Camargo
Desenvolvedor em início de carreira, apaixonado por tecnologia e automação.
🔗 LinkedIn
 · GitHub
