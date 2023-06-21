package entities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int codPedido;
    private Cliente cliente;
    private final double taxaDeServico = 500; //Valor experimental
    private List<Prato> prato = new ArrayList<>();
    private double pagamento;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getTaxaDeServico() {
        return taxaDeServico;
    }

    public List<Prato> getPrato() {
        return prato;
    }

    public void addPrato(Prato prato) { //Metodo que permite ao cliente adicionar quantos pratos precisar
        this.prato.add(prato);
    }

    public double getPagamento() {
        return pagamento;
    }

    public void setPagamento(double pagamento) {
        this.pagamento = pagamento;
    }

    public double precoTotal() { //Calcula o total do preco a ser pago pelo cliente
        double total = 0;

        for(Prato i: prato) {
            total += i.calcularPreco();
        }

        total += taxaDeServico;

        return total;
    }

    public String gerarFacturaRecibo() { //Factura provisória a ser tranformada em aquivo txt

        StringBuilder factura = new StringBuilder();

        factura.append("FACTURA\n------------------------------------------------");
        factura.append("\nSNACK BAR, O RESTAURANTE");
        factura.append("\nCliente: ");
        factura.append(cliente.getNumero() + " - ");
        factura.append(cliente.getNome());
        factura.append("\nPratos: ");
        for(Prato p: prato) {
            factura.append("\n\t" + p + " Preço: ");
            factura.append(p.calcularPreco() + "}\n");
        }
        factura.append("\nTaxa de serviço: " + taxaDeServico);
        factura.append("\nTotal: " + precoTotal());
        factura.append("\nPagamento: " + pagamento);
        factura.append("\nTroco: " + calcularTroco());
        factura.append("\n------------------------------------------------");

        return factura.toString();
    }

    public void gerarArquivoFacturaRecibo() {

        String path = "C:\\Users\\ThinkBook\\IdeaProjects\\OChaleSnackBarRestaurant\\FacturaRecibo.txt";

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){

            bw.write("FACTURA\n------------------------------------------------");
            bw.write("SNACK BAR, O RESTAURANTE");
            bw.write("\nCliente: ");
            bw.write(cliente.getNumero() + " - ");
            bw.write(cliente.getNome());
            bw.write("\nPratos: ");
            for(Prato p: prato) {
                bw.write("\n\t" + p + " Preço: " + p.calcularPreco());
                bw.write("}\n");
            }
            bw.write("\nTaxa de serviço: " + taxaDeServico);
            bw.write("\nTotal: " + precoTotal());
            bw.write("\nPagamento: " + pagamento);
            bw.write("\nTroco: " + calcularTroco());
            bw.write("\n------------------------------------------------");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public double calcularTroco() {
        if(pagamento > precoTotal()){
            return pagamento-precoTotal();
        } else {
            return 0;
        }
    }
}