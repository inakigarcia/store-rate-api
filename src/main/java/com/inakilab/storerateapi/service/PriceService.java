package com.inakilab.storerateapi.service;

import com.inakilab.storerateapi.dto.PriceDto;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {

    Optional<PriceDto> getPriceByPriority(Long brandId, Long productId, LocalDateTime givenDate);

}
