package com.fr.solution.plugin.function;


import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

/**
 * 计算几何平均数
 */
public class MathGeometricMean extends AbstractMathSummaryFunction {
    @Override
    public double getValue(SummaryStatistics summaryStatistics) {
        return summaryStatistics.getGeometricMean();
    }
}
