package com.xiazhenyu.thread;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Date: 2021/11/14
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@AllArgsConstructor
@NoArgsConstructor
public class SumResultMap {


    private Map<Integer, SumResult<?>> sumResultMap;

    protected static SumResultMap build() {
        return new SumResultMap(Maps.newHashMap());
    }


    public <K, V> Map<K, V> getMapResult(int index, Class<K> kClass, Class<V> vClass) {
        SumResult<?> sumResult = sumResultMap.get(index);
        if (Objects.isNull(sumResult)) {
            return Maps.newHashMap();
        }
        return (Map<K, V>) sumResult.getR();
    }


    public <K, V> Map<K, List<V>> getMapListResult(int index, Class<K> kClass, Class<V> vClass) {
        SumResult<?> sumResult = sumResultMap.get(index);
        return null;
    }


}