package com.fr.solution.plugin.function.string;

import com.fr.general.GeneralUtils;
import com.fr.script.AbstractFunction;
import com.fr.solution.plugin.function.AbstractSolutionFunction;
import com.fr.stable.ArrayUtils;
import com.fr.stable.Primitive;

/**
 * Created by richie on 16/3/21.
 */
public class StringMatch extends AbstractSolutionFunction {
    @Override
    public Object solve(Object[] args) {
        int len = ArrayUtils.getLength(args);
        if (len < 2) {
            return Primitive.ERROR_VALUE;
        }
        String content = GeneralUtils.objectToString(args[0]);
        String regex = GeneralUtils.objectToString(args[1]);
        return content.matches("\\Q" + regex + "\\E");
    }
}
