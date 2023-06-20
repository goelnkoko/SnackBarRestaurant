package entities;

import java.time.LocalDate;

public abstract class Prato {

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
        return  ", peso=" + peso +
                ", quantidade=" + quantidade;
    }
}
