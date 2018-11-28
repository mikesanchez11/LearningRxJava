package com.rxjava.chapter3.examples;

import io.reactivex.Observable;

public class SuppresingOperatorsLauncher {

    public static void main(String[] args) {
        filterOperator();

        takeOperator();

        skipOperator();

        takeWhileOperator();

        skipWhileOperator();

        distinctLengthOperator();

        distinctStringsOperator();

        distinctUntilChangedOperator();

        elementAtOperator();
    }

    /*
        Grabs the specific desired item you want.
        Return a Maybe<> as it will only return one emission, and if it doesn't find the item you want
        It will return an empty Maybe Observable.
    */
    private static void elementAtOperator() {
        System.out.println("Element at: ");
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .elementAt(3)
                .subscribe(System.out::println);
    }

    /*
       Will Only take the first occurrence and ignore duplicate consecutive occurrences.
       Output will be
       Received: 1
       Received: 2
       Received: 3
       Received: 2
       Received: 1
     */
    private static void distinctUntilChangedOperator() {
        System.out.println("Distinct Until Changed:");
        Observable.just(1, 1, 1, 2, 2, 3, 3, 2, 1, 1)
                .distinctUntilChanged()
                .subscribe(i -> System.out.println("Received: " + i));
        System.out.println();
    }

    /*
        Distinct only allows the first occurrence of every item, no duplicate strings with same length.
        First occurrence of string with x amount of letters will be saved first, if another word with
        the same amount of letters is emitted, it will be suppressed.

        Take in mind that distinct is expensive since it behaves like a hashmap.
    */
    private static void distinctStringsOperator() {
        System.out.println("Distinct Strings: ");
        Observable.just("Alpha", "Beta","Gamma", "Delta", "Epsilon")
                .distinct(s -> s.length())
                .subscribe(i -> System.out.println("Received: " + i));
        System.out.println();
    }

    /*
        Distinct only allows the first occurrence of every item, no duplicate string lengths.
    */
    private static void distinctLengthOperator() {
        System.out.println("Distinct String Length: ");
        Observable.just("Alpha", "Beta","Gamma", "Delta", "Epsilon")
                .map(s -> s.length())
                .distinct()
                .subscribe(i -> System.out.println("Received: " + i));
        System.out.println();
    }

    /*
        Skips all the items until the condition is true, once the condition is true
        it will take every item that follows
     */
    private static void skipWhileOperator() {
        System.out.println("Skip While: ");
        Observable.range(1,100)
                .skipWhile(i -> i <= 95)
                .subscribe(s -> System.out.println("Received: " + s));
        System.out.println();
    }

    /*
        Takes all the items that are true to the condition, once it is false
        it calls the onComplete() operator and completes.
     */
    private static void takeWhileOperator() {
        System.out.println("Take While: ");
        Observable.range(1,100)
                .takeWhile(i -> i < 5)
                .subscribe(s -> System.out.println("Received: " + s));
        System.out.println();
    }

    /*
        Filters the items you want, it must meet the condition in the method
        for it to make it through the bottom of the chain, otherwise it ignores that item.
     */
    private static void filterOperator() {
        System.out.println("Filter: ");
        Observable.just("Alpha", "Beta","Gamma", "Delta", "Epsilon")
                .filter(s -> s.length() <= 5)
                .subscribe(System.out::println);
        System.out.println();
    }

    /*
        Takes a specific number of items you want.
     */
    private static void takeOperator() {
        System.out.println("Take: ");
        Observable.just("Alpha", "Beta","Gamma", "Delta", "Epsilon")
                .take(3)
                .subscribe(System.out::println);
        System.out.println();
    }

    /*
        Skips a specific number of items you want.
     */
    private static void skipOperator() {
        System.out.println("Skip: ");
        Observable.just("Alpha", "Beta","Gamma", "Delta", "Epsilon")
                .skip(2)
                .subscribe(System.out::println);
        System.out.println();
    }
}
