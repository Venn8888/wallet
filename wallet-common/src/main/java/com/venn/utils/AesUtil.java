package com.venn.utils;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;

/**
 * @author dwf
 * @date 2019/9/29 20:24
 */

public class AesUtil {

    /**
     * aes加密
     *
     * @param content 待加密的内容
     * @param aesKey  aes密钥
     * @return 加密后的数据
     */
    private static String encryptWithoutRandom(String content, String aesKey) {
        if (content == null) {
            throw new IllegalArgumentException("content is null");
        }
        SecretKeySpec key = new SecretKeySpec(aesKey.getBytes(), "AES");
        try {
            //创建密码器
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            //初始化
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //加密
            byte[] bytes = cipher.doFinal(content.getBytes());
            return Base64.encode(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    /**
     * aes解密
     *
     * @param cipherText 待解密的数据
     * @param aesKey     aes密钥
     * @return 解密后的数据
     */
    private static String decryptWithoutRandomFromBase64(String cipherText, String aesKey) {
        if (StringUtils.isEmpty(cipherText)) {
            throw new IllegalArgumentException("cipherText is null");
        }
        byte[] cipherByte = base642byte(cipherText);
        SecretKeySpec keySpec = new SecretKeySpec(aesKey.getBytes(), "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(2, keySpec);
            byte[] result = cipher.doFinal(cipherByte);
            return new String(result, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    private static String byte2base64(byte[] bytes) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(bytes);
    }

    private static byte[] base642byte(String base64) {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            return base64Decoder.decodeBuffer(base64);
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
    }

    public static void main(String[] args) {
        String venn = AesUtil.encryptWithoutRandom("123456789", "vennvennvennvenn");
        System.out.println(venn);
        String random = AesUtil.decryptWithoutRandomFromBase64(venn, "vennvennvennvenn");
        System.out.println(random);
    }
}
