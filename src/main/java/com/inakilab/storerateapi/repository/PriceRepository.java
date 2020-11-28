package com.inakilab.storerateapi.repository;

import com.inakilab.storerateapi.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE p.brandId = :brandId AND p.productId = :productId AND :givenDate >= p.startDate AND :givenDate <= p.endDate ORDER BY p.priority DESC")
    List<Price> findPriceListByPriority(Long brandId, Long productId, LocalDateTime givenDate);

}
