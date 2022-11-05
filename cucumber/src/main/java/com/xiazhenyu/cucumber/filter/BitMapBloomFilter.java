package com.xiazhenyu.cucumber.filter;

import com.xiazhenyu.common.util.NumberUtil;

/**
 * Date: 2022/11/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class BitMapBloomFilter implements BloomFilter {


    private static final long serialVersionUID = -5298202174164017721L;

    private BloomFilter[] filters;


    public BitMapBloomFilter(int m) {
        long mNum = NumberUtil.div(String.valueOf(m), String.valueOf(5)).longValue();
        long size = mNum * 1024 * 8;
        filters = new BloomFilter[]{
                new DefaultFilter(size),
                new ELFFilter(size),
                new JSFilter(size),
                new PJWFilter(size),
                new SDBFilter(size)
        };
    }

    @Override
    public boolean contains(String str) {
        boolean flag = false;
        for (BloomFilter filter : filters) {
            flag |= filter.add(str);
        }
        return flag;
    }

    @Override
    public boolean add(String str) {
        for (BloomFilter filter : filters) {
            if (filter.contains(str) == false) {
                return false;
            }
        }
        return true;
    }
}