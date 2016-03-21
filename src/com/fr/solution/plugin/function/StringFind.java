package com.fr.solution.plugin.function;

import com.fr.general.GeneralUtils;
import com.fr.script.AbstractFunction;
import com.fr.stable.ArrayUtils;
import com.fr.stable.Primitive;

/**
 * 字符串包含的索引查找
 */
public class StringFind extends AbstractFunction {
    @Override
    public Object run(Object[] args) {
        int len = ArrayUtils.getLength(args);
        if (len < 2) {
            return Primitive.ERROR_VALUE;
        }
        String source = GeneralUtils.objectToString(args[0]);
        String target = GeneralUtils.objectToString(args[1]);
        boolean positive = true;
        if (len > 2) {
            positive = Boolean.parseBoolean(GeneralUtils.objectToString(args[2]));
        }
        if (positive) {
            return source.indexOf(target);
        } else {
            return source.lastIndexOf(target);
        }
    }
}
