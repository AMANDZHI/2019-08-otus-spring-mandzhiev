package amandzhi.springjdbc.service;

import amandzhi.springjdbc.model.Genre;

import java.util.Collection;

public interface GenreService {
    boolean insert(Genre entity);
    boolean update(Genre entity);
    Genre findById(Long id);
    boolean deleteById(Long id);
    Genre findByName(String name);
    Collection<Genre> findAll();
}