package com.gsm.platfra.util;

public class ValidateUtils {

//    /**
//     * 필수 조건 확인
//     *
//     * 값이 널이거나 문자열이 비어 있을 경우 예외를 발생시킨다.
//     *
//     * @param value 확인 대상
//     * @param fieldName 예외로 알려주는 필드명
//     */
//    public static void required(Object value, String fieldName) {
//        if (value == null ||
//                (value instanceof String &&
//                        !StringUtils.hasText((String) value))) {
//            throw new ValidationFailedException("error.cpn.0002", fieldName);
//        }
//    }
//
//    /**
//     * 문자열 길이 확인
//     * 문자열의 길이가 다를 경우 예외를 발생시킨다.
//     *
//     * @param value 대상 문자열
//     * @param fieldName 예외로 알려주는 필드명
//     * @param length 문자열의 길이
//     */
//    public static void required(String value, String fieldName, int length) {
//        if (value == null || value.length() != length) {
//            throw new ValidationFailedException("error.cpn.std.bplc.0001", fieldName, Integer.toString(length));
//        }
//    }
//
//    /**
//     * 필수 조건 확인(ALL 제외)
//     * @param value 필수 문자열
//     * @param fieldName 예외로 알려주는 필드명
//     */
//    public static void requiredNotAll(String value, String fieldName) {
//        if (!StringUtils.hasText(value) || "ALL".equals(value)) {
//            throw new ValidationFailedException("error.cpn.0002", fieldName);
//        }
//    }
//
//    /**
//     * 기간 유효성 확인
//     * <p>시작일자와 종료일자의 유효성을 확인하고 예외를 발생시킨다.</p>
//     * @param from 시작일자
//     * @param to 종료일자
//     * @param fromFieldName 예외로 알려주는 시작일자 필드명
//     * @param toFieldName 예외로 알려주는 종료일자 필드명
//     */
//    public static void validPeriod(LocalDate from, LocalDate to, String fromFieldName, String toFieldName) {
//        if (to == null) {
//            to = LocalDate.of(9999, 12, 31);
//        }
//        if (from.isAfter(to)) {
//            throw new ValidationFailedException("error.cpn.0004", fromFieldName, toFieldName);
//        }
//    }
//
//    /**
//     * 문자열 길이 제한 확인
//     * 문자열의 길이가 제한된 길이보다 이상일 경우 예외를 발생시킨다.
//     *
//     * @param value 대상 문자열
//     * @param fieldName 예외로 알려주는 필드명
//     * @param length 제한 문자열의 길이
//     */
//    public static void vaildLength(String value, String fieldName, int length) {
//        if (value != null && value.length() > length) {
//            throw new ValidationFailedException("error.cpn.std.bplc.0001", fieldName, Integer.toString(length));
//        }
//    }
}
