package com.inakilab.storerateapi.service;

import com.inakilab.storerateapi.dto.PriceDto;
import com.inakilab.storerateapi.exception.PriceNotFoundException;

import java.time.LocalDateTime;

public interface PriceService {

    PriceDto getPriceByPriority(Long brandId, Long productId, LocalDateTime givenDate) throws PriceNotFoundException;

}
