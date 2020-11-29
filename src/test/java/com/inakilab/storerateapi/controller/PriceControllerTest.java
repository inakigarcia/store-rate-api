package com.inakilab.storerateapi.controller;

import com.inakilab.storerateapi.dto.PriceDto;
import com.inakilab.storerateapi.exception.PriceNotFoundException;
import com.inakilab.storerateapi.service.PriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceController.class)
public class PriceControllerTest {
    public static final long SOME_BRAND_ID = 1L;
    public static final long SOME_PRICE_ID = 10L;
    public static final String SOME_DATE_TEXT = "2020-01-01T00:00:00Z";
    public static final String SOME_INCORRECT_INPUT = "chuchu";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    private PriceDto priceDto;

    private static LocalDateTime SOME_DATE = LocalDateTime.of(2020, 1, 1, 0, 0, 0);

    @Test
    public void it_should_inform_error_400_when_finding_Prize_with_bad_brandId_parameter() throws Exception {
        String url = String.format("/api/price/%s/%s?date=%s", SOME_INCORRECT_INPUT, SOME_PRICE_ID, SOME_DATE_TEXT);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();

        assertEquals(HttpServletResponse.SC_BAD_REQUEST, result.getResponse().getStatus());
    }

    @Test
    public void it_should_inform_error_400_when_finding_Prize_with_bad_date_parameter() throws Exception {
        String url = String.format("/api/price/%s/%s?date=%s", SOME_BRAND_ID, SOME_PRICE_ID, SOME_INCORRECT_INPUT);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void it_should_inform_error_404_when_no_prize_found() throws Exception {
        String url = String.format("/api/price/%s/%s?date=%s", SOME_BRAND_ID, SOME_PRICE_ID, SOME_DATE_TEXT);
        doThrow(PriceNotFoundException.class).when(priceService).getPriceByPriority(SOME_BRAND_ID, SOME_PRICE_ID, SOME_DATE);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    public void it_should_return_OK_when_a_Proce_is_found() throws Exception {
        priceDto = PriceDto.builder().build();
        String url = String.format("/api/price/%s/%s?date=%s", SOME_BRAND_ID, SOME_PRICE_ID, SOME_DATE_TEXT);
        doReturn(priceDto).when(priceService).getPriceByPriority(SOME_BRAND_ID, SOME_PRICE_ID, SOME_DATE);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
    }
}
