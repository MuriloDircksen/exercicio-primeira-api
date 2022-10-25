package br.com.futurodev.api.primeiraapi.repository;

import br.com.futurodev.api.primeiraapi.model.UsuarioModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {

    @Query(value = "select u from UsuarioModel u where u.nome like %?1%")
    ArrayList<UsuarioModel> getUserByName(String nome);
}
