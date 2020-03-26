package com.kmzko.configurator.services.deployers;

import com.kmzko.configurator.entity.PersonalConveyor;
import com.kmzko.configurator.repositories.PersonalConveyorRepo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PersonalConveyorDeployer implements Deployer<PersonalConveyor> {
    private final PersonalConveyorRepo conveyorRepo;

    public PersonalConveyorDeployer(PersonalConveyorRepo conveyorRepo) {
        this.conveyorRepo = conveyorRepo;
    }

    @Override
    public PersonalConveyor save(PersonalConveyor conveyor) {
        return conveyorRepo.save(conveyor);
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
