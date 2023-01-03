package com.limitsservice.controller;

import com.limitsservice.configuration.VariablesConfig;
import com.limitsservice.model.Limits;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/limits")
@AllArgsConstructor
public class LimitsController {

    private final VariablesConfig config;

    @GetMapping
    public Limits getLimits() {
        return new Limits(config.getMinimum(), config.getMaximum());
    }
}
