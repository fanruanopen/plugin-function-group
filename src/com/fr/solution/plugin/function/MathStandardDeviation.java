package com.fr.solution.plugin.function;


import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

/**
 * 计算标准差
 */
public class MathStandardDeviation extends AbstractMathSummaryFunction {

    @Override
    public double getValue(SummaryStatistics summaryStatistics) {
        return summaryStatistics.getStandardDeviation();
    }
}
