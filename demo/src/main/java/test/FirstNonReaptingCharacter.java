package test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonReaptingCharacter {

	public static void main(String[] args) {

		String  input= "ilovejavaechie";
		String output = Arrays.stream(input.split(""))
								.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new,  Collectors.counting()))
								.entrySet().stream()
								.filter(s->s.getValue()==1)
								.findFirst().get().getKey();
		System.out.println(output);

	}

}
