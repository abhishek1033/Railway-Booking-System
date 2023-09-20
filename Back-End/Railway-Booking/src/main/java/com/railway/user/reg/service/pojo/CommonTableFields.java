package com.railway.user.reg.service.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.EqualsAndHashCode;

@MappedSuperclass
@EqualsAndHashCode(callSuper=false)

public class CommonTableFields 
{
	@Column(name = "createAt", updatable = false)
	private Date createdAt;

	@Column(name = "createdBy", updatable = false)
	private String createdBy;

	@Column(name = "updatedAt")
	private Date updatedAt;

	@Column(name = "updatedBy")
	private String updatedBy;

	@Column(name = "status")
	private String status;

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
