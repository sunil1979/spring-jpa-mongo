package com.nichecs.service;

import java.util.List;

import com.nichecs.entity.User;
import com.nichecs.entity.UserManifest;

public interface UserService {
	public void saveUser(User user);
	public User getUser(Long userId);
	public void saveUser2Mongo(User user);
	public User getUserMongo(Long userId);
	public void saveUserManifest(UserManifest userManifest);
	public UserManifest getUserManifestMongo(Long manifestId);
	public void saveUserManifest2Mongo(List<UserManifest> umList);
}
