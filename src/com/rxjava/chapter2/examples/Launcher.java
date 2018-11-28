package com.rxjava.chapter2.examples;

import io.reactivex.Observable;

public class Launcher {

    public static int start = 1;
    public static int count = 5;
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

//        Observable<Integer> lengths = source.map(String::length);
//
//        Observable<Integer> filtered = lengths.filter(i -> i >= 5);
//
//        filtered.subscribe(s -> System.out.println("Received: " + s));

        /*
            The above code does the same thins as the below code.
            Simple and easy to understand.
            We map(Change the data froma string to the length of the string)
            then we filter that data by only wanting the data larger than or equal to 5
            we then print the data we received.
         */
        source.map(String::length)
                .filter(i -> i >= 5)
                .subscribe(s -> System.out.println("Received: "+ s));


        //Observable.defer()
        /*
            defer allows us to keep track of the change of the data,
            if we were to not use defer the final observer would be the same
        */
        Observable<Integer> rangeSource = Observable.defer(() -> Observable.range(start, count));
        rangeSource.subscribe(i -> System.out.println("Observer 1: " + i));
        count = 10;
        rangeSource.subscribe(i -> System.out.println("Observer 2: " + i));
    }
}
