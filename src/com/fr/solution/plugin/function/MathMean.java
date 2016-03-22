package com.fr.solution.plugin.function;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

/**
 * 计算算术平均数
 */
public class MathMean extends AbstractMathSummaryFunction {
    @Override
    public double getValue(SummaryStatistics summaryStatistics) {
        return summaryStatistics.getMean();
    }

    public static void main(String... args) {
        Object[] values = {1, 2, 3, 4};
        System.out.println(new MathMean().run(values));
    }
}
