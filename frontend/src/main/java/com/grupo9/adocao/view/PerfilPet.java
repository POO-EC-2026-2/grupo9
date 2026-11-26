package com.grupo9.adocao.view;

import com.grupo9.adocao.model.Pet;
import com.grupo9.adocao.service.PetService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Perfil do pet:
 *  - galeria de fotos + dados principais + botões Adotar e Voltar
 *  - informações importantes (vacinas, castração, convivência...)
 *  - espaço para mandar perguntas sobre o pet para a ONG
 */
public class PerfilPet extends ScrollPane {

    private final Pet pet;
    private final PetService petService;
    private final VBox listaPerguntas = new VBox(8);

    public PerfilPet(Pet pet, PetService petService, Runnable aoAdotar, Runnable aoVoltar) {
        this.pet = pet;
        this.petService = petService;
        getStyleClass().addAll("tela", "rolagem");
        setFitToWidth(true);

        VBox pagina = new VBox(24,
                criarTopo(aoAdotar, aoVoltar),
                criarInformacoesImportantes(),
                criarPerguntas());
        pagina.setMaxWidth(940);
        pagina.setPadding(new Insets(32, 32, 40, 32));

        StackPane centralizar = new StackPane(pagina);
        centralizar.setAlignment(Pos.TOP_CENTER);
        centralizar.getStyleClass().add("tela");
        setContent(centralizar);
    }

    // ---------- Topo: fotos, dados e botões ----------

    private Node criarTopo(Runnable aoAdotar, Runnable aoVoltar) {
        Label nome = new Label(pet.getNome());
        nome.getStyleClass().add("titulo");

        GridPane dados = new GridPane();
        dados.setHgap(16);
        dados.setVgap(8);
        adicionarLinha(dados, 0, "Espécie", pet.getEspecie());
        adicionarLinha(dados, 1, "Sexo", pet.getSexo());
        adicionarLinha(dados, 2, "Porte", pet.getPorte());
        adicionarLinha(dados, 3, "Idade", pet.getIdade());

        Label tituloDescricao = new Label("Sobre");
        tituloDescricao.getStyleClass().add("rotulo");
        Label descricao = new Label(pet.getDescricao());
        descricao.setWrapText(true);
        descricao.getStyleClass().add("descricao");

        Button adotar = new Button("Adotar");
        adotar.getStyleClass().add("botao-primario");
        adotar.setOnAction(e -> aoAdotar.run());

        Button voltar = new Button("Voltar");
        voltar.getStyleClass().add("botao-secundario");
        voltar.setCancelButton(true);
        voltar.setOnAction(e -> aoVoltar.run());

        HBox botoes = new HBox(12, voltar, adotar);
        botoes.setAlignment(Pos.CENTER_LEFT);
        botoes.setPadding(new Insets(16, 0, 0, 0));

        VBox informacoes = new VBox(12, nome, dados, tituloDescricao, descricao, botoes);
        HBox.setHgrow(informacoes, Priority.ALWAYS);

        HBox topo = new HBox(32, new GaleriaFotos(pet, 420, 320), informacoes);
        topo.setAlignment(Pos.TOP_LEFT);
        return topo;
    }

    private static void adicionarLinha(GridPane grade, int linha, String rotulo, String valor) {
        Label r = new Label(rotulo);
        r.getStyleClass().add("rotulo");
        Label v = new Label(valor);
        v.getStyleClass().add("valor");
        grade.add(r, 0, linha);
        grade.add(v, 1, linha);
    }

    // ---------- Informações importantes ----------

    private Node criarInformacoesImportantes() {
        GridPane itens = new GridPane();
        itens.setHgap(40);
        itens.setVgap(12);
        adicionarItem(itens, 0, 0, "Castrado(a)", pet.isCastrado());
        adicionarItem(itens, 1, 0, "Vermifugado(a)", pet.isVermifugado());
        adicionarItem(itens, 0, 1, "Convive bem com crianças", pet.isConviveComCriancas());
        adicionarItem(itens, 1, 1, "Convive bem com outros animais", pet.isConviveComOutrosAnimais());

        Label tituloVacinas = new Label("Vacinas tomadas");
        tituloVacinas.getStyleClass().add("rotulo");
        FlowPane vacinas = new FlowPane(8, 8);
        if (pet.getVacinas().isEmpty()) {
            vacinas.getChildren().add(criarSelo("Nenhuma vacina registrada", "selo-nao"));
        } else {
            for (String vacina : pet.getVacinas()) {
                vacinas.getChildren().add(criarSelo(vacina, "selo-vacina"));
            }
        }

        VBox secao = criarSecao("Informações importantes", itens, tituloVacinas, vacinas);

        if (!pet.getObservacoes().isBlank()) {
            Label tituloObs = new Label("Bom saber");
            tituloObs.getStyleClass().add("rotulo");
            Label obs = new Label(pet.getObservacoes());
            obs.setWrapText(true);
            obs.getStyleClass().add("descricao");
            secao.getChildren().addAll(tituloObs, obs);
        }
        return secao;
    }

    private static void adicionarItem(GridPane grade, int coluna, int linha, String texto, boolean valor) {
        Label rotulo = new Label(texto);
        rotulo.getStyleClass().add("valor");
        Label selo = criarSelo(valor ? "Sim" : "Não", valor ? "selo-sim" : "selo-nao");
        selo.setMinWidth(52);
        selo.setAlignment(Pos.CENTER);
        HBox item = new HBox(10, selo, rotulo);
        item.setAlignment(Pos.CENTER_LEFT);
        grade.add(item, coluna, linha);
    }

    private static Label criarSelo(String texto, String estilo) {
        Label selo = new Label(texto);
        selo.getStyleClass().addAll("selo", estilo);
        return selo;
    }

    // ---------- Perguntas ----------

    private Node criarPerguntas() {
        Label dica = new Label("Quer saber mais sobre " + artigo() + " " + pet.getNome()
                + "? Mande sua pergunta e a ONG responde.");
        dica.getStyleClass().add("subtitulo");
        dica.setWrapText(true);

        TextArea campo = new TextArea();
        campo.setPromptText("Ex.: " + (artigo().equals("a") ? "Ela" : "Ele") + " precisa de alguma ração especial?");
        campo.setPrefRowCount(3);
        campo.setWrapText(true);
        campo.getStyleClass().add("campo-pergunta");

        Button enviar = new Button("Enviar pergunta");
        enviar.getStyleClass().addAll("botao-primario", "botao-largo");
        enviar.setOnAction(e -> enviarPergunta(campo));

        HBox linhaBotao = new HBox(enviar);
        linhaBotao.setAlignment(Pos.CENTER_RIGHT);

        atualizarPerguntas();
        return criarSecao("Ficou com alguma dúvida?", dica, campo, linhaBotao, listaPerguntas);
    }

    private void enviarPergunta(TextArea campo) {
        String texto = campo.getText().trim();
        if (texto.isEmpty()) {
            Aviso.mostrar(getScene().getWindow(), Aviso.Tipo.ATENCAO, null,
                    "Escreva sua pergunta",
                    "O campo está vazio. Escreva o que você quer saber sobre " + artigo() + " " + pet.getNome() + ".",
                    "Ok");
            return;
        }
        petService.enviarPergunta(pet, texto);
        campo.clear();
        atualizarPerguntas();
        Aviso.mostrar(getScene().getWindow(), Aviso.Tipo.SUCESSO, pet,
                "Pergunta enviada!",
                "A ONG vai responder em breve. Você pode ver suas perguntas logo abaixo.",
                "Ok");
    }

    private void atualizarPerguntas() {
        listaPerguntas.getChildren().clear();
        var perguntas = petService.listarPerguntas(pet);
        if (perguntas.isEmpty()) {
            return;
        }
        Label titulo = new Label("Suas perguntas");
        titulo.getStyleClass().add("rotulo");
        listaPerguntas.getChildren().add(titulo);
        for (String pergunta : perguntas) {
            Label texto = new Label(pergunta);
            texto.setWrapText(true);
            texto.getStyleClass().add("descricao");
            Label status = new Label("Aguardando resposta da ONG");
            status.getStyleClass().addAll("selo", "selo-aguardando");
            VBox item = new VBox(6, texto, status);
            item.getStyleClass().add("pergunta-item");
            listaPerguntas.getChildren().add(item);
        }
    }

    // ---------- Utilitários ----------

    private static VBox criarSecao(String titulo, Node... conteudo) {
        Label t = new Label(titulo);
        t.getStyleClass().add("secao-titulo");
        VBox secao = new VBox(14, t);
        secao.getChildren().addAll(conteudo);
        secao.getStyleClass().add("secao");
        return secao;
    }

    /** "o" para macho, "a" para fêmea. */
    private String artigo() {
        return pet.getSexo().equalsIgnoreCase("Fêmea") ? "a" : "o";
    }
}
