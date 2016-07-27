package com.fr.solution.plugin.function.string;

import com.fr.general.GeneralUtils;
import com.fr.solution.plugin.function.AbstractSolutionFunction;
import com.fr.stable.ArrayUtils;
import com.fr.stable.Primitive;

/**
 * Created by richie on 16/7/27.
 * 颠倒给定字符串的大小写,将大写转化为小写,小写转化为大写
 */
public class StringSwapCase extends AbstractSolutionFunction {
    @Override
    public Object solve(Object[] args) {
        if (ArrayUtils.isEmpty(args)) {
            return Primitive.NULL;
        }
        String source = GeneralUtils.objectToString(args[0]);
        return reverseCase(source);
    }

    private String reverseCase(String text) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (Character.isUpperCase(c)) {
                chars[i] = Character.toLowerCase(c);
            } else if (Character.isLowerCase(c)) {
                chars[i] = Character.toUpperCase(c);
            }
        }
        return new String(chars);
    }
}
