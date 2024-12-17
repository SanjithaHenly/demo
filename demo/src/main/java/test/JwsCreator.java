//package test;
//
//import java.math.BigInteger;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.security.spec.RSAPrivateKeySpec;
//import java.security.spec.RSAPublicKeySpec;
//
//import org.springframework.cglib.core.KeyFactory;
//
//import com.nimbusds.jose.JOSEObjectType;
//import com.nimbusds.jose.JWSAlgorithm;
//import com.nimbusds.jose.JWSHeader;
//import com.nimbusds.jose.JWSObject;
//import com.nimbusds.jose.JWSSigner;
//import com.nimbusds.jose.Payload;
//import com.nimbusds.jose.crypto.RSASSASigner;
//import com.nimbusds.jose.crypto.RSASSAVerifier;
//import com.nimbusds.jose.util.Base64URL;
//import com.nimbusds.jwt.SignedJWT;
//
//public class JwsCreator {
//
//	public static void main(String[] args) throws Exception {
//		// RSA key parameters
//        String n = "g_hZZi-pBVXvLTB3iRPi6Hr1IeFAfaKaJH2c4OGPvyv4hstPA65dEkVEd0pO67OgnlqUg1s3j5bxBe_9JTPoWiQCb_bf-nyq_r0VebEtijc2qldS0sDa2ZZDqQ3uLVa0qXv7HbmKP3MDKJqyFC50BhgLeV9hnZ6Bb3pU0W5m_caMPkn7Gh2oMDn36TWr6MbzhLs3rwQQT4q5HyHGYppmdpLDlJSM3Um9gmsP1MaBuHCxtMkfQG4z-lcBmHgHiYjTbwk8h7GGv6ZMlG08ul4U0xJVSWEMXoDdb0gtPfbW3y0impYJjOOGJvLDtWZQXJjvPMK7c1GhsMFhN8rpyEDUwQ";
//        String e = "AQAB";
//        String d = "DRQsz_rDNnsoxAK8pgIph76_0oa_XHQj8J_eT08rbtfNvpYhk4zRPoFM4sGjhqp7rZXuiAIfRITCCEObg_db_Kr67tu5oNqAG9TjbvcHO15zdXhIBgkE53GD7RorU6T3LKDT50M_F0CvvHNwWJ2t8_ioEnRIeVV_IGwwQb0-ScIgn0aFGCOscTmWEiLH80IO5eV9hSCBxBl4Ook8f9ZjlWrBqdjrDeoZoTyLhMqdj-9fH86mYnTmZ994CKBagm60DZrywOlttMGuP7crwz7P1tVBUUYy90I2WQinv4jL2oy0vvMaWBiI4hj9Q2PKJ868WixMWiZh1dHI4Jo6PSZv_Q";
//
//        // Convert the key material into RSAPrivateKey and RSAPublicKey
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//
//        // Modulus (n) and Exponent (e)
//        BigInteger modulus = new BigInteger(1, Base64URL.from(n).decode());
//        BigInteger publicExponent = new BigInteger(1, Base64URL.from(e).decode());
//        BigInteger privateExponent = new BigInteger(1, Base64URL.from(d).decode());
//
//        // Create PrivateKeySpec and PublicKeySpec
//        RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(modulus, privateExponent);
//        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(modulus, publicExponent);
//
//        // Generate keys
//        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
//        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
//
//		// Create payload
//		String payload = "{" + "\"ver\": \"2.0.0\"," + "\"timestamp\": \"2024-12-12T05:23:55.384Z\","
//				+ "\"txnid\": \"644d2aff-e43b-4bb9-9047-498cbb98da\"," + "\"ConsentDetail\": {"
//				+ "    \"consentStart\": \"2024-12-12T05:23:55.384Z\","
//				+ "    \"consentExpiry\": \"2025-01-01T00:00:00.000Z\"," + "    \"consentMode\": \"STORE\","
//				+ "    \"fetchType\": \"PERIODIC\","
//				+ "    \"consentTypes\": [\"PROFILE\", \"TRANSACTIONS\", \"SUMMARY\"],"
//				+ "    \"fiTypes\": [\"DEPOSIT\"]," + "    \"DataConsumer\": {"
//				+ "        \"id\": \"lucidledger-01-fiu\"," + "        \"type\": \"FIU\"" + "    },"
//				+ "    \"Customer\": {" + "        \"Identifiers\": [{\"type\": \"MOBILE\", \"value\": \"9980111773\"}]"
//				+ "    }," + "    \"Purpose\": {" + "        \"code\": \"101\","
//				+ "        \"refUri\": \"https://api.rebit.org.in/aa/purpose/101.xml\","
//				+ "        \"text\": \"To provide your asset insights\","
//				+ "        \"Category\": {\"type\": \"Personal Finance\"}" + "    }," + "    \"FIDataRange\": {"
//				+ "        \"from\": \"2023-01-01T00:00:00.000Z\"," + "        \"to\": \"2025-01-01T00:00:00.000Z\""
//				+ "    }," + "    \"DataLife\": {\"unit\": \"YEAR\", \"value\": 3},"
//				+ "    \"Frequency\": {\"unit\": \"DAY\", \"value\": 10}" + "}" + "}";
//
//		// Create JWS header with RS256 algorithm
//		JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaJWK.getKeyID()).type(JOSEObjectType.JWT)
//				.build();
//
//		// Create the JWS object
//		JWSObject jwsObject = new JWSObject(header, new Payload(payload));
//
//		// Sign the JWS object
//		JWSSigner signer = new RSASSASigner(rsaJWK);
//		jwsObject.sign(signer);
//
//		// Output the JWS
//		String jws = jwsObject.serialize();
//		System.out.println("JWS: " + jws);
//
//		// Parse and verify the JWS
//		SignedJWT signedJWT = SignedJWT.parse(jws);
//		boolean verified = signedJWT.verify(new RSASSAVerifier(rsaJWK.toPublicJWK()));
//		System.out.println("Verified: " + verified);
//	}
//}
