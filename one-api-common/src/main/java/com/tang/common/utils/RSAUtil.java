package com.tang.common.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.StreamUtils;

import javax.crypto.Cipher;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class RSAUtil {
    public static String decrypt(String password){
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("key/privateKey.pem");
            InputStream inputStream = resources[0].getInputStream();
            String privateKeyString = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            inputStream.close();
            byte[] decoded = Base64.getMimeDecoder().decode(privateKeyString);
            PrivateKey preKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
            // 解密
            cipher.init(Cipher.DECRYPT_MODE, preKey);
            byte[] decode = Base64.getMimeDecoder().decode(password);
            byte[] cipherBytes = cipher.doFinal(decode);
            System.out.println(new String(cipherBytes));
            return new String(cipherBytes);

        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("密码解密失败");
        }
    }
}
