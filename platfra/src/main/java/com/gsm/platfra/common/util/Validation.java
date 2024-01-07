package com.gsm.platfra.common.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Validation {
    //text
    public static boolean checkText(String content, int min, int max) {
        if(content == null || content.length() == 0) {
            log.error("text data : content -> [ " + content + " ]");
            return false;
        }
        if(content.length() < min) {
            log.error("text length : min -> " + min + "/ content.length -> [ " + content.length() + " ]");
            return false;

        }
        if(max < content.length()) {
            log.error("text length : max -> " + max + "/ content.length -> [ " + content.length() + " ]");
            return false;
        }

        return true;
    }

    //number
    public static boolean checkNumber(int content, int min, int max) {
        if(content < min) {
            log.error("number size : min -> " + min + "/ number -> [ " + content + " ]");
            return false;

        }
        if(max < content) {
            log.error("number size : min -> " + min + "/ number -> [ " + content + " ]");
            return false;

        }
        return true;
    }

    //phone
    public static boolean checkPhone(String content) {
        if(!content.matches("^01([0|1|6|7|8|9])(\\d{3,4})(\\d{4})$")) {
            log.error("phone : [ " + content + " ]");
            return false;
        }

        return true;
    }

    //email
    public static boolean checkEmail(String content) {
        if(!content.matches("^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$")) {
            log.error("email : [ " + content + " ]");
            return false;
        }

        return true;
    }

}
