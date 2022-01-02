package com.xiazhenyu.corn.coreTec.volatileExecise;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Run {


    public static void main(String[] args) {

//        oldPrintString();
        newPrintString();
    }

    public static void oldPrintString() {
        PrintString printString = new PrintString();

        printString.printStringMethod();

        System.out.println("we need stop it");
        printString.setContinuePrint(false);
    }

    public static void newPrintString() {
        PrintString printString = new PrintString();
        new Thread(printString).start();

//        printString.printStringMethod();

        System.out.println("we need stop it");
        printString.setContinuePrint(false);
    }


}


