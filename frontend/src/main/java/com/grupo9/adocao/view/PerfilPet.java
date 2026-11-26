package com.grupo9.adocao.view;

import com.grupo9.adocao.model.Pet;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/** Perfil do pet: imagem, informações, descrição e os botões Adotar e Voltar. */
public class PerfilPet extends ScrollPane {

    public PerfilPet(Pet pet, Runnable aoAdotar, Runnable aoVoltar) {
        getStyleClass().addAll("tela", "rolagem");
        setFitToWidth(true);

        Label nome = new Label(pet.getNome());
        nome.getStyleClass().add("titulo");

        GridPane dados = new GridPane();
        dados.setHgap(16);
        dados.setVgap(8);
        adicionarLinha(dados, 0, "Espécie", pet.getEspecie());
        adicionarLinha(dados, 1, "Sexo", pet.getSexo());
        adicionarLinha(dados, 2, "Porte", pet.getPorte());
        adicionarLinha(dados, 3, "Idade", pet.getIdade());

        Label tituloDescricao = new Label("Sobre");
        tituloDescricao.getStyleClass().add("rotulo");
        Label descricao = new Label(pet.getDescricao());
        descricao.setWrapText(true);
        descricao.getStyleClass().add("descricao");

        Button adotar = new Button("Adotar");
        adotar.getStyleClass().add("botao-primario");
        adotar.setDefaultButton(true);
        adotar.setOnAction(e -> aoAdotar.run());

        Button voltar = new Button("Voltar");
        voltar.getStyleClass().add("botao-secundario");
        voltar.setCancelButton(true);
        voltar.setOnAction(e -> aoVoltar.run());

        HBox botoes = new HBox(12, voltar, adotar);
        botoes.setPadding(new Insets(16, 0, 0, 0));

        VBox informacoes = new VBox(12, nome, dados, tituloDescricao, descricao, botoes);
        informacoes.setMaxWidth(420);

        HBox conteudo = new HBox(32, new ImagemPet(pet, 360, 300), informacoes);
        conteudo.setAlignment(Pos.TOP_CENTER);
        conteudo.setPadding(new Insets(40, 32, 32, 32));
        setContent(conteudo);
    }

    private static void adicionarLinha(GridPane grade, int linha, String rotulo, String valor) {
        Label r = new Label(rotulo);
        r.getStyleClass().add("rotulo");
        Label v = new Label(valor);
        v.getStyleClass().add("valor");
        grade.add(r, 0, linha);
        grade.add(v, 1, linha);
    }
}
