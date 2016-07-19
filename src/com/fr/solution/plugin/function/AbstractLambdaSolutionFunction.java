package com.fr.solution.plugin.function;

import com.fr.function.LambdaFunction;
import com.fr.general.FArray;
import com.fr.plugin.ExtraClassManager;
import com.fr.stable.StringUtils;
import com.fr.stable.fun.FunctionProcessor;
import com.fr.stable.script.Node;

/**
 * Created by richie on 16/7/18.
 */
public abstract class AbstractLambdaSolutionFunction extends LambdaFunction {

    private void record() {
        FunctionProcessor processor = ExtraClassManager.getInstance().getFunctionProcessor();
        if (processor != null) {
            processor.recordFunction(Demo.p);
        }
    }

    public abstract Object solve(FArray array, Node[] args);

    @Override
    public Object run(FArray array, Node[] arguments) {
        record();
        return solve(array, arguments);
    }

    @Override
    public String getCN() {
        return StringUtils.EMPTY;
    }

    @Override
    public String getEN() {
        return StringUtils.EMPTY;
    }
}
