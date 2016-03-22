package com.fr.solution.plugin.function;


import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

/**
 * 求方差
 */
public class MathVariance extends AbstractMathSummaryFunction {

    @Override
    public double getValue(SummaryStatistics summaryStatistics) {
        return summaryStatistics.getVariance();
    }

}
