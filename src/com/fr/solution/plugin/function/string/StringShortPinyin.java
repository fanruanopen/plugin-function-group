package com.fr.solution.plugin.function.string;

import com.fr.stable.pinyin.PinyinFormat;
import com.fr.stable.pinyin.PinyinHelper;

/**
 * Created by richie on 16/7/12.
 */
public class StringShortPinyin extends StringPinyin {

    @Override
    protected String convert(String content, String separator, PinyinFormat format) {
        return PinyinHelper.getShortPinyin(content);
    }
}
