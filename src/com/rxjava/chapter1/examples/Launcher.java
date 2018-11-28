package com.rxjava.chapter1.examples;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Launcher {

    public static void main(String[] args) {
        Observable<String> myStrings = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        /*
            This:
            s -> System.out.println(s)
            is replaced by this when optimizing the code:
            System.out::println
         */
        System.out.println("These will be the String objects:");
        myStrings.subscribe(System.out::println);

        System.out.println("\nThese will be the size of the string:");
        myStrings.map(s -> s.length())
                .subscribe(s -> System.out.println(s));


        System.out.println();
        Observable<Long> secondIntervals = Observable.interval(1, TimeUnit.SECONDS);
        secondIntervals.subscribe(s -> System.out.println(s));
        /*
           Hold Observable for 5 seconds so Observable above has a chance to fire
        */
        sleep(5000);
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
