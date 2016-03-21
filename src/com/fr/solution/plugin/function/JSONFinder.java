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
import java.util.ArrayList;
import java.util.List;

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

    private List<?> findResult(String content) {
        List<?> data = new ArrayList<Object>();
        if (content.startsWith("{")) {
            try {
                JSONArray array = new JSONArray(content);
                findFromJSONArray(array, data);
            } catch (JSONException e) {
                FRLogger.getLogger().error(e.getMessage(), e);

            }
        } else if (content.startsWith("{")) {
            try {
                JSONObject js = new JSONObject(content);
            } catch (JSONException e) {
                FRLogger.getLogger().error(e.getMessage(), e);

            }
        }
        return null;
    }

    private void findFromJSONArray(JSONArray jsonArray, List<?> data) throws JSONException {
        int len = jsonArray.length();
        for (int i = 0; i < len; i ++) {
            Object el = jsonArray.get(i);
            if (el instanceof JSONArray) {
                findFromJSONArray((JSONArray) el, data);
            } else if (el instanceof JSONObject) {

            }
        }
    }

    private void findFromJSONObject(JSONObject jsonObject, List<?> data) throws JSONException {

    }
}
