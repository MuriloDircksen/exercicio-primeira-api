package br.com.futurodev.api.primeiraapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pedido")
public class PedidoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime dataHoraCadastro;
    @UpdateTimestamp
   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime dataHoraAtualizacao;

    @OneToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_cliente"))
    private ClienteModel clienteModel;

    @OneToOne
    @JoinColumn(name = "idFormaPagamento", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_formaPagamento"))
    private FormaPagamentoModel formaPagamentoModel;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<ItemPedidoModel> itensPedidoModel = new ArrayList<ItemPedidoModel>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(OffsetDateTime dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    public OffsetDateTime getDataHoraAtualização() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualização(OffsetDateTime dataHoraAtualização) {
        this.dataHoraAtualizacao = dataHoraAtualização;
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

    public List<ItemPedidoModel> getItensPedidoModel() {
        return itensPedidoModel;
    }

    public void setItensPedidoModel(List<ItemPedidoModel> itensPedidoModel) {
        this.itensPedidoModel = itensPedidoModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoModel that = (PedidoModel) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public OffsetDateTime getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(OffsetDateTime dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }


}
