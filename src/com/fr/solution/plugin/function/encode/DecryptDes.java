package com.fr.solution.plugin.function.encode;

/**
 * Created by richie on 16/5/10.
 */
public class DecryptDes extends DesOverload {

    @Override
    public Object result(String plainText, String key) throws Exception {
        return decrypt(plainText, key);
    }
}
