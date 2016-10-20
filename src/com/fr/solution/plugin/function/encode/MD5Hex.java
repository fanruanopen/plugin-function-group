package com.fr.solution.plugin.function.encode;

import com.fr.general.GeneralUtils;
import com.fr.solution.plugin.function.AbstractSolutionFunction;
import com.fr.stable.ArrayUtils;
import com.fr.stable.Primitive;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by richie on 16/5/10.
 */
public class MD5Hex extends AbstractSolutionFunction {
    @Override
    public Object solve(Object[] args) {
        int len = ArrayUtils.getLength(args);
        if (len < 1) {
            return Primitive.ERROR_VALUE;
        }
        String text = GeneralUtils.objectToString(args[0]);

        return DigestUtils.md5Hex(text);
    }
}
