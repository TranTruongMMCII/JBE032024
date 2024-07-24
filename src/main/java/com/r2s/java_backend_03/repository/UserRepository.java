package com.r2s.java_backend_03.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.r2s.java_backend_03.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	// jpql + hql
	List<User> findByDeleted(boolean isDeleted);

	List<User> findByName(String name);

	List<User> findByNameContains(String name);

	List<User> findByNameContaining(String name);

	@Query(nativeQuery = true, value = "SELECT * FROM jbe032024.tbl_user where col_name like ?1;")
	List<User> findByNameByQuery(String name);

	List<User> findByDeletedAndNameContains(boolean isDeleted, String name);
	
	Optional<User> findByUserName(String userName);
}
