package classes;

public class MensagemService {
    public void mostrarMensagemCompraSucesso(Produto produto, int quantidade, double precoTotal) {
        System.out.println("Compra realizada com sucesso.");
        System.out.println("Produto: " + produto.getDescricao());
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Preço Total: " + precoTotal);
    }

    public void mostrarMensagemEstoqueInsuficiente() {
        System.out.println("Estoque insuficiente.");
    }

    public void mostrarMensagemProdutoNaoEncontrado() {
        System.out.println("Produto não encontrado para o Código Próprio fornecido.");
    }
}