package com.grupo9.adocao.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/** Rodapé fixo na parte de baixo da janela. */
public class Rodape extends HBox {

    public Rodape() {
        getStyleClass().add("rodape");
        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10, 24, 10, 24));

        Label esquerda = new Label("Adote com amor: um pet muda a sua vida e você muda a dele.");
        esquerda.getStyleClass().add("rodape-texto");

        Region espaco = new Region();
        HBox.setHgrow(espaco, Priority.ALWAYS);

        Label direita = new Label("Sistema de Adoção • Grupo 9 • POO 2026");
        direita.getStyleClass().add("rodape-texto");

        getChildren().addAll(esquerda, espaco, direita);
    }
}
