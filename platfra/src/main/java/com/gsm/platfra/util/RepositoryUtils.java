package com.gsm.platfra.util;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.NullExpression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.gsm.platfra.util.DateUtils.*;
import static com.querydsl.core.types.dsl.Expressions.*;
import static java.math.BigDecimal.ZERO;
import static org.springframework.util.StringUtils.isEmpty;

/**
 * Repository Utils
 *
 * @author hsnc22030709
 */
@Component
public class RepositoryUtils {
    
    public static final String ALL = "ALL";
    
    public static final BiFunction<StringExpression, String, BooleanExpression> eq = SimpleExpression::eq;
    public static final BiFunction<StringExpression, String, BooleanExpression> goe = ComparableExpression::goe;
    public static final BiFunction<StringExpression, String, BooleanExpression> loe = ComparableExpression::loe;
    public static final BiFunction<StringExpression, String, BooleanExpression> contains = StringExpression::contains;      // %값%
    public static final BiFunction<StringExpression, String, BooleanExpression> startsWith = StringExpression::startsWith;  // 값%
    public static final BiFunction<StringExpression, String, BooleanExpression> like = StringExpression::like;              // 값
    
    private static final BooleanExpression FALSE = TRUE.isFalse();
    
    
    /**
     * 조건이 참이면 표현식 적용
     *
     * @param predicate  조건
     * @param expression 평가식
     * @return BooleanExpression
     */
    public static BooleanExpression _if(boolean predicate, BooleanExpression expression) {
        return predicate ? expression : null;
    }
    
    /**
     * 조건이 참이면 표현식 적용
     *
     * @param predicate  조건
     * @param expression 평가식 지연
     * @return BooleanExpression
     */
    public static BooleanExpression _if(boolean predicate, Supplier<BooleanExpression> expression) {
        return predicate ? expression.get() : null;
    }
    
    /**
     * 조건이 참이면 표현식1 적용 거짓이면 표현식2 적용
     *
     * @param predicate   조건
     * @param expression1 평가식1
     * @param expression2 평가식2
     * @return BooleanExpression
     */
    public static BooleanExpression _if(boolean predicate, BooleanExpression expression1, BooleanExpression expression2) {
        return predicate ? expression1 : expression2;
    }
    
    /**
     * 조건이 참이면 평가식1 적용 거짓이면 평가식2 적용
     *
     * @param predicate   조건
     * @param expression1 평가식1 지연
     * @param expression2 평가식2 지연
     * @return BooleanExpression
     */
    public static BooleanExpression _if(boolean predicate, Supplier<BooleanExpression> expression1, Supplier<BooleanExpression> expression2) {
        return predicate ? expression1.get() : expression2.get();
    }
    
    /**
     * 조건이 참이면 표현식1 적용 거짓이면 표현식2 적용
     *
     * @param predicate   조건
     * @param expression1 표현식1
     * @param expression2 표현식2
     * @return BooleanExpression
     */
    public static Expression<?> _if(boolean predicate, Expression<?> expression1, Expression<?> expression2) {
        return predicate ? expression1 : expression2;
    }
    
    /**
     * 조건이 참이면 정렬순서 적용
     *
     * @param predicate      조건
     * @param orderSpecifier 정렬순서
     * @return OrderSpecifier
     */
    public static OrderSpecifier<?> _if(boolean predicate, OrderSpecifier<?> orderSpecifier) {
        return predicate ? orderSpecifier : null;
    }
    
    /**
     * 조건이 참이면 정렬순서1 적용 거짓이면 정렬순서2 적용
     *
     * @param predicate       조건
     * @param orderSpecifier1 정렬순서1
     * @param orderSpecifier2 정렬순서2
     * @return OrderSpecifier
     */
    public static OrderSpecifier<?> _if(boolean predicate, OrderSpecifier<?> orderSpecifier1, OrderSpecifier<?> orderSpecifier2) {
        return predicate ? orderSpecifier1 : orderSpecifier2;
    }
    
    /**
     * 조건이 참이면 정렬순서1 적용 거짓이면 정렬순서2 적용
     *
     * @param predicate       조건
     * @param orderSpecifier1 정렬순서1
     * @param orderSpecifier2 정렬순서2
     * @return OrderSpecifier
     */
    public static OrderSpecifier<?>[] _if(boolean predicate, OrderSpecifier<?>[] orderSpecifier1, OrderSpecifier<?>[] orderSpecifier2) {
        return predicate ? orderSpecifier1 : orderSpecifier2;
    }
    
    /**
     * 값이 널이면 표현식 적용
     *
     * @param value      값
     * @param expression 평가식
     * @return BooleanExpression
     */
    public static BooleanExpression ifNull(Object value, BooleanExpression expression) {
        return _if(value == null, expression);
    }
    
    /**
     * 값이 널이 아니면 표현식 적용
     *
     * @param value      값
     * @param expression 평가식
     * @return BooleanExpression
     */
    public static BooleanExpression ifNotNull(Object value, BooleanExpression expression) {
        return _if(value != null, expression);
    }
    
    /**
     * 값이 널이 아니면 표현식 적용
     *
     * @param value      값
     * @param expression 평가식 지연
     * @return BooleanExpression
     */
    public static BooleanExpression ifNotNull(Object value, Supplier<BooleanExpression> expression) {
        return _if(value != null, expression);
    }
    
    /**
     * 빈 문자열이면 표현식 적용
     *
     * @param value      문자열
     * @param expression 평가식
     * @return BooleanExpression
     */
    public static BooleanExpression ifEmpty(String value, BooleanExpression expression) {
        return _if(isEmpty(value), expression);
    }
    
    /**
     * 빈 문자열이 아니면 표현식 적용
     *
     * @param value      문자열
     * @param expression 평가식
     * @return BooleanExpression
     */
    public static BooleanExpression ifNotEmpty(String value, BooleanExpression expression) {
        return _if(!isEmpty(value), expression);
    }
    
    /**
     * 값이 널이 아니면 함수 적용
     *
     * @param fn    적용 함수
     * @param value 값
     * @return BooleanExpression
     */
    public static <T extends Comparable<?>> BooleanExpression ifNotNull(Function<T, BooleanExpression> fn, T value) {
        return value == null ? null : fn.apply(value);
    }
    
    /**
     * 값이 비어있으면 거짓으로 평가
     *
     * @param path  경로
     * @param fn    비교방법
     * @param value 값
     * @return BooleanExpression
     */
    public static BooleanExpression require(StringPath path, BiFunction<StringExpression, String, BooleanExpression> fn, String value) {
        return !StringUtils.hasText(value) ? FALSE : fn.apply(path, value);
    }
    
    /**
     * 값이 존재할 경우에만 적용
     *
     * @param path  경로
     * @param fn    비교방법
     * @param value 값
     * @return BooleanExpression
     */
    public static BooleanExpression hasText(StringPath path, BiFunction<StringExpression, String, BooleanExpression> fn, String value) {
        return !StringUtils.hasText(value) ? null : fn.apply(path, value);
    }
    
    /**
     * path가 ALL 또는 일치함 여부
     *
     * @param path  경로
     * @param value 값
     * @return BooleanExpression
     */
    public static BooleanExpression allOrEq(StringPath path, String value) {
        return path.eq(ALL).or(path.eq(value));
    }
    
    /**
     * path가 ALL 또는 일치함 여부
     *
     * @param path  경로
     * @param value 비교 표현식
     * @return BooleanExpression
     */
    public static BooleanExpression allOrEq(StringPath path, StringExpression value) {
        return path.eq(ALL).or(path.eq(value));
    }
    
    /**
     * 컬렉션이 존재할 경우 IN 연산 적용
     *
     * @param path       경로
     * @param collection 컬렉션
     * @return BooleanExpression
     */
    public static BooleanExpression hasItem(StringPath path, Collection<String> collection) {
        return CollectionUtils.isEmpty(collection) ? null : path.in(collection);
    }
    
    /**
     * 컬렉션이 존재할 경우 NOT IN 연산 적용
     *
     * @param path       경로
     * @param collection 컬렉션
     * @return BooleanExpression
     */
    public static BooleanExpression hasItemNotIn(StringPath path, Collection<String> collection) {
        return CollectionUtils.isEmpty(collection) ? null : path.notIn(collection);
    }
    
    /**
     * NULL과 빈 문자열을 기본값으로 치환하여 적용
     *
     * @param path         경로
     * @param fn           비교방법
     * @param defaultValue 기본값
     * @param value        값
     * @return BooleanExpression
     */
    public static BooleanExpression nvl(StringPath path, BiFunction<StringExpression, String, BooleanExpression> fn, String defaultValue, String value) {
        return fn.apply(nvl(path, defaultValue), value);
    }
    
    /**
     * NULL과 빈 문자열을 기본값으로 치환하여 적용
     *
     * @param path         경로
     * @param defaultValue 기본값
     * @return StringExpression
     */
    public static StringExpression nvl(StringExpression path, String defaultValue) {
        return path.coalesce("").when("").then(defaultValue).otherwise(path);
    }
    
    /**
     * NULL과 빈 문자열을 기본경로로 치환하여 적용
     *
     * @param path        경로
     * @param defaultPath 기본경로
     * @return StringExpression
     */
    public static StringExpression nvl(StringExpression path, StringPath defaultPath) {
        return path.coalesce("").when("").then(defaultPath).otherwise(path);
    }
    
    /**
     * boolean 형에 대해 NULL일 경우 기본값을 적용
     *
     * @param path         경로
     * @param defaultValue 기본값
     * @return BooleanExpression
     */
    public static BooleanExpression nvl(BooleanPath path, boolean defaultValue) {
        return path.coalesce(defaultValue);
    }
    
    /**
     * Number 형에 대해 NULL일 경우 기본값을 적용
     *
     * @param path         경로
     * @param defaultValue 기본값
     * @param <T>          Type
     * @return NumberExpression
     */
    @SuppressWarnings("unchecked")
    public static <T extends Number & Comparable<?>> NumberExpression<T> nvl(NumberPath<T> path, T defaultValue) {
        return (NumberExpression<T>) path.coalesce(defaultValue);
    }
    
    /**
     * Date 형에 대해 NULL일 경우 기본값을 적용
     *
     * @param path         경로
     * @param defaultValue 기본값
     * @return DateExpression<LocalDate>
     */
    public static DateExpression<LocalDate> nvl(DatePath<LocalDate> path, LocalDate defaultValue) {
        return path.coalesce(defaultValue);
    }
    
    /**
     * Date 형에 대해 NULL일 경우 기본값을 적용
     *
     * @param path         경로
     * @param defaultValue 기본값
     * @return DateExpression<LocalDate>
     */
    public static DateExpression<LocalDate> nvl(DatePath<LocalDate> path, DatePath<LocalDate> defaultValue) {
        return path.coalesce(defaultValue);
    }
    
    /**
     * Date 형에 대해 NULL일 경우 19000101을 기본값을 적용
     *
     * @param path 경로
     * @return DateExpression<LocalDate>
     */
    public static DateExpression<LocalDate> firstDate(DatePath<LocalDate> path) {
        return nvl(path, FIRST_DATE);
    }
    
    /**
     * Date 형에 대해 NULL일 경우 99991231을 기본값을 적용
     *
     * @param path 경로
     * @return DateExpression<LocalDate>
     */
    public static DateExpression<LocalDate> lastDate(DatePath<LocalDate> path) {
        return nvl(path, LAST_DATE);
    }
    
    /**
     * Date 형에 대해 NULL일 경우 99991231을 기본값을 적용
     *
     * @param date localDate
     * @return LocalDate
     */
    public static LocalDate lastDate(LocalDate date) {
        return date == null ? LAST_DATE : date;
    }
    
    /**
     * QueryDSL 표현식에 날짜를 더한다
     *
     * @param path  DatePath
     * @param value 더할 날짜
     * @return DateTemplate<LocalDate>
     */
    public static DateTemplate<LocalDate> addDays(DatePath<LocalDate> path, int value) {
        return dateTemplate(LocalDate.class, "ADDDATE({0},{1})", path, asNumber(value));
    }
    
    /**
     * QueryDSL 표현식에 날짜를 더한다
     *
     * @param expression DateExpression
     * @param value      더할 날짜
     * @return DateTemplate<LocalDate>
     */
    public static DateTemplate<LocalDate> addDays(DateExpression<LocalDate> expression, int value) {
        return dateTemplate(LocalDate.class, "ADDDATE({0},{1})", expression, asNumber(value));
    }
    
    /**
     * QueryDSL 표현식에 개월을 더한다
     *
     * @param expression StringExpression
     * @param value      더할 개월
     * @return StringTemplate
     */
    public static StringTemplate addMonths(StringExpression expression, int value) {
        return stringTemplate("DATE_FORMAT({0},{1})", dateTemplate(LocalDate.class, "DATE_ADD({0}, INTERVAL {1} MONTH)", expression.concat("01"), asNumber(value)), "yyyyMM");
    }
    
    /**
     * QueryDSL 개월수 구하기
     *
     * @param from 시작일
     * @param to   종료일
     * @return numberTemplate
     */
    public static NumberTemplate<Integer> monthsBetween(LocalDate from, DateExpression<LocalDate> to) {
        return numberTemplate(Integer.class, "TIMESTAMPDIFF(MONTH, {0}, {1})", from, to);
    }
    
    /**
     * QueryDSL 해당월의 1일 구하기
     *
     * @param datePath DatePath<LocalDate>
     * @return DateTemplate<LocalDate>
     */
    public static DateTemplate<LocalDate> atFirstDate(DatePath<LocalDate> datePath) {
        return Expressions.dateTemplate(LocalDate.class, datePath.yearMonth().stringValue().concat("01").toString());
    }
    
    /**
     * QueryDSL 날짜형 표현식을 문자열 표현식으로 변환
     *
     * @param coalesce Coalesce<LocalDate>
     * @param format   날짜 포맷
     * @return DateTemplate<LocalDate>
     */
    public static StringTemplate dateFormat(Coalesce<LocalDate> coalesce, String format) {
        return stringTemplate("DATE_FORMAT({0},{1})", coalesce, format);
    }
    
    /**
     * QueryDSL 날짜형 표현식을 문자열 표현식으로 변환
     *
     * @param localDate DatePath<LocalDate>
     * @param format    날짜 포맷
     * @return DateTemplate<LocalDate>
     */
    public static StringTemplate dateFormat(DatePath<LocalDate> localDate, String format) {
        return stringTemplate("DATE_FORMAT({0},{1})", localDate, format);
    }
    
    /**
     * QueryDSL 문자열 표현식을 날짜형 표현식으로 변환
     *
     * @param expression StringExpression
     * @param format     날짜 포맷
     * @return DateTimeTemplate<LocalDateTime>
     */
    public static DateTimeTemplate<LocalDateTime> strToDate(StringExpression expression, String format) {
        return dateTimeTemplate(LocalDateTime.class, "STR_TO_DATE({0},{1})", expression, format);
    }
    
    /**
     * 기준일자가 두 DatePath 기간내인지 확인
     *
     * @param value   LocalDate 기준일자
     * @param sttDate DatePath 시작일
     * @param endDate DatePath 종료일
     * @return BooleanExpression
     */
    public static BooleanExpression between(LocalDate value, DatePath<LocalDate> sttDate, DatePath<LocalDate> endDate) {
        return sttDate.loe(value).and(lastDate(endDate).goe(value));
    }
    
    /**
     * 기준일자가 두 DatePath 기간내인지 확인
     *
     * @param value   DateOperation<LocalDate> 기준일자
     * @param sttDate DatePath 시작일
     * @param endDate DatePath 종료일
     * @return BooleanExpression
     */
    public static BooleanExpression between(DateOperation<LocalDate> value, DatePath<LocalDate> sttDate, DatePath<LocalDate> endDate) {
        return sttDate.loe(value).and(lastDate(endDate).goe(value));
    }
    
    /**
     * 기준일자가 두 DatePath 기간내인지 확인
     *
     * @param value   DatePath 기준일자
     * @param sttDate DatePath 시작일
     * @param endDate DatePath 종료일
     * @return BooleanExpression
     */
    public static BooleanExpression between(DatePath<LocalDate> value, DatePath<LocalDate> sttDate, DatePath<LocalDate> endDate) {
        return sttDate.loe(value).and(lastDate(endDate).goe(value));
    }
    
    /**
     * 기준일자가 두 DatePath 기간내인지 확인
     *
     * @param value   DateExpression 기준일자
     * @param sttDate DatePath 시작일
     * @param endDate DatePath 종료일
     * @return BooleanExpression
     */
    public static BooleanExpression between(DateExpression<LocalDate> value, DatePath<LocalDate> sttDate, DatePath<LocalDate> endDate) {
        return sttDate.loe(value).and(lastDate(endDate).goe(value));
    }
    
    /**
     * 기준일자가 두 DatePath 기간내인지 확인
     *
     * @param coalesce Coalesce 기준일자
     * @param sttDate  DatePath 시작일
     * @param endDate  DatePath 종료일
     * @return BooleanExpression
     */
    public static BooleanExpression between(Coalesce<LocalDate> coalesce, DatePath<LocalDate> sttDate, DatePath<LocalDate> endDate) {
        return sttDate.loe(coalesce).and(lastDate(endDate).goe(coalesce));
    }
    
    /**
     * 숫자가 두 NumberPath 사이인지 확인
     *
     * @param value 기준값
     * @param from  시작값
     * @param to    종료값
     * @param <T>   Type
     * @return BooleanExpression
     */
    public static <T extends Number & Comparable<?>> BooleanExpression between(T value, NumberPath<T> from, NumberPath<T> to) {
        return from.loe(value).and(to.goe(value));
    }
    
    /**
     * between 확장
     * 시작과 종료가 모두 null일 경우 예외 방지
     *
     * @param path ComparableExpression
     * @param from Comparable 시작
     * @param to   Comparable 종료
     * @return BooleanExpression
     */
    public static <T extends Comparable<?>> BooleanExpression between(ComparableExpression<T> path, T from, T to) {
        return from == null && to == null ? null : path.between(from, to);
    }
    
    /**
     * between 확장
     * 시작과 종료가 모두 null일 경우 예외 방지
     *
     * @param path ComparableExpression
     * @param from Expression 시작
     * @param to   Expression 종료
     * @return BooleanExpression
     */
    public static <T extends Comparable<?>> BooleanExpression between(ComparableExpression<T> path, Expression<T> from, Expression<T> to) {
        return from == null && to == null ? null : path.between(from, to);
    }
    
    /**
     * between 확장
     * 빈 문자열인 경우에는 조건에서 제외
     *
     * @param path StringPath
     * @param from 시작
     * @param to   종료
     * @return BooleanExpression
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static BooleanExpression between(StringPath path, String from, String to) {
        return between((ComparableExpression) path, emptyToNull(from), emptyToNull(to));
    }
    
    /**
     * between 확장
     * 빈 문자열인 경우에는 조건에서 제외
     *
     * @param value 기준값
     * @param from  시작 Stringpath
     * @param to    종료 Stringpath
     * @return BooleanExpression
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static BooleanExpression between(String value, StringPath from, StringPath to) {
        return from.loe(value).and(to.coalesce(fromDate(LAST_DATE)).goe(value));
    }
    
    /**
     * 년월일 date 타입을 년월로 between 구하기
     * <p>
     * as-is: substr(value, 1, 6) between substr(sttDate, 1, 6) and substr(endDate, 1, 6)
     * </p>
     *
     * @param value   기준 일자
     * @param sttDate 시작 일자
     * @param endDate 종료 일자
     * @return BooleanExpression
     */
    public static BooleanExpression betweenMonth(LocalDate value, DatePath<LocalDate> sttDate, DatePath<LocalDate> endDate) {
        return sttDate.yearMonth().loe(Expressions.numberPath(Integer.class, toYearMonthString(value)))
            .and(lastDate(endDate).yearMonth().goe(Expressions.numberPath(Integer.class, toYearMonthString(value))));
    }
    
    
    /**
     * 빈 문자열을 null 로 치환
     *
     * @param value 문자열
     * @return 문자열
     */
    private static String emptyToNull(String value) {
        return StringUtils.hasText(value) ? value : null;
    }
    
    /**
     * 두 표현식중 큰쪽을 반환하는 표현식
     *
     * @param expression1 표현식1
     * @param expression2 표현식2
     * @param <T>         비교 가능한 형식
     * @return 큰 표현식
     */
    public static <T extends Comparable<?>> ComparableExpression<T> max(ComparableExpression<T> expression1, ComparableExpression<T> expression2) {
        return _case()
            .when(expression1.gt(expression2))
            .then(expression1)
            .otherwise(expression2);
    }
    
    /**
     * 두 표현식중 작은쪽을 반환하는 표현식
     *
     * @param expression1 표현식1
     * @param expression2 표현식2
     * @param <T>         비교 가능한 형식
     * @return 작은 표현식
     */
    public static <T extends Comparable<?>> ComparableExpression<T> min(ComparableExpression<T> expression1, ComparableExpression<T> expression2) {
        return _case()
            .when(expression1.lt(expression2))
            .then(expression1)
            .otherwise(expression2);
    }
    
    /**
     * 숫자 경로에 대해 합을 구한다.
     *
     * @param path 경로
     * @return DSL 표현식
     */
    public static DslExpression<BigDecimal> sum(NumberPath<BigDecimal> path) {
        return path.sum().coalesce(ZERO).as(path);
    }
    
    /**
     * CaseBuilder 단축문
     *
     * @return CaseBuilder
     */
    public static CaseBuilder _case() {
        return new CaseBuilder();
    }
    
    /**
     * NullExpression 단축문
     *
     * @param <T> Type
     * @return NullExpression<T>
     */
    public static <T> NullExpression<T> _null() {
        return nullExpression();
    }
    
    /**
     * 정렬 가변인자를 배열로 변환
     *
     * @param order OrderSpecifier
     * @return OrderSpecifier[]
     */
    public static OrderSpecifier<?>[] order(OrderSpecifier<?>... order) {
        return order;
    }
}
