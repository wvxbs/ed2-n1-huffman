/**
 * Projeto 1 - Estrutura de Dados II - Huffman
 * Integrantes:
 * - Gabriel Ferreira RA: 10442043
 * - Gian Lucca Campanha Ribeiro RA: 10438361
 * - Enrique Cipolla Martins - RA: 10427834
 */

package com.mackenzie.model;

/**
 * Representa um nó da Árvore de Huffman.
 * Implementa Comparable para ser utilizado na Fila de Prioridade (Min-Heap).
 */

public class No implements Comparable<No> {
    public char caractere;
    public int frequencia;
    public No esquerda, direita;

    // Construtor para nós folha (contêm caracteres)
    public No(char caractere, int frequencia) {
        this.caractere = caractere;
        this.frequencia = frequencia;
    }

    // Construtor para nós internos (soma das frequências dos filhos)
    public No(int frequencia, No esquerda, No direita) {
        this.frequencia = frequencia;
        this.esquerda = esquerda;
        this.direita = direita;
    }

    // Verifica se o nó é uma folha
    public boolean isFolha() {
        return (this.esquerda == null && this.direita == null);
    }

    @Override
    public int compareTo(No outroNo) {
        // Retorna a diferença de frequências para o Min-Heap
        return this.frequencia - outroNo.frequencia;
    }

    @Override
    public String toString() {
        if (isFolha()) return "No('" + caractere + "', " + frequencia + ")";
        return "No(interno, " + frequencia + ")";
    }
}