package com.element.testapp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"unittests"})
@Slf4j
public class TestAppApplicationTest {

    @Test
    public void contextLoads() {
        log.info("Running contextLoads()...");
    }
}
