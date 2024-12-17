package test;

import java.util.Arrays;

public class JwsVerifier {

	public static void main(String[] args) {
		int[] array1 = { 1, 3, 5, 7, 8 };
		int[] array2 = { 21, 23, 55, 76, 81 };

		int arrayOneSize = array1.length;
		int arrayTwoSize = array2.length;

		int[] mergeArray = new int[arrayOneSize + arrayTwoSize];
		System.arraycopy(array1, 0, mergeArray, 0, arrayTwoSize);
		System.arraycopy(array2, 0, mergeArray, arrayOneSize, arrayTwoSize);

		System.out.println("merged array:" + Arrays.toString(mergeArray));

	}

}
