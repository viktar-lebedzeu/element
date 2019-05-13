package com.element.testapp.module;

import com.element.testapp.config.InsuranceConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author Viktar Lebedzeu
 */
@Service
@Slf4j
public class ModuleRegistry {
    private final InsuranceConfiguration configuration;

    @Autowired
    public ModuleRegistry(InsuranceConfiguration configuration) {
        this.configuration = configuration;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void handleContextRefresh(ContextRefreshedEvent event) {
        log.info("Handling ContextRefreshedEvent");
    }
}
