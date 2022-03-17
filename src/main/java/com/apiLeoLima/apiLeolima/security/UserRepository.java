package com.apiLeoLima.apiLeolima.security;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{
	

	User findByUsername(String username);
}
