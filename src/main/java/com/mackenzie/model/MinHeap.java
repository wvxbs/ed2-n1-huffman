/**
 * Projeto 1 - Estrutura de Dados II - Huffman
 * Integrantes:
 * - Gabriel Ferreira RA: 10442043
 * - Gian Lucca Campanha Ribeiro RA: 10438361
 * - Enrique Cipolla Martins - RA: 10427834
 */

package com.mackenzie.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Implementação manual de uma Fila de Prioridades usando Min-Heap
 */

public class MinHeap {
    private ArrayList<No> heap = new ArrayList<>();

    // Mantém a propriedade de min-heap subindo o elemento
    private void heapifyUp(int index) {
        int pai = (index - 1) / 2;
        if (index > 0 && heap.get(index).compareTo(heap.get(pai)) < 0) {
            Collections.swap(heap, index, pai);
            heapifyUp(pai);
        }
    }

    // Mantém a propriedade de min-heap descendo o elemento
    private void heapifyDown(int index) {
        int esq = 2 * index + 1;
        int dir = 2 * index + 2;
        int menor = index;

        if (esq < heap.size() && heap.get(esq).compareTo(heap.get(menor)) < 0) menor = esq;
        if (dir < heap.size() && heap.get(dir).compareTo(heap.get(menor)) < 0) menor = dir;

        if (menor != index) {
            Collections.swap(heap, index, menor);
            heapifyDown(menor);
        }
    }

    public void inserir(No no) {
        heap.add(no);
        heapifyUp(heap.size() - 1);
    }

    public No removerMin() {
        if (heap.isEmpty()) return null;
        No min = heap.get(0);
        No ultimo = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, ultimo);
            heapifyDown(0);
        }
        return min;
    }

    public int size() { return heap.size(); }

    // Retorna cópia do estado atual para a Etapa 2 de impressão
    public ArrayList<No> getDados() { return new ArrayList<>(heap); }
}