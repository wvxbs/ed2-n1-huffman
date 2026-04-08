/**
 * Projeto 1 - Estrutura de Dados II - Huffman
 * Integrantes:
 * - Gabriel Ferreira RA: 10442043
 * - Gian Lucca Campanha Ribeiro RA: 10438361
 * - Enrique Cipolla Martins - RA: 10427834
 */

package com.mackenzie.view;

import com.mackenzie.model.No;
import java.util.ArrayList;

/**
 * Classe responsável por imprimir as saídas
 */

public class ConsoleView {
    public void imprimirFrequencias(int[] t) {
        System.out.println("ETAPA 1: Tabela de Frequencia de Caracteres");
        for (int i = 0; i < 256; i++)
            if (t[i] > 0) System.out.println("Caractere '" + (char)i + "' (ASCII: " + i + "): " + t[i]);
        System.out.println();
    }

    public void imprimirHeap(ArrayList<No> h) {
        System.out.println("ETAPA 2: Min-Heap Inicial (Vetor)");
        System.out.println(h);
        System.out.println();
    }

    public void imprimirArvore(No r) {
        System.out.println("ETAPA 3: Arvore de Huffman");
        imprimirArvRec(r, "");
        System.out.println();
    }

    private void imprimirArvRec(No n, String p) {
        if (n == null) return;
        if (n.isFolha()) System.out.println(p + "('" + n.caractere + "', " + n.frequencia + ")");
        else {
            System.out.println(p + "(RAIZ, " + n.frequencia + ")");
            imprimirArvRec(n.esquerda, p + "  ");
            imprimirArvRec(n.direita, p + "  ");
        }
    }

    public void imprimirCodigos(String[] t) {
        System.out.println("ETAPA 4: Tabela de Codigos de Huffman");
        for (int i = 0; i < 256; i++)
            if (t[i] != null) System.out.println("Caractere '" + (char)i + "': " + t[i]);
        System.out.println();
    }

    public void imprimirResumo(long bOrig, long bitsComp) {
        System.out.println("ETAPA 5: Resumo da Compressao");
        long bComp = (bitsComp + 7) / 8;
        double taxa = (1.0 - ((double)bitsComp / (bOrig * 8))) * 100.0;
        System.out.printf("Tamanho original....: %d bits (%d bytes)\n", bOrig * 8, bOrig);
        System.out.printf("Tamanho comprimido..: %d bits (%d bytes)\n", bitsComp, bComp);
        System.out.printf("Taxa de compressao..: %.2f%%\n\n", taxa);
    }
}