package com.mapper;

import java.io.Serializable;

import com.entity.BaseEntity;

public interface BaseDao<T extends BaseEntity<ID>, ID extends Serializable> {

	T selectByPrimaryKey(ID id);

	int insert(T entity);
	
	int deleteByPrimaryKey(String key);
	
	int updateByPrimaryKey(T entity);

	void refresh(T entity);

	void clear();

	void flush();


}
