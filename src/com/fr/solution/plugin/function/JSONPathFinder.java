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
import com.jayway.jsonpath.JsonPath;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by richie on 16/3/21.
 */
public class JSONPathFinder extends AbstractFunction {
    @Override
    public Object run(Object[] args) {
        int len = ArrayUtils.getLength(args);
        if (len < 2) {
            return Primitive.ERROR_VALUE;
        }
        String content = GeneralUtils.objectToString(args[0]).trim();
        String filter = GeneralUtils.objectToString(args[1]);
        Object result = Primitive.NULL;

        if (content.startsWith("[") || content.startsWith("{")) {
            result = JsonPath.read(content, filter);
        } else if  (content.toLowerCase().startsWith("http:")) {
            try {
                URL url = new URL(content);
                result = JsonPath.read(url.openStream(), filter);
            } catch (MalformedURLException e) {
                FRLogger.getLogger().error(e.getMessage(), e);
            } catch (IOException e) {
                FRLogger.getLogger().error(e.getMessage(), e);
            }
        } else {
            File file = new File(content);
            try {
                result = JsonPath.read(file, filter);
            } catch (IOException e) {
                FRLogger.getLogger().error(e.getMessage(), e);
            }
        }
        if (result instanceof String) {
            return result;
        } else if (result instanceof net.minidev.json.JSONArray) {
            FArray<Object> list = new FArray<Object>();
            net.minidev.json.JSONArray array = (net.minidev.json.JSONArray)result;
            for (Object el : array) {
                list.add(el);
            }
            return list;
        } else {
            return result;
        }
    }

    public static void main(String... args) throws Exception {
        URL url = new URL("http://7xs469.com1.z0.glb.clouddn.com/test.json");
        InputStream stream = url.openStream();
        Object result = JsonPath.read(stream, "$.store.book[?(@.author == 'Nigel Rees')].author");
        System.out.println(result);

    }
}
