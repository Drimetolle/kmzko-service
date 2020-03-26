package com.kmzko.configurator.repositories;

import com.kmzko.configurator.domains.conveyor.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepo extends JpaRepository<Detail, Long> {
}
