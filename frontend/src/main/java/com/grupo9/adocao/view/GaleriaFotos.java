package com.grupo9.adocao.view;

import com.grupo9.adocao.model.Pet;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.List;

/**
 * Galeria com as fotos do pet: setinhas para passar e bolinhas embaixo
 * mostrando qual foto está aberta (dá para clicar nas bolinhas também).
 */
public class GaleriaFotos extends VBox {

    private final Pet pet;
    private final List<String> fotos;
    private final double largura;
    private final double altura;
    private final StackPane quadro = new StackPane();
    private final List<Circle> bolinhas = new ArrayList<>();
    private int atual = 0;

    public GaleriaFotos(Pet pet, double largura, double altura) {
        super(10);
        this.pet = pet;
        this.fotos = pet.getFotos();
        this.largura = largura;
        this.altura = altura;
        setAlignment(Pos.TOP_CENTER);

        StackPane area = new StackPane(quadro);
        area.setMaxSize(largura, altura);

        if (fotos.size() > 1) {
            Button anterior = criarSeta(true);
            anterior.setOnAction(e -> mostrar(atual - 1));
            StackPane.setAlignment(anterior, Pos.CENTER_LEFT);
            StackPane.setMargin(anterior, new Insets(0, 0, 0, 10));

            Button proxima = criarSeta(false);
            proxima.setOnAction(e -> mostrar(atual + 1));
            StackPane.setAlignment(proxima, Pos.CENTER_RIGHT);
            StackPane.setMargin(proxima, new Insets(0, 10, 0, 0));

            area.getChildren().addAll(anterior, proxima);
        }
        getChildren().add(area);

        if (fotos.size() > 1) {
            HBox indicador = new HBox(8);
            indicador.setAlignment(Pos.CENTER);
            for (int i = 0; i < fotos.size(); i++) {
                int indice = i;
                Circle bolinha = new Circle(5);
                bolinha.getStyleClass().add("galeria-bolinha");
                bolinha.setOnMouseClicked(e -> mostrar(indice));
                bolinhas.add(bolinha);
                indicador.getChildren().add(bolinha);
            }
            getChildren().add(indicador);
        }

        mostrar(0);
    }

    /** Mostra a foto do índice informado; passa do fim para o começo e vice-versa. */
    private void mostrar(int indice) {
        if (fotos.isEmpty()) {
            quadro.getChildren().setAll(new ImagemPet(pet, largura, altura));
            return;
        }
        atual = (indice + fotos.size()) % fotos.size();
        quadro.getChildren().setAll(new ImagemPet(pet, fotos.get(atual), largura, altura));
        for (int i = 0; i < bolinhas.size(); i++) {
            bolinhas.get(i).getStyleClass().remove("galeria-bolinha-ativa");
            if (i == atual) {
                bolinhas.get(i).getStyleClass().add("galeria-bolinha-ativa");
            }
        }
    }

    private static Button criarSeta(boolean esquerda) {
        Polygon triangulo = esquerda
                ? new Polygon(8, 0, 0, 7, 8, 14)
                : new Polygon(0, 0, 8, 7, 0, 14);
        triangulo.getStyleClass().add("galeria-seta-icone");

        Button seta = new Button();
        seta.setGraphic(triangulo);
        seta.getStyleClass().add("galeria-seta");
        return seta;
    }
}
