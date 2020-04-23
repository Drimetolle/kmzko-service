package com.kmzko.configurator.repositories;

import com.kmzko.configurator.domains.conveyor.Conveyor;
import com.kmzko.configurator.domains.ConveyorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConveyorRepo extends JpaRepository<Conveyor, Long> {
    @Query(
            value = "SELECT * FROM CONVEYOR c WHERE c.IS_TEMPLATE = TRUE order by c.created desc limit 1",
            nativeQuery = true)
    Conveyor getTemplate(ConveyorType type);
}

