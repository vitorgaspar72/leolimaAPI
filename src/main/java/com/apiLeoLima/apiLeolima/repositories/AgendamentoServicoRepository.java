package com.apiLeoLima.apiLeolima.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiLeoLima.apiLeolima.models.AgendamentoServico;

@Repository
public interface AgendamentoServicoRepository extends JpaRepository<AgendamentoServico, Integer>{

}
