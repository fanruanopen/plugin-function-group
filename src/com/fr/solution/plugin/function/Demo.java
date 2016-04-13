package com.fr.solution.plugin.function;

import com.fr.stable.fun.FunctionProcessor;
import com.fr.stable.fun.impl.AbstractFunctionProcessor;

/**
 * Created by richie on 16/4/13.
 */
public class Demo {
    private static final FunctionProcessor p = new AbstractFunctionProcessor() {
        @Override
        public int getId() {
            return 1;
        }

        @Override
        public String getLocaleKey() {
            return "nothing";
        }
    };

    public static void main(String... args) {
        System.out.println(p);
    }
}
