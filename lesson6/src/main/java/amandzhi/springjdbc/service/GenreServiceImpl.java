package amandzhi.springjdbc.service;

import amandzhi.springjdbc.model.Genre;
import amandzhi.springjdbc.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@org.springframework.stereotype.Service(value = "genreService")
public class GenreServiceImpl implements GenreService<Genre> {

    private GenreRepository<Genre> genreRepository;

    public GenreServiceImpl() {
    }

    @Autowired
    public GenreServiceImpl(GenreRepository<Genre> genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public boolean insert(Genre entity) {
        return genreRepository.insert(entity);
    }

    @Override
    public boolean update(Genre entity) {
        return genreRepository.update(entity);
    }

    @Override
    public Genre findById(Long id) {
        return genreRepository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return genreRepository.deleteById(id);
    }

    @Override
    public Genre findByName(String name) {
        return genreRepository.findByName(name);
    }

    @Override
    public Collection<Genre> findAll() {
        return genreRepository.findAll();
    }
}