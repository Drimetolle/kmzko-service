package com.kmzko.configurator.services.deployers;

import com.kmzko.configurator.entity.PersonalConveyor;
import com.kmzko.configurator.repositories.PersonalConveyorRepo;
import org.springframework.stereotype.Service;

@Service
public class PersonalConveyorDeployer implements Deployer<PersonalConveyor> {
    private final PersonalConveyorRepo conveyorRepo;

    public PersonalConveyorDeployer(PersonalConveyorRepo conveyorRepo) {
        this.conveyorRepo = conveyorRepo;
    }

    @Override
    public PersonalConveyor save(PersonalConveyor conveyor) {
        return conveyor;
    }

    @Override
    public boolean delete(PersonalConveyor o) {
        return false;
    }

    @Override
    public boolean deleteById(long o) {
        return false;
    }
}
