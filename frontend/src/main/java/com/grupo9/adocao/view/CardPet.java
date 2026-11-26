package com.grupo9.adocao.view;

import com.grupo9.adocao.model.Pet;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

/** Bloco da tela inicial: imagem + informações resumidas. Clicar abre o perfil. */
public class CardPet extends VBox {

    static final double LARGURA_IMAGEM = 240;
    static final double ALTURA_IMAGEM = 180;

    public CardPet(Pet pet, Consumer<Pet> aoClicar) {
        super(8);
        getStyleClass().add("card-pet");
        setCursor(Cursor.HAND);

        Label nome = new Label(pet.getNome());
        nome.getStyleClass().add("card-nome");

        Label resumo = new Label(pet.getEspecie() + " • " + pet.getSexo() + " • " + pet.getPorte());
        resumo.getStyleClass().add("card-info");

        Label idade = new Label(pet.getIdade());
        idade.getStyleClass().add("card-info");

        getChildren().addAll(new ImagemPet(pet, LARGURA_IMAGEM, ALTURA_IMAGEM), nome, resumo, idade);

        setOnMouseClicked(e -> aoClicar.accept(pet));
    }
}
