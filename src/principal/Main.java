package principal;

import database.DB;
import entities.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    /*FUNCAO PRINCIPAL*/
    public static void main(String[] args) {

        Connection conn = DB.getConnection(); //Inicia conexao com a base de dados

        Scanner sc = new Scanner(System.in);
        List<Cliente> cliente = new ArrayList<>();
        List<Pedido> pedido = new ArrayList<>();

        int contador1 = 0, contador2 = 0;
        char resp1, resp2;

        System.out.println("Bem-vindo ao Snack Bar e Restaurante, o Chalé!".toUpperCase());

        //Inicializa a seção do cliente
        do {
            cliente.add(novoCliente(conn)); //Registra um novo cliente

            //Registra os pedidos do cliente
            do{
                pedido.add(novoPedido(cliente.get(contador1), conn));

                pedido.get(contador2).setPagamento(pagamento(pedido.get(contador2)));

                System.out.println(pedido.get(contador2).gerarFacturaRecibo()); //Imprime a factura
                pedido.get(contador2).gerarArquivoFacturaRecibo();

                System.out.println("\n====== COMPRA FEITA COM SUCESSO!! ======\n");

                System.out.print("Gostaria de fazer uma nova compra? [s]sim [n]nao: ");
                resp2 = sc.next().charAt(0);
                sc.nextLine();

                contador2++;
            } while (resp2 == 's' || resp2 == 'S');

            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            System.out.print("Processo terminado. Gostaria de iniciar novamente?: ");
            resp1 = sc.next().charAt(0);
            sc.nextLine();

            contador1++;
        } while (resp1 == 's' || resp1 == 'S');

        DB.closeConnection();
    }




    /*FUNCOES DE REGISTRO DE CLIENTES, PEDIDOS E PRODUTOS*/
    public static Cliente novoCliente(Connection conn) {
        Scanner sc = new Scanner(System.in);
        Cliente cliente = new Cliente();

        //Comando para insciar elementos na base de dados
        PreparedStatement pst = null;

        System.out.println("DADOS DO CLIENTE!" +
                           "\n--------------------------------------------------------");
        System.out.print("Nome: ");
        cliente.setNome(sc.nextLine());
        System.out.print("Endereço: ");
        cliente.setEndereco(sc.nextLine());
        System.out.print("Telefone: ");
        cliente.setTelefone(sc.nextLine());

        try {
            pst = conn.prepareStatement("insert into cliente " +
                                            "(nome, endereco, telefone) " +
                                            "values (?, ?, ?)",
                                            Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getEndereco());
            pst.setString(3, cliente.getTelefone());

            int linhasAfectadas = pst.executeUpdate();

            if(linhasAfectadas > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                while(rs.next()) {
                    cliente.setNumero(rs.getInt(1));
                }
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(pst);
        }

        System.out.println("Registo concluído!\n" +
                           "----------------------------------------------------------");

        return cliente;
    }
    public static Pedido novoPedido(Cliente cliente, Connection conn) {

        Pedido pedido = new Pedido(cliente);
        Scanner sc = new Scanner(System.in);

        pedidoDB(pedido, conn);

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
                        pedido.addPrato(Pizza(pedido, conn));
                case 2 ->
                        pedido.addPrato(Lanche(pedido, conn));
                case 3 ->
                        pedido.addPrato(Salgadinho(pedido, conn));
                default ->
                        System.out.println("Opcao invalida!");
            }

            System.out.println("Preco unitario: " + pedido.getPrato().get(numeroDoPrato).calcularPreco()/pedido.getPrato().get(numeroDoPrato).getQuantidade() +
                                "\nQuantidade: " + pedido.getPrato().get(numeroDoPrato).getQuantidade() +
                                "\nTaxa de Serviço: " + pedido.getTaxaDeServico() +
                                "\nPreco total: " + pedido.precoTotal());

            System.out.println("\nGostaria de adicionar um prato? [s] sim [n] nao");
            response = sc.next().charAt(0);
            sc.nextLine();

            numeroDoPrato++;
        } while(response == 's' || response == 'S');

        updatePedidoDB(pedido, conn);//Coloca tudo na BD
        return pedido;
    }

    public static Prato Pizza(Pedido pedido, Connection conn) {

        Scanner sc = new Scanner(System.in);

        System.out.print("""
                            Adicione o tipo de recheio:
                            [1] Queijo
                            [2] Cogumelo
                            [3] Peperroni
                            :""");
        int menuRecheio = sc.nextInt();

        String recheio;
        switch (menuRecheio) {
            case 1 ->
                recheio = "Queijo";
            case 2 ->
                recheio = "Cogumelo";
            case 3 ->
                recheio = "Peperroni";
            default ->
                recheio = "";
        }

        System.out.print("""
                           Adicione o tipo de molho desejado: 
                           [1] Molho de tomate
                           [2] Molho de gorgonzola
                           [3] Molho Parisiense
                           [4] Molho picante
                           [5] Molho de strogonoff
                           :""");

        int menuMolho = sc.nextInt();

        String molho;
        switch (menuMolho) {
            case 1 ->
                molho = "Molho de tomate ";
            case 2 ->
                molho = "Molho de gorgonzola";
            case 3 ->
                molho = "Molho parisiense";
            case 4 ->
                molho = "Molho picante";
            case 5 ->
                molho = "Molho de strogonoff";
            default ->
                molho = "";
        }

        System.out.print("""
                        Qual  tipo de cobertura deseja?:
                        [1] De alho
                        [2] Calabreza
                        [3] Rustica
                        [4] Atum & Beringela
                        :""");

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
                cobertura = "";
            }
        }

        System.out.println("Gostaria de uma cobertura recheada? [s] sim [n] nao");
        boolean coberturaRecheada = sc.next().charAt(0) == 's';
        System.out.println("Quantas deseja? ");
        int quantidade = sc.nextInt();

        Pizza prato = new Pizza(500, LocalDate.now(), 5, recheio, molho, cobertura, coberturaRecheada, quantidade);
        pizzaDB(pedido, prato, conn);
        return prato;
    }

    public static Prato Salgadinho(Pedido pedido, Connection conn) {
        Scanner sc = new Scanner(System.in);

        System.out.print("""
                            Adicione o tipo que prefere:
                            [1] Frito
                            [2] Assado
                            :""");
        int menuTipo = sc.nextInt();
        String tipo;

        switch(menuTipo){
            case 1 ->
                tipo = "Frito";
            case 2 ->
                tipo = "Assado" ;
            default ->
                tipo = " null";
        }

        System.out.print("""
                            Qual das opcoes preferes:
                            [1] Bolinhos com queijo
                            [2] Bolinhos com queijo e presunto
                            [3] Risóis de carne 
                            [4] Risóis de camarão
                            [5] Coxinha de frango
                            [6] Coxinha de frango com requeijão cremoso
                            :""");
        int menuRecheio = sc.nextInt();

        String recheio;
        switch (menuRecheio) {
            case 1 ->
                recheio = "Bolinhos com queijo";
            case 2 ->
                recheio = "Bolinhos com queijo e presunto";
            case 3 ->
                recheio = "Risóis de carne";
            case 4 ->
                recheio = "Risóes de camarão";
            case 5 ->
                recheio = "Coxinha de frango";
            case 6 ->
                recheio = "Coxinha de frango com requeião cremoso";
            default ->
                recheio = ".";
        }

        String massa = ".";

        System.out.println("Quantos deseja? ");
        int quantidade = sc.nextInt();

        Salgadinho prato = new Salgadinho(100, LocalDate.now(), 6.0, recheio, massa, tipo, quantidade);
        salgadinhoDB(pedido, prato, conn);
        return prato;
    }

    public static Prato Lanche(Pedido pedido, Connection conn) {
        Scanner sc = new Scanner(System.in);

        System.out.print("""
                            Adicione o tipo de lanche:
                            [1] Pão francês
                            [2] Pão de forma
                            [3] Pão australiano
                            [4] Hamburguer
                            [5] Sandwich
                            :""");
        int menuPao = sc.nextInt();

        String recheio = "canela";
        String molho = "ketchup e mayoneise";

        String pao = null;
        switch (menuPao) {
            case 1 ->
                pao = "Pão francês";
            case 2 ->
                pao = "Pão de forma";
            case 3 ->
                pao = "Pão australiano";
            case 4 ->
                pao = "Hamburguer";
            case 5 ->
                pao = "Sandwich";
            default ->
                pao = "";
        }

        System.out.println("Quantos deseja? ");
        int quantidade = sc.nextInt();

        Lanche prato = new Lanche(1000.0, LocalDate.now(), 1.0, recheio, pao, molho, quantidade);
        lancheBD(pedido, prato, conn);
        return prato;
    }

    /*FUNCAO DE PAGAMENTO*/
    public static double pagamento(Pedido pedido) {

        Scanner sc = new Scanner(System.in);
        double pagamento;

        System.out.println("Insira o valor de pagamento: ");
        pagamento = sc.nextDouble();

        //Verifica se o cliente insere um valor igual ou superior ao preco total da compra
        while (pagamento < pedido.precoTotal()) {
            System.out.println("Dinheiro insuficiente!");
            System.out.println("Insira o valor de pagamento: ");
            pagamento = sc.nextDouble();
        }

        return pagamento;
    }




    /*FUNCOES DE REGISTRO NA BASE DE DADOS*/

    public static void pedidoDB (Pedido pedido, Connection conn) {
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement("insert into pedido (taxaDeServico, fk_cliente)" +
                            "values (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            pst.setDouble(1, pedido.getTaxaDeServico());
            pst.setInt(2, pedido.getCliente().getNumero());

            int lAfectada = pst.executeUpdate();
            if(lAfectada > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                while(rs.next()) {
                    pedido.setCodPedido(rs.getInt(1));
                }
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePedidoDB (Pedido pedido, Connection conn) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("update pedido set preco_total = ?, pagamento = ?, troco = ? where cod_ped = ?");
            pst.setDouble(1, pedido.precoTotal());
            pst.setDouble(2, pedido.getPagamento());
            pst.setDouble(3, pedido.calcularTroco());
            pst.setInt(4, pedido.getCodPedido());
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    } //Atualiza a instancia do pedido na bd

    public static void pizzaDB(Pedido pedido, Pizza prato, Connection conn) {

        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement("insert into prato(preco_ven, peso, recheio, quantidade, preco_tot, fk_pedido)" +
                            "values (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            pst.setDouble(1, prato.getPrecoDeVenda());
            pst.setDouble(2, prato.getPeso());
            pst.setString(3, prato.getRecheio());
            pst.setInt(4, prato.getQuantidade());
            pst.setDouble(5, prato.calcularPreco());
            pst.setInt(6, pedido.getCodPedido());
            int lAfectadas = pst.executeUpdate();

            if(lAfectadas > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                while(rs.next()) {
                    prato.setNumero(rs.getInt(1));
                }
            }

            pst = conn.prepareStatement(" insert into pizza( cobertura, molho, cob_rech, fk_prato) values (?,?,?,?)");
            pst.setString(1, prato.getCobertura());
            pst.setString(2, prato.getMolho());
            pst.setBoolean(3, prato.isCoberturaRecheada());
            pst.setInt(4, prato.getNumero());
            pst.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void salgadinhoDB(Pedido pedido, Salgadinho prato, Connection conn) {
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement("insert into prato(preco_ven, peso, recheio, quantidade, preco_tot, fk_pedido)" +
                            "values (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            pst.setDouble(1, prato.getPrecoDeVenda());
            pst.setDouble(2, prato.getPeso());
            pst.setString(3, prato.getRecheio());
            pst.setInt(4, prato.getQuantidade());
            pst.setDouble(5, prato.calcularPreco());
            pst.setInt(6, pedido.getCodPedido());
            int lAfectadas = pst.executeUpdate();

            if(lAfectadas > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                while(rs.next()) {
                    prato.setNumero(rs.getInt(1));
                }
            }

            pst = conn.prepareStatement(" insert into salgadinho(massa, tipo, quantidadeVendida, fk_prato) values (?,?,?,?)");
            pst.setString(1, prato.getMassa());
            pst.setString(2, prato.getTipo());
            pst.setInt(3, prato.getQuantidadeVendida());
            pst.setInt(4, prato.getNumero());
            pst.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void lancheBD(Pedido pedido, Lanche prato, Connection conn) {

        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement("insert into prato(preco_ven, peso, recheio, quantidade, preco_tot, fk_pedido)" +
                            "values (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            pst.setDouble(1, prato.getPrecoDeVenda());
            pst.setDouble(2, prato.getPeso());
            pst.setString(3, prato.getRecheio());
            pst.setInt(4, prato.getQuantidade());
            pst.setDouble(5, prato.calcularPreco());
            pst.setInt(6, pedido.getCodPedido());
            int lAfectadas = pst.executeUpdate();

            if(lAfectadas > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                while(rs.next()) {
                    prato.setNumero(rs.getInt(1));
                }
            }

            pst = conn.prepareStatement(" insert into lanche(pao, molho, fk_prato) values (?,?,?)");
            pst.setString(1, prato.getPao());
            pst.setString(2, prato.getMolho());
            pst.setInt(3, prato.getNumero());
            pst.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}