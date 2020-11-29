package com.inakilab.storerateapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inakilab.storerateapi.dto.PriceDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerIntegrationTest {
    public static final long SOME_BRAND_ID = 2L;
    public static final long SOME_PRICE_ID = 201002L;
    public static final String SOME_DATE_TEXT = "2020-05-10T00:00:00Z";

    public static final String SOME_INCORRECT_INPUT = "chuchu";

    public static final long GIVEN_PRODUCT_ID = 35455L;
    public static final long GIVEN_BRAND_ID = 1L;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private static LocalDateTime SOME_DATE = LocalDateTime.of(2020, 5, 10, 0, 0, 0);

    @Test
    public void it_should_return_price_for_day_14_at_10_for_product_35455_and_brand_1() throws Exception {
        String selectedDate = "2020-06-14T10:00:00Z";
        String url = String.format("/api/price/%s/%s?date=%s", GIVEN_BRAND_ID, GIVEN_PRODUCT_ID, selectedDate);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        String responseAsString = result.getResponse().getContentAsString();
        PriceDto priceDto = objectMapper.readValue(responseAsString, PriceDto.class);

        assertEquals(BigDecimal.valueOf(35.5), priceDto.getValue());
    }

    @Test
    public void it_should_return_price_for_day_14_at_16_for_product_35455_and_brand_1() throws Exception {
        String selectedDate = "2020-06-14T16:00:00Z";
        String url = String.format("/api/price/%s/%s?date=%s", GIVEN_BRAND_ID, GIVEN_PRODUCT_ID, selectedDate);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        String responseAsString = result.getResponse().getContentAsString();
        PriceDto priceDto = objectMapper.readValue(responseAsString, PriceDto.class);

        assertEquals(BigDecimal.valueOf(25.45), priceDto.getValue());
    }

    @Test
    public void it_should_return_price_for_day_14_at_21_for_product_35455_and_brand_1() throws Exception {
        String selectedDate = "2020-06-14T21:00:00Z";
        String url = String.format("/api/price/%s/%s?date=%s", GIVEN_BRAND_ID, GIVEN_PRODUCT_ID, selectedDate);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        String responseAsString = result.getResponse().getContentAsString();
        PriceDto priceDto = objectMapper.readValue(responseAsString, PriceDto.class);

        assertEquals(BigDecimal.valueOf(35.50), priceDto.getValue());
    }

    @Test
    public void it_should_return_price_for_day_15_at_10_for_product_35455_and_brand_1() throws Exception {
        String selectedDate = "2020-06-15T10:00:00Z";
        String url = String.format("/api/price/%s/%s?date=%s", GIVEN_BRAND_ID, GIVEN_PRODUCT_ID, selectedDate);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        String responseAsString = result.getResponse().getContentAsString();
        PriceDto priceDto = objectMapper.readValue(responseAsString, PriceDto.class);

        assertEquals(BigDecimal.valueOf(30.50), priceDto.getValue());
    }

    @Test
    public void it_should_return_price_for_day_16_at_21_for_product_35455_and_brand_1() throws Exception {
        String selectedDate = "2020-06-16T21:00:00Z";
        String url = String.format("/api/price/%s/%s?date=%s", GIVEN_BRAND_ID, GIVEN_PRODUCT_ID, selectedDate);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        String responseAsString = result.getResponse().getContentAsString();
        PriceDto priceDto = objectMapper.readValue(responseAsString, PriceDto.class);

        assertEquals(BigDecimal.valueOf(38.95), priceDto.getValue());
    }

    @Test
    public void it_should_return_not_found_for_non_registered_dates() throws Exception {
        String selectedDate = "2010-06-16T21:00:00Z";
        String url = String.format("/api/price/%s/%s?date=%s", GIVEN_BRAND_ID, GIVEN_PRODUCT_ID, selectedDate);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    public void it_should_return_bad_request_for_given_text_instead_brand_id() throws Exception {
        String selectedDate = "2020-06-16T21:00:00Z";
        String url = String.format("/api/price/%s/%s?date=%s", SOME_INCORRECT_INPUT, GIVEN_PRODUCT_ID, selectedDate);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void it_should_return_bad_request_for_given_text_instead_product_id() throws Exception {
        String selectedDate = "2020-06-16T21:00:00Z";
        String url = String.format("/api/price/%s/%s?date=%s", GIVEN_BRAND_ID, SOME_INCORRECT_INPUT, selectedDate);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();
    }

}
