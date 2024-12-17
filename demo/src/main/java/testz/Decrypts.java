package testz;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.Security;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Decrypts {
	public static void main(String[] args) throws Exception {
		Security.addProvider(new BouncyCastleProvider());

		// Retrieve and decode the RSA public key
		String rsaPublicKeyBase64 = getPublicKey();

		byte[] rsaPublicKeyBytes = Base64.getDecoder().decode(rsaPublicKeyBase64);

		// Clean and prepare the encrypted data for decryption
		String encryptedFI = "PEFjY291bnQgdHlwZT0iZGVwb3NpdCIgbWFza2VkQWNjTnVtYmVyPSJYWFhYWFhYMzQ2OCIgdmVyc2lvbj0iMS4wIiBsaW5rZWRBY2NSZWY9ImY2YjE0ODJlLThmMDgtMTFlOC04NjJhLTAyNTUyYjBkM2MzNiI-Cgk8UHJvZmlsZT4KCQk8SG9sZGVycz4KCQkJPEhvbGRlciBuYW1lPSJKb2huIERvZSIgdHlwZT0iU0lOR0xFIiBhYWRoYXI9IjEyMzQ1Njc4OTAxMiIgcGFuPSJBQkNERTEyMzRGIiBlbWFpbD0iam9obi5kb2VAZXhhbXBsZS5jb20iLz4KCQk8L0hvbGRlcnM-Cgk8L1Byb2ZpbGU-Cgk8U3VtbWFyeSBjdXJyZW50QmFsYW5jZT0iMTUwMDAuMDAiIGN1cnJlbmN5PSJJTlIiIGV4Y2hnZVJhdGU9IjEuMDAiPgoJCTxQZW5kaW5nPjA8L1BlbmRpbmc-Cgk8L1N1bW1hcnk-Cgk8VHJhbnNhY3Rpb25zPgoJCTxUcmFuc2FjdGlvbiBkYXRlPSIyMDIzLTEwLTAxIiB0eXBlPSJDUkVESVQiIG1vZGU9IkFUTSIgYW1vdW50PSI1MDAwLjAwIi8-CgkJPFRyYW5zYWN0aW9uIGRhdGU9IjIwMjMtMTAtMDIiIHR5cGU9IkRFQklUIiBtb2RlPSJVUEkiIGFtb3VudD0iMjAwMC4wMCIvPgoJPC9UcmFuc2FjdGlvbnM-CjwvQWNjb3VudD4";

		// Clean the Base64 string and add padding if necessary
		// String cleanedBase64 = cleanBase64String(encryptedFI);

		// Nonce provided in the KeyMaterial
		String nonceHex = "29512b70ca8446b5947163765599cf15";
		byte[] nonce = hexStringToByteArray(nonceHex);

		// Generate local ECDH key pair using Bouncy Castle's support for Curve25519
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("X25519", "BC");
		KeyPair localKeyPair = keyPairGen.generateKeyPair();

		// Perform key agreement
		KeyAgreement keyAgreement = KeyAgreement.getInstance("X25519", "BC");
		keyAgreement.init(localKeyPair.getPrivate());

		// Convert the received public key to PublicKey object
		PublicKey dhPublicKey = KeyFactory.getInstance("X25519", "BC")
				.generatePublic(new X509EncodedKeySpec(rsaPublicKeyBytes));
		keyAgreement.doPhase(dhPublicKey, true);

		// Generate AES key from shared secret
		byte[] sharedSecret = keyAgreement.generateSecret();
		SecretKeySpec aesKey = new SecretKeySpec(sharedSecret, 0, 16, "AES");

		// Decrypt data
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, nonce);
		cipher.init(Cipher.DECRYPT_MODE, aesKey, gcmParameterSpec);

		// Decode the Base64 encoded encrypted data
		byte[] encryptedData = Base64.getDecoder().decode(encryptedFI);
		byte[] decryptedData = cipher.doFinal(encryptedData);

		// Print the decrypted data
		System.out.println("Decrypted Data: " + new String(decryptedData));
	}

	private static String getPublicKey() throws Exception {
		String nBase64 = "jwQP0JuLGmQ0Ztn_3DNPNAHtl8g-XOv66pgQjjpvNd5oKotB7hRFgZdqG_-8G6p4C0a-zGHT8kcAlB_1pe01lEd7BxGGIiLAol3gABNAWHwnl1n54iPEV0dYM40n1prcHXmxqVscaH9c6enLt_vpiPHYQWo1j0q86HQq7u1s7KeF4tzH3_oViWIfFNVj-VJ_10V_LSC1uBh45bzPJ8Xz2NzrjvDTvYhgV_ROX_RSiAos9MxolYwhXG-JsDhRwgUIR6cq0pdeP4A9Az7k9Eq2aitsP8LY2vFRSmM96u7LWKeiTWReBoPwoN_Jy6Wi18AHJGrUIy6R8gtihRTpDUuHkQ";
		String eBase64 = "AQAB"; // Exponent in Base64

		// Decode the Base64 values
		byte[] nBytes = Base64.getUrlDecoder().decode(nBase64);
		byte[] eBytes = Base64.getDecoder().decode(eBase64);

		// Convert the byte arrays to BigIntegers
		BigInteger n = new BigInteger(1, nBytes);
		BigInteger e = new BigInteger(1, eBytes);

		// Create the RSAPublicKeySpec
		RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(n, e);

		// Generate the RSA Public Key
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPublicKey rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(rsaPublicKeySpec);

		// Return the Base64 encoded public key
		return Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded());
	}

	private static String cleanBase64String(String input) {
		String cleanedBase64 = input.replaceAll("[^A-Za-z0-9+/=]", "");
		int paddingLengths = cleanedBase64.length() % 4;
		if (paddingLengths > 0) {
			cleanedBase64 += "====".substring(paddingLengths);
		}
		return cleanedBase64;
	}

	private static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}
}
