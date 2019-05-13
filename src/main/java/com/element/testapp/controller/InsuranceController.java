package com.element.testapp.controller;

import com.element.testapp.dto.CoverageRequest;
import com.element.testapp.dto.PriceResponse;
import com.element.testapp.module.InsuranceModule;
import com.element.testapp.service.ModulesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Insurance controller
 * @author Viktar Lebedzeu
 */
@RestController
@RequestMapping(path = "/insurance")
@Slf4j
public class InsuranceController {
    /** Modules service */
    private final ModulesService modulesService;

    @Autowired
    public InsuranceController(ModulesService modulesService) {
        this.modulesService = modulesService;
    }

    @GetMapping(path = "/modules")
    public @ResponseBody ResponseEntity<List<InsuranceModule>> availableModules() {
        final List<InsuranceModule> modules = modulesService.getAvailableModules();
        return new ResponseEntity<>(modules, HttpStatus.OK);
    }

    @PostMapping(path = "/price")
    public @ResponseBody ResponseEntity<PriceResponse> calculatePrice(@RequestBody CoverageRequest request) {
        final PriceResponse response = modulesService.calculatePrice(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
