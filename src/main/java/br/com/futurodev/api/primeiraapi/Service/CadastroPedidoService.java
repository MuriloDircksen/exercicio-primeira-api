package br.com.futurodev.api.primeiraapi.Service;


import br.com.futurodev.api.primeiraapi.model.PedidoModel;
import br.com.futurodev.api.primeiraapi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CadastroPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public PedidoModel salvar(PedidoModel pedido){


        return pedidoRepository.save(pedido);
    }

    @Transactional
    public void deletar(Long idPedido){


        pedidoRepository.deleteById(idPedido);
    }
    public PedidoModel getPedidoById (Long idPedido){
        return pedidoRepository.findById(idPedido).get();
    }

    public List<PedidoModel> getPedidoByClienteName(String nome){

        return pedidoRepository.getUserByName(nome);
    }
}
