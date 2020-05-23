package com.kmzko.configurator.services.detailService;

import com.kmzko.configurator.domains.ConveyorType;
import com.kmzko.configurator.domains.conveyor.Conveyor;
import com.kmzko.configurator.dto.conveyor.ConveyorDto;
import com.kmzko.configurator.mappers.ConveyorMapper;
import com.kmzko.configurator.repositories.ConveyorRepo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConveyorDetailService implements DetailService<ConveyorDto> {
    private final ConveyorRepo conveyorRepo;
    private final ConveyorMapper mapper;

    public ConveyorDetailService(ConveyorRepo conveyorRepo, ConveyorMapper mapper) {
        this.conveyorRepo = conveyorRepo;
        this.mapper = mapper;
    }

    public ConveyorDto getConveyorTemplate(ConveyorType type) {
        return mapper.toDto(conveyorRepo.getTemplate(type));
    }

    @Override
    public List<ConveyorDto> getAll() {
        return conveyorRepo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<ConveyorDto> getById(long id) {
        Optional<Conveyor> conveyor = conveyorRepo.findById(id);

        return conveyor.map(mapper::toDto);
    }

    @Override
    public ConveyorDto save(ConveyorDto conveyor) {
        return mapper.toDto(conveyorRepo.save(mapper.toEntity(conveyor)));
    }

    @Override
    public boolean delete(ConveyorDto conveyor) {
        try {
            conveyorRepo.delete(mapper.toEntity(conveyor));
        }
        catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteById(long id) {
        try {
            conveyorRepo.deleteById(id);
        }
        catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }
}
