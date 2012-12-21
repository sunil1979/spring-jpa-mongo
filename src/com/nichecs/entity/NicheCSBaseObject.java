package com.nichecs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.Transient;

import java.io.Serializable;

@SuppressWarnings("serial")
@MappedSuperclass
public class NicheCSBaseObject implements Serializable {

	@Transient
	private Date created;
	@Transient
	private Date updated;
	@Transient
	private Boolean deleteStatus = false;
	@Transient
	private Boolean activeStatus = true;

	@PrePersist
	protected void onCreate() {
		this.updated = this.created = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updated = new Date();
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated")
	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Column(nullable = true)
	public Boolean getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	@Column(nullable = true)
	public Boolean getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Boolean activeStatus) {
		this.activeStatus = activeStatus;
	}
}
