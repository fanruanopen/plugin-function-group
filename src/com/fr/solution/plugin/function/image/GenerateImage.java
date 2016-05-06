package com.fr.solution.plugin.function.image;

import com.fr.general.GeneralUtils;
import com.fr.solution.plugin.function.AbstractSolutionFunction;
import com.fr.stable.ArrayUtils;
import com.fr.stable.Primitive;

/**
 * Created by richie on 16/5/4.
 * 将对象转换为图片
 */
public class GenerateImage extends AbstractSolutionFunction {


    @Override
    public Object solve(Object[] args) {
        int len = ArrayUtils.getLength(args);
        if (len < 1) {
            return Primitive.NULL;
        }
        String path = GeneralUtils.objectToString(args[0]);

        return new ImagePainter(path);
    }

}
