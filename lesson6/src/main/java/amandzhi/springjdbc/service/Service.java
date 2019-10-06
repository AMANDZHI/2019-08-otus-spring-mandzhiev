package amandzhi.springjdbc.service;

import java.util.Collection;

public interface Service<T> {
    boolean insert(T entity);
    boolean update(T entity);
    T findById(Long id);
    boolean deleteById(Long id);
    T findByName(String name);
    Collection<T> findAll();
}