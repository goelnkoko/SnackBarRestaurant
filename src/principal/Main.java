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

        do{

            cliente.add(novoCliente(contador)); //Registra um novo cliente

            pedido.add(new Pedido(cliente.get(contador)));

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
                        pedido.get(contador).addPrato(Pizza());
                    case 2 ->
                        pedido.get(contador).addPrato(Lanche());
                    case 3 ->
                        pedido.get(contador).addPrato(Salgadinho());
                    default ->
                        System.out.println("Opcao invalida!");
                }

                System.out.println("Preco unitario: " + pedido.get(contador).getPrato().get(numeroDoPrato).calcularPreco() +
                                   "\nPreco total: " + pedido.get(contador).calcularTotal());

                System.out.println("Gostaria de adicionar um prato? [s] sim [n] nao");
                response = sc.next().charAt(0);
                sc.nextLine();

                numeroDoPrato++;
            } while(response == 's' || response == 'S');

            double pagamento;
            do { //Verifica se o cliente insere um valor igual ou superior ao preco total da compra
                System.out.println("Insira o valor de pagamento: ");
                pagamento = sc.nextDouble();
                if(pagamento < pedido.get(contador).calcularTotal()) {
                    System.out.println("Dinheiro insuficiente!");
                }
            } while (pagamento < pedido.get(contador).calcularTotal());

            pedido.get(contador).setPagamento(pagamento);

            System.out.println(pedido.get(contador).gerarFactura()); //Imprime a factura

            System.out.println("Gostaria de fazer uma nova compra? [s] sim [n] nao");
            novaCompra = sc.next().charAt(0);
            sc.nextLine();

        } while (novaCompra == 's' || novaCompra == 'S');
    }

    public static Cliente novoCliente(int contador) {
        Scanner sc = new Scanner(System.in);

        System.out.println("DADOS DO CLIENTE!" +
                           "\n--------------------------------------------------------");
        System.out.print("Nome: ");
        String name = sc.nextLine();
        System.out.print("Endereço: ");
        String adress = sc.nextLine();

        Cliente cliente = new Cliente(contador, name, adress);

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

    //Adiciona Pizza ao carrinho
    public static Prato Pizza() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Adicione o tipo de recheio: "); //Provisório
        String recheio = sc.nextLine();
        System.out.print("Adicione o molho desejado: "); //Provisório
        String molho = sc.nextLine();
        System.out.print("Adicione o tipo de cobertura: "); //Provisório
        String cobertura = sc.nextLine();
        System.out.println("Gostaria de uma cobertura recheada? [s] sim [n] nao");
        boolean coberturaRecheada = sc.next().charAt(0) == 's';
        System.out.println("Quantas pizzas deseja? ");
        int quantidade = sc.nextInt();

        return new Pizza(500, LocalDate.now(), 300.0, recheio, molho, cobertura, coberturaRecheada, quantidade);
    }
    //Adiciona Salgadinho ao carrinho
    public static Prato Salgadinho() {
        return new Salgadinho();
    }

    //Adiciona Lanche ao carrinho
    public static Prato Lanche() {
        return new Salgadinho();
    }
}


