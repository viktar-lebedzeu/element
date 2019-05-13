package com.element.testapp.service;

import com.element.testapp.config.InsuranceConfiguration;
import com.element.testapp.module.InsuranceModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
        if (configuration == null || configuration.getModules() == null) {
            return new ArrayList<>(0);
        }
        Comparator<InsuranceModule> byId = Comparator.comparing(InsuranceModule::getId);
        return configuration.getModules().values()
                .stream()
                .sorted(byId).collect(Collectors.toList());
    }
}
