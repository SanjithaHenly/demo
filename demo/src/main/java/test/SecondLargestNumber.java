package test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SecondLargestNumber {

	public static void main(String[] args) {
		
		int[] numbers = {2,34,21,4,8};
		 Integer output =  Arrays.stream(numbers)
				.boxed()
				.sorted(Comparator.reverseOrder())
				.skip(1)
				.findFirst()
				.get();
		// .collect(Collectors.toList());
		System.out.println(output);
				

	}

}
