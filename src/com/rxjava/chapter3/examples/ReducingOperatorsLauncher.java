package com.rxjava.chapter3.examples;

import io.reactivex.Observable;

public class ReducingOperatorsLauncher {
    public static void main(String[] args) {
        countOperator();

        reduceOperator();

        allOperator();

        containsOperator();
    }

    private static void containsOperator() {
        System.out.println("Contains Operator: ");
        Observable.range(1,10000)
                .contains(9563)
                .subscribe(s -> System.out.println("Received: " + s));
        System.out.println();
    }

    private static void allOperator() {
        System.out.println("All Operator: ");
        Observable.just(5, 3, 7, 11, 2, 14)
                .all(i -> i < 10)
                .subscribe(s -> System.out.println("Received: " + s));
        System.out.println();
    }

    /*
        Similar to scan, however this will only emit the final result instead of every single value after the sum.
     */
    private static void reduceOperator() {
        System.out.println("Reduce Operator: ");
        Observable.just(5, 3, 7, 10, 2, 14)
                .reduce((total, next) -> total + next)
                .subscribe(s -> System.out.println("Received: " + s));
        System.out.println();
    }

    private static void countOperator() {
        System.out.println("Count Operator: ");
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .count()
                .subscribe(s -> System.out.println("Received: " + s));
        System.out.println();
    }
}
