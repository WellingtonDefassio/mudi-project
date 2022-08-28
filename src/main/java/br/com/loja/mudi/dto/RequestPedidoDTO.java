package br.com.loja.mudi.dto;

import br.com.loja.mudi.model.Pedido;
import br.com.loja.mudi.model.StatusPedido;

import javax.validation.constraints.NotBlank;

public class RequestPedidoDTO {
    @NotBlank
    private String nomeProduto;
    @NotBlank
    private String urlProduto;
    @NotBlank
    private String urlImagem;
    private String descricao;

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getUrlProduto() {
        return urlProduto;
    }

    public void setUrlProduto(String urlProduto) {
        this.urlProduto = urlProduto;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Pedido toPedido() {
        Pedido pedido = new Pedido();
        pedido.setNomeProduto(this.nomeProduto);
        pedido.setDescricao(this.descricao);
        pedido.setUrlImagem(this.urlImagem);
        pedido.setUrlProduto(this.urlProduto);
        pedido.setStatusPedido(StatusPedido.AGUARDANDO);

        return pedido;
    }
}
