package testz;

public class SpecialCharacter {

	public static void main(String[] args) {
		String str ="javatechie!@#$";
		
		for(char st : str.toCharArray() ) {
			if(Character.isLetterOrDigit(st)) {
				System.out.println(st);
			}
			
		}
		
		//System.out.println("Number of special characters: " + count);
		
	}

}
