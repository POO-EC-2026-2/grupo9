package com.grupo9.adocao.view;

import com.grupo9.adocao.model.Pet;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;

/**
 * Área de imagem do pet. Se o pet não tiver imagem (ou ela não carregar),
 * mostra um placeholder com a inicial do nome.
 *
 * O caminho da imagem pode ser uma URL (http:, file:) ou um recurso do
 * classpath, por exemplo "/imagens/thor.jpg" em src/main/resources/imagens.
 */
public class ImagemPet extends StackPane {

    public ImagemPet(Pet pet, double largura, double altura) {
        setPrefSize(largura, altura);
        setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        setAlignment(Pos.CENTER);
        getStyleClass().add("imagem-pet");

        Image imagem = carregar(pet.getCaminhoImagem());
        if (imagem != null) {
            ImageView view = new ImageView(imagem);
            view.setFitWidth(largura);
            view.setFitHeight(altura);
            view.setPreserveRatio(false);
            view.setSmooth(true);
            Rectangle cantos = new Rectangle(largura, altura);
            cantos.setArcWidth(16);
            cantos.setArcHeight(16);
            view.setClip(cantos);
            getChildren().add(view);
        } else {
            getStyleClass().add("imagem-placeholder");
            Label inicial = new Label(pet.getNome().substring(0, 1).toUpperCase());
            inicial.getStyleClass().add("imagem-inicial");
            inicial.setStyle("-fx-font-size: " + (int) (altura * 0.4) + "px;");
            getChildren().add(inicial);
        }
    }

    private static Image carregar(String caminho) {
        if (caminho == null || caminho.isBlank()) {
            return null;
        }
        String url = caminho;
        if (!caminho.contains(":")) {
            URL recurso = ImagemPet.class.getResource(caminho);
            if (recurso == null) {
                return null;
            }
            url = recurso.toExternalForm();
        }
        Image imagem = new Image(url, false);
        return imagem.isError() ? null : imagem;
    }
}
