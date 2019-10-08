package amandzhi.springjdbc.service;

import amandzhi.springjdbc.model.Author;
import amandzhi.springjdbc.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@org.springframework.stereotype.Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService<Author> {

    private AuthorRepository<Author> authorRepository;

    public AuthorServiceImpl() {
    }

    @Autowired
    public AuthorServiceImpl(AuthorRepository<Author> authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public boolean insert(Author entity) {
        return authorRepository.insert(entity);
    }

    @Override
    public boolean update(Author entity) {
        return authorRepository.update(entity);
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return authorRepository.deleteById(id);
    }

    @Override
    public Author findByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public Collection<Author> findAll() {
        return authorRepository.findAll();
    }
}