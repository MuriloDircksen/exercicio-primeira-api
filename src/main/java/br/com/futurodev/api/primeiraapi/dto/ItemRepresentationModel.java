package br.com.futurodev.api.primeiraapi.dto;

import br.com.futurodev.api.primeiraapi.model.PedidoModel;
import br.com.futurodev.api.primeiraapi.model.ProdutoModel;

public class ItemRepresentationModel {
    private Long id;
    private String produto;
    private Integer quantidade;
    private Double valorItem;
    private ProdutoModel produtoModel;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorItem() {
        return valorItem;
    }

    public void setValorItem(Double valorItem) {
        this.valorItem = valorItem;
    }

    public ProdutoModel getProdutoModel() {
        return produtoModel;
    }

    public void setProdutoModel(ProdutoModel produtoModel) {
        this.produtoModel = produtoModel;
    }


}
