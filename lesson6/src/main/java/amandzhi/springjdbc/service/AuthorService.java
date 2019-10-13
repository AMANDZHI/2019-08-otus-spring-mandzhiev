package amandzhi.springjdbc.service;

import amandzhi.springjdbc.model.Author;

import java.util.Collection;

public interface AuthorService {
    boolean insert(Author entity);
    boolean update(Author entity);
    Author findById(Long id);
    boolean deleteById(Long id);
    Author findByName(String name);
    Collection<Author> findAll();
}