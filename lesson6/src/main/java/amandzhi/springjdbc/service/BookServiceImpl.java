package amandzhi.springjdbc.service;

import amandzhi.springjdbc.model.Book;
import amandzhi.springjdbc.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@org.springframework.stereotype.Service(value = "bookService")
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl() {
    }

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean insert(Book book) {
        return bookRepository.insert(book);
    }

    @Override
    public boolean update(Book book) {
        return bookRepository.update(book);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return bookRepository.deleteById(id);
    }

    @Override
    public Book findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public Collection<Book> findAll() {
        return bookRepository.findAll();
    }
}