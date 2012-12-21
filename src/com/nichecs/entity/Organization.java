package com.nichecs.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Entity
@Table(name = "organization")
@XmlRootElement(name="organization")
@Document
public class Organization extends NicheCSBaseObject {
	private Long id;
	private Long version;
	private String organizationName;
	@Transient
	private List<User> orgUsers;
	
	@Id
	@Column(name = "organization_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Version
	public Long getVersion() {
		return version;
	}
	
	public void setVersion(Long version) {
		this.version = version;
	}
	
	public String getOrganizationName() {
		return organizationName;
	}
	
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	@OneToMany(mappedBy="userOrganization")
	@XmlTransient
	public List<User> getOrgUsers() {
		return orgUsers;
	}

	public void setOrgUsers(List<User> orgUsers) {
		this.orgUsers = orgUsers;
	}
	
	public void addUser(User user){
		if(user == null){
			return;
		}else{
			if(this.orgUsers == null){
				this.orgUsers = new ArrayList<User>();
			}
			this.orgUsers.add(user);
		}
	}
}
