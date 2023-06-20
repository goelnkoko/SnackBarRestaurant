package entities;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private int numero;
    private String nome;
    private List<String> telefone = new ArrayList<>();
    private String endereco;

    public Cliente(int numero) {
        this.numero = numero;
    }
    public Cliente(int numero, String nome, String endereco) {
        this.numero = numero;
        this.nome = nome;
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getTelefone() {
        return telefone;
    }

    public void addTelefone(String telefone) {
        this.telefone.add(telefone);
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
