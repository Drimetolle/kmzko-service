package com.kmzko.configurator.repositories;

import com.kmzko.configurator.domains.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepo extends JpaRepository<Rate, Long> {
}
