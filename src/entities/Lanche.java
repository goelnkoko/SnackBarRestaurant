package entities;

import java.time.LocalDate;

public class Lanche extends Prato{

    private String pao;
    private String molho;

    public Lanche(Double precoDeVenda, LocalDate dataDeValidade, Double peso, String pao, String molho) {
        super(precoDeVenda, dataDeValidade, peso);
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
