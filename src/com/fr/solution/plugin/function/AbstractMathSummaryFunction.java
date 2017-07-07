package com.fr.solution.plugin.function;

import com.fr.general.FArray;
import com.fr.general.GeneralUtils;
import com.fr.stable.ArrayUtils;
import com.fr.stable.Primitive;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

/**
 * Created by richie on 16/3/22.
 */
public abstract class AbstractMathSummaryFunction extends AbstractSolutionFunction {

    @Override
    public Object solve(Object[] args) {
        if (ArrayUtils.isEmpty(args)) {
            return Primitive.ERROR_VALUE;
        }
        Object[] data = generateArray(args);
        SummaryStatistics summaryStatistics = new SummaryStatistics();
        for (Object value : data) {
            Number number = GeneralUtils.objectToNumber(value);
            summaryStatistics.addValue(number.doubleValue());
        }
        return getValue(summaryStatistics);
    }

    public abstract double getValue(SummaryStatistics summaryStatistics);

    private Object[] generateArray(Object[] args) {
        if (ArrayUtils.getLength(args) > 1) {
            return args;
        }
        Object element = args[0];
        if (element instanceof FArray) {
            return ((FArray)element).asObjects();
        } else {
            return args;
        }
    }
}
