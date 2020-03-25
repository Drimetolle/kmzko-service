package com.kmzko.service.services.deployers;

import com.kmzko.service.entity.PersonalConveyor;
import org.springframework.stereotype.Service;

@Service
public class PersonalConveyorDeployer implements Deployer<PersonalConveyor> {
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
