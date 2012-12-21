package com.nichecs.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.nichecs.entity.Address;
import com.nichecs.entity.Organization;
import com.nichecs.entity.Phone;
import com.nichecs.entity.User;
import com.nichecs.entity.UserManifest;
import com.nichecs.repository.mongodb.UserMRepository;
import com.nichecs.service.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ebuild-spring-context.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class UserServiceTest {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private UserMRepository userMRepository;

	private List<User> globalList;

	private UserManifest targetManifest;
	
	public void saveUser() throws Exception {

		Organization org = new Organization();
		org.setOrganizationName("Niche Consulting");
		Address userAddress1 = new Address();
		userAddress1.setAddress("Address1");
		Address userAddress2 = new Address();
		userAddress2.setAddress("Address2");
		Phone phone1 = new Phone();
		phone1.setPhoneNumber("1234");
		Phone phone2 = new Phone();
		phone2.setPhoneNumber("5678");

		User user1 = new User();
		user1.setName("user1");
		user1.setUserOrganization(org);
		user1.addAddress(userAddress1);
		user1.addAddress(userAddress2);
		user1.addPhone(phone1);
		user1.addPhone(phone2);
		phone1.setOwner(user1);
		phone2.setOwner(user1);

		userService.saveUser(user1);

		User user2 = new User();
		user2.setName("user2");
		user2.setUserOrganization(org);
		user2.addAddress(userAddress1);
		user2.addAddress(userAddress2);
		user2.addPhone(phone1);
		user2.addPhone(phone2);

		userService.saveUser(user2);

		User user3 = new User();
		user3.setName("user3");
		user3.setUserOrganization(org);
		user3.addAddress(userAddress1);
		user3.addAddress(userAddress2);
		user3.addPhone(phone1);
		user3.addPhone(phone2);

		userService.saveUser(user3);

		User user4 = new User();
		user4.setName("user4");
		user4.setUserOrganization(org);
		user4.addAddress(userAddress1);
		user4.addAddress(userAddress2);
		user4.addPhone(phone1);
		user4.addPhone(phone2);

		userService.saveUser(user4);

		User user5 = new User();
		user5.setName("user5");
		user5.setUserOrganization(org);
		user5.addAddress(userAddress1);
		user5.addAddress(userAddress2);
		user5.addPhone(phone1);
		user5.addPhone(phone2);

		userService.saveUser(user5);

		User user6 = new User();
		user6.setName("user6");
		user6.setUserOrganization(org);
		user6.addAddress(userAddress1);
		user6.addAddress(userAddress2);
		user6.addPhone(phone1);
		user6.addPhone(phone2);

		userService.saveUser(user6);

		User user7 = new User();
		user7.setName("user7");
		user7.setUserOrganization(org);
		user7.addAddress(userAddress1);
		user7.addAddress(userAddress2);
		user7.addPhone(phone1);
		user7.addPhone(phone2);

		userService.saveUser(user7);

		User user8 = new User();
		user8.setName("user8");
		user8.setUserOrganization(org);
		user8.addAddress(userAddress1);
		user8.addAddress(userAddress2);
		user8.addPhone(phone1);
		user8.addPhone(phone2);

		userService.saveUser(user8);

		User user9 = new User();
		user9.setName("user9");
		user9.setUserOrganization(org);
		user9.addAddress(userAddress1);
		user9.addAddress(userAddress2);
		user9.addPhone(phone1);
		user9.addPhone(phone2);

		userService.saveUser(user9);

		UserManifest um1 = new UserManifest();
		um1.setParentUser(user1);
		um1.setChildUser(user2);
		userService.saveUserManifest(um1);

		UserManifest um2 = new UserManifest();
		um2.setParentUser(user1);
		um2.setChildUser(user3);
		userService.saveUserManifest(um2);

		UserManifest um3 = new UserManifest();
		um3.setParentUser(user3);
		um3.setChildUser(user4);
		userService.saveUserManifest(um3);

		UserManifest um4 = new UserManifest();
		um4.setParentUser(user3);
		um4.setChildUser(user5);
		userService.saveUserManifest(um4);

		UserManifest um5 = new UserManifest();
		um5.setParentUser(user4);
		um5.setChildUser(user8);
		userService.saveUserManifest(um5);

		UserManifest um6 = new UserManifest();
		um6.setParentUser(user4);
		um6.setChildUser(user9);
		userService.saveUserManifest(um6);

		UserManifest um7 = new UserManifest();
		um7.setParentUser(user2);
		um7.setChildUser(user6);
		userService.saveUserManifest(um7);

		UserManifest um8 = new UserManifest();
		um8.setParentUser(user6);
		um8.setChildUser(user7);
		userService.saveUserManifest(um8);

		UserManifest um9 = new UserManifest();
		um9.setParentUser(user7);
		um9.setChildUser(user8);
		userService.saveUserManifest(um9);

	}

	
	public void saveUser2Mongo() throws Exception {
		User user = userService.getUser(new Long("1").longValue());
		userService.saveUser2Mongo(user);
	}

	public void getUserMongo() throws Exception {
		User user = userService.getUserMongo(new Long("1").longValue());
		this.printUserManifest(user, 1);
	}

	private void printUserManifest(User user, int level) throws Exception {
		System.out.println("Parent User :" + level + "-" + user.getName());
		for (UserManifest um : user.getUserManifest()) {
			if (um.getChildUser() != null) {
				level = level + 1;
				this.printUserManifest(um.getChildUser(), level);
			}
			level = level - 1;
		}
	}

	public void updateUserMongo() throws Exception {
		User user1 = userService.getUserMongo(new Long("1").longValue());// retrieve
																			// from
																			// mongo
		User user7 = userService.getUser(new Long("7").longValue());// retrieve
																	// from
																	// mysql
		for (UserManifest um : user1.getUserManifest()) {
			if (um.getChildUser().getId().equals(new Long("2"))) {
				um.setChildUser(user7);
			}
		}
		user1.setId(new Long("11").longValue());

		/*
		 * User user7 = userService.getUser(new
		 * Long("7").longValue());//retrieve from mysql for(UserManifest um :
		 * user1.getUserManifest()){ if(um.getChildUser().getId().equals(new
		 * Long("2"))){ um.setChildUser(user7); } }
		 * 
		 * userService.saveUserManifest2Mongo(user1.getUserManifest());//update
		 * mongo
		 */
		userService.saveUser2Mongo(user1);
	}

	
	public void checkElementLevel() throws Exception {
		User user = userMRepository.findOne(new Long("1").longValue());
		User targetUser = userService.getUser(new Long("8").longValue());
		User targetUserParent = userService.getUser(new Long("7").longValue());
		this.findLevel(user, targetUser, targetUserParent, new ArrayList<User>());
		System.out.println("globalList Size :" + globalList.size());
		globalList.remove(user);
		for (User usr : globalList) {
			System.out.print(usr.getId() + "->");
		}
		System.out.println("");
		User nextUser = user;
		int globalListSize = globalList.size();
		for(int i=0;i<globalListSize;i++){
			User loopUser = nextUser;
			for (UserManifest um : loopUser.getUserManifest()) {
				System.out.println("UM ID :"+um.getId());
				if (globalList.contains(um.getChildUser())) {
					globalList.remove(um.getChildUser());
					nextUser = um.getChildUser();
				}
			}			
		}
		
		System.out.println("Loop User :" + nextUser.getId());

	}

	@Test
	public void updateElement() throws Exception{
		User user = userMRepository.findOne(new Long("1").longValue());
		User targetUser = userService.getUser(new Long("8").longValue());
		User targetUserParent = userService.getUser(new Long("7").longValue());
		this.findLevel(user, targetUser, targetUserParent, new ArrayList<User>());
		User newChildUser = userService.getUser(new Long("4").longValue());
		targetManifest.setChildUser(newChildUser);
		userMRepository.save(user);

	}
	
	private void findLevel(User source, User target, User targetUserParent, List<User> pathList) {
		for (UserManifest em : source.getUserManifest()) {
			if (em.getChildUser().getId().equals(target.getId()) && source.getId().equals(targetUserParent.getId())) {
				
				System.out.println("TargetElement Found :" + em.getChildUser().getId());
				System.out.println("Parent Element :" + source.getId());
				
				targetManifest = em;
				pathList.add(source);
				System.out.println("Path :");
				globalList = new ArrayList<User>();
				for (User pathStr : pathList) {
					System.out.print(pathStr.getId() + "->");
					globalList.add(pathStr);
				}
				break;
			} else {
				pathList.add(source);
				this.findLevel(em.getChildUser(), target, targetUserParent, pathList);
				pathList.remove(source);
			}
		}
	}
}
