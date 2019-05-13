package com.element.testapp.module;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Base interface of all insurance modules
 * @author Viktar Lebedzeu
 */
@Component
public abstract class InsuranceModule {
    @Getter
    @Setter
    private CoverageMeasures coverage;

    @Getter
    @Setter
    private double risk;


}
