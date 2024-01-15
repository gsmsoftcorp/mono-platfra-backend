package com.gsm.platfra.common.util;

import java.time.LocalDate;

public class DateUtils {

    public static LocalDate toLocalDate(String date) {
        return LocalDate.parse(date);
    }
}
