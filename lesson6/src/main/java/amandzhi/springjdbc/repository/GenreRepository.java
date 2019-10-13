package amandzhi.springjdbc.repository;

import amandzhi.springjdbc.model.Genre;

import java.util.Collection;

public interface GenreRepository {
    boolean insert(Genre entity);
    boolean update(Genre entity);
    Genre findById(Long id);
    boolean deleteById(Long id);
    Genre findByName(String name);
    Collection<Genre> findAll();
}