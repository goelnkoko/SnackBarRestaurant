package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pedido {

    private Cliente cliente;
    private double taxaDeServico = 500; //Valor experimental
    private List<Prato> prato = new ArrayList<>();
    private double pagamento;


    public Pedido(Cliente cliente, Prato prato) {
        this.cliente = cliente;
        this.prato.add(prato);
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

    public double calcularTotal() { //Calcula o total do preco a ser pago pelo cliente
        double total = 0;

        for(Prato i: prato) {
            total += i.calcularPreco();
        }

        total += taxaDeServico;

        return total;
    }

    public String gerarFactura() { //Factura provisória a ser tranformada em aquivo txt
        return "Pedido{" +
                "cliente=" + cliente.getNome() +
                ", prato=" + prato.get(0) +
                ", quantidade=" + prato.get(0).getQuantidade() +
                ", taxaDeServico=" + taxaDeServico +
                ", pagamento=" + pagamento + "kz" +
                ", troco=" + calcularTroco() + "kz" +
                ", total=" + calcularTotal() + "kz" +
                '}';
    }

    public double calcularTroco() {
        if(pagamento > calcularTotal()){
            return pagamento-calcularTotal();
        } else {
            return 0;
        }
    }

}