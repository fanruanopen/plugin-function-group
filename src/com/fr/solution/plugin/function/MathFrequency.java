package com.fr.solution.plugin.function;

import com.fr.general.FArray;
import com.fr.general.GeneralUtils;
import com.fr.script.AbstractFunction;
import com.fr.stable.ArrayUtils;
import com.fr.stable.Primitive;
import org.apache.commons.math3.stat.Frequency;

/**
 * 计算给定的值出现的次数和平率
 */
public class MathFrequency extends AbstractSolutionFunction {
    @Override
    public Object solve(Object[] args) {
        int len = ArrayUtils.getLength(args);
        if (len < 2) {
            return Primitive.ERROR_VALUE;
        }
        Object content = args[0];
        Frequency f = new Frequency();
        if (content instanceof FArray) {
            FArray array = (FArray) content;
            int size = array.length();
            for (int i = 0; i < size; i++) {
                f.addValue(GeneralUtils.objectToString(array.elementAt(i)));
            }
        }
        String filter = GeneralUtils.objectToString(args[1]);
        if (len > 2) {
            return f.getPct(filter);
        } else {
            return f.getCount(filter);
        }
    }
}
