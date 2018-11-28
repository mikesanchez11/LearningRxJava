package com.rxjava.chapter3.examples;

import io.reactivex.Observable;

import java.util.HashSet;

public class CollectionOperatorsLauncher {
    public static void main(String[] args) {
        toListOperator();

        toMapOperator();

        toMultimapOperator();

        collectOperator();
    }

    private static void collectOperator() {
        System.out.println("Collect Operator: ");
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .collect(HashSet::new, HashSet::add)
                .subscribe(s -> System.out.println("Received: " + s));
        System.out.println();
    }

    private static void toMapOperator() {
        System.out.println("To Map Operator: ");
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toMap(String::length)
                .subscribe(s -> System.out.println("Received: " + s));
        System.out.println();
    }

    private static void toMultimapOperator() {
        System.out.println("To Multimap Operator: ");
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toMultimap(String::length)
                .subscribe(s -> System.out.println("Received: " + s));
        System.out.println();
    }

    private static void toListOperator() {
        System.out.println("To List Operator: ");
        Observable.range(1,7)
                .toList()
                .subscribe(s -> System.out.println("Received: " + s));
        System.out.println();
    }
}
