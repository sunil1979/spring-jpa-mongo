package com.nichecs.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nichecs.entity.UserManifest;

public interface UserManifestRepository extends JpaRepository<UserManifest,Long> {

}
