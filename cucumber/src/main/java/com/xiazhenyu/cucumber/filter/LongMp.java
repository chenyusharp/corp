package com.xiazhenyu.cucumber.filter;

import java.io.Serializable;

/**
 * Date: 2022/10/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class LongMp implements BitMap, Serializable {

    private static final long serialVersionUID = -8630420812631869174L;

    private final int[] ints;

    public LongMp() {
        ints = new int[93750000];
    }

    public LongMp(int size) {
        this.ints = new int[size];
    }

    @Override
    public void add(long i) {
        int r = (int) (i / BitMap.MACHINE64);
        int c = (int) (i & (BitMap.MACHINE64 - 1));
        ints[r] = ints[r] | (1 << c);
    }

    @Override
    public boolean contains(long i) {
        int r = (int) (i / BitMap.MACHINE64);
        int c = (int) (i & (BitMap.MACHINE64 - 1));
        return ((ints[r] >>> c) & 1) == 1;
    }

    @Override
    public void remove(long i) {
        int r = (int) (i / BitMap.MACHINE64);
        int c = (int) (i & (BitMap.MACHINE64 - 1));
        ints[r] &= ~(1 << c);
    }


}