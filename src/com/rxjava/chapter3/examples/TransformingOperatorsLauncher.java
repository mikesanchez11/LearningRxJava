package com.rxjava.chapter3.examples;

import io.reactivex.Observable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class TransformingOperatorsLauncher {
    public static void main(String[] args) {
        mapOperator();

        startWithOperator();

        startWithArrayOperator();

        switchIfEmpty();

        defaultIfEmpty();

        sortedData();

        delayOperator();

        scanOperator();
    }

    /*
     Scan is used to do adding rolling sums, concatenating strings and boolean reductions.
     Similar to reduce() however scan does not require an onComplete() call.
    */
    private static void scanOperator() {
        System.out.println("Scan: ");
        Observable.just(5, 3, 7, 10, 2, 14)
                .scan((accumulator, next) -> accumulator + next)
                .subscribe(s -> System.out.println("Received: " + s));
        System.out.println();
    }

    private static void delayOperator() {
        System.out.println("Delay Operator: ");
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
            .delay(3, TimeUnit.SECONDS)
                .subscribe(s -> System.out.println("Received: " + s));
        sleep(5000);
        System.out.println();
    }

    public static void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sortedData() {
        System.out.println("Sorted data: ");
        Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
                .sorted() // this can have a parameter like .sorted(Comparator.reverseOrder()) to do reverseOrder
                .subscribe(System.out::println);
        System.out.println();
    }

    private static void switchIfEmpty() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .filter(s -> s.startsWith("Z"))
                .switchIfEmpty(Observable.just("Zeta", "Eta", "Theta"))
                .subscribe(s -> System.out.println("Received: " + s),
                        e -> System.out.println("Received Error: " + e));
        System.out.println();
    }

    private static void defaultIfEmpty() {
        System.out.println("Default if Empty operator: ");
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .filter(s -> s.startsWith("Z"))
                .defaultIfEmpty("None")
                .subscribe(System.out::println);
        System.out.println();
    }

    private static void startWithArrayOperator() {
        System.out.println("Start with array operator: ");
        Observable<String> menu = Observable.just("Coffee", "Tea", "Espresso", "Latte");
        menu.startWithArray("COFFEE SHOP MENU", "------------------")
                .subscribe(System.out::println);
        System.out.println();
    }

    private static void startWithOperator() {
        System.out.println("Start with operator: ");
        Observable<String> menu = Observable.just("Coffee", "Tea", "Espresso", "Latte");
        menu.startWith("COFFEE SHOP MENU")
                .subscribe(System.out::println);
        System.out.println();
    }

    private static void mapOperator() {
        System.out.println("Map Operator: ");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/d/yyyy");

        Observable.just("1/3/2016", "5/9/2016", "10/12/2016")
                .map(s -> LocalDate.parse(s, dtf))
                .subscribe(d -> System.out.println("Received: " + d));
        System.out.println();
    }
}
