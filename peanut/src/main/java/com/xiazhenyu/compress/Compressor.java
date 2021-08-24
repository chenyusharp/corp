package com.xiazhenyu.compress;

import java.io.IOException;

/**
 * Date: 2021/8/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface Compressor {

    byte[] compress(byte[] array) throws IOException;

    byte[] unCompress(byte[] array) throws IOException;

}
