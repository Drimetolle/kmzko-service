package com.kmzko.configurator.services.detailService;

import com.kmzko.configurator.domains.conveyor.Detail;
import com.kmzko.configurator.dto.conveyor.DetailDto;
import com.kmzko.configurator.mappers.DetailMapper;
import com.kmzko.configurator.repositories.DetailRepo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComponentDetailService implements DetailService<DetailDto>  {
    private final DetailRepo repository;
    private final DetailMapper mapper;

    public ComponentDetailService(DetailRepo repository, DetailMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<DetailDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<DetailDto> getById(long id) {
        Optional<Detail> detail = repository.findById(id);

        return detail.map(mapper::toDto);
    }

    @Override
    public DetailDto save(DetailDto detail) {
        return mapper.toDto(repository.save(mapper.toEntity(detail)));
    }

    @Override
    public boolean delete(DetailDto detail) {
        try {
            repository.delete(mapper.toEntity(detail));
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
