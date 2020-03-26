package com.kmzko.configurator.repositories;

import com.kmzko.configurator.domains.conveyor.Conveyor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConveyorRepo extends JpaRepository<Conveyor, Long> {
}

