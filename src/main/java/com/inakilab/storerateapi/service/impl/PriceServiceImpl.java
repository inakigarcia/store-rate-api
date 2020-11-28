package com.inakilab.storerateapi.service.impl;

import com.inakilab.storerateapi.dto.PriceDto;
import com.inakilab.storerateapi.factory.PriceDtoFactory;
import com.inakilab.storerateapi.model.Price;
import com.inakilab.storerateapi.repository.PriceRepository;
import com.inakilab.storerateapi.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public Optional<PriceDto> getPriceByPriority(Long brandId, Long productId, LocalDateTime givenDate) {
        List<Price> priceListByPriority = priceRepository.findPriceListByPriority(brandId, productId, givenDate);

        Optional<PriceDto> selectedPrice = priceListByPriority.stream()
                .map(PriceDtoFactory::create)
                .findFirst();

        return selectedPrice;
    }

}
