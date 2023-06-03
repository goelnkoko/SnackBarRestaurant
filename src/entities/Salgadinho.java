package entities;

import java.time.LocalDate;
public class Salgadinho extends Prato {

    private String massa;
    private String tipo;
    private int quantidadeVendida;

    public Salgadinho(double precoDeVenda, LocalDate dataDeValidade, Double peso, String massa, String tipo, int quantidadeVendida) {
        super(precoDeVenda, dataDeValidade, peso);
        this.massa = massa;
        this.tipo = tipo;
        this.quantidadeVendida = quantidadeVendida;
    }

    public String getMassa() {
        return massa;
    }

    public void setMassa(String massa) {
        this.massa = massa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void somarQuantidadeVendida() {
        quantidadeVendida += getQuantidade();
    }
}
