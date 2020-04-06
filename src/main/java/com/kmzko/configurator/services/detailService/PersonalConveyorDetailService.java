package com.kmzko.configurator.services.detailService;

import com.kmzko.configurator.entity.user.PersonalConveyor;
import com.kmzko.configurator.repositories.PersonalConveyorRepo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalConveyorDetailService implements DetailService<PersonalConveyor> {
    private final PersonalConveyorRepo conveyorRepo;

    public PersonalConveyorDetailService(PersonalConveyorRepo conveyorRepo) {
        this.conveyorRepo = conveyorRepo;
    }

    @Override
    public List<PersonalConveyor> getAll() {
        return conveyorRepo.findAll();
    }

    @Override
    public Optional<PersonalConveyor> getById(long id) {
        return conveyorRepo.findById(id);
    }

    @Override
    public PersonalConveyor save(PersonalConveyor conveyor) {
        return conveyorRepo.save(conveyor);
    }

    @Override
    public PersonalConveyor update(PersonalConveyor personalConveyor) {
        return null;
    }

    @Override
    public boolean delete(PersonalConveyor conveyor) {
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
