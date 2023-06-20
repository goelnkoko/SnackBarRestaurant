package entities;

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

        factura.append("------------------------------------------------");
        factura.append("Factura\nCliente: ");
        factura.append(cliente.getNome());
        factura.append("\nPrato: ");
        for(Prato p: prato) {
            factura.append(p);
            factura.append("\n");
        }
        factura.append("\ntaxaDeServico: ");
        factura.append(taxaDeServico);
        factura.append("\nTotal: ");
        factura.append(precoTotal());
        factura.append("\nPagamento: ");
        factura.append(pagamento);
        factura.append("\nTroco: ");
        factura.append(calcularTroco());
        factura.append("------------------------------------------------");

        return factura.toString();
    }

    public void gerarArquivoFactura() {

    }

    public double calcularTroco() {
        if(pagamento > precoTotal()){
            return pagamento-precoTotal();
        } else {
            return 0;
        }
    }

}