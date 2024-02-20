package com.eptison.tools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 非序列化对象组对象
 *
 * @author 朱智贤
 * @since 2020-03-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpPairV2<K, V> {

    private K left;
    private V right;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        EpPairV2<?, ?> epPair = (EpPairV2<?, ?>) o;

        return new EqualsBuilder()
                .append(left, epPair.left)
                .append(right, epPair.right)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(left)
                .append(right)
                .toHashCode();
    }
}
