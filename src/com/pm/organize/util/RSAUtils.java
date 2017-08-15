package com.pm.organize.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * RSA算法加密/解密工具类
 * 
 * @author youliang.fang
 */

public abstract class RSAUtils {
    private static final Logger LOGGER = Logger.getLogger(RSAUtils.class);

    /** 算法名称 */
    private static final String ALGORITHOM = "RSA";

    /** 安全服务 */
    private static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();

    /** 公钥KEY */
    private static final String PUBLICKEY = "PUBLIC_KEY";

    /** 私钥KEY */
    private static final String PRIVATEKEY = "PRIVATE_KEY";

    /**
     * 生成并返回RSA密钥
     */
    public static HashMap<String, Object> getKeyPairMap() {
        try {
            HashMap<String, Object> map = new HashMap<String, Object>();
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
            keyPairGen.initialize(1024, new SecureRandom((Math.random() + "PM").getBytes()));
            KeyPair keyPair = keyPairGen.generateKeyPair();
            map.put(PUBLICKEY, keyPair.getPublic());
            map.put(PRIVATEKEY, keyPair.getPrivate());
            return map;
        } catch (Exception e) {
            LOGGER.error(e, e);
        }
        return null;
    }

    /**
     * 返回KeyPair公钥
     * 
     * @param HashMap
     * @return RSAPublicKey
     */
    public static RSAPublicKey getPublicKey(HashMap<String, Object> map) {
        if (map != null) {
            return (RSAPublicKey) map.get(PUBLICKEY);
        }
        return null;
    }

    /**
     * 返回KeyPair私钥
     * 
     * @param keyPair
     * @return RSAPrivateKey
     */
    public static RSAPrivateKey getPrivateKey(HashMap<String, Object> map) {
        if (map != null) {
            return (RSAPrivateKey) map.get(PRIVATEKEY);
        }
        return null;
    }

    /**
     * 返回JSON的公钥字符串
     * 
     * @return Json
     */
    public static String getPublicKeyToJson(HashMap<String, Object> map) {
        RSAPublicKey rsaPublicKey = getPublicKey(map);
        return "[{\"exponent\":\"" + new String(Hex.encodeHex(rsaPublicKey.getPublicExponent().toByteArray()))
                + "\",\"modulus\":\"" + new String(Hex.encodeHex(rsaPublicKey.getModulus().toByteArray())) + "\"}]";
    }

    /**
     * 使用公钥加密字符串
     * 
     * @param keyPair
     * @param plaintext
     * @return string
     */
    public static String encryptString(HashMap<String, Object> map, String text) {
        if (text == null) {
            return null;
        }
        byte[] data = text.getBytes();
        try {
            Cipher ci = Cipher.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
            ci.init(Cipher.ENCRYPT_MODE, getPublicKey(map));
            byte[] en_data = ci.doFinal(data);
            return new String(Hex.encodeHex(en_data));
        } catch (Exception e) {
            LOGGER.error(e, e);
        }
        return null;
    }

    /**
     * 使用私钥解密字符串
     * 
     * @param keyPair
     * @param encrypttext
     * @return key
     */
    public static String decryptString(HashMap<String, Object> map, String text) {
        if (text == null) {
            return null;
        }
        try {
            byte[] en_data = Hex.decodeHex(text.toCharArray());
            Cipher ci = Cipher.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
            ci.init(Cipher.DECRYPT_MODE, getPrivateKey(map));
            byte[] data = ci.doFinal(en_data);
            return new String(data);
        } catch (Exception e) {
            LOGGER.error(e, e);
        }
        return null;
    }

    /**
     * 使用默认的私钥解密由JS加密（使用此类提供的公钥加密）的字符串
     * 
     * @param text 密文
     * @return 原文字符串
     */
    public static String decryptStringByJs(HashMap<String, Object> map, String text) {
        String jstext = decryptString(map, text);
        if (jstext == null) {
            return null;
        }
        return StringUtils.reverse(jstext);
    }
}
