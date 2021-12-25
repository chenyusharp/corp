package com.xiazhenyu.sesame;


/**
 * Date: 2021/12/14
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class AllotOnStack {


    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void alloc() {
        User user = new User();
        user.setId(1);
        user.setName("test");
    }


    public static class User {

        int id;
        String name;
        byte b;
        Object o;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public byte getB() {
            return b;
        }

        public void setB(byte b) {
            this.b = b;
        }

        public Object getO() {
            return o;
        }

        public void setO(Object o) {
            this.o = o;
        }
    }


}