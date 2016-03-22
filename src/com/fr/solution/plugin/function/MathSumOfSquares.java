package com.fr.solution.plugin.function;


import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

/**
 * 计算平方和
 */
public class MathSumOfSquares extends AbstractMathSummaryFunction {
    @Override
    public double getValue(SummaryStatistics summaryStatistics) {
        return summaryStatistics.getSumsq();
    }
}
