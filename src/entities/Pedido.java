package entities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pedido {

    private Cliente cliente;
    private double taxaDeServico = 500; //Valor experimental
    private List<Prato> prato = new ArrayList<>();
    private double pagamento;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
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

    public String gerarFactura() { //Factura provisória a ser tranformada em aquivo txt

        StringBuilder factura = new StringBuilder();

        factura.append("FACTURA\n------------------------------------------------");
        factura.append("\nCliente: ");
        factura.append(cliente.getNome());
        factura.append("\nPrato: ");
        for(Prato p: prato) {
            factura.append(p);
        }
        factura.append("\ntaxaDeServico: ");
        factura.append(taxaDeServico);
        factura.append("\nTotal: ");
        factura.append(precoTotal());
        factura.append("\nPagamento: ");
        factura.append(pagamento);
        factura.append("\nTroco: ");
        factura.append(calcularTroco());
        factura.append("\n------------------------------------------------");

        return factura.toString();
    }

    public void gerarArquivoFactura() { //Factura provisória a ser tranformada em aquivo txt

        File path = new File("C:\\Users\\ThinkBook\\IdeaProjects\\OChaleSnackBarRestaurant\\Factura.txt");

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write("FACTURA\n------------------------------------------------");
            bw.write("\nCliente: " + cliente.getNome());
            bw.write("\nPrato: ");
            for(Prato p: prato) {
                bw.write(p.toString());
            }
            bw.write("\ntaxaDeServico: " + taxaDeServico);
            bw.write("\nTotal: " + precoTotal());
            bw.write("\nPagamento: " + pagamento);
            bw.write("\nTroco: " + calcularTroco());
            bw.write("\n------------------------------------------------");
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
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