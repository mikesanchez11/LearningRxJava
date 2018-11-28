package com.rxjava.chapter3.examples;

import io.reactivex.Observable;

public class ErrorOperatorLauncher {
    public static void main(String[] args) {

        onErrorReturnItemOperator();

        retryOperator();
    }

    /*
        This will retry x amount you want, or indefinite if you don't include the x value.
     */
    private static void retryOperator() {
        System.out.println("Retry Operator: ");
        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .map(i -> 10 / i)
                .retry(2)
                .subscribe(i -> System.out.println("RECEIVED: " + i),
                        e -> System.out.println("RECEIVED ERROR: " + e));
        System.out.println();
    }

    /*
        Will return a default value that you give it when an error occurs
     */

    private static void onErrorReturnItemOperator() {
        System.out.println("On Error Return Item Operator: ");
        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .map(i -> 10 / i)
                .onErrorReturnItem(-1)
                .subscribe(i -> System.out.println("RECEIVED: " + i),
                        e -> System.out.println("RECEIVED ERROR: " + e));
        System.out.println();
    }
}
