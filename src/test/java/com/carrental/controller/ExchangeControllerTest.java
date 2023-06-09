package com.carrental.controller;

import com.carrental.api.exchangeRateApiClient.client.ExchangeApiClient;
import com.carrental.controller.ExchangeController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@WebMvcTest(ExchangeController.class)
class ExchangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExchangeApiClient exchangeApiClient;

    @Test
    public void testGetExchangeRate() throws Exception {
        Map<String, Double> expectedRates = new HashMap<>();
        expectedRates.put("USD", 1.0);
        expectedRates.put("EUR", 0.8);
        expectedRates.put("PLN", 3.7);

        when(exchangeApiClient.getResponse()).thenReturn(ResponseEntity.ok(expectedRates));

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/exchange"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.USD").value(1.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.EUR").value(0.8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.PLN").value(3.7));

        Mockito.verify(exchangeApiClient, Mockito.times(1)).getResponse();
        Mockito.verifyNoMoreInteractions(exchangeApiClient);
    }
}