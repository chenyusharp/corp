package com.eptison.tk;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;

/**
 * Date: 2024/10/7
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Signature {

    public static String generateSignature(HttpUrl httpUrl, String contentType, String secret,RequestBody requestBody) {
        List<String> parameterNameList = new ArrayList<>(httpUrl.queryParameterNames());
        parameterNameList.removeIf(param -> "sign".equals(param) || "access_token".equals(param));
        Collections.sort(parameterNameList);
        StringBuilder parameterStr = new StringBuilder(httpUrl.encodedPath());
        for (String parameterName : parameterNameList) {
            parameterStr.append(parameterName).append(httpUrl.queryParameter(parameterName));
        }
        if (!"multipart/form-data".equalsIgnoreCase(contentType)) {
            try {
                if (requestBody != null) {
                    Buffer bodyBuffer = new Buffer();
                    requestBody.writeTo(bodyBuffer);
                    byte[] bodyBytes = bodyBuffer.readByteArray();
                    parameterStr.append(new String(bodyBytes, StandardCharsets.UTF_8));
                }
            } catch (Exception e) {
                throw new RuntimeException("failed to generate signature params", e);
            }
        }

        // wrap the string generated in step 5 with the App secret
        String signatureParams = secret + parameterStr + secret;

        // encode wrapped string using HMAC-SHA256
        return generateSHA256(signatureParams, secret);
    }

    /**
     * generate signature by SHA256
     * @param signatureParams signature params
     * @return signature
     */
    public static String generateSHA256(String signatureParams, String secret) {
        try {
            // Get an HmacSHA256 Mac instance and initialize with the secret key
            Mac sha256HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256HMAC.init(secretKeySpec);

            // Update with input data
            sha256HMAC.update(signatureParams.getBytes(StandardCharsets.UTF_8));

            // Compute the HMAC and get the result
            byte[] hashBytes = sha256HMAC.doFinal();

            // Convert to hex string
            StringBuilder sb = new StringBuilder();
            for (byte hashByte : hashBytes) {
                sb.append(String.format("%02x", hashByte & 0xff));
            }

            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("failed to generate signature result", e);
        }
    }
}