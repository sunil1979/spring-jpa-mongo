package com.nichecs.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nichecs.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
}
