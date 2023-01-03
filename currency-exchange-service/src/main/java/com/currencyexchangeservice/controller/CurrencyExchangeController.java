package com.currencyexchangeservice.controller;

import com.currencyexchangeservice.model.CurrencyExchange;
import com.currencyexchangeservice.repository.CurrencyExchangeRepo;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency-exchange")
@AllArgsConstructor
public class CurrencyExchangeController {
    private final Environment environment;
    private final CurrencyExchangeRepo repository;

    @GetMapping("/from/{from}/to/{to}")
    public CurrencyExchange getExchangeValue(@PathVariable String from,
                                             @PathVariable String to) {
        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to)
                .orElseThrow(() -> new RuntimeException("Unable to find data for " + from + " and " + to));

        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);

        return currencyExchange;
    }
}
