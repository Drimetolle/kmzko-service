package com.kmzko.configurator.services.deployers;

import com.kmzko.configurator.domains.conveyor.Conveyor;
import com.kmzko.configurator.repositories.ConveyorRepo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConveyorDetailService implements DetailService<Conveyor> {
    private final ConveyorRepo conveyorRepo;

    public ConveyorDetailService(ConveyorRepo conveyorRepo) {
        this.conveyorRepo = conveyorRepo;
    }

    @Override
    public List<Conveyor> getAll() {
        return conveyorRepo.findAll();
    }

    @Override
    public Conveyor getById(long id) {
        Optional<Conveyor> item = conveyorRepo.findById(id);

        return item.orElseGet(Conveyor::new);
    }

    @Override
    public Conveyor save(Conveyor conveyor) {
        return conveyorRepo.save(conveyor);
    }

    @Override
    public Conveyor update(Conveyor conveyor) {
        return null;
    }

    @Override
    public boolean delete(Conveyor conveyor) {
        try {
            conveyorRepo.delete(conveyor);
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