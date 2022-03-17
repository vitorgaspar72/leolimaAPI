package com.apiLeoLima.apiLeolima.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiLeoLima.apiLeolima.models.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer>{

}
