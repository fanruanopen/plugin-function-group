package com.fr.solution.plugin.function;

import com.fr.general.FRLogger;
import com.fr.general.GeneralUtils;
import com.fr.script.AbstractFunction;
import com.fr.script.Calculator;
import com.fr.stable.ArrayUtils;
import com.fr.stable.Primitive;
import com.fr.stable.StableUtils;
import com.fr.stable.UtilEvalError;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by richie on 16/4/12.
 */
public class ScriptEval extends AbstractFunction {

    @Override
    public Object run(Object[] args) {
        int len = ArrayUtils.getLength(args);
        if (len == 0) {
            return Primitive.NULL;
        }
        Calculator cal = this.getCalculator();

        ScriptEngine scriptEngine = ScriptEngineHelper.getScriptEngine();
        if (scriptEngine == null) {
            return Primitive.NULL;
        }
        Bindings bindings = scriptEngine.createBindings();
        bindings.clear();
        scriptEngine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
        StringBuilder sb = new StringBuilder();
        sb.append("(function(");
        List<String> parameters = new ArrayList<String>();
        if (len > 1 && len % 2 == 1) {
            for (int i = 1; i < len; i += 2) {
                String name = GeneralUtils.objectToString(args[i]);
                parameters.add(name);
                String value = GeneralUtils.objectToString(args[i + 1]);
                try {
                    Object r = cal.evalValue(value);
                    bindings.put(name, r);
                } catch (UtilEvalError utilEvalError) {
                    FRLogger.getLogger().error(utilEvalError.getMessage(), utilEvalError);
                }
            }
        }
        sb.append(StableUtils.join(parameters, ","));
        sb.append(")");
        sb.append("{");
        sb.append(GeneralUtils.objectToString(args[0]));
        sb.append("}");
        sb.append(")(");
        sb.append(StableUtils.join(parameters, ","));
        sb.append(")");

        Object result = null;
        try {
            result = scriptEngine.eval(sb.toString());
        } catch (ScriptException e) {
            FRLogger.getLogger().error(e.getMessage(), e);
        }
        if (result != null) {
            if (cal != null) {
                try {
                    return cal.evalValue(GeneralUtils.objectToString(result));
                } catch (UtilEvalError utilEvalError) {
                    FRLogger.getLogger().error(utilEvalError.getMessage(), utilEvalError);
                }
            }
        }
        return Primitive.NULL;
    }
}
