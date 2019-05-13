package com.element.testapp.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Insurance coverage measures
 * @author Viktar Lebedzeu
 */
@Data
@NoArgsConstructor
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
    boolean checkCoverage(long coverage) {
        return (min == null || min <= coverage) && (max == null || max >= coverage);
    }

    /**
     * Returns string of coverage measures
     * @return Generated measures string
     */
    String measuresString() {
        return "[" + min + ", " + max + "]";
    }
}
