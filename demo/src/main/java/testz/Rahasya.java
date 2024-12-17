package testz;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Rahasya {

	public static void main(String[] args) {
		 try {
	            String originalText = "The concept for Disneyland began when Walt \r\n"
	            		+ "Disney was visiting Griffith Park in Los \r\n"
	            		+ "Angeles with his daughters Diane and Sharon. \r\n"
	            		+ "While watching them ride the merry-go-round,\r\n"
	            		+ "he came up with the idea of a place where \r\n"
	            		+ "adults and their children could go and have\r\n"
	            		+ "fun together, though this idea lay dormant\r\n"
	            		+ "for many years.[10][11] The earliest documented \r\n"
	            		+ "draft of Disney's plans was sent as a memo to \r\n"
	            		+ "studio production designer Dick Kelsey on August\r\n"
	            		+ "31, 1948, where it was referred to as a \"Mickey\r\n"
	            		+ "Mouse Park\", based on notes Disney made during \r\n"
	            		+ "his and Ward Kimball's trip to the Chicago \r\n"
	            		+ "Railroad Fair the same month, with a \r\n"
	            		+ "two-day stop in Henry Ford's Museum and Greenfield Village,\r\n"
	            		+ "a place with attractions like a Main Street and steamboat rides,\r\n"
	            		+ " which he had visited eight years earlier.[";
	            System.out.println("originalText: " + originalText);
	            SecretKey secretKey = generateKey();

	            // Encrypt the text
	            String encryptedText = encrypt(originalText, secretKey);
	            System.out.println("Encrypted Text: " + encryptedText);

	            // Decrypt the text
	            String decryptedText = decrypt(encryptedText, secretKey); 
	            System.out.println("Decrypted Text: " + decryptedText);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
	
	
	 private static final String ALGORITHM = "AES";

	    // Method to encrypt text
	    public static String encrypt(String data, SecretKey secretKey) throws Exception {
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
	        return Base64.getEncoder().encodeToString(encryptedBytes);
	    }

	    // Method to decrypt text
	    public static String decrypt(String encryptedData, SecretKey secretKey) throws Exception {
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.DECRYPT_MODE, secretKey);
	        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
	        return new String(decryptedBytes);
	    }

	    // Generate a secret key
	    public static SecretKey generateKey() throws Exception {
	        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
	        keyGenerator.init(128); // Key size
	        return keyGenerator.generateKey();
	    }

}
