package br.com.futurodev.api.primeiraapi.input;

import br.com.futurodev.api.primeiraapi.dto.ItemRepresentationModel;
import br.com.futurodev.api.primeiraapi.model.ClienteModel;
import br.com.futurodev.api.primeiraapi.model.FormaPagamentoModel;
import br.com.futurodev.api.primeiraapi.model.ItemPedidoModel;
import br.com.futurodev.api.primeiraapi.model.ProdutoModel;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoInput { //DTO entrada
    private Long id;
    private ClienteModel clienteModel;
    private FormaPagamentoModel formaPagamentoModel;
    private List<ItemRepresentationModel> itens = new ArrayList<ItemRepresentationModel>();




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteModel getClienteModel() {
        return clienteModel;
    }

    public void setClienteModel(ClienteModel clienteModel) {
        this.clienteModel = clienteModel;
    }

    public FormaPagamentoModel getFormaPagamentoModel() {
        return formaPagamentoModel;
    }

    public void setFormaPagamentoModel(FormaPagamentoModel formaPagamentoModel) {
        this.formaPagamentoModel = formaPagamentoModel;
    }

    public List<ItemRepresentationModel> getItens() {
        return itens;
    }

    public void setItens(List<ItemRepresentationModel> itens) {
        this.itens = itens;
    }


}
