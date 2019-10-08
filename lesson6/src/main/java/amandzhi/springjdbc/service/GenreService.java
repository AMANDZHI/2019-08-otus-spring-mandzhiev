package amandzhi.springjdbc.service;

import java.util.Collection;

public interface GenreService<Genre> {
    boolean insert(Genre entity);
    boolean update(Genre entity);
    Genre findById(Long id);
    boolean deleteById(Long id);
    Genre findByName(String name);
    Collection<Genre> findAll();
}