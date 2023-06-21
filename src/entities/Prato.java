package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Prato {

    private int numero;
    private double precoDeVenda;
    private LocalDate dataDeValidade;
    private double peso;
    private String recheio;
    private int quantidade;

    public Prato(double precoDeVenda, LocalDate dataDeValidade, double peso, int quantidade, String recheio) {
        this.precoDeVenda = precoDeVenda;
        this.dataDeValidade = dataDeValidade;
        this.peso = peso;
        this.quantidade = quantidade;
        this.recheio = recheio;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Double getPrecoDeVenda() {
        return precoDeVenda;
    }

    protected void setPrecoDeVenda(Double precoDeVenda) {
        this.precoDeVenda = precoDeVenda;
    }

    public LocalDate getDataDeValidade() {
        return dataDeValidade;
    }

    public void setDataDeValidade(LocalDate dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getRecheio() {
        return recheio;
    }

    public void setRecheio(String recheio) {
        this.recheio = recheio;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double calcularPreco() {
        return peso*precoDeVenda*quantidade;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return  peso + "kgs, " +
                quantidade + "un, " +
                "Data de Exp: " + dataDeValidade.format(dtf);
    }
}
