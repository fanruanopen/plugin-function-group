package com.fr.solution.plugin.function;

import com.fr.stable.fun.FunctionHelper;
import com.fr.stable.fun.FunctionProcessor;
import com.fr.stable.fun.impl.AbstractFunctionProcessor;

/**
 * Created by richie on 16/4/13.
 */
public class Demo {
    public static final FunctionProcessor p = new AbstractFunctionProcessor() {
        @Override
        public int getId() {
            return FunctionHelper.generateFunctionID("com.fr.solution.plugin.function");
        }

        @Override
        public String getLocaleKey() {
            return "Solution Functions";
        }
    };

    public static void main(String... args) {
        System.out.println(p.getId());
    }
}
