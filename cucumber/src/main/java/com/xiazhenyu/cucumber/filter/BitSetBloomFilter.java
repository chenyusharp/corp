package com.xiazhenyu.cucumber.filter;

import java.util.BitSet;

/**
 * Date: 2022/11/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class BitSetBloomFilter implements BloomFilter {


    private static final long serialVersionUID = 6390923879424439625L;

    private final BitSet bitSet;

    private final int bitSetSize;

    private final int addedElements;
    private final int hashFunctionNumber;

    public BitSetBloomFilter(int c, int n, int k) {
        this.bitSetSize = (int) Math.ceil(c * k);
        this.bitSet = new BitSet(this.bitSetSize);
        this.addedElements = n;
        this.hashFunctionNumber = k;
    }


    public void init(String path, String charsetName) {
//        init(path,Charsetu);

    }


    @Override
    public boolean contains(String str) {
        return false;
    }

    @Override
    public boolean add(String str) {
        return false;
    }
}