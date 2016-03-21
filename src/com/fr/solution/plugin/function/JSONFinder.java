package com.fr.solution.plugin.function;

import com.fr.general.FArray;
import com.fr.general.FRLogger;
import com.fr.general.GeneralUtils;
import com.fr.general.IOUtils;
import com.fr.json.JSONArray;
import com.fr.json.JSONException;
import com.fr.json.JSONObject;
import com.fr.script.AbstractFunction;
import com.fr.stable.ArrayUtils;
import com.fr.stable.EncodeConstants;
import com.fr.stable.Primitive;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by richie on 16/3/21.
 */
public class JSONFinder extends AbstractFunction {
    @Override
    public Object run(Object[] args) {
        int len = ArrayUtils.getLength(args);
        if (len == 0) {
            return Primitive.ERROR_VALUE;
        }
        String content = GeneralUtils.objectToString(args[0]).trim();

        if (content.startsWith("[") || content.startsWith("{")) {
            return findResult(content);
        } else if  (content.toLowerCase().startsWith("http:")) {
            try {
                URL url = new URL(content);
                InputStream in = url.openStream();
                content = IOUtils.inputStream2String(in, EncodeConstants.ENCODING_UTF_8);
                return findResult(content);
            } catch (Exception e) {
                FRLogger.getLogger().error(e.getMessage(), e);
            }
        }

        return Primitive.NULL;
    }

    private FArray<?> findResult(String content) {
        if (content.startsWith("{")) {
            try {
                JSONArray array = new JSONArray(content);
                return findFromJSONArray(array);
            } catch (JSONException e) {
                FRLogger.getLogger().error(e.getMessage(), e);
                return new FArray();
            }
        } else if (content.startsWith("{")) {
            try {
                JSONObject js = new JSONObject(content);
            } catch (JSONException e) {
                FRLogger.getLogger().error(e.getMessage(), e);
                return new FArray();
            }
        }
        return new FArray();
    }

    private FArray findFromJSONArray(JSONArray jsonArray) throws JSONException {
        return new FArray();
    }
}
