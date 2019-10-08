package amandzhi.springjdbc.repository;

import java.util.Collection;

public interface GenreRepository<Genre> {
    boolean insert(Genre entity);
    boolean update(Genre entity);
    Genre findById(Long id);
    boolean deleteById(Long id);
    Genre findByName(String name);
    Collection<Genre> findAll();
}