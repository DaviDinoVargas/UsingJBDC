package classes;

import java.util.List;

public class Compra {
    public static void comprarProdutoPorCodigoProprio(Jbdc jbdc, int codigoProprio, int quantidade, MensagemService mensagemService) {
        List<Produto> produtos = jbdc.getListaProdutos();

        for (Produto produto : produtos) {
            if (produto.getCodigoProprio() == codigoProprio) {
                if (produto.getEstoque() >= quantidade) {
                    double precoTotal = produto.getPreco() * quantidade;
                    mensagemService.mostrarMensagemCompraSucesso(produto, quantidade, precoTotal);
                    produto.setEstoque(produto.getEstoque() - quantidade);
                    jbdc.salvarDadosNoArquivo();
                } else {
                    mensagemService.mostrarMensagemEstoqueInsuficiente();
                }
                return;
            }
        }

        mensagemService.mostrarMensagemProdutoNaoEncontrado();
    }
}