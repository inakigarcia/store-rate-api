package com.inakilab.storerateapi.service;

import com.inakilab.storerateapi.dto.PriceDto;
import com.inakilab.storerateapi.model.Price;
import com.inakilab.storerateapi.repository.PriceRepository;
import com.inakilab.storerateapi.service.impl.PriceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Service
@RequiredArgsConstructor
public class PriceServiceTest {

    public static final long GIVEN_ID = 123L;
    public static final long GIVEN_PRODUCT_ID = 125L;
    public static final LocalDateTime GIVEN_DATE = LocalDateTime.of(2010, 5, 15, 0, 0, 0);

    @InjectMocks
    private PriceServiceImpl priceService;

    @Mock
    private PriceRepository priceRepository;

    private Price price1;
    private Price price2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        price1 = Price.builder()
                .brandId(123L)
                .currency("EUR")
                .endDate(LocalDateTime.of(2010, 4, 15, 0, 0, 0))
                .startDate(LocalDateTime.of(2010, 5, 15, 0, 0, 0))
                .priceList(124L)
                .productId(125L)
                .value(BigDecimal.TEN)
                .build();
        price2 = Price.builder()
                .brandId(123L)
                .currency("EUR")
                .endDate(LocalDateTime.of(2010, 4, 15, 0, 0, 0))
                .startDate(LocalDateTime.of(2010, 5, 15, 0, 0, 0))
                .priceList(124L)
                .productId(125L)
                .value(BigDecimal.TEN)
                .build();
    }

    @Test
    public void it_should_call_repository_with_once_given_parameters() throws Exception {
        doReturn(Collections.singletonList(price1)).when(priceRepository).findPriceListByPriority(GIVEN_ID, GIVEN_PRODUCT_ID, GIVEN_DATE);

        priceService.getPriceByPriority(GIVEN_ID, GIVEN_PRODUCT_ID, GIVEN_DATE);

        verify(priceRepository, times(1)).findPriceListByPriority(GIVEN_ID, GIVEN_PRODUCT_ID, GIVEN_DATE);
    }

    @Test()
    public void it_should_return_empty_when_no_Price_found() {
        doReturn(Arrays.asList()).when(priceRepository).findPriceListByPriority(GIVEN_ID, GIVEN_PRODUCT_ID, GIVEN_DATE);

        Exception exception = assertThrows(
                Exception.class,
                () -> priceService.getPriceByPriority(GIVEN_ID, GIVEN_PRODUCT_ID, GIVEN_DATE)
        );

    }

    @Test
    public void it_should_return_a_PriceDto_if_some_price_found() throws Exception  {
        doReturn(Collections.singletonList(price1)).when(priceRepository).findPriceListByPriority(GIVEN_ID, GIVEN_PRODUCT_ID, GIVEN_DATE);

        PriceDto res = priceService.getPriceByPriority(GIVEN_ID, GIVEN_PRODUCT_ID, GIVEN_DATE);

        assertNotNull(res);
    }

    @Test
    public void it_should_return_a_PriceDto_for_the_only_found_price() throws Exception  {
        doReturn(Collections.singletonList(price1)).when(priceRepository).findPriceListByPriority(GIVEN_ID, GIVEN_PRODUCT_ID, GIVEN_DATE);

        PriceDto res = priceService.getPriceByPriority(GIVEN_ID, GIVEN_PRODUCT_ID, GIVEN_DATE);

        assertEquals(res.getBrandId(), price1.getBrandId());
        assertEquals(res.getPriceList(), price1.getPriceList());
        assertEquals(res.getProductId(), price1.getProductId());
        assertEquals(res.getCurrency(), price1.getCurrency());
        assertEquals(res.getEndDate(), price1.getEndDate());
        assertEquals(res.getStartDate(), price1.getStartDate());
        assertEquals(res.getValue(), price1.getValue());
    }

    @Test
    public void it_should_return_a_PriceDto_for_the_first_found_price() throws Exception  {
        doReturn(Arrays.asList(price1, price2)).when(priceRepository).findPriceListByPriority(GIVEN_ID, GIVEN_PRODUCT_ID, GIVEN_DATE);

        PriceDto res = priceService.getPriceByPriority(GIVEN_ID, GIVEN_PRODUCT_ID, GIVEN_DATE);

        assertEquals(res.getBrandId(), price1.getBrandId());
        assertEquals(res.getPriceList(), price1.getPriceList());
        assertEquals(res.getProductId(), price1.getProductId());
        assertEquals(res.getCurrency(), price1.getCurrency());
        assertEquals(res.getEndDate(), price1.getEndDate());
        assertEquals(res.getStartDate(), price1.getStartDate());
        assertEquals(res.getValue(), price1.getValue());
    }
}
