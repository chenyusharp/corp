package com.xiazhenyu.sesame.classLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * Date: 2022/1/9
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ClassLoaderTest {


    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = classLoader.loadClass("com.xiazhenyu.sesame.classLoader.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.xiazhenyu.sesame.classLoader.ClassLoaderTest);


    }

}