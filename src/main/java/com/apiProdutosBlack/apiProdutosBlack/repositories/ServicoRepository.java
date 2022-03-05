package com.apiProdutosBlack.apiProdutosBlack.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiProdutosBlack.apiProdutosBlack.models.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {

}
