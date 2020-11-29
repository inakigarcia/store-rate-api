package com.inakilab.storerateapi.repository;

import com.inakilab.storerateapi.model.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PriceRepositoryTest {
    private static final long BRAND_ID = 1L;
    private static final long PRODUCT_ID = 1001;

    private static final long BRAND_ID_NON_EXISTING = 55L;
    private static final long PRODUCT_ID_NON_EXISTING  = 5001;

    @Autowired
    private PriceRepository priceRepository;

    @Test
    @Sql("classpath:price_repository_mock_data.sql")
    void it_should_return_the_only_proce_for_given_criteria_and_dates() {
        LocalDateTime localDateTime = LocalDateTime.of(2010, 5, 15, 0, 0, 0);

        List<Price> priceOptional = priceRepository.findPriceListByPriority(BRAND_ID, PRODUCT_ID, localDateTime);

        assertEquals(201003, priceOptional.get(0).getPriceList());
    }

    @Test
    @Sql("classpath:price_repository_mock_data.sql")
    void it_should_return_the_highest_priority_first_for_given_criteria_when_there_are_several_prices() {
        LocalDateTime localDateTime = LocalDateTime.of(2010, 1, 15, 0, 0, 0);

        List<Price> prices = priceRepository.findPriceListByPriority(BRAND_ID, PRODUCT_ID, localDateTime);

        assertEquals(2, prices.size());
        assertEquals(201002, prices.get(0).getPriceList());
    }

    @Test
    @Sql("classpath:price_repository_mock_data.sql")
    void it_should_return_empty_when_no_prices_found_for_given_date() {
        LocalDateTime localDateTime = LocalDateTime.of(2011, 1, 15, 0, 0, 0);

        List<Price> prices = priceRepository.findPriceListByPriority(BRAND_ID, PRODUCT_ID, localDateTime);

        assertEquals(0, prices.size());
    }

    @Test
    @Sql("classpath:price_repository_mock_data.sql")
    void it_should_return_empty_when_no_prices_found_for_given_brandId() {
        LocalDateTime localDateTime = LocalDateTime.of(2010, 1, 15, 0, 0, 0);

        List<Price> prices = priceRepository.findPriceListByPriority(BRAND_ID_NON_EXISTING, PRODUCT_ID, localDateTime);

        assertEquals(0, prices.size());
    }

    @Test
    @Sql("classpath:price_repository_mock_data.sql")
    void it_should_return_empty_when_no_prices_found_for_given_productId() {
        LocalDateTime localDateTime = LocalDateTime.of(2010, 1, 15, 0, 0, 0);

        List<Price> prices = priceRepository.findPriceListByPriority(BRAND_ID, PRODUCT_ID_NON_EXISTING, localDateTime);

        assertEquals(0, prices.size());
    }
}
