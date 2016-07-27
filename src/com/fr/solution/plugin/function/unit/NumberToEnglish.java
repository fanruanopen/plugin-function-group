package com.fr.solution.plugin.function.unit;

import com.fr.general.GeneralUtils;
import com.fr.solution.plugin.function.AbstractSolutionFunction;
import com.fr.stable.ArrayUtils;
import com.fr.stable.Primitive;
import com.zphinx.reader.english.ChainOfResponsibilityTranslator;
import com.zphinx.reader.english.NumberReader;
import com.zphinx.reader.exception.NumberReaderException;

/**
 * Created by richie on 16/7/27.
 * 将阿拉伯数字转换成英文
 */
public class NumberToEnglish extends AbstractSolutionFunction {
    @Override
    public Object solve(Object[] args) {
        if (ArrayUtils.isEmpty(args)) {
            return Primitive.NULL;
        }
        String source = GeneralUtils.objectToString(args[0]).trim();

        NumberReader reader = new NumberReader(new ChainOfResponsibilityTranslator());
        try {
            return reader.translate(source);
        } catch (NumberReaderException ignore) {

        }
        return Primitive.ERROR_VALUE;
    }
}
