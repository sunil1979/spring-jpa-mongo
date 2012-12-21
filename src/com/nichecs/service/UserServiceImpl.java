package com.nichecs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.nichecs.entity.User;
import com.nichecs.entity.UserManifest;
import com.nichecs.repository.jpa.UserManifestRepository;
import com.nichecs.repository.jpa.UserRepository;
import com.nichecs.repository.mongodb.UserMRepository;
import com.nichecs.repository.mongodb.UserManifestMRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository uRepository;

	@Autowired
	UserMRepository umRepository;

	@Autowired
	UserManifestRepository uManifestRepository;

	@Autowired
	UserManifestMRepository uManifestMRepository;

	@Override
	public void saveUser(User user) {
		uRepository.save(user);
	}

	@Override
	public User getUser(Long userId) {
		return uRepository.findOne(userId);

	}

	@Override
	public void saveUser2Mongo(User user) {
		umRepository.save(user);
		//this.saveUserManifest2Mongo(user.getUserManifest());
	}

	@Override
	public void saveUserManifest2Mongo(List<UserManifest> umList) {
		for (UserManifest um : umList) {
			uManifestMRepository.save(um);
			if (um.getChildUser() != null) {
				this.saveUserManifest2Mongo(um.getChildUser().getUserManifest());
			}
		}
		return;
	}

	@Override
	public User getUserMongo(Long userId) {
		return umRepository.findOne(userId);
	}

	@Override
	public void saveUserManifest(UserManifest userManifest) {
		uManifestRepository.save(userManifest);
	}

	@Override
	public UserManifest getUserManifestMongo(Long manifestId) {
		return uManifestMRepository.findOne(manifestId);
	}
}
