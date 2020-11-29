package com.inakilab.storerateapi.factory;

import com.inakilab.storerateapi.dto.PriceDto;
import com.inakilab.storerateapi.model.Price;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PriceDtoFactoryTest {

    private Price dummyPrice;

    private Price price;

    @Test
    public void it_should_generate_PriceDto_with_same_values_of_given_Price() {
        price = Price.builder()
                .brandId(123L)
                .currency("EUR")
                .endDate(LocalDateTime.of(2010, 4, 15, 0, 0, 0))
                .startDate(LocalDateTime.of(2010, 5, 15, 0, 0, 0))
                .priceList(124L)
                .productId(125L)
                .value(BigDecimal.TEN)
                .build();

        PriceDto res = PriceDtoFactory.create(price);

        assertEquals(res.getBrandId(), price.getBrandId());
        assertEquals(res.getPriceList(), price.getPriceList());
        assertEquals(res.getProductId(), price.getProductId());
        assertEquals(res.getCurrency(), price.getCurrency());
        assertEquals(res.getEndDate(), price.getEndDate());
        assertEquals(res.getStartDate(), price.getStartDate());
        assertEquals(res.getValue(), price.getValue());
    }

    @Test
    public void it_should_get_just_once_each_required_values_of_given_Price() {
        dummyPrice = mock(Price.class);

        PriceDtoFactory.create(dummyPrice);

        verify(dummyPrice, times(1)).getBrandId();
        verify(dummyPrice, times(1)).getPriceList();
        verify(dummyPrice, times(1)).getProductId();
        verify(dummyPrice, times(1)).getCurrency();
        verify(dummyPrice, times(1)).getEndDate();
        verify(dummyPrice, times(1)).getStartDate();
        verify(dummyPrice, times(1)).getValue();
    }
}
