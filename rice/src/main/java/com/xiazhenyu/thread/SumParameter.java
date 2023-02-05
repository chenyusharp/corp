package com.xiazhenyu.thread;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Date: 2021/11/10
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class SumParameter<T> {

    /**
     * 任务执行的主要参数
     */
    private final List<T> t;

    /**
     * 任务执行的扩展参数
     */
    private final Map<String, Object> params;

    /**
     * 任务执行的最小粒度 当任务执行的主要参数过多时，会拆分成多个最小粒度的任务
     */
    private final Integer groupBy;


    /**
     * 任务来源，用于监控执行时间过长的任务
     */
    private String source;


    public SumParameter(List<T> t, Map<String, Object> params, Integer groupBy) {
        this.t = t;
        this.params = params;
        this.groupBy = groupBy;
    }

    public List<T> getT() {
        return t;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public Integer getGroupBy() {
        return groupBy;
    }

    public String getSource() {
        return source;
    }

    public <O> O getparams(String key, Class<O> clazz) {
        Object o = params.get(key);
        if (Objects.isNull(o)) {
            return null;
        }
        return clazz.cast(o);
    }

    public <O> List<O> getParamsWithList(String key, Class<O> clazz) {
        Object o = params.get(key);
        if (Objects.isNull(o)) {
            return null;
        }
        return (List<O>) o;
    }

    public static <T> SumParameter<T> build(List<T> t, Map<String, Object> oMap, Integer groupBy) {
        List<T> ts = Lists.newArrayList();
        HashMap<String, Object> os = Optional.ofNullable(oMap).map(Maps::newHashMap).orElse(Maps.newHashMap());
        return new SumParameter<>(ts, os, Optional.ofNullable(groupBy).orElse(50));
    }


    public static <T> SumParameter<T> build(String source, List<T> t, Map<String, Object> oMap, Integer groupBy) {
        List<T> ts = Lists.newArrayList();
        HashMap<String, Object> os = Optional.ofNullable(oMap).map(Maps::newHashMap).orElse(Maps.newHashMap());
        SumParameter<T> sumParameter = new SumParameter<>(ts, os, Optional.ofNullable(groupBy).orElse(50));
        sumParameter.source = source;
        return sumParameter;
    }


}