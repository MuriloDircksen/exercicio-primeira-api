package br.com.futurodev.api.primeiraapi.controllers;

import br.com.futurodev.api.primeiraapi.Service.CadastroPedidoService;
import br.com.futurodev.api.primeiraapi.Service.CadastroUsuarioService;
import br.com.futurodev.api.primeiraapi.dto.ItemRepresentationModel;
import br.com.futurodev.api.primeiraapi.dto.PedidoRepresentationModel;
import br.com.futurodev.api.primeiraapi.dto.TelefoneRepresetationModel;
import br.com.futurodev.api.primeiraapi.dto.UsuarioRepresentationModel;
import br.com.futurodev.api.primeiraapi.input.PedidoInput;
import br.com.futurodev.api.primeiraapi.input.UsuarioInput;
import br.com.futurodev.api.primeiraapi.model.ItemPedidoModel;
import br.com.futurodev.api.primeiraapi.model.PedidoModel;
import br.com.futurodev.api.primeiraapi.model.TelefoneModel;
import br.com.futurodev.api.primeiraapi.model.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {

    @Autowired
    private CadastroPedidoService cadastroPedidoService;

    @PostMapping(value = "/", produces = "application/json")

    public ResponseEntity<PedidoRepresentationModel> cadastrar(@RequestBody PedidoInput pedidoInput) {
        //converte um pedido input em pedido model, interessante quando o metodo não
        // irá necessitar salvar dados
        PedidoModel pedido = cadastroPedidoService.salvar(toDomainObject(pedidoInput));
        //converte pedido model em pedido representationmodel
        return new ResponseEntity<PedidoRepresentationModel>(toModel(pedido), HttpStatus.CREATED);

        }

        @PutMapping(value = "/", produces = "Application/json")

        public ResponseEntity<PedidoRepresentationModel> atualizar(@RequestBody PedidoInput pedidoInput){
        PedidoModel pedido = cadastroPedidoService.salvar(toDomainObject(pedidoInput));
        return new ResponseEntity<PedidoRepresentationModel>(toModel(pedido), HttpStatus.OK);
        }

        @DeleteMapping(value = "/")
        @ResponseBody

        public ResponseEntity<String> deletar (@RequestParam Long idPedido){

        cadastroPedidoService.deletar(idPedido);
        return new ResponseEntity<>("Usuario deletado!", HttpStatus.OK);
        }
    @GetMapping(value = "/{idUsuario}", produces = "application/json")

    public ResponseEntity<PedidoRepresentationModel> getUserById(@PathVariable(value = "idUsuario") Long idUsuario){

        PedidoModel pedido = cadastroPedidoService.getPedidoById(idUsuario);

        PedidoRepresentationModel pedidoRepresentationModel = toModel(pedido);


        return new ResponseEntity<PedidoRepresentationModel>(pedidoRepresentationModel, HttpStatus.OK);
    }
    @GetMapping(value = "/buscarpornome", produces = "application/json")
    @ResponseBody

    public ResponseEntity<List<PedidoRepresentationModel>> getUserByName(@RequestParam(name= "nome") String nome) {
        //obtem a lista de pedidos do tipo model
        List<PedidoModel> pedidos = cadastroPedidoService.getPedidoByClienteName(nome);

        //convertemos a lista do objeto tipo pedido model para pedido representation model(DTO)
        List<PedidoRepresentationModel> pedidosRepresentationModel = toColletionModel(pedidos);
        return new ResponseEntity<List<PedidoRepresentationModel>>
                (pedidosRepresentationModel, HttpStatus.OK);
    }



        private PedidoRepresentationModel toModel(PedidoModel pedido) {
        PedidoRepresentationModel pedidoRepresentationModel=new PedidoRepresentationModel();
        //realizar a conversão do UsuarioModel para representation model
        pedidoRepresentationModel.setId(pedido.getId());
        pedidoRepresentationModel.setDataHoraAtualização(pedido.getDataHoraAtualização());
        pedidoRepresentationModel.setDataHoraCadastro(pedido.getDataHoraCadastro());
        pedidoRepresentationModel.setClienteModel(pedido.getClienteModel());
        pedidoRepresentationModel.setFormaPagamentoModel(pedido.getFormaPagamentoModel());



        for(int i=0; i<pedido.getItensPedidoModel().size();i++) {
            ItemRepresentationModel itens = new ItemRepresentationModel();
            itens.setId(pedido.getItensPedidoModel().get(i).getId());
            itens.setProduto(pedido.getItensPedidoModel().get(i).getProduto());
            itens.setQuantidade(pedido.getItensPedidoModel().get(i).getQuantidade());
            itens.setValorItem(pedido.getItensPedidoModel().get(i).getValorItem());
            itens.setProdutoModel(pedido.getItensPedidoModel().get(i).getProdutoModel());
            pedidoRepresentationModel.getItens().add(itens);
        }
        return pedidoRepresentationModel;
    }

    //convert uma lista de objeto pedido model em pedidorepresentationmodel
    private List<PedidoRepresentationModel> toColletionModel(List<PedidoModel> pedidosModel){
        return pedidosModel.stream()
                .map(usuarioModel ->toModel(usuarioModel)).collect(Collectors.toList());
    }

    //converter um objeto do tipo usuario input para usuario model
    private PedidoModel toDomainObject(PedidoInput pedidoInput){
        PedidoModel pedidoModel = new PedidoModel();
        pedidoModel.setClienteModel(pedidoInput.getClienteModel());
        pedidoModel.setId(pedidoInput.getId());
        pedidoModel.setFormaPagamentoModel(pedidoInput.getFormaPagamentoModel());

        for(int i=0; i<pedidoInput.getItens().size();i++) {
            ItemPedidoModel itensModel = new ItemPedidoModel();
            itensModel.setId(pedidoInput.getItens().get(i).getId());
            itensModel.setProduto(pedidoInput.getItens().get(i).getProduto());
            itensModel.setQuantidade(pedidoInput.getItens().get(i).getQuantidade());
            itensModel.setValorItem(pedidoInput.getItens().get(i).getValorItem());
            itensModel.setProdutoModel(pedidoInput.getItens().get(i).getProdutoModel());
            itensModel.setPedido(pedidoModel);
            pedidoModel.getItensPedidoModel().add(itensModel);
        }


        return pedidoModel;

    }
}
