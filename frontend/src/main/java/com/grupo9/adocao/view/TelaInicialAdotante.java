package com.grupo9.adocao.view;

import com.grupo9.adocao.model.Pet;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.function.Consumer;

/**
 * Tela inicial do adotante: os pets aparecem em blocos, três por linha.
 * Quando há mais pets do que cabem na janela, aparece a barra de rolagem.
 */
public class TelaInicialAdotante extends BorderPane {

    private static final int COLUNAS = 3;

    public TelaInicialAdotante(List<Pet> pets, Consumer<Pet> aoSelecionarPet) {
        getStyleClass().add("tela");

        Label titulo = new Label("Encontre seu novo amigo");
        titulo.getStyleClass().add("titulo");
        Label subtitulo = new Label("Clique em um pet para ver o perfil completo.");
        subtitulo.getStyleClass().add("subtitulo");
        VBox cabecalho = new VBox(4, titulo, subtitulo);
        cabecalho.setPadding(new Insets(24, 32, 8, 32));
        setTop(cabecalho);

        if (pets.isEmpty()) {
            Label vazio = new Label("Nenhum pet disponível para adoção no momento.");
            vazio.getStyleClass().add("subtitulo");
            setCenter(vazio);
            return;
        }

        GridPane grade = new GridPane();
        grade.setHgap(24);
        grade.setVgap(24);
        grade.setPadding(new Insets(16, 32, 32, 32));
        for (int c = 0; c < COLUNAS; c++) {
            ColumnConstraints coluna = new ColumnConstraints();
            coluna.setPercentWidth(100.0 / COLUNAS);
            coluna.setHalignment(HPos.CENTER);
            coluna.setHgrow(Priority.ALWAYS);
            grade.getColumnConstraints().add(coluna);
        }

        for (int i = 0; i < pets.size(); i++) {
            grade.add(new CardPet(pets.get(i), aoSelecionarPet), i % COLUNAS, i / COLUNAS);
        }

        ScrollPane rolagem = new ScrollPane(grade);
        rolagem.setFitToWidth(true);
        rolagem.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        rolagem.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        rolagem.getStyleClass().add("rolagem");
        setCenter(rolagem);
    }
}
