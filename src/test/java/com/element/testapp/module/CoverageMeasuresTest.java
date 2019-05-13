package com.element.testapp.module;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test suite tfor testing CoverageMeasures behaviour
 * @author Viktar Lebedzeu
 */
@Slf4j
public class CoverageMeasuresTest {

    @Test
    public void testCheckCoverage() {
        // Testing measures checking with defined both min and max values
        CoverageMeasures measures = new CoverageMeasures(-20L, 50L);
        Assert.assertTrue(measures.checkCoverage(-10L));
        Assert.assertTrue(measures.checkCoverage(-20L));
        Assert.assertTrue(measures.checkCoverage(40L));
        Assert.assertTrue(measures.checkCoverage(50L));

        Assert.assertFalse(measures.checkCoverage(-21L));
        Assert.assertFalse(measures.checkCoverage(51L));

        // Testing measures checking with undefined min value
        measures = new CoverageMeasures(null, 50L);
        Assert.assertTrue(measures.checkCoverage(-10L));
        Assert.assertTrue(measures.checkCoverage(-20L));
        Assert.assertTrue(measures.checkCoverage(40L));
        Assert.assertTrue(measures.checkCoverage(50L));

        Assert.assertTrue(measures.checkCoverage(-21L));
        Assert.assertTrue(measures.checkCoverage(-100L));
        Assert.assertFalse(measures.checkCoverage(51L));

        // Testing measures checking with undefined max value
        measures = new CoverageMeasures(-20L, null);
        Assert.assertTrue(measures.checkCoverage(-10L));
        Assert.assertTrue(measures.checkCoverage(-20L));
        Assert.assertTrue(measures.checkCoverage(40L));
        Assert.assertTrue(measures.checkCoverage(50L));

        Assert.assertFalse(measures.checkCoverage(-21L));
        Assert.assertTrue(measures.checkCoverage(51L));
        Assert.assertTrue(measures.checkCoverage(100L));

        // Testing measures checking with undefined both min and max values
        measures = new CoverageMeasures();
        Assert.assertTrue(measures.checkCoverage(-10L));
        Assert.assertTrue(measures.checkCoverage(-20L));
        Assert.assertTrue(measures.checkCoverage(40L));
        Assert.assertTrue(measures.checkCoverage(50L));

        Assert.assertTrue(measures.checkCoverage(-21L));
        Assert.assertTrue(measures.checkCoverage(-1000L));
        Assert.assertTrue(measures.checkCoverage(51L));
        Assert.assertTrue(measures.checkCoverage(1000L));
        Assert.assertTrue(measures.checkCoverage(Long.MIN_VALUE));
        Assert.assertTrue(measures.checkCoverage(Long.MAX_VALUE));
    }
}
