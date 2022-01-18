package com.xiazhenyu.sesame.dispatch;

/**
 * Date: 2022/1/18
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class FieldHasNoPolymorphic {


    static class Father {

        public int money = 1;

        public Father() {
            money = 2;
            showMeTheMoney();
        }

        public void showMeTheMoney() {
            System.out.println("I am Father,i have $" + money);
        }
    }


    static class Son extends Father {

        public int money = 3;

        public Son() {
            money = 4;
            showMeTheMoney();
        }

        @Override
        public void showMeTheMoney() {
            System.out.println("I am Son,i have $" + money);
        }

    }


    public static void main(String[] args) {
        Father gay = new Son();
        System.out.println("The gay has $" + gay.money);
    }

}