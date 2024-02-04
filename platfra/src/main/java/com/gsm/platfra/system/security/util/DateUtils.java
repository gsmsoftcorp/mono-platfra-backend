package com.gsm.platfra.system.security.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.YearMonth;

import static java.time.format.DateTimeFormatter.BASIC_ISO_DATE;
import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * Date Utils
 *
 */
public class DateUtils {

    public static final String LAST_DATE_STR = "99991231";
    public static final LocalDate FIRST_DATE = LocalDate.of(1900, 1, 1);
    public static final LocalDate LAST_DATE = LocalDate.of(9999, 12, 31);

    private static final BigDecimal ORACLE_DAYS_IN_MONTHS = BigDecimal.valueOf(31);

    /**
     * LocalDate.now() 별칭
     * @return LocalDate
     */
    public static LocalDate today() {
        return LocalDate.now();
    }

    public static boolean isLastDate(LocalDate value) {
        return LAST_DATE.equals(value);
    }

    /**
     * yyyyMMdd 형식의 날짜 문자열을 LocalDate 형식으로 변환
     * @param value yyyyMMdd 형식의 날짜 문자열
     * @return LocalDate
     */
    public static LocalDate toDate(String value) {
        return LocalDate.parse(value, BASIC_ISO_DATE);
    }

    /**
     * LocalDate 형식을 yyyyMMdd 형식의 날짜 문자열로 변환
     * @param value LocalDate
     * @return yyyyMMdd 형식의 날짜 문자열
     */
    public static String fromDate(LocalDate value) {
        return value.format(BASIC_ISO_DATE);
    }

    /**
     * yyyyMM 형식의 년월 문자열을 YearMonth 형식으로 변환
     * @param value yyyyMM 형식의 년월 문자열
     * @return YearMonth
     */
    public static YearMonth toYearMonth(String value) {
        return YearMonth.parse(value, ofPattern("yyyyMM"));
    }

    /**
     * LocalDate 를 YearMonth 형식으로 변환
     * @param value LocalDate
     * @return YearMonth
     */
    public static YearMonth toYearMonth(LocalDate value) {
        return YearMonth.from(value);
    }

    /**
     * YearMonth 형식의 년월을 yyyyMM 문자열로 변환
     * @param value YearMonth 형식 년월
     * @return yyyyMM 형식의 년월 문자열
     */
    public static String fromYearMonth(YearMonth value) {
        return value.format(ofPattern("yyyyMM"));
    }

    /**
     * LocalDate 를 yyyyMM 문자열로 변환
     * @param value LocalDate
     * @return yyyyMM 형식의 년월 문자열
     */
    public static String toYymm(LocalDate value) {
        if (value == null) return "";
        return fromYearMonth(toYearMonth(value));
    }

    /**
     * LocalDate 형식의 날짜를 yyyyMM 문자열로 변환
     * @param value LocalDate 형식 날짜
     * @return yyyyMM 형식의 년월 문자열
     */
    public static String toYearMonthString(LocalDate value) {
        return value.format(ofPattern("yyyyMM"));
    }

    /**
     * YearMonth 형식의 날짜를 yyyyMM 문자열로 변환
     * @param value YearMonth 형식 날짜
     * @return yyyyMM 형식의 년월 문자열
     */
    public static String toYearMonthString(YearMonth value) {
        return value.format(ofPattern("yyyyMM"));
    }

    /**
     * 해당 월의 첫번째 날을 반환
     *
     * @param value 년월
     * @return 해당 년월의 첫번째 날
     */
    public static LocalDate startOfMonth(String value) {
        return startOfMonth(toYearMonth(value));
    }

    /**
     * 해당 월의 첫번째 날을 반환
     *
     * @param value 년월
     * @return 해당 년월의 첫번째 날
     */
    public static LocalDate startOfMonth(LocalDate value) {
        return startOfMonth(toYearMonth(value));
    }

    /**
     * 해당 월의 첫번째 날을 반환
     *
     * @param value 년월
     * @return 해당 년월의 첫번째 날
     */
    public static LocalDate startOfMonth(YearMonth value) {
        return value.atDay(1);
    }

    /**
     * 해당 월의 마지막 날을 반환
     *
     * @param value 년월
     * @return 해당 년월의 마지막 날
     */
    public static LocalDate endOfMonth(String value) {
        return endOfMonth(toYearMonth(value));
    }

    /**
     * 해당 월의 마지막 날을 반환
     *
     * @param value 년월
     * @return 해당 년월의 마지막 날
     */
    public static LocalDate endOfMonth(LocalDate value) {
        return endOfMonth(toYearMonth(value));
    }

    /**
     * 해당 월의 마지막 날을 반환
     *
     * @param value 년월
     * @return 해당 년월의 마지막 날
     */
    public static LocalDate endOfMonth(YearMonth value) {
        return value.atEndOfMonth();
    }

    /**
     * 오라클 MONTHS_BETWEEN 유사한 값 제공
     * @param end 종료일
     * @param start 시작일
     * @return 월 차이
     */
    public static BigDecimal monthsBetween(LocalDate end, LocalDate start) {
        var period = start.until(end);
        var months = BigDecimal.valueOf(period.toTotalMonths());
        var days = BigDecimal.valueOf(period.getDays());
        return months.add(days.divide(ORACLE_DAYS_IN_MONTHS, MathContext.DECIMAL32));
    }

    /**
     * 금일이 달의 마지막 날인지 검사
     * @return 마직막 날 여부
     */
    public static boolean isEndOfMonth() {
        return isEndOfMonth(LocalDate.now());
    }

    /**
     * 해당 날짜가 달의 마지막 날인지 검사
     * @param value 마지막 날 여부 검사 날짜
     * @return 마직막 날 여부
     */
    public static boolean isEndOfMonth(LocalDate value) {
        return YearMonth.from(value).atEndOfMonth().equals(value);
    }

    /**
     * 날짜 크기 비교
     * @param value1 LocalDate
     * @param value2 LocalDate
     * @return 평가
     */
    public static boolean gt(LocalDate value1, LocalDate value2) {
        return value1.isAfter(value2);
    }

    /**
     * 날짜 크기 비교
     * @param value1 LocalDate
     * @param value2 LocalDate
     * @return 평가
     */
    public static boolean goe(LocalDate value1, LocalDate value2) {
        return value1.isAfter(value2) || value1.isEqual(value2);
    }

    /**
     * 날짜 크기 비교
     * @param value1 LocalDate
     * @param value2 LocalDate
     * @return 평가
     */
    public static boolean lt(LocalDate value1, LocalDate value2) {
        return value1.isBefore(value2);
    }

    /**
     * 날짜 크기 비교
     * @param value1 LocalDate
     * @param value2 LocalDate
     * @return 평가
     */
    public static boolean loe(LocalDate value1, LocalDate value2) {
        return value1.isBefore(value2) || value1.isEqual(value2);
    }

    /**
     * 해당 일자의 1일에 개월수를 더해서 리턴(이상)
     * @param value 날짜
     * @param months 개월수
     * @return LocalDate
     */
    public static LocalDate moreDate(LocalDate value, int months) {
        return startOfMonth(value).plusMonths(months);
    }

    /**
     * 해당 일자의 1일에 개월수를 더한 후 -1일을 리턴(미만)
     * @param value 날짜
     * @param months 개월수
     * @return LocalDate
     */
    public static LocalDate underDate(LocalDate value, int months) {
        return startOfMonth(value).plusMonths(months).minusDays(1);
    }

    /**
     * 주민등록번호에서 생년월일 추출(yymmdd -> yyyyMMdd)
     * @param value 주민등록번호
     * @return LocalDate
     */
    public static LocalDate convertBirthDate(String value) {
        String yy = value.substring(0, 2);
        if (value.length() > 6) {
            value = value.substring(0, 6);
        }
        return "00".equals(yy) ? toDate("20"+value) : toDate("19"+value);
    }
}
