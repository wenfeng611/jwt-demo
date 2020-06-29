package org.skytech.kefu.common.utils;

import com.google.common.hash.Hashing;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.nio.charset.Charset;

public class EncryptUtils {

    /**
     * 加密算法
     */
    public final static String hashAlgorithmName = "SHA-256";
    /**
     * 循环次数
     */
    public final static int hashIterations = 16;

    public static String sha256(String password, String salt) {
        return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
    }

    public static String md5(String str) {
        return md5New(md5New(str));
    }

    private static String md5New(String src) {
        return Hashing.md5().hashString(src, Charset.forName("UTF-8")).toString();
    }
}
