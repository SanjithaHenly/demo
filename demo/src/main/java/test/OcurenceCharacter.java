package test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OcurenceCharacter {

	public static void main(String[] args) {
		String  input= "ilovejavaechie";
		List<String> output = Arrays.stream(input.split(""))
				       .collect(Collectors.groupingBy(Function.identity(), 
				    		   Collectors.counting()))
				       .entrySet().stream()
				       .filter(e->e.getValue()>1)
				       .map(e->e.getKey())
				       .collect(Collectors.toList());
		
		System.out.println(output);

	}

}
