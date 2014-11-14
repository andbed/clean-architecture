package com.github.andbed.cleanarch.learningtest.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Example1 {

    public static void main(String[] args) {




    }


    public static void oldFashionedSimpleFor() {

        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6);

        int total = 0;

        for (int i=0; i<ints.size(); i++) {
            total += ints.get(i);
        }

        System.out.println(total);
    }

    public static void oldFashionedComplexFor() {

        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6);

        List<Integer> evenDoubles = new ArrayList<>();

        for (int i=0; i<ints.size(); i++) {
            if (ints.get(i) % 2 == 0) {
                Integer doubled = ints.get(i) * 2;
                evenDoubles.add(doubled);
            }
        }
        System.out.println(evenDoubles);
    }

    public static void functionalSimpleFor() {

        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6);

        Integer total = ints.stream()
                .mapToInt(e -> e * 2)
                .sum();

        System.out.println(total);

    }

    public static void functionalComplexFor() {

        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6);

        List<Integer> evenDoubles = ints.stream()
                .filter(e -> e % 2 == 0)
                .map(e -> e * 2)
                .collect(Collectors.toList());

        System.out.println(evenDoubles);
        universalListProcessor(e -> e * 2, e -> e % 2 == 0);
    }

    public static void universalListProcessor(Function<Integer, Integer> func, Predicate<Integer> pred) {
        List<Integer> collection = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> col = collection.stream()
                .filter(pred)
                .map(func)
                .collect(Collectors.toList());
        System.out.println(col);

    }

}
