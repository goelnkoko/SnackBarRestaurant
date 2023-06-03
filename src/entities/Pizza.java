package entities;

import java.time.LocalDate;

public class Pizza extends Prato {

    private String molho;
    private String cobertura;
    private boolean coberturaRecheada;

    public Pizza(double precoDeVenda, LocalDate dataDeValidade, double peso, String recheio, String molho, String cobertura, boolean coberturaRecheada) {
        super(precoDeVenda, dataDeValidade, peso);
        this.molho = molho;
        this.cobertura = cobertura;
        this.coberturaRecheada = coberturaRecheada;
    }

    public String getMolho() {
        return molho;
    }

    public void setMolho(String molho) {
        this.molho = molho;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    public boolean isCoberturaRecheada() {
        return coberturaRecheada;
    }

    public void setCoberturaRecheada(boolean coberturaRecheada) {
        this.coberturaRecheada = coberturaRecheada;
    }
}
