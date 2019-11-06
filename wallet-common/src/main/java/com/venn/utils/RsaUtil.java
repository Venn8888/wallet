package com.venn.utils;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;
import javax.crypto.Cipher;

/**
 * @author dwf
 * @date 2019/9/29 19:56
 */

public class RsaUtil {

  /**
   * Sha256验签
   *
   * @param content 签名前数据
   * @param sign Base64后的签名数据
   * @param publicKey 公钥
   * @return 返回验签结果 true/false
   */
  private static boolean signCheckSha256(String content, String sign, String publicKey) {
    try {
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      byte[] encodedKey = Base64.decode(publicKey);
      PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
      Signature signature = Signature.getInstance("SHA256WithRSA");
      signature.initVerify(pubKey);
      signature.update(content.getBytes());
      return signature.verify(Base64.decode(sign));
    } catch (Exception e) {
      throw new RuntimeException(e.toString());
    }
  }

  /**
   * Sha256签名
   *
   * @param content 待签名的内容
   * @param privateKey 私钥
   * @return 签名后的Base64数据
   */
  private static String signSha256(String content, String privateKey) {
    try {
      PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      PrivateKey priKey = keyFactory.generatePrivate(priPKCS8);
      Signature signature = Signature.getInstance("SHA256WithRSA");
      signature.initSign(priKey);
      signature.update(content.getBytes());
      return Base64.encode(signature.sign());
    } catch (Exception e) {
      throw new RuntimeException(e.toString());
    }
  }

  private static String encode(String pubKey, String plainText) {
    try {
      byte[] buffer = Base64.decode(pubKey);
      X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      PublicKey publicKey = keyFactory.generatePublic(keySpec);
      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
      cipher.init(1, publicKey);
      byte[] bytes = cipher.doFinal(plainText.getBytes());
      return Base64.encode(bytes);
    } catch (Exception e) {
      throw new RuntimeException(e.toString());
    }
  }

  private static String decode(String priKey, String base64Text) {
    try {
      byte[] buffer = Base64.decode(priKey);
      PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      PrivateKey privatekey = keyFactory.generatePrivate(keySpec);
      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
      cipher.init(2, privatekey);
      byte[] bytes = cipher.doFinal(Base64.decode(base64Text));
      return new String(bytes);
    } catch (Exception e) {
      throw new RuntimeException(e.toString());
    }

  }

  public static void main(String[] args) {
    String pubKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKHCFq6ipumMUAfWtnjjSMaSYPBhqOWO\n"
        + "K9Fd4n1XhNSZGSadYg98D9cgYc0w5l0rZWuuFsqC8Af6JuksEbmcMAcCAwEAAQ==";

    String priKey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAocIWrqKm6YxQB9a2\n"
        + "eONIxpJg8GGo5Y4r0V3ifVeE1JkZJp1iD3wP1yBhzTDmXStla64WyoLwB/om6SwR\n"
        + "uZwwBwIDAQABAkBg82OE6BgCgwa0rAxSCGfmHHXdnasNa1j38718QqhqgyRCMawh\n"
        + "lqNv+o5yPX3xN/9So6120cs1AONqfqQLS0SBAiEA1fHBrpie2DGiti0b0bgGw0Yc\n"
        + "LLvr7j2z4XTqJHfpMt0CIQDBji9XkzWcix80qolZKdlXhPa/3FVMneDBQtEbzb9m\n"
        + "MwIgCtaMcUPaCCm7jG8Mkbs43HuYwctjUFZf3nQFyIMqlSECIB5H7VYpHLER/t7R\n"
        + "c010w6Dyl1vqz5l99aSmnGpaJQCLAiBUbznE9WlnD1ZZXwvMIgkEZb3vwJtjebXe\n"
        + "BYxGTJSmaA==";

    Random random = new Random();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 8; i++) {
      sb.append(random.nextInt(9));
    }
    System.out.println(sb.toString());

    String sha256 = RsaUtil.signSha256(sb.toString(), priKey);
    System.out.println(sha256);

    boolean checkSha256 = RsaUtil.signCheckSha256(sb.toString(), sha256, pubKey);
    System.out.println(checkSha256);

    String encode = RsaUtil.encode(pubKey, sb.toString());
    System.out.println("encode: " + encode);

    String decode = RsaUtil.decode(priKey, encode);
    System.out.println("decode:" + decode);
  }
}
