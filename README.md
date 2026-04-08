# Projeto 1 - Estrutura de Dados II - Huffman
## Integrantes do Grupo:
Gabriel Ferreira RA: 10442043

Gian Lucca Campanha Ribeiro RA: 10438361

Enrique Cipolla Martins - RA: 10427834

## Instruções para Execução:
### Estrutura de arquivos:
**O projeto foi criado com Maven.**

projeto/

src/main/java/com/mackenzie/ (Contém as pastas model, view, controller, utils e a Main.java)

compilar_e_testar.sh

arq_de_teste.txt

### Compilação e Teste Automático:
Execute o script para compilar o código fonte e gerar o arquivo huffman.jar:

```bash
./compilar_e_testar.sh
```
O script realizará automaticamente um teste de compressão e descompressão, verificando a integridade dos arquivos via comando cmp.

### Execução Manual:
Para realizar testes individuais com outros arquivos, utilize a sintaxe exigida pelo enunciado:

Para Comprimir (c):

```bash
java -jar target/huffman.jar c arquivo_original.txt arquivo_comprimido.huff
```
Para Descomprimir (d):

```bash
java -jar target/huffman.jar d arquivo_comprimido.huff arquivo_restaurado.txt
```