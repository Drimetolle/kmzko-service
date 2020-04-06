package com.kmzko.configurator.services.detailService;

import com.kmzko.configurator.domains.conveyor.Detail;
import com.kmzko.configurator.repositories.DetailRepo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComponentDetailService implements DetailService<Detail>  {
    private final DetailRepo repository;

    public ComponentDetailService(DetailRepo repository) {
        this.repository = repository;
    }

    @Override
    public List<Detail> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Detail> getById(long id) {
        return repository.findById(id);
    }

    @Override
    public Detail save(Detail detail) {
        return repository.save(detail);
    }

    @Override
    public Detail update(Detail detail) {
        return null;
    }

    @Override
    public boolean delete(Detail detail) {
        try {
            repository.delete(detail);
        }
        catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteById(long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }
}
