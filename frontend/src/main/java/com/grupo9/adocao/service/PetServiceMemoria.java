package com.grupo9.adocao.service;

import com.grupo9.adocao.model.Pet;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/** Dados de exemplo para testar as telas enquanto o backend não está pronto. */
public class PetServiceMemoria implements PetService {

    private final List<Pet> pets = new ArrayList<>();
    private final Set<Integer> solicitados = new LinkedHashSet<>();

    public PetServiceMemoria() {
        pets.add(new Pet(1, "Thor", "Cachorro", "Macho", "Grande", "3 anos",
                "Thor é brincalhão e muito carinhoso. Adora correr e se dá bem com crianças.", "/imagens/thor.jpg"));
        pets.add(new Pet(2, "Mel", "Gato", "Fêmea", "Pequeno", "1 ano",
                "Mel é calma, gosta de colo e de dormir no sol. Já está vacinada e castrada.", "/imagens/mel.jpg"));
        pets.add(new Pet(3, "Bidu", "Cachorro", "Macho", "Médio", "5 anos",
                "Bidu foi resgatado das ruas e hoje é super dócil. Ideal para quem tem quintal.", "/imagens/bidu.jpg"));
        pets.add(new Pet(4, "Luna", "Gato", "Fêmea", "Pequeno", "6 meses",
                "Luna é curiosa e cheia de energia. Vai alegrar a sua casa!", "/imagens/luna.jpg"));
        pets.add(new Pet(5, "Pipoca", "Cachorro", "Fêmea", "Pequeno", "2 anos",
                "Pipoca é companheira e se adapta bem a apartamento.", "/imagens/pipoca.jpg"));
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
}
