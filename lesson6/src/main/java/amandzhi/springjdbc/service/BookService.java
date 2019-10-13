package amandzhi.springjdbc.service;

import amandzhi.springjdbc.model.Book;

import java.util.Collection;

public interface BookService {
    boolean insert(Book entity);
    boolean update(Book entity);
    Book findById(Long id);
    boolean deleteById(Long id);
    Book findByName(String name);
    Collection<Book> findAll();
}