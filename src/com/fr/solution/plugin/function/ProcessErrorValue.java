package com.fr.solution.plugin.function;

import com.fr.general.GeneralUtils;
import com.fr.script.AbstractFunction;
import com.fr.stable.ArrayUtils;
import com.fr.stable.Primitive;
import com.fr.stable.StringUtils;

/**
 * 处理公式错误值
 */
public class ProcessErrorValue extends AbstractSolutionFunction {
    @Override
    public Object solve(Object[] args) {
        int len = ArrayUtils.getLength(args);
        if (len == 0) {
            return Primitive.ERROR_VALUE;
        }
        Object content = args[0];
        if (content == Primitive.ERROR_NAME || content == Primitive.ERROR_VALUE || content == Primitive.NULL) {
            String result = StringUtils.EMPTY;
            if (len > 1) {
                result = GeneralUtils.objectToString(args[1]);
            }
            return result;
        }
        return content;
    }
}
