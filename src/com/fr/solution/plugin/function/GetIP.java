package com.fr.solution.plugin.function;

import com.fr.base.Formula;
import com.fr.general.GeneralUtils;
import com.fr.script.AbstractFunction;
import com.fr.stable.ArrayUtils;
import com.fr.stable.Primitive;
import com.fr.stable.UtilEvalError;
import com.fr.web.core.SessionDealWith;
import com.fr.web.core.SessionIDInfor;

public class GetIP extends AbstractSolutionFunction {

    @Override
    public Object solve(Object[] args) {
        String sessionID = null;
        if (ArrayUtils.isEmpty(args)) {
            try {
                Object obj = getCalculator().eval(new Formula("=$sessionID"));
                sessionID = GeneralUtils.objectToString(obj);
            } catch (UtilEvalError utilEvalError) {
                utilEvalError.printStackTrace();
            }
        } else {
            sessionID = GeneralUtils.objectToString(args[0]);
        }

        SessionIDInfor infor = SessionDealWith.getSessionIDInfor(sessionID);
        if (infor == null) {
            return Primitive.NULL;
        }
        return infor.getRemoteAddress();
    }
}