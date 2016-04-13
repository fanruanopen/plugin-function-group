package com.fr.solution.plugin.function;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.util.List;

/**
 * Created by richie on 16/4/13.
 */
public class ScriptEngineHelper {

    private static ScriptEngine scriptEngine;

    public static ScriptEngine getScriptEngine() {
        if (scriptEngine == null) {
            ScriptEngineManager manager = new ScriptEngineManager();
            List<ScriptEngineFactory> factoryList = manager.getEngineFactories();
            ScriptEngineFactory factory = factoryList.get(0);
            if (factory != null) {
                scriptEngine = factory.getScriptEngine();
            }
        }
        return scriptEngine;
    }
}
