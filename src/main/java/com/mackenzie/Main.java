/**
 * Projeto 1 - Estrutura de Dados II - Huffman
 * Integrantes:
 * - Gabriel Ferreira RA: 10442043
 * - Gian Lucca Campanha Ribeiro RA: 10438361
 * - Enrique Cipolla Martins - RA: 10427834
 */

package com.mackenzie;

import com.mackenzie.controller.HuffmanController;

/**
 * Ponto de entrada
 */

public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Uso: java -jar huffman.jar c <origem> <destino> (Comprimir)");
            System.out.println("     java -jar huffman.jar d <origem> <destino> (Descomprimir)");
            return;
        }
        new HuffmanController().executar(args[0], args[1], args[2]);
    }
}