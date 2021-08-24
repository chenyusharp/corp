package com.xiazhenyu.compress;

import io.netty.handler.codec.compression.Snappy;

/**
 * Date: 2021/8/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class CompressorFactory {


    public static Compressor get(byte extraInfo) {
        switch (extraInfo & 24) {
            case 0x0:
                return new SnappyCompressor();
            default:
                return new SnappyCompressor();
        }
    }

}