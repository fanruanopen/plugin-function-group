package com.fr.solution.plugin.function.string;

import com.fr.general.GeneralUtils;
import com.fr.script.AbstractFunction;
import com.fr.solution.plugin.function.AbstractSolutionFunction;
import com.fr.stable.ArrayUtils;
import com.fr.stable.Primitive;
import com.fr.stable.StringUtils;
import com.fr.stable.pinyin.PinyinFormat;
import com.fr.stable.pinyin.PinyinHelper;

/**
 * Created by richie on 16/3/21.
 */
public class StringPinyin extends AbstractSolutionFunction {
    @Override
    public Object solve(Object[] args) {
        int len = ArrayUtils.getLength(args);
        if (len < 1) {
            return Primitive.ERROR_VALUE;
        }
        String content = GeneralUtils.objectToString(args[0]);
        String separator = StringUtils.EMPTY;
        if (len > 1) {
            separator = GeneralUtils.objectToString(args[1]);
        }
        PinyinFormat format = PinyinFormat.WITH_TONE_MARK;
        if (len > 2) {
            int f = GeneralUtils.objectToNumber(args[2]).intValue();
            if (f == -1) {
                format = PinyinFormat.WITH_TONE_NUMBER;
            } else if (f == 1) {
                format = PinyinFormat.WITHOUT_TONE;
            }
        }
        return PinyinHelper.convertToPinyinString(content, separator, format);
    }
}
