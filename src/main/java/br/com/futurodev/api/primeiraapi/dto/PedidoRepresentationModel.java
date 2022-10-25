package br.com.futurodev.api.primeiraapi.dto;

import br.com.futurodev.api.primeiraapi.model.ClienteModel;
import br.com.futurodev.api.primeiraapi.model.FormaPagamentoModel;
import br.com.futurodev.api.primeiraapi.model.ItemPedidoModel;
import br.com.futurodev.api.primeiraapi.model.ProdutoModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepresentationModel { //dto de saida

    private Long id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime dataHoraAtualização;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime dataHoraCadastro;
    private ClienteModel clienteModel;
    private FormaPagamentoModel formaPagamentoModel;
    private List<ItemRepresentationModel> itens = new ArrayList<ItemRepresentationModel>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getDataHoraAtualização() {
        return dataHoraAtualização;
    }

    public void setDataHoraAtualização(OffsetDateTime dataHoraAtualização) {
        this.dataHoraAtualização = dataHoraAtualização;
    }

    public OffsetDateTime getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(OffsetDateTime dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
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
