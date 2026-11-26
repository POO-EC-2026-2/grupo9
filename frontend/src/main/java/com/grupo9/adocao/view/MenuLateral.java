package com.grupo9.adocao.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/** Barra lateral com as opções do adotante. O item da tela atual fica destacado. */
public class MenuLateral extends VBox {

    private Button selecionado;

    public MenuLateral(String nomeSistema) {
        super(4);
        getStyleClass().add("menu-lateral");
        setPrefWidth(210);
        setMinWidth(210);
        setPadding(new Insets(24, 14, 24, 14));

        Label logo = new Label(nomeSistema);
        logo.getStyleClass().add("menu-logo");
        logo.setWrapText(true);
        getChildren().add(logo);
    }

    public void adicionarSecao(String titulo) {
        Label secao = new Label(titulo.toUpperCase());
        secao.getStyleClass().add("menu-secao");
        getChildren().add(secao);
    }

    /** Item que, ao ser clicado, fica marcado como a tela atual. */
    public Button adicionarItem(String texto, Runnable acao) {
        Button item = criarBotao(texto);
        item.setOnAction(e -> {
            selecionar(item);
            acao.run();
        });
        return item;
    }

    /** Item que só executa uma ação (ex.: sair), sem ficar marcado. */
    public Button adicionarAcao(String texto, Runnable acao) {
        Button item = criarBotao(texto);
        item.setOnAction(e -> acao.run());
        return item;
    }

    /** Empurra os próximos itens para o fim da barra. */
    public void adicionarEspaco() {
        Region espaco = new Region();
        VBox.setVgrow(espaco, Priority.ALWAYS);
        getChildren().add(espaco);
    }

    public void selecionar(Button item) {
        if (selecionado != null) {
            selecionado.getStyleClass().remove("menu-item-ativo");
        }
        selecionado = item;
        if (item != null) {
            item.getStyleClass().add("menu-item-ativo");
        }
    }

    private Button criarBotao(String texto) {
        Button item = new Button(texto);
        item.getStyleClass().add("menu-item");
        item.setMaxWidth(Double.MAX_VALUE);
        item.setAlignment(Pos.CENTER_LEFT);
        getChildren().add(item);
        return item;
    }
}
