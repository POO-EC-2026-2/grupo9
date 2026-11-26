
package com.grupo9.adocao.service;

import com.grupo9.adocao.model.Pet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** Dados de exemplo para testar as telas enquanto o backend não está pronto. */
public class PetServiceMemoria implements PetService {

    private final List<Pet> pets = new ArrayList<>();
    private final Set<Integer> solicitados = new LinkedHashSet<>();
    private final Map<Integer, List<String>> perguntas = new HashMap<>();

    public PetServiceMemoria() {
        Pet thor = new Pet(1, "Thor", "Cachorro", "Macho", "Grande", "3 anos",
                "Thor é brincalhão e muito carinhoso. Adora correr, buscar petiscos no ar e se dá bem com crianças.",
                "/imagens/thor-1.jpg", "/imagens/thor-2.jpg", "/imagens/thor-3.jpg");
        thor.setVacinas("V10", "Antirrábica", "Gripe canina");
        thor.setCastrado(true);
        thor.setVermifugado(true);
        thor.setConviveComCriancas(true);
        thor.setConviveComOutrosAnimais(true);
        thor.setObservacoes("Precisa de passeios diários e de espaço para gastar energia.");
        pets.add(thor);

        Pet mel = new Pet(2, "Mel", "Gato", "Fêmea", "Pequeno", "4 anos",
                "Mel é calma, gosta de colo e de tirar longas sonecas no sol.",
                "/imagens/mel-1.jpg", "/imagens/mel-2.jpg");
        mel.setVacinas("V4 (quádrupla felina)", "Antirrábica");
        mel.setCastrado(true);
        mel.setVermifugado(true);
        mel.setConviveComCriancas(true);
        mel.setConviveComOutrosAnimais(false);
        mel.setObservacoes("Prefere ser o único pet da casa.");
        pets.add(mel);

        Pet bidu = new Pet(3, "Bidu", "Cachorro", "Macho", "Médio", "5 anos",
                "Bidu foi resgatado das ruas e hoje é super dócil. Ideal para quem tem quintal.",
                "/imagens/bidu-1.jpg", "/imagens/bidu-2.jpg");
        bidu.setVacinas("V10", "Antirrábica");
        bidu.setCastrado(true);
        bidu.setVermifugado(true);
        bidu.setConviveComCriancas(true);
        bidu.setConviveComOutrosAnimais(true);
        bidu.setObservacoes("Tem um pouco de medo de barulhos altos, como fogos de artifício.");
        pets.add(bidu);

        Pet luna = new Pet(4, "Luna", "Gato", "Fêmea", "Pequeno", "6 meses",
                "Luna é curiosa e cheia de energia. Vai alegrar a sua casa!",
                "/imagens/luna-1.jpg", "/imagens/luna-2.jpg", "/imagens/luna-3.jpg");
        luna.setVacinas("V4 (1ª dose)");
        luna.setCastrado(false);
        luna.setVermifugado(true);
        luna.setConviveComCriancas(true);
        luna.setConviveComOutrosAnimais(true);
        luna.setObservacoes("Ainda vai tomar o reforço da vacina. A castração já está agendada.");
        pets.add(luna);

        Pet pipoca = new Pet(5, "Pipoca", "Cachorro", "Fêmea", "Pequeno", "7 anos",
                "Pipoca é companheira e tranquila, adora um cochilo e se adapta bem a apartamento.",
                "/imagens/pipoca-1.jpg", "/imagens/pipoca-2.jpg", "/imagens/pipoca-3.jpg");
        pipoca.setVacinas("V10", "Antirrábica");
        pipoca.setCastrado(true);
        pipoca.setVermifugado(true);
        pipoca.setConviveComCriancas(true);
        pipoca.setConviveComOutrosAnimais(true);
        pipoca.setObservacoes("Toma um remédio para as articulações uma vez por dia.");
        pets.add(pipoca);
    }

    @Override
    public List<Pet> listarPetsDisponiveis() {
        return List.copyOf(pets);
    }

    @Override
    public boolean solicitarAdocao(Pet pet) {
        return solicitados.add(pet.getId());
    }

    @Override
    public List<Pet> listarSolicitacoes() {
        List<Pet> resultado = new ArrayList<>();
        for (Integer id : solicitados) {
            for (Pet pet : pets) {
                if (pet.getId() == id) {
                    resultado.add(pet);
                }
            }
        }
        return resultado;
    }

    @Override
    public void enviarPergunta(Pet pet, String pergunta) {
        perguntas.computeIfAbsent(pet.getId(), id -> new ArrayList<>()).add(pergunta);
    }

    @Override
    public List<String> listarPerguntas(Pet pet) {
        return List.copyOf(perguntas.getOrDefault(pet.getId(), List.of()));
    }
}
