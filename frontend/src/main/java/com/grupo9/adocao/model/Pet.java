package com.grupo9.adocao.model;

import java.util.ArrayList;
import java.util.List;

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
    private final List<String> fotos; // a primeira aparece no bloco da tela inicial

    // Saúde e comportamento
    private List<String> vacinas = new ArrayList<>();
    private boolean castrado;
    private boolean vermifugado;
    private boolean conviveComCriancas;
    private boolean conviveComOutrosAnimais;
    private String observacoes = "";

    public Pet(int id, String nome, String especie, String sexo, String porte,
               String idade, String descricao, String... fotos) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.sexo = sexo;
        this.porte = porte;
        this.idade = idade;
        this.descricao = descricao;
        this.fotos = List.of(fotos);
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEspecie() { return especie; }
    public String getSexo() { return sexo; }
    public String getPorte() { return porte; }
    public String getIdade() { return idade; }
    public String getDescricao() { return descricao; }
    public List<String> getFotos() { return fotos; }

    /** Foto principal, ou null se o pet não tiver foto. */
    public String getCaminhoImagem() {
        return fotos.isEmpty() ? null : fotos.get(0);
    }

    public List<String> getVacinas() { return vacinas; }
    public void setVacinas(String... vacinas) { this.vacinas = List.of(vacinas); }

    public boolean isCastrado() { return castrado; }
    public void setCastrado(boolean castrado) { this.castrado = castrado; }

    public boolean isVermifugado() { return vermifugado; }
    public void setVermifugado(boolean vermifugado) { this.vermifugado = vermifugado; }

    public boolean isConviveComCriancas() { return conviveComCriancas; }
    public void setConviveComCriancas(boolean conviveComCriancas) { this.conviveComCriancas = conviveComCriancas; }

    public boolean isConviveComOutrosAnimais() { return conviveComOutrosAnimais; }
    public void setConviveComOutrosAnimais(boolean conviveComOutrosAnimais) { this.conviveComOutrosAnimais = conviveComOutrosAnimais; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}
