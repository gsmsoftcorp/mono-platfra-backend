package com.gsm.platfra.util;


import lombok.NonNull;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * masking utils
 *
 * <p>상세 설명
 *
 * @author hsnc22030702
 */
public class MaskingUtils {

    /**
     * 전화번호 마스킹
     * @param telNo 전화 번호
     * @return
     */
    public static String getMaskedTelNo(String telNo) {
        String regex = "(\\d{2,3})-?(\\d{3,4})-?(\\d{4})$";

        Matcher matcher = Pattern.compile(regex).matcher(telNo);
        if (matcher.find()) {
            String target = matcher.group(2);
            int length = target.length();
            char[] c = new char[length];
            Arrays.fill(c, '*');

            return telNo.replace(target, String.valueOf(c));
        }

        return telNo;
    }

    /**
     * 이메일 마스킹
     * @param email 이메일
     * @return
     */
    public static String getMaskedEmail(@NonNull String email) {
        String mask = "*****";
        int at = email.indexOf("@");
        if (at > 2) {
            int maskLen = Math.min(Math.max(at / 2, 2), 3);
            int start = (at - maskLen) / 3;
            return email.substring(0, start) + mask.substring(0, maskLen) + email.substring(start + maskLen);
        }
        
        return email;
    }

}
