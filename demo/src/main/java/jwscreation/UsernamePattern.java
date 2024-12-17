package jwscreation;

public class UsernamePattern {

//    public static boolean validate(String username) {
//        throw new UnsupportedOperationException("Waiting to be implemented.");
//    }
//    
	public static void main(String[] args) {
		// Test examples

		System.out.println(validate("Robert.Domek")); // Valid username, returns true
		System.out.println(validate("Robert Domek")); // Invalid username, returns false
	}

	public static boolean validate(String username) {

		if (username == null || username.length() < 6 || username.length() > 20) {
			return false;
		}

		if (Character.isDigit(username.charAt(0)) || username.charAt(0) == '.') {
			return false;
		}

		if (username.charAt(username.length() - 1) == '.') {
			return false;
		}

		boolean dotFound = false;
		for (int i = 0; i < username.length(); i++) {
			char ch = username.charAt(i);

			if (Character.isLetterOrDigit(ch)) {
				continue;
			} else if (ch == '.') {
				if (dotFound) {

					return false;
				}
				dotFound = true;
			} else {

				return false;
			}
		}

		return true;
	}

}