/**
 * Projeto 1 - Estrutura de Dados II - Huffman
 * Integrantes:
 * - Gabriel Ferreira RA: 10442043
 * - Gian Lucca Campanha Ribeiro RA: 10438361
 * - Enrique Cipolla Martins - RA: 10427834
 */

package com.mackenzie.controller;

import com.mackenzie.model.Huffman;
import com.mackenzie.view.ConsoleView;
import java.io.File;

/**
 * Controla o fluxo de execução e faz as medições de performance
 */

public class HuffmanController {
    private Huffman model = new Huffman();
    private ConsoleView view = new ConsoleView();

    public void executar(String modo, String in, String out) {
        try {
            long t1 = System.nanoTime(); // Medição de tempo
            if ("c".equals(modo)) {
                model.analisarFrequencia(in);
                view.imprimirFrequencias(model.getTabelaFrequencia());
                view.imprimirHeap(model.montarHeapInicial());
                model.construirArvore();
                view.imprimirArvore(model.getArvore());
                model.gerarTabelaCodigos();
                view.imprimirCodigos(model.getTabelaCodigos());
                model.comprimir(in, out);

                // Exibe resumo baseado no arquivo de saída gerado
                view.imprimirResumo(new File(in).length(), new File(out).length() * 8);
            } else if ("d".equals(modo)) {
                model.descomprimir(in, out);
            }
            double total = (System.nanoTime() - t1) / 1_000_000.0;
            System.out.printf("Tempo total de execução: %.2f ms\n", total);
        } catch (Exception e) {
            System.err.println("Erro durante a execução: " + e.getMessage());
        }
    }
}