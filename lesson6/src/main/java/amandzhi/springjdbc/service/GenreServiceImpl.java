package amandzhi.springjdbc.service;

import amandzhi.springjdbc.model.Genre;
import amandzhi.springjdbc.repository.RepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collection;
import java.util.Optional;

@org.springframework.stereotype.Service(value = "genreService")
public class GenreServiceImpl implements Service<Genre> {

    private RepositoryDao<Genre> genreRepository;

    public GenreServiceImpl() {
    }

    @Autowired
    public GenreServiceImpl(@Qualifier("genreRepository") RepositoryDao<Genre> genreRepository) {
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