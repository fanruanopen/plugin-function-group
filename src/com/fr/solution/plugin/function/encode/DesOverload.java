package com.fr.solution.plugin.function.encode;

import com.fr.general.FRLogger;
import com.fr.general.GeneralUtils;
import com.fr.solution.plugin.function.AbstractSolutionFunction;
import com.fr.stable.ArrayUtils;
import com.fr.stable.Primitive;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;

/**
 * Created by richie on 16/5/10.
 */
public abstract class DesOverload extends AbstractSolutionFunction {
    //算法名称
    public static final String KEY_ALGORITHM = "DES";
    //算法名称/加密模式/填充方式
    //DES共有四种工作模式-->>ECB：电子密码本模式、CBC：加密分组链接模式、CFB：加密反馈模式、OFB：输出反馈模式
    public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";

    @Override
    public Object solve(Object[] args) {
        int len = ArrayUtils.getLength(args);
        if (len < 2) {
            return Primitive.ERROR_VALUE;
        }
        String plainText = GeneralUtils.objectToString(args[0]);
        String key = GeneralUtils.objectToString(args[1]);

        try {
            return result(plainText, key);
        } catch (Exception e) {
            FRLogger.getLogger().error(e.getMessage(), e);
        }
        return Primitive.ERROR_VALUE;
    }

    public abstract Object result(String plainText, String key) throws Exception;

    private Key toKey(byte[] key) throws Exception {
        DESKeySpec dks = new DESKeySpec(key);                                      //实例化Des密钥
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM); //实例化密钥工厂
        return keyFactory.generateSecret(dks);
    }

    public String encrypt(String data, String key) throws Exception {
        Key k = toKey(Base64.decodeBase64(key));                           //还原密钥
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);              //实例化Cipher对象，它用于完成实际的加密操作
        cipher.init(Cipher.ENCRYPT_MODE, k);                               //初始化Cipher对象，设置为加密模式
        return Base64.encodeBase64String(cipher.doFinal(data.getBytes())); //执行加密操作。加密后的结果通常都会用Base64编码进行传输
    }

    public String decrypt(String data, String key) throws Exception {
        Key k = toKey(Base64.decodeBase64(key));
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);                           //初始化Cipher对象，设置为解密模式
        return new String(cipher.doFinal(Base64.decodeBase64(data)));  //执行解密操作
    }
}
