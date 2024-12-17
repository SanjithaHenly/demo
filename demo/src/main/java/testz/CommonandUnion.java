package testz;

import java.util.HashSet;
import java.util.Set;

public class CommonandUnion {

	public static void main(String[] args) {
		int array1[] = { 3, 7, 5, 4, 6, 9 };
		int array2[] = { 3, 10, 15, 4, 96, 90 };

		getCommon(array1, array2);
		getUnion(array1, array2);
	}

	public static void getCommon(int array1[], int array2[]) {

		Set<Integer> s = new HashSet<Integer>();

		for (int i = 0; i < array1.length; i++) {

			s.add(array1[i]);

		}

		for (int i = 0; i < array2.length; i++) {
			if (s.contains(array2[i])) {
				System.out.println(array2[i]+ " ");
			}

		}
	}
	
	
	public static void getUnion(int array1[], int array2[]) {

		Set<Integer> s = new HashSet<Integer>();

		for (int i = 0; i < array1.length; i++) {

			s.add(array1[i]);

		}

		for (int i = 0; i < array2.length; i++) {
			
			s.add(array2[i]);

		}
		
		System.out.println(s);
	}
}
