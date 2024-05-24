package com.eptison.qimen;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xukesheng
 */
@UtilityClass
public class Md5Util {

    private static final Logger log = LoggerFactory.getLogger(Md5Util.class);

    /**
     * Md5加密
     * @param plainText
     * @return Md5String
     */
    public static String getMd5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            return dist(md);
        } catch (NoSuchAlgorithmException e) {
//            throw EpBizException.inst(EpBizCommonErrorCode.MD5_ENCODE_ERROR);
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * Md5加密
     * @param plainText
     * @return Md5String
     */
    public static String encoderByMd5(String plainText) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // 这句是关键
            md5.update(plainText.getBytes(StandardCharsets.UTF_8));
            return dist(md5);
        } catch (Exception e) {
//            throw EpBizException.inst(EpBizCommonErrorCode.MD5_DECODE_ERROR);
            return null;
        }
    }


    private String dist(MessageDigest md){
        byte []mdArr = md.digest();
        int i;
        StringBuilder buf = new StringBuilder();
        for (int offset = 0; offset < mdArr.length; offset++) {
            i = mdArr[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        //32位加密
        return buf.toString();
    }

    @SneakyThrows
    public static String sign(Map<String, String> params, String body, String secretKey) {
        // 1. 第一步，确保参数已经排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        // 2. 第二步，把所有参数名和参数值拼接在一起(包含body体)
        String joinedParams = joinRequestParams(params, body, secretKey, keys);

        MessageDigest md5Instance = MessageDigest.getInstance("MD5");
        md5Instance.update(joinedParams.getBytes(StandardCharsets.UTF_8));
        byte[]abstractMessage = md5Instance.digest();
        // 4. 把二进制转换成大写的十六进制
        return byte2Hex(abstractMessage);
    }

    private static String byte2Hex(byte[] bytes) {
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        int j = bytes.length;
        char[] str = new char[j * 2];
        int k = 0;
        for (byte byte0 : bytes) {
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }


    private String joinRequestParams(Map<String, String> params, String body, String secretKey, String[] sortedKes) {
        // 前面加上secretKey
        StringBuilder sb = new StringBuilder(secretKey);
        for (String key : sortedKes) {
            if (!"sign".equals(key)) {
                String value = params.get(key);
                if (isNotEmpty(key) && isNotEmpty(value)) {
                    sb.append(key).append(value);
                }
            }
        }
        // 拼接body体
        sb.append(body);
        // 最后加上secretKey
        sb.append(secretKey);
        return sb.toString();
    }

    private boolean isNotEmpty(String s) {
        return null != s && !"".equals(s);
    }
}
