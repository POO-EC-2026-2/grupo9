package com.grupo9.adocao;

import com.grupo9.adocao.model.Pet;
import com.grupo9.adocao.service.PetService;
import com.grupo9.adocao.service.PetServiceMemoria;
import com.grupo9.adocao.view.Aviso;
import com.grupo9.adocao.view.MenuLateral;
import com.grupo9.adocao.view.PerfilPet;
import com.grupo9.adocao.view.Rodape;
import com.grupo9.adocao.view.TelaEmConstrucao;
import com.grupo9.adocao.view.TelaInicialAdotante;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

/**
 * Ponto de entrada do front.
 * A janela tem menu lateral, rodapé e uma área central que troca de tela.
 */
public class App extends Application {

    private final PetService petService = new PetServiceMemoria();
    private final BorderPane layout = new BorderPane();
    private Stage janela;
    private MenuLateral menu;
    private Runnable telaAtual; // usada pelo botão Voltar do perfil

    @Override
    public void start(Stage stage) {
        janela = stage;

        menu = criarMenu();
        layout.setLeft(menu);
        layout.setBottom(new Rodape());

        Scene cena = new Scene(layout, 1180, 720);
        cena.getStylesheets().add(App.class.getResource("estilo.css").toExternalForm());

        stage.setTitle("Sistema de Adoção");
        stage.setMinWidth(1120);
        stage.setMinHeight(560);
        stage.setScene(cena);
        stage.show();
    }

    private MenuLateral criarMenu() {
        MenuLateral m = new MenuLateral("Sistema de Adoção");

        m.adicionarSecao("Escolher animal");
        Button todos = m.adicionarItem("Todos os pets", () -> mostrarPets(null));
        m.adicionarItem("Cachorros", () -> mostrarPets("Cachorro"));
        m.adicionarItem("Gatos", () -> mostrarPets("Gato"));

        m.adicionarSecao("Minha conta");
        m.adicionarItem("Minhas solicitações", this::mostrarSolicitacoes);
        m.adicionarItem("Editar cadastro", () -> mostrar(new TelaEmConstrucao(
                "Editar cadastro", "Em breve você poderá alterar seu nome, telefone e endereço aqui.")));

        m.adicionarEspaco();
        m.adicionarAcao("Sair da conta", this::sair);

        m.selecionar(todos);
        mostrarPets(null);
        return m;
    }

    /** especie == null mostra todos os pets. */
    private void mostrarPets(String especie) {
        telaAtual = () -> mostrarPets(especie);
        List<Pet> pets = petService.listarPetsDisponiveis().stream()
                .filter(p -> especie == null || p.getEspecie().equalsIgnoreCase(especie))
                .toList();

        if (especie == null) {
            mostrar(new TelaInicialAdotante(pets, this::abrirPerfil));
        } else {
            String plural = especie.equals("Gato") ? "Gatos" : "Cachorros";
            mostrar(new TelaInicialAdotante(plural + " para adoção",
                    "Clique em um pet para ver o perfil completo.",
                    "Nenhum pet desta espécie disponível no momento.", pets, this::abrirPerfil));
        }
    }

    private void mostrarSolicitacoes() {
        telaAtual = this::mostrarSolicitacoes;
        mostrar(new TelaInicialAdotante("Minhas solicitações",
                "Pets que você pediu para adotar. A ONG vai entrar em contato.",
                "Você ainda não pediu para adotar nenhum pet.",
                petService.listarSolicitacoes(), this::abrirPerfil));
    }

    private void abrirPerfil(Pet pet) {
        Runnable voltar = telaAtual;
        mostrar(new PerfilPet(pet, petService, () -> adotar(pet), voltar));
    }

    private void mostrar(Node tela) {
        layout.setCenter(tela);
    }

    private void adotar(Pet pet) {
        // "o Thor" / "a Mel"
        String artigo = pet.getSexo().equalsIgnoreCase("Fêmea") ? "a" : "o";
        String nome = artigo + " " + pet.getNome();

        if (petService.solicitarAdocao(pet)) {
            Aviso.mostrar(janela, Aviso.Tipo.SUCESSO, pet,
                    "Pedido enviado!",
                    "Que alegria! Seu pedido para adotar " + nome + " foi enviado. "
                            + "A ONG vai analisar e entrar em contato com você em breve.",
                    "Oba!");
        } else {
            Aviso.mostrar(janela, Aviso.Tipo.ATENCAO, pet,
                    "Você já pediu para adotar " + nome,
                    "Seu pedido está em análise. Você pode acompanhar em \"Minhas solicitações\", no menu ao lado.",
                    "Entendi");
        }
    }

    private void sair() {
        boolean sair = Aviso.confirmar(janela, "Já vai?",
                "Deseja mesmo sair da conta? Os pets vão sentir sua falta!", "Sair", "Ficar");
        if (sair) {
            // Quando houver tela de login, voltar para ela em vez de fechar.
            Platform.exit();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
