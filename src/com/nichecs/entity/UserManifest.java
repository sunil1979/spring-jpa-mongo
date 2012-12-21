package com.nichecs.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Entity
@Table(name = "usermanifest")
@XmlRootElement(name="usermanifest")
@Document(collection="usermanifest")
public class UserManifest {
	private Long id;
	private Long version;
	@Transient
	private User parentUser;
	private User childUser;
	
	@Id
	@Column(name = "usermanifest_id")
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
	
	@XmlTransient
	@ManyToOne
	public User getParentUser() {
		return parentUser;
	}
	
	public void setParentUser(User parentUser) {
		this.parentUser = parentUser;
	}

	@ManyToOne
	public User getChildUser() {
		return childUser;
	}

	public void setChildUser(User childUser) {
		this.childUser = childUser;
	}
	
}
