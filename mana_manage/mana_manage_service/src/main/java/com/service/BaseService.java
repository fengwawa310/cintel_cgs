package com.service;

import java.io.Serializable;
import java.util.List;

import com.entity.BaseEntity;


public interface BaseService<T , ID extends Serializable> {

    T find(ID id);

    List<T> findAll();

    List<T> findList(ID... ids);

    long count();

    boolean exists(ID id);

    /**
     * 保存
     *
     * @param entity
     * @return 成功数目
     */
    int save(T entity);

    int update(T entity);

    int update(T entity, String... ignoreProperties);

    int delete(ID id);

    int delete(ID... ids);

    int delete(T entity);


}
