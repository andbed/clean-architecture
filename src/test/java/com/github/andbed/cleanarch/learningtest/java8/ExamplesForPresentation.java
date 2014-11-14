package com.github.andbed.cleanarch.learningtest.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Optional;

public class ExamplesForPresentation {

    public static void main(String [] args) {

        Optional<Computer> computer = Optional.of(new Computer());
        computer.get().soundCard = Optional.of(new SoundCard());
        computer.get().soundCard.get().driver = Optional.of(new Usb());

        String name = computer.flatMap(c -> c.soundCard)
                .flatMap(s -> s.driver)
                .map(u -> u.name)
                .orElse("UNKNOWN");


        Optional<Integer> elem = find(123);

        elem.ifPresent(System.out::println);

        LocalDateTime timePoint = LocalDateTime.now();     // The current date and time
        LocalDate.of(2012, Month.DECEMBER, 12); // from values
        LocalTime.of(17, 18); // the train I took home today
        LocalTime.parse("10:15:30"); // From a String

        int day = timePoint.getDayOfMonth();
        timePoint.plusDays(10);

    }

    public static Optional<Integer> find(int id) {
        return null;
    }

    static class Computer {
        Optional<SoundCard> soundCard;
    }

    static class SoundCard {
        Optional<Usb> driver;
    }

    static class Usb {
        public String name="aaa";
    }
}
