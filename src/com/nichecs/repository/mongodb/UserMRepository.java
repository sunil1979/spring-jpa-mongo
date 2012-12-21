package com.nichecs.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nichecs.entity.User;

public interface UserMRepository extends MongoRepository<User, Long> {

}
