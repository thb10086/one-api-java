package com.tang.common.utils;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Base64;

public class ApiKeyGenerator {
    private static SecureRandom random = new SecureRandom();

    public static String nextApiKey() {
        byte[] randomBytes = new byte[16]; // 16 bytes for random part
        random.nextBytes(randomBytes);

        long timestamp = System.currentTimeMillis(); // current timestamp

        ByteBuffer buffer = ByteBuffer.allocate(24);
        for (int i = 0; i < 8; i++) {
            buffer.put((byte) (timestamp >>> (i * 8)));
            buffer.put(randomBytes, 2 * i, 2);
        }

        String encoded = Base64.getUrlEncoder().withoutPadding().encodeToString(buffer.array());

        // replace '-' with '0' and '_' with '1'
        encoded = encoded.replace('-', '0').replace('_', '1');

        return "sk-" + encoded;
    }

}
