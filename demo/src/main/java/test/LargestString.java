package test;

import java.util.Arrays;

public class LargestString {

	public static void main(String[] args) {
		String[]  input= {"ilovejavaechie","java"};
		
		String output = Arrays.stream(input)
				.reduce((W1, W2)-> W1.length()> W2.length()? W1: W2)
				.get();
		System.out.println(output);
				
	}

}
