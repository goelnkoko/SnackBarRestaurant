package principal;

import entities.*;

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

        int contador = 0;
        char novaCompra;

        System.out.println("Bem-vindo ao Snack Bar e Restaurante, o Chalé!".toUpperCase());

        do {

            cliente.add(novoCliente(contador)); // Registra um novo cliente

            pedido.add(new Pedido(cliente.get(contador)));

            char response;
            int numeroDoPrato = 0;
            do {
                System.out.print("""
                        O que gostaria pedir:
                        [1] Pizza
                        [2] Lanche
                        [3] Salgadinho
                        :""");
                int option = sc.nextInt();
                sc.nextLine(); // Consome o enter deixado pelo nextInt(), limpando a área

                switch (option) {
                    case 1 ->
                        pedido.get(contador).addPrato(Pizza());

                    case 2 ->
                        pedido.get(contador).addPrato(Lanche());
                    case 3 ->
                        pedido.get(contador).addPrato(Salgadinho());

                    default ->
                        System.out.println("Opcao invalida!");
                }

                System.out.println(
                        "Preco unitario: " + pedido.get(contador).getPrato().get(numeroDoPrato).calcularPreco() +
                                "\nPreco total: " + pedido.get(contador).precoTotal());

                System.out.println("Gostaria de adicionar um prato? [s] sim [n] nao");
                response = sc.next().charAt(0);
                sc.nextLine();

                numeroDoPrato++;
            } while (response == 's' || response == 'S');

            double pagamento;
            do { // Verifica se o cliente insere um valor igual ou superior ao preco total da
                 // compra
                System.out.println("Insira o valor de pagamento: ");
                pagamento = sc.nextDouble();
                if (pagamento < pedido.get(contador).precoTotal()) {
                    System.out.println("Dinheiro insuficiente!");
                }
            } while (pagamento < pedido.get(contador).precoTotal());

            pedido.get(contador).setPagamento(pagamento);

            System.out.println(pedido.get(contador).gerarFactura()); // Imprime a factura
            // pedido.get(contador).gerarArquivoFactura();

            System.out.println("Gostaria de fazer uma nova compra? [s] sim [n] nao");
            novaCompra = sc.next().charAt(0);
            sc.nextLine();

        } while (novaCompra == 's' || novaCompra == 'S');

        // Relatorio do numero de pedidos e vendas efectuadas diariamente
    }

    public static Cliente novoCliente(int contador) {
        Scanner sc = new Scanner(System.in);

        Cliente cliente = new Cliente();
        cliente.setNumero(contador);

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

    // Adiciona Pizza ao carrinho
    public static Prato Pizza() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Adicione o tipo de recheio: ");
        System.out.println("""
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

        System.out.print("Adicione o tipo de molho desejado: ");
        System.out.print("""

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

        System.out.print(" Qual  tipo de cobertura deseja?:");
        System.out.print("""
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
        System.out.println("Quantas pizzas deseja? ");
        int quantidade = sc.nextInt();

        return new Pizza(500, LocalDate.now(), 5, recheio, molho, cobertura, coberturaRecheada, quantidade);
    }

    // Adiciona Salgadinho ao carrinho

    public static Prato Salgadinho() {
      
      {
      Scanner sc = new Scanner(System.in);

        System.out.print("Adicione o tipo que prefere: ");
        System.out.println("""
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
      
System.out.print("Adicione o tipo de recheio: ");
System.out.println("""
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

     
     
     
     return new Salgadinho(100, LocalDate.now(), 6, recheio, quantidade);
      }
    }

    // Adiciona Lanche ao carrinho

    public static Prato Lanche() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Adicione o tipo de lanche: ");
        System.out.println("""

                [1] Pão francês
                [2] Pão de forma
                [3] Pão australiano
                [4] Hamburguer
                [5] Sandwich

                """);

        int menuTipo = sc.nextInt();
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

        return new Lanche(600, LocalDate.now(), 2, recheio, molho, cobertura, coberturaRecheada, quantidade);
    }
}
