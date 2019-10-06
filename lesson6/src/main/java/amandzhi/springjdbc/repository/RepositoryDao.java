package amandzhi.springjdbc.repository;

import java.util.Collection;
import java.util.Optional;

public interface RepositoryDao<T> {
    boolean insert(T entity);
    boolean update(T entity);
    T findById(Long id);
    boolean deleteById(Long id);
    T findByName(String name);
    Collection<T> findAll();
}
