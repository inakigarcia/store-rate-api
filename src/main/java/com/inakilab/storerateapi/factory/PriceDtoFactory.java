package com.inakilab.storerateapi.factory;

import com.inakilab.storerateapi.dto.PriceDto;
import com.inakilab.storerateapi.model.Price;

public class PriceDtoFactory {

    public static PriceDto create(Price price) {
        return PriceDto.builder()
                .brandId(price.getBrandId())
                .currency(price.getCurrency())
                .endDate(price.getEndDate())
                .startDate(price.getStartDate())
                .priceList(price.getPriceList())
                .productId(price.getProductId())
                .value(price.getValue())
                .build();
    }
}
