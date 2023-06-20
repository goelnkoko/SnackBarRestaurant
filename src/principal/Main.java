package principal;

import database.DB;
import entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Cliente> cliente = new ArrayList<>();
        List<Pedido> pedido = new ArrayList<>();

        //DB.closeConnection();

        int contador1 = 0, contador2 = 0;
        char resp1, resp2;

        System.out.println("Bem-vindo ao Snack Bar e Restaurante, o Chalé!".toUpperCase());

        do {
            cliente.add(novoCliente(contador1)); //Registra um novo cliente

            do{
                pedido.add(novoPedido(cliente.get(contador1)));

                pedido.get(contador2).setPagamento(pagamento(pedido.get(contador2)));

                System.out.println(pedido.get(contador2).gerarFactura()); //Imprime a factura

                System.out.println("\n====== COMPRA FEITA COM SUCESSO!! ======\n");

                System.out.print("Gostaria de fazer uma nova compra? [s]sim [n]nao: ");
                resp2 = sc.next().charAt(0);
                sc.nextLine();
            } while (resp2 == 's' || resp2 == 'S');

            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            System.out.print("Gostaria de fazer uma nova compra?: ");
            resp1 = sc.next().charAt(0);
            sc.nextLine();
        } while (resp1 == 's' || resp1 == 'S');
    }

    public static Cliente novoCliente(int contador) {
        Scanner sc = new Scanner(System.in);

        Cliente cliente = new Cliente(contador);

        System.out.println("DADOS DO CLIENTE!" +
                           "\n--------------------------------------------------------");
        System.out.print("Nome: ");
        cliente.setNome(sc.nextLine());
        System.out.print("Endereço: ");
        cliente.setEndereco(sc.nextLine());

        char response;
        do {
            System.out.print("Telefone: ");
            String telefone = sc.nextLine();
            cliente.addTelefone(telefone);
            System.out.print("Gostaria de adicionar mais um? [s] sim [n] nao");
            response = sc.next().charAt(0);
            sc.nextLine();
        } while (response == 's' || response == 'S');

        System.out.println("Registo concluído!\n" +
                           "----------------------------------------------------------");

        return cliente;
    }

    public static Pedido novoPedido(Cliente cliente) {

        Pedido pedido = new Pedido(cliente);
        Scanner sc = new Scanner(System.in);

        char response;
        int numeroDoPrato = 0;
        do {
            System.out.println("O que gostaria pedir: ");
            System.out.print("""
                    [1] Pizza
                    [2] Lanche
                    [3] Salgadinho
                    :""");
            int option = sc.nextInt();
            sc.nextLine(); //Consome o enter deixado pelo nextInt(), limpando a área

            switch(option) {
                case 1 ->
                        pedido.addPrato(Pizza());
                case 2 ->
                        pedido.addPrato(Lanche());
                case 3 ->
                        pedido.addPrato(Salgadinho());
                default ->
                        System.out.println("Opcao invalida!");
            }

            System.out.println("Preco unitario: " + pedido.getPrato().get(numeroDoPrato).calcularPreco() +
                    "\nPreco total: " + pedido.precoTotal());

            System.out.println("Gostaria de adicionar um prato? [s] sim [n] nao");
            response = sc.next().charAt(0);
            sc.nextLine();

            numeroDoPrato++;
        } while(response == 's' || response == 'S');

        return pedido;
    }

    //Adiciona Pizza ao carrinho
    public static Prato Pizza() {

        Scanner sc = new Scanner(System.in);

        System.out.print(" ");
        System.out.println("""
                            Adicione o tipo de recheio:
                            [1] Queijo
                            [2] cogumelo
                            [3] Peperroni
                            """);
        int menuRecheio = sc.nextInt();

        String recheio;
        switch (menuRecheio) {
            case 1 -> {
                recheio = "Queijo";
            }
            case 2 -> {
                recheio = "Cogumelo";
            }
            case 3 -> {
                recheio = "Peperroni";
            }
            default -> {
                recheio = null;
            }
        }

        System.out.print("""
                           Adicione o tipo de molho desejado: 
                           [1] Molho de tomate
                           [2] Molho de gorgonzola
                           [3] Molho Parisiense
                           [4] Molho picante
                           [5] Molho de strogonoff
                            """);

        int menuMolho = sc.nextInt();

        String molho;
        switch (menuMolho) {
            case 1 -> {
                molho = "Molho de tomate ";
            }
            case 2 -> {
                molho = "Molho de gorgonzola";
            }
            case 3 -> {
                molho = "Molho parisiense";
            }
            case 4 -> {
                molho = "Molho picante";
            }
            case 5 -> {
                molho = "Molho de strogonoff";
            }
            default -> {
                molho = null;
            }
        }

        System.out.print("""
                        Qual  tipo de cobertura deseja?:
                        [1] De alho
                        [2] Calabreza
                        [3] Rustica
                        [4] Atum & Beringela""");

        int menuCobertura = sc.nextInt();

        String cobertura;
        switch (menuCobertura) {
            case 1 -> {
                cobertura = "De alho ";
            }
            case 2 -> {
                cobertura = "Calabreza";
            }
            case 3 -> {
                cobertura = "Rustica";
            }
            case 4 -> {
                cobertura = "Atum & Beringela";
            }

            default -> {
                cobertura = null;
            }
        }

        System.out.println("Gostaria de uma cobertura recheada? [s] sim [n] nao");
        boolean coberturaRecheada = sc.next().charAt(0) == 's';
        System.out.println("Quantas deseja? ");
        int quantidade = sc.nextInt();

        return new Pizza(500, LocalDate.now(), 5, recheio, molho, cobertura, coberturaRecheada, quantidade);



    }

    //Adiciona Salgadinho ao carrinho
    public static Prato Salgadinho() {
        Scanner sc = new Scanner(System.in);

        System.out.println("""
                            Adicione o tipo que prefere:
                            [1] Frito
                            [2] Assado
                            """);
        int menuTipo = sc.nextInt();
        String tipo;

        switch(menuTipo){
            case 1 -> {
                tipo = "Frito";
            }
            case 2 -> {
                tipo = "Assado" ;
            }
            default -> {
                tipo = " null";
            }
        }

        System.out.println("""
                            Adicione o tipo de recheio:
                            [1] Bolinhas (queijo ou queijo com presunto)
                            [2] Risoles (carne ou camarão)
                            [3] Coxinha (frango ou frango com requeijão cremoso)
                            """);
        int menuRecheio = sc.nextInt();

        String recheio;
        switch (menuRecheio) {
            case 1 -> {
                recheio = "Bolinhas (queijo ou queijo com presunto)";
            }
            case 2 -> {
                recheio = "Risoles (carne ou camarão)";
            }
            case 3 -> {
                recheio = "Coxinha (frango ou frango com requeijão cremoso)";
            }
            default -> {
                recheio = null;
            }
        }

        String massa = null;

        System.out.println("Quantos deseja? ");
        int quantidade = sc.nextInt();

        return new Salgadinho(100, LocalDate.now(), 6.0, recheio, massa, tipo, quantidade);
    }

    //Adiciona Lanche ao carrinho
    public static Prato Lanche() {
        Scanner sc = new Scanner(System.in);

        System.out.println("""
                            Adicione o tipo de lanche:
                            [1] Pão francês
                            [2] Pão de forma
                            [3] Pão australiano
                            [4] Hamburguer
                            [5] Sandwich
                            """);
        int menuTipo = sc.nextInt();
        String recheio = null;
        String pao = null;
        String molho = null;

        String tipo;
        switch (menuTipo) {
            case 1 -> {
                tipo = "Pão francês";
            }
            case 2 -> {
                tipo = "Pão de forma";
            }
            case 3 -> {
                tipo = "Pão australiano";
            }
            case 4 -> {
                tipo = "Hamburguer";
            }
            case 5 -> {
                tipo = "Sandwich";
            }
            default -> {
                tipo = " null";
            }
        }
        System.out.println("Quantos deseja? ");
        int quantidade = sc.nextInt();

        return new Lanche(600.0, LocalDate.now(), 2.0, recheio, pao, molho, quantidade);
    }

    public static double pagamento(Pedido pedido) {

        Scanner sc = new Scanner(System.in);
        double pagamento;

        //Verifica se o cliente insere um valor igual ou superior ao preco total da compra
        do {
            System.out.println("Insira o valor de pagamento: ");
            pagamento = sc.nextDouble();
            if(pagamento < pedido.precoTotal()) {
                System.out.println("Dinheiro insuficiente!");
            }
        } while (pagamento < pedido.precoTotal());

        return pagamento;
    }
}


