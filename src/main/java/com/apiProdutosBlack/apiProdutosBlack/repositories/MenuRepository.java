package com.apiProdutosBlack.apiProdutosBlack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiProdutosBlack.apiProdutosBlack.models.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer>{

}
