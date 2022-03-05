package com.apiProdutosBlack.apiProdutosBlack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiProdutosBlack.apiProdutosBlack.models.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer>{

}
