package com.apiLeoLima.apiLeolima.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiLeoLima.apiLeolima.models.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer>{

}
