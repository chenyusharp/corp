package com.xiazhenyu.cucumber.filter;

/**
 * Date: 2022/10/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public abstract class AbstractFilter implements BloomFilter {

    private static final long serialVersionUID = -6313197924306774958L;

    protected static int DEFAULT_MACHINE_NUM = BitMap.MACHINE32;

    private BitMap bm = null;

    protected long size;


    public AbstractFilter(long maxValue, int machineNum) {
        init(maxValue, machineNum);
    }

    private void init(long maxValue, int machineNum) {
        this.size = maxValue;
        switch (machineNum) {
            case BitMap.MACHINE32:
                bm = new IntMp((int) (size / machineNum));
                break;
            default:
                throw new RuntimeException("Error Machine number!");

        }
    }

    @Override
    public boolean contains(String str) {
        final long hash = Math.abs(hash(str));
        return bm.contains(hash);
    }


    @Override
    public boolean add(String str) {
        final long hash = Math.abs(hash(str));
        if (bm.contains(hash)) {
            return false;
        }
        bm.add(hash);
        return true;
    }

    public abstract long hash(String str);


}