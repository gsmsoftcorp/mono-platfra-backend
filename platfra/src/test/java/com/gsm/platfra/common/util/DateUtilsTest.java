package com.gsm.platfra.common.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @DisplayName("String -> LocalDate 변환 테스트")
    @Test
    void toLocalDate() {
        String date = "2021-08-01";

        var localDate = DateUtils.toLocalDate(date);

        assertEquals(2021, localDate.getYear());
        assertEquals(8, localDate.getMonthValue());
        assertEquals(1, localDate.getDayOfMonth());
    }

}