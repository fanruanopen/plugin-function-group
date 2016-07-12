package com.fr.solution.plugin.function.string;

import com.fr.general.FArray;
import com.fr.general.GeneralUtils;
import com.fr.solution.plugin.function.AbstractSolutionFunction;
import com.fr.stable.ArrayUtils;
import com.fr.stable.Primitive;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by richie on 16/7/12.
 */
public class StringFetch extends AbstractSolutionFunction {
    @Override
    public Object solve(Object[] args) {
        int len = ArrayUtils.getLength(args);
        if (len < 2) {
            return Primitive.ERROR_VALUE;
        }
        String content = GeneralUtils.objectToString(args[0]);
        String regex = GeneralUtils.objectToString(args[1]);
        FArray<String> result = new FArray<String>();
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(content);
        while (m.find()) {
            result.add(m.group());
        }
        return result;
    }
}
