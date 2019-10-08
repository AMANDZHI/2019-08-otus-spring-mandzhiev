package amandzhi.springjdbc.repository;

import java.util.Collection;

public interface BookRepository<Book> {
    boolean insert(Book entity);
    boolean update(Book entity);
    Book findById(Long id);
    boolean deleteById(Long id);
    Book findByName(String name);
    Collection<Book> findAll();
}