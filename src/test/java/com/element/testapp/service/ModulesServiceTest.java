package com.element.testapp.service;

import com.element.testapp.config.InsuranceConfiguration;
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
        final Map<String, InsuranceModule> saveModules = configuration.getModules();

        configuration.setModules(new HashMap<>());
        final List<InsuranceModule> modules = service.getAvailableModules();
        Assert.assertEquals(0, modules.size());

        final List<String> moduleIds = modules.stream().map(InsuranceModule::getId).collect(Collectors.toList());
        Assert.assertEquals("[]", StringUtils.join(moduleIds));

        // Restoring previous values
        configuration.setModules(saveModules);
    }
}
