package com.apiLeoLima.apiLeolima.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiLeoLima.apiLeolima.models.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {

}
