package com.currencyexchangeservice.controller;

import com.currencyexchangeservice.model.CurrencyExchange;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency-exchange")
@AllArgsConstructor
public class CurrencyExchangeController {
    private final Environment environment;

    @GetMapping("/from/{from}/to/{to}")
    public CurrencyExchange getExchangeValue(@PathVariable String from,
                                             @PathVariable String to) {
        CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);

        return currencyExchange;
    }
}
