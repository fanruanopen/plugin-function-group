package com.fr.solution.plugin.function.encode;

/**
 * Created by richie on 16/5/10.
 */
public class EncryptDes extends DesOverload {

    @Override
    public Object result(String plainText, String key) throws Exception {
        return encrypt(plainText, key);
    }
}
