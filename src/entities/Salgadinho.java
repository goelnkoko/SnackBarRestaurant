package entities;

import java.time.LocalDate;
public class Salgadinho extends Prato {

    private String massa;
    private String tipo;
    private int quantidadeVendida;

    public Salgadinho(double precoDeVenda, LocalDate dataDeValidade, Double peso, String recheio, String massa, String tipo, int quantidade) {
        super(precoDeVenda, dataDeValidade, peso, quantidade, recheio);
        this.massa = massa;
        this.tipo = tipo;
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

    @Override
    public String toString() {
        return "Salgadinho {" +
                tipo +
                ", com recheio de " + getRecheio() +
                "\n\te massa de '" + massa +
                "\n\t" + super.toString();
    }
}
