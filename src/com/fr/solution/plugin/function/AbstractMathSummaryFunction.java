package com.fr.solution.plugin.function;

import com.fr.general.GeneralUtils;
import com.fr.script.AbstractFunction;
import com.fr.stable.ArrayUtils;
import com.fr.stable.Primitive;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

/**
 * Created by richie on 16/3/22.
 */
public abstract class AbstractMathSummaryFunction extends AbstractFunction {

    @Override
    public Object run(Object[] args) {
        if (ArrayUtils.isEmpty(args)) {
            return Primitive.ERROR_VALUE;
        }
        SummaryStatistics summaryStatistics = new SummaryStatistics();
        for (Object value : args) {
            Number number = GeneralUtils.objectToNumber(value);
            summaryStatistics.addValue(number.doubleValue());
        }
        return getValue(summaryStatistics);
    }

    public abstract double getValue(SummaryStatistics summaryStatistics);
}
