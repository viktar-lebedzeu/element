package com.element.testapp.module;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Insurance module settings
 * @author Viktar Lebedzeu
 */
@Data
@Slf4j
public class InsuranceModule {
    /** Module Id */
    private String id;
    /** Coverage measures */
    private CoverageMeasures coverage;
    /** Risk percentage */
    private double risk;

    /**
     * Checks if the given value fits coverage measures
     * @param value Coverage value to check
     * @return True if the given value fits coverage measures, false otherwise
     */
    public boolean checkCoverageValue(long value) {
        return (coverage == null || coverage.checkCoverage(value));
    }

    public double getPrice(long value) {
        if (!checkCoverageValue(value)) {
            log.error("The coverage value {} is not correct for {} measures", value, coverage.measuresString());
            throw new IllegalArgumentException(
                    "The coverage value " + value +
                    " is not correct for " + coverage.measuresString() + " measures");
        }
        return ((double) value * risk);
    }
}
