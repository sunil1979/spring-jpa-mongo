package com.nichecs.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Entity
@Table(name = "user")
@XmlRootElement(name="user")
@Document
public class User extends NicheCSBaseObject {
	private Long id;
	private Long version;
	private String name;
	private Organization userOrganization;
	private List<Phone> userPhones;
	private List<Address> userAddress;
	private List<UserManifest> userManifest;
	
	@Id
	@Column(name = "user_id")
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(cascade = {
			javax.persistence.CascadeType.PERSIST,
			javax.persistence.CascadeType.MERGE })
	@XmlElement
	public Organization getUserOrganization() {
		return userOrganization;
	}

	public void setUserOrganization(Organization userOrganization) {
		this.userOrganization = userOrganization;
	}

	@OneToMany(mappedBy="owner",cascade = {
			javax.persistence.CascadeType.PERSIST,
			javax.persistence.CascadeType.MERGE })
	@XmlElementWrapper(name="userPhones")
	@XmlElement(name="phone")
	public List<Phone> getUserPhones() {
		return userPhones;
	}

	public void setUserPhones(List<Phone> userPhones) {
		this.userPhones = userPhones;
	}

	@ManyToMany(cascade = {
			javax.persistence.CascadeType.PERSIST,
			javax.persistence.CascadeType.MERGE })
	@JoinTable(name = "user_address", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
	@XmlElementWrapper(name="userAddress")
	@XmlElement(name="address")
	public List<Address> getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(List<Address> userAddress) {
		this.userAddress = userAddress;
	}
	
	public void addAddress(Address address){
		if(address == null){
			return;
		}else{
			if(this.userAddress == null){
				this.userAddress = new ArrayList<Address>();
			}
			this.userAddress.add(address);
		}
	}
	
	public void addPhone(Phone phone){
		if(phone == null){
			return;
		}else{
			if(this.userPhones == null){
				this.userPhones = new ArrayList<Phone>();
			}
			this.userPhones.add(phone);
		}
	}

	@OneToMany(mappedBy="parentUser",cascade=CascadeType.ALL)
	public List<UserManifest> getUserManifest() {
		return userManifest;
	}

	public void setUserManifest(List<UserManifest> userManifest) {
		this.userManifest = userManifest;
	}
}
