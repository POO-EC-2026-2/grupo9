package com.grupo9.adocao;

import com.grupo9.adocao.model.Pet;
import com.grupo9.adocao.service.PetService;
import com.grupo9.adocao.service.PetServiceMemoria;
import com.grupo9.adocao.view.PerfilPet;
import com.grupo9.adocao.view.TelaInicialAdotante;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/** Ponto de entrada do front. Controla a troca entre a tela inicial e o perfil do pet. */
public class App extends Application {

    private final PetService petService = new PetServiceMemoria();
    private Scene cena;
    private Stage janela;

    @Override
    public void start(Stage stage) {
        janela = stage;
        cena = new Scene(criarTelaInicial(), 900, 640);
        cena.getStylesheets().add(App.class.getResource("estilo.css").toExternalForm());

        stage.setTitle("Sistema de Adoção");
        stage.setMinWidth(760);
        stage.setMinHeight(480);
        stage.setScene(cena);
        stage.show();
    }

    private Parent criarTelaInicial() {
        return new TelaInicialAdotante(petService.listarPetsDisponiveis(), this::abrirPerfil);
    }

    private void abrirPerfil(Pet pet) {
        cena.setRoot(new PerfilPet(pet, () -> adotar(pet), this::voltarParaInicio));
    }

    private void voltarParaInicio() {
        cena.setRoot(criarTelaInicial());
    }

    private void adotar(Pet pet) {
        Alert alerta;
        if (petService.solicitarAdocao(pet)) {
            alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText("Solicitação enviada!");
            alerta.setContentText("Seu pedido para adotar " + pet.getNome()
                    + " foi enviado. A ONG vai analisar e entrar em contato.");
        } else {
            alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("Solicitação já existe");
            alerta.setContentText("Você já pediu para adotar " + pet.getNome() + ".");
        }
        alerta.initOwner(janela);
        alerta.setTitle("Adoção");
        alerta.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
