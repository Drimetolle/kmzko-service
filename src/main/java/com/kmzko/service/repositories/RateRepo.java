package com.kmzko.service.repositories;

import com.kmzko.service.domains.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepo extends JpaRepository<Rate, Long> {
}
