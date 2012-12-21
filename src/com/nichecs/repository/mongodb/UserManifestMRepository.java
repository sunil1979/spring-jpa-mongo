package com.nichecs.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nichecs.entity.UserManifest;

public interface UserManifestMRepository extends MongoRepository<UserManifest, Long> {

}
