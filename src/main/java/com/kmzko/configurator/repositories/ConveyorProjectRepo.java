package com.kmzko.configurator.repositories;

import com.kmzko.configurator.entity.user.ConveyorProject;
import com.kmzko.configurator.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConveyorProjectRepo extends JpaRepository<ConveyorProject, Long> {
}
