/**
 * Projeto 1 - Estrutura de Dados II - Huffman
 * Integrantes:
 * - Gabriel Ferreira RA: 10442043
 * - Gian Lucca Campanha Ribeiro RA: 10438361
 * - Enrique Cipolla Martins - RA: 10427834
 */

package com.mackenzie.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Utilitário para leitura de bits individuais a partir de um InputStream.
 */

public class BitInputStream implements AutoCloseable {
    private final InputStream in;
    private int buffer;
    private int count;

    public BitInputStream(InputStream in) {
        this.in = in;
        this.count = 0;
    }

    /**
     * Lê o próximo bit do fluxo.
     * @return 0 ou 1, ou -1 se atingir o fim do arquivo.
     */

    public int readBit() throws IOException {
        if (count == 0) {
            buffer = in.read();
            if (buffer == -1) return -1;
            count = 8;
        }
        count--;
        
        // Extrai o bit mais significativo do buffer
        return (buffer >> count) & 1;
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}