package com.erp.school.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erp.school.common.BaseRepository;
import com.erp.school.entity.UserEntity;

import jakarta.persistence.Entity;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByEmail(String email);

	// 🔹 Get all users except deleted
	@Query("SELECT u FROM UserEntity u WHERE u.status != 'DELETED'")
	List<UserEntity> findAllUsers();

	// 🔹 Get user by id (not deleted)
	@Query(value = "SELECT * FROM users WHERE id = :id AND status != 'DELETED'", nativeQuery = true)
	Optional<UserEntity> findActiveUserById(@Param("id") Long id);

	// 🔹 Get user by email (not deleted)
	@Query(value = "SELECT * FROM users WHERE email = :email AND status != 'DELETED'", nativeQuery = true)
	Optional<UserEntity> findActiveUserByEmail(@Param("email") String email);

	boolean existsByPhone(String phone);

	boolean existsByEmail(String email);

	Optional<UserEntity> findByPhone(String phone);

}
