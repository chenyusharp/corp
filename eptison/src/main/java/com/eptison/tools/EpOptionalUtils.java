package com.eptison.tools;

import com.google.common.collect.Lists;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Optional 工具类
 *
 * @author LMyang
 * @date 2020/4/12
 */
@UtilityClass
public class EpOptionalUtils {

    public static <T> Optional<T> notNull(T o) {
        return Optional.ofNullable(o);
    }

    /**
     * if input is null or blank
     * return Optional.empty()
     * else
     * return Optional.of(value);
     */
    public static Optional<String> notBlank(String value) {
        return StringUtils.isBlank(value) ? Optional.empty() : Optional.of(value);
    }

    /**
     * if input is null or empty
     * return Optional.empty()
     * else
     * return Optional.of(value);
     */
    public static <T> Optional<Collection<T>> notEmpty(Collection<T> collection) {
        return CollectionUtils.isEmpty(collection) ? Optional.empty() : Optional.of(collection);
    }

    public static <T> Optional<List<T>> notEmptyList(List<T> list) {
        return CollectionUtils.isEmpty(list) ? Optional.empty() : Optional.of(list);
    }

    public static <T> Optional<List<List<T>>> partitionIfNotEmpty(List<T> list, int size) {
        return CollectionUtils.isEmpty(list) ? Optional.empty() :
                Optional.of(list).map(v -> Lists.partition(v, size));
    }

    /**
     * if input is null or empty
     * return Optional.empty()
     * else
     * return Optional.of(value);
     */
    public static <K, V> Optional<Map<K, V>> notEmpty(Map<K, V> map) {
        return MapUtils.isEmpty(map) ? Optional.empty() : Optional.of(map);
    }

    public static <T> List<T> emptyOrNewArrayList(List<T> list) {
        return notEmptyList(list).orElse(new ArrayList<>());
    }

    public static <K, V> Map<K, V> emptyOrNewHashMap(Map<K, V> map) {
        return MapUtils.isEmpty(map) ? new HashMap<>() : map;
    }

    public static <T> T nullOrElse(T obj, T elseValue) {
        return Optional.ofNullable(obj).orElse(elseValue);
    }

    public static BigDecimal nullOrZero(BigDecimal decimal) {
        return Optional.ofNullable(decimal).orElse(BigDecimal.ZERO);
    }

    /**
     * 为空则返回0，否则返回原值
     */
    public static Integer nullAsIntZero(Integer decimal) {
        return Optional.ofNullable(decimal).orElse(0);
    }

    /**
     * 为空则返回0，否则返回原值
     */
    public static Long nullAsLongZero(Long decimal) {
        return Optional.ofNullable(decimal).orElse(0L);
    }

    public static <T> List<T> nullOrEmptyList(List<T> list) {
        return notEmptyList(list).orElse(Collections.emptyList());
    }

    public static String blankOrElse(String value, String other) {
        return StringUtils.isBlank(value) ? other : value;
    }

    public static <T> List<T> emptyOrElse(List<T> list, List<T> other) {
        return notEmptyList(list).orElse(other);
    }

    public static Optional<Boolean> isTrue(Boolean bool) {
        return bool == null || !bool ? Optional.empty() : notNull(bool);
    }

    public static void isTrue(Boolean bool, Runnable task) {
        if (Boolean.TRUE.equals(bool)) {
            task.run();
        }
    }

    public static void isTrue(Boolean bool, Runnable task, Runnable elseTask) {
        if (Boolean.TRUE.equals(bool)) {
            task.run();
        } else {
            elseTask.run();
        }
    }

    public static <T, R> R nullOrElse(T t, Function<? super T, ? extends R> mapper, R elseValue) {
        R r;
        return t != null && (r = mapper.apply(t)) != null ? r : elseValue;
    }

    public static <T, R> R nullOrElse(T t, Function<? super T, ? extends R> mapper, Supplier<R> supplier) {
        return nullOrElse(t, mapper, supplier.get());
    }

    public static <T, R> Optional<R> applyNotNull(T t, Consumer<T> before, Function<T, R> after) {
        AtomicReference<R> r = new AtomicReference<>();
        notNull(t).ifPresent(x -> {
            before.accept(t);
            r.set(after.apply(t));
        });
        return Optional.ofNullable(r.get());
    }

    public static <V> V trueOrElse(boolean flag, V trueValue, V falseValue) {
        return flag ? trueValue : falseValue;
    }

}
