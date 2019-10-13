package amandzhi.springjdbc.repository;

import amandzhi.springjdbc.model.Book;

import java.util.Collection;

public interface BookRepository {
    boolean insert(Book entity);
    boolean update(Book entity);
    Book findById(Long id);
    boolean deleteById(Long id);
    Book findByName(String name);
    Collection<Book> findAll();
}