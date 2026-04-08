#!/bin/bash

SRC_DIR="src/main/java"
TARGET_DIR="target"
CLASSES_DIR="${TARGET_DIR}/classes"
JAR_FILE="${TARGET_DIR}/huffman.jar"
MAIN_CLASS="com.mackenzie.Main"
ARQ_TESTE="arq_de_teste.txt"

rm -rf "$TARGET_DIR"
mkdir -p "$CLASSES_DIR"

echo "--- Compilando Projeto..."
find "$SRC_DIR" -name "*.java" | xargs javac -d "$CLASSES_DIR"
if [ $? -ne 0 ]; then echo "Erro na compilação!"; exit 1; fi

echo "--- Gerando huffman.jar..."
jar cfe "$JAR_FILE" "$MAIN_CLASS" -C "$CLASSES_DIR" .
if [ $? -ne 0 ]; then echo "Erro ao criar JAR!"; exit 1; fi

# Teste de Compressão
echo -e "\n--- Testando Compressão..."
java -jar "$JAR_FILE" c "$ARQ_TESTE" teste.huff

# Teste de Descompressão
echo -e "\n--- Testando Descompressão..."
java -jar "$JAR_FILE" d teste.huff teste_restaurado.txt

# Verificação de Integridade
echo -e "\n--- Verificando Integridade..."
if cmp -s "$ARQ_TESTE" teste_restaurado.txt; then
    echo "SUCESSO: Arquivos são idênticos."
else
    echo "FALHA: Arquivos são diferentes."
fi

# Limpeza de temporários
rm -f teste.huff teste_restaurado.txt