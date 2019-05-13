package com.element.testapp.module;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Insurance coverage measures
 * @author Viktar Lebedzeu
 */
@Data
@AllArgsConstructor
public class CoverageMeasures {
    /** Minimum measure */
    private Long min;

    /** Maximum measure */
    private Long max;

    /**
     * Checks if coverage value fits measures
     * @param coverage Coverage value to check
     * @return True if coverage fits measures, false otherwise
     */
    public boolean checkCoverage(long coverage) {
        return (min == null || min <= coverage) && (max == null || max >= coverage);
    }
}
