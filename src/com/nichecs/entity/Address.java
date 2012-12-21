package com.nichecs.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "address")
@XmlRootElement(name="address")
@Document
public class Address extends NicheCSBaseObject {
	private Long id;
	private Long version;
	private String address;
	@Transient
	private List<User> userAddress;
	
	@Id
	@Column(name = "address_id")
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
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@ManyToMany(mappedBy="userAddress")
	@XmlTransient
	public List<User> getUserAddress() {
		return userAddress;
	}
	
	public void setUserAddress(List<User> userAddress) {
		this.userAddress = userAddress;
	}
}
