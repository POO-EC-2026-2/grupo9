package com.grupo9.adocao.model;

/**
 * Dados de um pet exibidos no front.
 * Quando o backend estiver pronto, esta classe pode ser trocada pelo modelo dele.
 */
public class Pet {

    private final int id;
    private final String nome;
    private final String especie;
    private final String sexo;
    private final String porte;
    private final String idade;
    private final String descricao;
    private final String caminhoImagem; // pode ser null (mostra um placeholder)

    public Pet(int id, String nome, String especie, String sexo, String porte,
               String idade, String descricao, String caminhoImagem) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.sexo = sexo;
        this.porte = porte;
        this.idade = idade;
        this.descricao = descricao;
        this.caminhoImagem = caminhoImagem;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEspecie() { return especie; }
    public String getSexo() { return sexo; }
    public String getPorte() { return porte; }
    public String getIdade() { return idade; }
    public String getDescricao() { return descricao; }
    public String getCaminhoImagem() { return caminhoImagem; }
}
