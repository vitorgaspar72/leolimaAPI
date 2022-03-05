package com.apiProdutosBlack.apiProdutosBlack.repositories;
import com.apiProdutosBlack.apiProdutosBlack.models.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer>{

}
