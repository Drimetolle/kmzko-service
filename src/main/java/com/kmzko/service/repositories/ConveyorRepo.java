package com.kmzko.service.repositories;

import com.kmzko.service.domains.conveyor.Conveyor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConveyorRepo extends CrudRepository<Conveyor, Long> {
}

