package com.element.testapp.config;

import com.element.testapp.module.InsuranceModule;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Insurance configuration component
 * @author Viktar Lebedzeu
 */
@Component
@Slf4j
@Data
@ConfigurationProperties(prefix = "element.insurance", ignoreInvalidFields = true, ignoreUnknownFields = true)
public class InsuranceConfiguration implements InitializingBean {
    /** Insurance module settings */
    private Map<String, InsuranceModule> modules;


    private String name;

    @Override
    public void afterPropertiesSet() throws Exception {
        // Updating id field for all modules
        modules.entrySet().parallelStream().forEach(e -> e.getValue().setId(e.getKey()));
    }
}
