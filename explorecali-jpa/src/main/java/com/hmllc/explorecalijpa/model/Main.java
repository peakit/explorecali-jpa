package com.hmllc.explorecalijpa.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
	Tour tour1 = new Tour(1, "AB", "Some descriptionAB", "Some blurbAB", 100, "2");
	Tour tour2 = new Tour(2, "BA", "Some descriptionBA", "Some blurbBA", 200, "1");
	Tour tour3 = new Tour(3, "CA", "Some descriptionCA", "Some blurbCA", 100, "1");
	Tour tour4 = new Tour(4, "CB", "Some descriptionCB", "Some blurbCB", 200, "4");
	Tour tour5 = new Tour(5, "DC", "Some descriptionDC", "Some blurbDC", 500, "2");

	List<Tour> tours = List.of(tour1, tour2, tour3, tour4, tour5);

	// grouping tours by duration
	Map<String, List<Tour>> toursByDuration = tours.stream().collect(Collectors.groupingBy(Tour::getDuration));
	System.out.println(toursByDuration);

	// counting tours by duration
	Map<String, Long> numOfToursByDuration = tours.stream()
		.collect(Collectors.groupingBy(Tour::getDuration, Collectors.counting()));
	System.out.println(numOfToursByDuration);

	// minimum price tour for each duration
	Map<String, Optional<Tour>> minPriceTourByDuration = tours.stream().collect(
		Collectors.groupingBy(Tour::getDuration, Collectors.minBy(Comparator.comparingInt(Tour::getPrice))));
	System.out.println(minPriceTourByDuration);

	int[] input = { 0, 1, 0, 2, 1, 0, 2, 1, 0 };
	System.out.println(dutchNationalFlag(input));

    }

    private static List<Integer> dutchNationalFlag(int[] input) {
	Integer count0s = 0;
	Integer count1s = 0;
	Integer count2s = 0;

	for (int i = 0; i < input.length; i++) {
	    if (input[i] == 0) {
		count0s++;
	    } else if (input[i] == 1) {
		count1s++;
	    } else if (input[i] == 2) {
		count2s++;
	    }
	}

	List<Integer> output = new ArrayList<>(count0s + count1s + count2s);
	for (int i = 1; i <= count0s; i++) {
	    output.add(0);
	}
	for (int i = 1; i <= count1s; i++) {
	    output.add(1);
	}
	for (int i = 1; i <= count2s; i++) {
	    output.add(2);
	}
	return output;
    }

}
