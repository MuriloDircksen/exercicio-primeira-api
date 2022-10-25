package br.com.futurodev.api.primeiraapi.repository;

import br.com.futurodev.api.primeiraapi.model.PedidoModel;

import br.com.futurodev.api.primeiraapi.model.UsuarioModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PedidoRepository extends CrudRepository<PedidoModel, Long> {
//estabelece comunicação com o servidor

    //@Query(value = "select p from PedidoModel p inner join ClienteModel c where c.nome like %?1%")
   @Query(value = "select p from PedidoModel p where p.clienteModel.nome like %?1%")
    ArrayList<PedidoModel> getUserByName(String nome);
}
