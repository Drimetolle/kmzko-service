package com.kmzko.configurator.repositories;

import com.kmzko.configurator.entity.user.PersonalConveyor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalConveyorRepo extends JpaRepository<PersonalConveyor, Long> {
}
