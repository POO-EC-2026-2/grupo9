package com.grupo9.adocao.service;

import com.grupo9.adocao.model.Pet;

import java.util.List;

/**
 * Contrato entre o front e a fonte de dados dos pets.
 * Hoje a implementação é em memória; depois o backend implementa esta interface.
 */
public interface PetService {

    List<Pet> listarPetsDisponiveis();

    /** Registra o pedido de adoção do pet. Retorna true se o pedido foi aceito para análise. */
    boolean solicitarAdocao(Pet pet);
}
