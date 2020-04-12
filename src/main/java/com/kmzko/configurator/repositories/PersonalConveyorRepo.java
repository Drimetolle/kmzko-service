package com.kmzko.configurator.repositories;

import com.kmzko.configurator.entity.user.PersonalConveyor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalConveyorRepo extends JpaRepository<PersonalConveyor, Long> {
    List<PersonalConveyor> findAllById(Long id);
}
