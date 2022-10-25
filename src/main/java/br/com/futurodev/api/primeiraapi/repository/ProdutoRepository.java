package br.com.futurodev.api.primeiraapi.repository;

import br.com.futurodev.api.primeiraapi.model.ProdutoModel1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel1, Long> {


}
