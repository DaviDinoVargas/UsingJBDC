package classes;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Jbdc {
    private static final String ARQUIVO = "produtos.txt";
    private List<Produto> listaProdutos;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public Jbdc() {
        listaProdutos = new ArrayList<>();
        carregarDadosDoArquivo();
    }

    public void adicionarProduto(Produto produto) {
        Date dataHoraAtual = new Date();
        String dataHoraFormatada = dateFormat.format(dataHoraAtual);
        produto.setDataHora(dataHoraFormatada);
        listaProdutos.add(produto);
        salvarDadosNoArquivo();
    }

    public void carregarDadosDoArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 5) {
                    String descricao = partes[0];
                    double preco = Double.parseDouble(partes[1]);
                    int estoque = Integer.parseInt(partes[2]);
                    int codigoProprio = Integer.parseInt(partes[3]);
                    String dataHora = partes[4];
                    Produto produto = new Produto(descricao, preco, estoque, codigoProprio);
                    produto.setDataHora(dataHora);
                    listaProdutos.add(produto);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public void salvarDadosNoArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Produto produto : listaProdutos) {
                writer.write(produto.getDescricao() + "," + produto.getPreco() + "," + produto.getEstoque() + "," + produto.getCodigoProprio() + "," + produto.getDataHora());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    public void removerProduto(int codigo) {
        if (codigo >= 0 && codigo < listaProdutos.size()) {
            listaProdutos.remove(codigo);
            salvarDadosNoArquivo();
        } else {
            System.out.println("Código inválido.");
        }
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void listarTodosProdutos() {
        for (Produto produto : listaProdutos) {
            System.out.println(produto);
        }
    }

    public void exibirProdutoPorCodigoProprio(int codigoProprio) {
        for (Produto produto : listaProdutos) {
            if (produto.getCodigoProprio() == codigoProprio) {
                System.out.println(produto);
                return;
            }
        }
        System.out.println("Produto não encontrado para o Código Próprio fornecido.");
    }
}