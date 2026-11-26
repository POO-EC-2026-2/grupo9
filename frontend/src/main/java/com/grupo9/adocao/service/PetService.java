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

    /** Pets que o adotante já pediu para adotar. */
    List<Pet> listarSolicitacoes();

    /** Envia uma pergunta do adotante sobre o pet para a ONG. */
    void enviarPergunta(Pet pet, String pergunta);

    /** Perguntas que o adotante já fez sobre o pet. */
    List<String> listarPerguntas(Pet pet);
}
