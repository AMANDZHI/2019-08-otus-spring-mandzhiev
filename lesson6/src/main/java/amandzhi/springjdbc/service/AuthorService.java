package amandzhi.springjdbc.service;

import java.util.Collection;

public interface AuthorService<Author> {
    boolean insert(Author entity);
    boolean update(Author entity);
    Author findById(Long id);
    boolean deleteById(Long id);
    Author findByName(String name);
    Collection<Author> findAll();
}