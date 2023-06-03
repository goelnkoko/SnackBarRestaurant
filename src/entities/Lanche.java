package entities;

import java.time.LocalDate;

public class Lanche extends Prato{

    private String pao;
    private String molho;

    public Lanche(Double precoDeVenda, LocalDate dataDeValidade, Double peso, String recheio, String pao, String molho, int quantidade) {
        super(precoDeVenda, dataDeValidade, peso, quantidade, recheio);
        this.pao = pao;
        this.molho = molho;
    }

    public String getPao() {
        return pao;
    }

    public void setPao(String pao) {
        this.pao = pao;
    }

    public String getMolho() {
        return molho;
    }

    public void setMolho(String molho) {
        this.molho = molho;
    }
}
