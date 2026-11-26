package com.grupo9.adocao.view;

import com.grupo9.adocao.model.Pet;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * Janela de aviso personalizada, com as cores do sistema.
 * Substitui o Alert padrão do JavaFX (que corta textos longos).
 */
public final class Aviso {

    public enum Tipo { SUCESSO, ATENCAO, PERGUNTA }

    private static final double TAMANHO_FOTO = 110;

    private Aviso() {
    }

    /** Aviso com um botão. Se pet não for null, mostra a foto dele no topo. */
    public static void mostrar(Window dono, Tipo tipo, Pet pet, String titulo, String mensagem, String textoBotao) {
        Stage janela = criarJanela(dono);
        Button ok = criarBotao(textoBotao, "botao-primario", janela);
        ok.setDefaultButton(true);
        ok.setCancelButton(true);
        exibir(janela, tipo, pet, titulo, mensagem, ok);
    }

    /** Pergunta com dois botões. Retorna true se a pessoa clicou em textoSim. */
    public static boolean confirmar(Window dono, String titulo, String mensagem, String textoSim, String textoNao) {
        Stage janela = criarJanela(dono);
        boolean[] resposta = {false};

        Button nao = criarBotao(textoNao, "botao-secundario", janela);
        nao.setCancelButton(true);
        Button sim = criarBotao(textoSim, "botao-primario", janela);
        sim.setDefaultButton(true);
        sim.setOnAction(e -> {
            resposta[0] = true;
            janela.close();
        });

        exibir(janela, Tipo.PERGUNTA, null, titulo, mensagem, nao, sim);
        return resposta[0];
    }

    private static Stage criarJanela(Window dono) {
        Stage janela = new Stage(StageStyle.TRANSPARENT);
        janela.initOwner(dono);
        janela.initModality(Modality.WINDOW_MODAL);
        janela.setResizable(false);
        return janela;
    }

    private static Button criarBotao(String texto, String estilo, Stage janela) {
        Button botao = new Button(texto);
        botao.getStyleClass().add(estilo);
        botao.setOnAction(e -> janela.close());
        return botao;
    }

    private static void exibir(Stage janela, Tipo tipo, Pet pet, String titulo, String mensagem, Button... botoes) {
        Label t = new Label(titulo);
        t.getStyleClass().add("aviso-titulo");
        t.setWrapText(true);

        Label m = new Label(mensagem);
        m.getStyleClass().add("aviso-mensagem");
        m.setWrapText(true);

        HBox linhaBotoes = new HBox(12, botoes);
        linhaBotoes.setAlignment(Pos.CENTER);
        linhaBotoes.setPadding(new Insets(8, 0, 0, 0));

        VBox caixa = new VBox(12, criarTopo(tipo, pet), t, m, linhaBotoes);
        caixa.setAlignment(Pos.TOP_CENTER);
        caixa.setPrefWidth(400);
        caixa.setPadding(new Insets(28, 32, 24, 32));
        caixa.getStyleClass().addAll("aviso", "aviso-" + tipo.name().toLowerCase());

        StackPane raiz = new StackPane(caixa);
        raiz.setPadding(new Insets(16)); // espaço para a sombra
        raiz.setStyle("-fx-background-color: transparent;");

        Scene cena = new Scene(raiz);
        cena.setFill(Color.TRANSPARENT);
        if (janela.getOwner() != null && janela.getOwner().getScene() != null) {
            cena.getStylesheets().addAll(janela.getOwner().getScene().getStylesheets());
        }
        janela.setScene(cena);
        janela.showAndWait();
    }

    /** Foto redonda do pet ou, sem pet, um círculo colorido com um símbolo. */
    private static Node criarTopo(Tipo tipo, Pet pet) {
        if (pet != null) {
            ImagemPet foto = new ImagemPet(pet, TAMANHO_FOTO, TAMANHO_FOTO);
            foto.setClip(new Circle(TAMANHO_FOTO / 2, TAMANHO_FOTO / 2, TAMANHO_FOTO / 2));
            StackPane moldura = new StackPane(foto);
            moldura.getStyleClass().add("aviso-moldura");
            moldura.setMaxSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
            return moldura;
        }
        String simbolo = switch (tipo) {
            case SUCESSO -> "✓";
            case ATENCAO -> "!";
            case PERGUNTA -> "?";
        };
        Label icone = new Label(simbolo);
        icone.getStyleClass().add("aviso-icone");
        icone.setMinSize(64, 64);
        icone.setMaxSize(64, 64);
        icone.setAlignment(Pos.CENTER);
        return icone;
    }
}
