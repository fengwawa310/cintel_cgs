package com.entity;

import java.io.Serializable;
import java.util.Date;


public class BaseEntity <String extends Serializable> implements Serializable{

	private static final long serialVersionUID = -9132424013045297607L;

	private String  id;

	/**
	 * 系统创建时间
	 */
	private Date creatTime;

	/**
	 * 系统修改时间
	 */
	private Date modifyTime;

	private Long version;
	
	

	public String  getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	
//	@Override
//	public String toString() {
//		return String.format("Entity of type %s with id: %s", getClass().getName(), getId());
//	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!BaseEntity.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		BaseEntity<?> other = (BaseEntity<?>) obj;
		return getId() != null ? getId().equals(other.getId()) : false;
	}

	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += getId() != null ? getId().hashCode() * 31 : 0;
		return hashCode;
	}

}
