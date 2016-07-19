package com.fr.solution.plugin.function.string;

import com.fr.base.FRContext;
import com.fr.base.ParameterMapNameSpace;
import com.fr.general.FArray;
import com.fr.general.GeneralUtils;
import com.fr.script.Calculator;
import com.fr.solution.plugin.function.AbstractLambdaSolutionFunction;
import com.fr.stable.StableUtils;
import com.fr.stable.StringUtils;
import com.fr.stable.UtilEvalError;
import com.fr.stable.script.Expression;
import com.fr.stable.script.NameSpace;
import com.fr.stable.script.Node;
import com.fr.third.antlr.ANTLRException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by richie on 16/7/18.
 * 动态拼接字符串
 */
public class StringConditionConcat extends AbstractLambdaSolutionFunction {


    @Override
    public Object solve(FArray array, Node[] arguments) {
        Calculator cal = this.getCalculator();
        Expression expression;
        try {
            expression = cal.parse(arguments[0]);
        } catch (ANTLRException e) {
            FRContext.getLogger().error(e.getMessage(), e);
            return array;
        }
        Expression delimeterExpression = null;
        if (arguments.length > 1) {
            try {
                delimeterExpression = cal.parse(arguments[0]);
            } catch (ANTLRException e) {
                FRContext.getLogger().error(e.getMessage(), e);
                return array;
            }
        }

        Iterator it = array.iterator();
        int index = 0;

        List<String> data = new ArrayList<String>();

        while (it.hasNext()) {
            Object ob = it.next();
            java.util.Map<String, Object> m = new java.util.HashMap<String, Object>();
            m.put("item", ob);
            m.put("index", index + 1);
            NameSpace ns = ParameterMapNameSpace.create(m);
            cal.pushNameSpace(ns);
            try {
                Object obj = cal.evalValue(expression);
                if (obj != null && Boolean.parseBoolean(obj.toString())) {
                    data.add(GeneralUtils.objectToString(ob));
                }
            } catch (UtilEvalError e) {
                FRContext.getLogger().error(e.getMessage(), e);
            }
            cal.removeNameSpace(ns);
            index++;
        }
        String se = StringUtils.EMPTY;
//        if (arguments.length > 2) {
//            se = GeneralUtils.objectToString(arguments[1]);
//        }
        return StableUtils.join(data.toArray(new String[data.size()]), se);
    }
}
