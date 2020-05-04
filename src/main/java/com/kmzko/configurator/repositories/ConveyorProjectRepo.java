package com.kmzko.configurator.repositories;

import com.kmzko.configurator.entity.user.ConveyorProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConveyorProjectRepo extends JpaRepository<ConveyorProject, Long> {
}
