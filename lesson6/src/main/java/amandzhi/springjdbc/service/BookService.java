package amandzhi.springjdbc.service;

import java.util.Collection;

public interface BookService<Book> {
    boolean insert(Book entity);
    boolean update(Book entity);
    Book findById(Long id);
    boolean deleteById(Long id);
    Book findByName(String name);
    Collection<Book> findAll();
}