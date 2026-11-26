package com.grupo9.adocao.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/** Tela provisória para opções do menu que ainda serão feitas. */
public class TelaEmConstrucao extends VBox {

    public TelaEmConstrucao(String titulo, String mensagem) {
        super(10);
        getStyleClass().add("tela");
        setAlignment(Pos.CENTER);

        Label t = new Label(titulo);
        t.getStyleClass().add("titulo");
        Label m = new Label(mensagem);
        m.getStyleClass().add("subtitulo");
        m.setWrapText(true);

        getChildren().addAll(t, m);
    }
}
