package com.xiazhenyu.corn.completableFuture;

import io.reactivex.Observable;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Date: 2021/10/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class BasicMain {


    public static CompletableFuture<Integer> compute() {
        return new CompletableFuture<>();
    }

    public static void main(String[] args) throws IOException {
        final CompletableFuture<Integer> f = compute();
        class Client extends Thread {

            CompletableFuture<Integer> f;

            Client(String threadName, CompletableFuture<Integer> f) {
                super(threadName);
                this.f = f;
            }


            @Override
            public void run() {

                try {
                    System.out.println(this.getName() + ": " + f.get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        new Client("Client1", f).start();
        new Client("Client2", f).start();

        System.out.println("waiting");

        f.complete(100);

        f.obtrudeValue(100);
        System.in.read();


    }

}