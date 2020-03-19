package com.kmzko.service.repositories;

import com.kmzko.service.domains.conveyor.Conveyor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConveyorRepo extends JpaRepository<Conveyor, Long> {
}

