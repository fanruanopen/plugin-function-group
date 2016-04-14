package com.fr.solution.plugin.function;

import com.fr.general.GeneralUtils;
import com.fr.script.AbstractFunction;
import com.fr.stable.ArrayUtils;
import com.fr.stable.Primitive;
import com.fr.web.core.SessionDealWith;
import com.fr.web.core.SessionIDInfor;

public class GetIP extends AbstractSolutionFunction {

    @Override
    public Object solve(Object[] args) {
        if (ArrayUtils.isEmpty(args)) {
            return Primitive.ERROR_VALUE;
        }
        SessionIDInfor infor = SessionDealWith.getSessionIDInfor(GeneralUtils.objectToString(args[0]));
        return infor.getRemoteAddress();
    }
}