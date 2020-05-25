package com.kmzko.configurator.repositories;

import com.kmzko.configurator.entity.user.ConveyorProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConveyorProjectRepo extends JpaRepository<ConveyorProject, Long> {
    List<ConveyorProject> findConveyorProjectsByUser_Id(long id);
}
