package com.kmzko.service.repositories;

import com.kmzko.service.domains.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepo extends CrudRepository<Rate, Long> {
}
