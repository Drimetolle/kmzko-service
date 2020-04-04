package com.kmzko.configurator.repositories;

import com.kmzko.configurator.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepo extends JpaRepository<Purchase, Long> {
}
