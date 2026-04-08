/**
 * Projeto 1 - Estrutura de Dados II - Huffman
 * Integrantes:
 * - Gabriel Ferreira RA: 10442043
 * - Gian Lucca Campanha Ribeiro RA: 10438361
 * - Henrique Cipola
 */

package com.mackenzie.utils;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Utilitário para escrita de bits individuais em um OutputStream.
 * Agrupa bits em bytes antes de descarregar no arquivo.
 */

public class BitOutputStream implements AutoCloseable {
    private final OutputStream out;
    private int buffer;
    private int count;

    public BitOutputStream(OutputStream out) {
        this.out = out;
        this.buffer = 0;
        this.count = 0;
    }

    // Escreve um bit (0 ou 1) no buffer.
    public void writeBit(int bit) throws IOException {
        buffer = (buffer << 1) | (bit & 1);
        count++;
        // Quando completa 8 bits, escreve o byte no arquivo
        if (count == 8) {
            out.write(buffer);
            count = 0;
            buffer = 0;
        }
    }

    @Override
    public void close() throws IOException {
        // Se restarem bits no buffer, completa com zeros à direita
        if (count > 0) {
            out.write(buffer << (8 - count));
        }
        out.close();
    }
}