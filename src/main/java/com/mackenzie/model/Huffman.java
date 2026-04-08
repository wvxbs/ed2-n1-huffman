/**
 * Projeto 1 - Estrutura de Dados II - Huffman
 * Integrantes:
 * - Gabriel Ferreira RA: 10442043
 * - Gian Lucca Campanha Ribeiro RA: 10438361
 * - Henrique Cipola
 */

package com.mackenzie.model;

import java.io.*;
import java.util.ArrayList;
import com.mackenzie.utils.*;

/**
 * Classe principal da lógica do algoritmo de Huffman.
 */

public class Huffman {
    private int[] tabelaFrequencia = new int[256];
    private String[] tabelaCodigos = new String[256];
    private No arvoreHuffman;

    // Getters
    public int[] getTabelaFrequencia() { return tabelaFrequencia; }

    public String[] getTabelaCodigos() { return tabelaCodigos; }

    public No getArvore() { return arvoreHuffman; }

    // Passo 1: Analisa a frequência de cada byte no arquivo
    public void analisarFrequencia(String caminho) throws IOException {
        try (FileInputStream fis = new FileInputStream(caminho)) {
            int b;
            while ((b = fis.read()) != -1) tabelaFrequencia[b]++;
        }
    }

    // Retorna o estado do Heap antes da construção da árvore para a Etapa 2
    public ArrayList<No> montarHeapInicial() {
        MinHeap mh = new MinHeap();
        for (int i = 0; i < 256; i++) {
            if (tabelaFrequencia[i] > 0) mh.inserir(new No((char) i, tabelaFrequencia[i]));
        }
        return mh.getDados();
    }

    // Passo 3: Constrói a árvore de Huffman usando o Min-Heap
    public void construirArvore() {
        MinHeap mh = new MinHeap();
        for (int i = 0; i < 256; i++) {
            if (tabelaFrequencia[i] > 0) mh.inserir(new No((char) i, tabelaFrequencia[i]));
        }
        while (mh.size() > 1) {
            No e = mh.removerMin();
            No d = mh.removerMin();
            // Novo nó interno com a soma das frequências
            mh.inserir(new No(e.frequencia + d.frequencia, e, d));
        }
        this.arvoreHuffman = mh.removerMin();
    }

    // Passo 4: Percorre a árvore para gerar os códigos binários
    public void gerarTabelaCodigos() {
        gerarRecursivo(arvoreHuffman, "");
    }

    private void gerarRecursivo(No n, String c) {
        if (n == null) return;
        if (n.isFolha()) tabelaCodigos[n.caractere] = c;
        else {
            gerarRecursivo(n.esquerda, c + "0");
            gerarRecursivo(n.direita, c + "1");
        }
    }

    // Passo 5: Grava cabeçalho e dados comprimidos
    public void comprimir(String in, String out) throws IOException {
        try (FileInputStream fis = new FileInputStream(in);
             ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(out))) {
            oos.writeObject(tabelaFrequencia); // Salva tabela de frequências como cabeçalho

            StringBuilder sb = new StringBuilder();
            int b;
            while ((b = fis.read()) != -1) sb.append(tabelaCodigos[b]);

            oos.writeInt(sb.length()); // Salva total de bits para evitar lixo no final do arquivo

            try (BitOutputStream bos = new BitOutputStream(oos)) {
                for (int i = 0; i < sb.length(); i++) bos.writeBit(sb.charAt(i) - '0');
            }
        }
    }

    // Parte 2: Reconstroi a árvore e realiza o percurso guiado
    public void descomprimir(String in, String out) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(in));
             FileOutputStream fos = new FileOutputStream(out)) {

            this.tabelaFrequencia = (int[]) ois.readObject();
            construirArvore(); // Reconstroi a árvore a partir do cabeçalho

            int totalBits = ois.readInt();
            No atual = arvoreHuffman;

            try (BitInputStream bis = new BitInputStream(ois)) {
                for (int i = 0; i < totalBits; i++) {
                    int bit = bis.readBit();
                    // Percurso guiado: 0 = esq, 1 = dir
                    atual = (bit == 0) ? atual.esquerda : atual.direita;

                    if (atual.isFolha()) {
                        fos.write(atual.caractere);
                        atual = arvoreHuffman; // Volta para a raiz após achar um caractere
                    }
                }
            }
        }
    }
}