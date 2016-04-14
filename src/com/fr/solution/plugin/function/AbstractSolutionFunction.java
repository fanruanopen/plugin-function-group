package com.fr.solution.plugin.function;

import com.fr.plugin.ExtraClassManager;
import com.fr.script.AbstractFunction;
import com.fr.stable.fun.FunctionProcessor;

/**
 * Created by richie on 16/4/14.
 */
public abstract class AbstractSolutionFunction extends AbstractFunction {

    public Object run(Object[] args) {
        record();
        return solve(args);
    }


    public abstract Object solve(Object[] args);

    private void record() {
        FunctionProcessor processor = ExtraClassManager.getInstance().getFunctionProcessor();
        if (processor != null) {
            processor.recordFunction(Demo.p);
        }
    }

}
