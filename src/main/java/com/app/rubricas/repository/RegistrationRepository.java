package com.app.rubricas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.rubricas.model.User;

public interface RegistrationRepository extends JpaRepository<User, Integer> {
	public User findByEmailId(String emailId);
	public User findByEmailIdAndPassword(String emailId, String password);
}
