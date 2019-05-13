package com.element.testapp.service;

import com.element.testapp.config.InsuranceConfiguration;
import com.element.testapp.dto.CoverageRequest;
import com.element.testapp.dto.PriceResponse;
import com.element.testapp.module.InsuranceModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Viktar Lebedzeu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles({"unittests"})
@SpringBootTest
@Slf4j
public class ModulesServiceTest {
    @Autowired
    private ModulesService service;

    @Autowired
    private InsuranceConfiguration configuration;

    @Test
    public void testAvailableModules() {
        final List<InsuranceModule> modules = service.getAvailableModules();
        final List<String> moduleIds = modules.stream().map(InsuranceModule::getId).collect(Collectors.toList());
        Assert.assertEquals("[bike, electronics, jewelry, sports-equipment]", StringUtils.join(moduleIds));
    }

    @Test
    public void testEmptyAvailableModules() {
        // Saving previous values
        final Map<String, InsuranceModule> savedModules = configuration.getModules();

        configuration.setModules(new HashMap<>());
        List<InsuranceModule> modules = service.getAvailableModules();
        Assert.assertEquals(0, modules.size());

        List<String> moduleIds = modules.stream().map(InsuranceModule::getId).collect(Collectors.toList());
        Assert.assertEquals("[]", StringUtils.join(moduleIds));

        configuration.setModules(null);
        modules = service.getAvailableModules();
        Assert.assertEquals(0, modules.size());

        moduleIds = modules.stream().map(InsuranceModule::getId).collect(Collectors.toList());
        Assert.assertEquals("[]", StringUtils.join(moduleIds));

        // Restoring previous values
        configuration.setModules(savedModules);
    }

    @Test
    public void testCalculatePrice() {
        CoverageRequest request = new CoverageRequest().addModule("bike", 1000L);
        checkCalculatedPrice(request, service.calculatePrice(request), 300.0, 0);

        request = new CoverageRequest().addModule("bike", 5000L);
        checkCalculatedPrice(request, service.calculatePrice(request), 0.0, 1);

        request = new CoverageRequest().addModule("bike", -100L);
        checkCalculatedPrice(request, service.calculatePrice(request), 0.0, 1);

        request = new CoverageRequest().addModule("bike", null);
        checkCalculatedPrice(request, service.calculatePrice(request), 0.0, 1);

        CoverageRequest emptyRequest = new CoverageRequest();
        checkCalculatedPrice(emptyRequest, service.calculatePrice(null), 0.0, 0);

        CoverageRequest requestWithoutModules = new CoverageRequest();
        requestWithoutModules.setModules(null);
        checkCalculatedPrice(emptyRequest, service.calculatePrice(requestWithoutModules), 0.0, 0);
        checkCalculatedPrice(emptyRequest, service.calculatePrice(emptyRequest), 0.0, 0);

        request = new CoverageRequest()
                .addModule("bike", 2000L) // 600
                .addModule("jewelry", 8000L) // 400
                .addModule("electronics", 6000L) // 2100
                .addModule("sports-equipment", 15000L) // 4500
                .addModule("incorrect-module-id", 100000L) // -
        ;
        checkCalculatedPrice(request, service.calculatePrice(request), 7600.0, 1);

        // Saving previous values
        final Map<String, InsuranceModule> savedModules = configuration.getModules();

        configuration.setModules(null);
        checkCalculatedPrice(request, service.calculatePrice(request), 0.0, 5);

        // Restoring previous values
        configuration.setModules(savedModules);
    }

    /**
     * Auxiliary method to check request, response and expected values
     * @param request Request
     * @param response Reponse for checking
     * @param expectedTotalPrice Expected total price
     * @param expectedErrors Expected errors count
     */
    private static void checkCalculatedPrice(CoverageRequest request, PriceResponse response,
                                             Double expectedTotalPrice, int expectedErrors) {
        Assert.assertNotNull(response);
        Assert.assertEquals(request.getModules().size(), response.getRecords().size());
        Assert.assertEquals(expectedTotalPrice, response.getTotalPrice(), 5);
        Assert.assertEquals(expectedErrors, response.getErrorsCount());
    }
}
