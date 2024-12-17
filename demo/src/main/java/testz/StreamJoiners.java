package testz;

import java.util.StringJoiner;

public class StreamJoiners {

	
	public static void main(String[] args) {
		
		StringJoiner strjoiners = new StringJoiner("," ," [ "," ] ");
		
		strjoiners.add("A");
		
		strjoiners.add("B");
		strjoiners.add("C");
		
		System.out.println(strjoiners);
		
		

		StringJoiner strjoiners2 = new StringJoiner(":");
		
		strjoiners2.add("P");
		
		strjoiners2.add("Q");
		
		
		System.out.println(strjoiners2);
		
		System.out.println(strjoiners.merge(strjoiners2));

	}

}
