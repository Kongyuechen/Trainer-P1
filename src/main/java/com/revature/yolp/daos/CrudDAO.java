package com.revature.yolp.daos;

import java.util.List;

public interface CrudDAO<T> {
    void save(T obj);
    void delete(String id);
    void update(String id);
    List<T> findAll();
    T findById(String id);
}
