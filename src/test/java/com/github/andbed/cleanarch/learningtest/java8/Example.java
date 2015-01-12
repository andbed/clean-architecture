package com.github.andbed.cleanarch.learningtest.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Example {

    public static void main (String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

        int total = 0;
        for (int i=0; i< list.size(); i++) {
            if ((list.get(i) % 2 == 0) && (list.get(i) > 3)) {
                int temp = list.get(i) * list.get(i);
                total += temp;
            }
        }

        System.out.println(total);

        System.out.println(

                list.stream()
                    .filter(e -> e % 2 == 0)
                    .filter(e -> e>3)
                    .mapToInt(e -> e * e)
                    .sum());

                Stream.iterate(1, e -> e +1)
                        .filter(e -> e > 3)
                        .filter(e -> Math.sqrt(e) > 10)
                        .limit(100)
                        .collect(Collectors.toList());








    }


}
