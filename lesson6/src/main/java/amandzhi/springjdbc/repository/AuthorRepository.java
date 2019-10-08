package amandzhi.springjdbc.repository;

import java.util.Collection;

public interface AuthorRepository<Author> {
    boolean insert(Author entity);
    boolean update(Author entity);
    Author findById(Long id);
    boolean deleteById(Long id);
    Author findByName(String name);
    Collection<Author> findAll();
}
