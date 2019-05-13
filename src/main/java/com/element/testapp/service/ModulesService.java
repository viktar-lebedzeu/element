package com.element.testapp.service;

import com.element.testapp.config.InsuranceConfiguration;
import com.element.testapp.dto.CoverageRequest;
import com.element.testapp.dto.PriceResponse;
import com.element.testapp.module.InsuranceModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Provides service methods to work with modules
 * @author Viktar Lebedzeu
 */
@Service
@Slf4j
public class ModulesService {
    /** Insurance configuration bean */
    private final InsuranceConfiguration configuration;

    @Autowired
    public ModulesService(InsuranceConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * Returns list of available insurance modules
     * @return List of available modules
     */
    public List<InsuranceModule> getAvailableModules() {
        if (/* configuration == null ||*/ configuration.getModules() == null) {
            return new ArrayList<>(0);
        }
        Comparator<InsuranceModule> byId = Comparator.comparing(InsuranceModule::getId);
        return configuration.getModules().values()
                .stream()
                .sorted(byId).collect(Collectors.toList());
    }

    /**
     * Calculates price for all selected modules
     * @param request List of selected modules and theirs coverage
     * @return Calculated price response
     */
    public PriceResponse calculatePrice(CoverageRequest request) {
        PriceResponse response = new PriceResponse();
        final Map<String, InsuranceModule> modules = configuration.getModules();
        // Skipping wrong request values
        if (request != null && request.getModules() != null && !request.getModules().isEmpty() && modules != null) {
            request.getModules().forEach((moduleId, selectedCoverage) -> {

                // checking for correct module Id
                if (modules.containsKey(moduleId)) {
                    final InsuranceModule module = modules.get(moduleId);

                    if (selectedCoverage == null) {
                        // Null coverage is forbidden
                        response.addRecord(moduleId, null, "Null value of coverage is not allowed");

                    }
                    else {
                        try {
                            // Adding correctly calculated price
                            double price = module.getPrice(selectedCoverage);
                            response.addRecord(moduleId, price, null);
                        }
                        catch (IllegalArgumentException e) {
                            response.addRecord(moduleId, null, e.getMessage());
                        }
                    }
                }
                else {
                    // Add incorrect module w/ error message into response list
                    response.addRecord(moduleId, null, "Incorrect module Id [\"" + moduleId + "\"]");
                }
            });
        }
        return response;
    }
}
