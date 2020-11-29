package com.inakilab.storerateapi.service.impl;

import com.inakilab.storerateapi.dto.PriceDto;
import com.inakilab.storerateapi.exception.PriceNotFoundException;
import com.inakilab.storerateapi.factory.PriceDtoFactory;
import com.inakilab.storerateapi.model.Price;
import com.inakilab.storerateapi.repository.PriceRepository;
import com.inakilab.storerateapi.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Transactional
    public PriceDto getPriceByPriority(Long brandId, Long productId, LocalDateTime givenDate) throws PriceNotFoundException {
        List<Price> priceListByPriority = priceRepository.findPriceListByPriority(brandId, productId, givenDate);

        PriceDto selectedPrice = priceListByPriority.stream()
                .map(PriceDtoFactory::create)
                .findFirst()
                .orElseThrow(PriceNotFoundException::new);

        return selectedPrice;
    }

}
