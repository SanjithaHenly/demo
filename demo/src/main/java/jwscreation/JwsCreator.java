package jwscreation;

import java.io.StringWriter;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.util.Base64;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JwsCreator {

	public static String privatekey() {
		String pem = null;
		try {
			// JWK fields
			String n = "g_hZZi-pBVXvLTB3iRPi6Hr1IeFAfaKaJH2c4OGPvyv4hstPA65dEkVEd0pO67OgnlqUg1s3j5bxBe_9JTPoWiQCb_bf-nyq_r0VebEtijc2qldS0sDa2ZZDqQ3uLVa0qXv7HbmKP3MDKJqyFC50BhgLeV9hnZ6Bb3pU0W5m_caMPkn7Gh2oMDn36TWr6MbzhLs3rwQQT4q5HyHGYppmdpLDlJSM3Um9gmsP1MaBuHCxtMkfQG4z-lcBmHgHiYjTbwk8h7GGv6ZMlG08ul4U0xJVSWEMXoDdb0gtPfbW3y0impYJjOOGJvLDtWZQXJjvPMK7c1GhsMFhN8rpyEDUwQ";
	        String e = "AQAB";
	        String d = "DRQsz_rDNnsoxAK8pgIph76_0oa_XHQj8J_eT08rbtfNvpYhk4zRPoFM4sGjhqp7rZXuiAIfRITCCEObg_db_Kr67tu5oNqAG9TjbvcHO15zdXhIBgkE53GD7RorU6T3LKDT50M_F0CvvHNwWJ2t8_ioEnRIeVV_IGwwQb0-ScIgn0aFGCOscTmWEiLH80IO5eV9hSCBxBl4Ook8f9ZjlWrBqdjrDeoZoTyLhMqdj-9fH86mYnTmZ994CKBagm60DZrywOlttMGuP7crwz7P1tVBUUYy90I2WQinv4jL2oy0vvMaWBiI4hj9Q2PKJ868WixMWiZh1dHI4Jo6PSZv_Q";
	        String p = "9hkl2PkN8UzvgDZ5kqvs3uUg2yefQLeUChVltxaohMeTZWMO-k1iO9vEaq3F9YHeyCH6zJIWiHl9XCbDzfjKTs9Hy4COYrEFFnG-6Q6uWbs6oZGsIxPP3qbhT2fu4EDdz0leDsENMqA7_LH9O4PazTSpl6n_9fjfvz4jkCobPJ8";
	        String q = "iUepvEFFxM4p0gI9ksCdnKVry8XdiwbyX-SCMn3FDNm_0_EEGSu0ma5KYmjb9Oq8_ZbVzeNODuCYEEE_btziroyjq0nmJD82je-zbpMbuXOi4UzA7GMzUQg26DvvyWVVTgyabTW_-pTKRghf6mmi5BMwBOgm5_nMUjk50n0NEp8";
	        String dp = "Vg1NEqVjnrCMPoTN1d-QIBB2gKtGIFcQyMXanz6pBmTSwWz128gbRVr_P14sDkCvKcPX8phSkL4Ke6KCbQ9FjnEkZaA6KYBEiyiS3ONpS68QPVa2nj1bPjuUJTPubzO_W7AH15jhiIZG84E5Two4A_EaLBIhklzHwBN2U_6lL3k";
	        String dq = "GLLS__LCabEp5wXOLCwJb1h3t3bG8C90xfnnzsu_-xrmH2yabyjk2k14RpJVGJBvJjTQDLXbomOYGDyU_A4znnHhNH0cMeNJnmnE350J_OioIl7byuviHK8cqdW2w4Y-vccYtZNZEe1ZIxZ4o0UUMHKfThKyhUP1FcoD9DHZe0M";
	        String qi = "t4lHvrGzvNGvnFeHz_q01NC2GZg9tafomAQsXRvnDTMiZSLJVuH1JYD387ijreBKrjDX7nNRXz31lJqmqTuCx7JRwIQvHIlWYqN7La8a6Xe0SuNd3lOlb2XNbrOGc6PM49V8M71cGZP3PVHkPa5Gi0NcjLZzM0XpEdEpCBb17DQ";


			// Convert Base64 to BigInteger
			BigInteger modulus = new BigInteger(1, Base64.getUrlDecoder().decode(n));
			BigInteger privateExponent = new BigInteger(1, Base64.getUrlDecoder().decode(d));
			BigInteger primeP = new BigInteger(1, Base64.getUrlDecoder().decode(p));
			BigInteger primeQ = new BigInteger(1, Base64.getUrlDecoder().decode(q));
			BigInteger exponentDP = new BigInteger(1, Base64.getUrlDecoder().decode(dp));
			BigInteger exponentDQ = new BigInteger(1, Base64.getUrlDecoder().decode(dq));
			BigInteger coefficient = new BigInteger(1, Base64.getUrlDecoder().decode(qi));

			// Create RSA private key specification
			RSAPrivateCrtKeySpec keySpec = new RSAPrivateCrtKeySpec(modulus, BigInteger.valueOf(65537), privateExponent,
					primeP, primeQ, exponentDP, exponentDQ, coefficient);

			// Generate PrivateKey object
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
			 // Replace these values with your JWK fields
//	        String n = "mXma3RFA-uVO2r-frpy9NAXwomrH_5aoxdqF4McmAxkAUrBkUtmqN2uQ_PTmywbiFNclEfZJySnWuW-qMK6dBBpmqzpRE6qnsXoPxiYlMeMV9FgAeTwQu91vw42RgDoUisPQQkBVLDwfCZH21OKJsFSyrc7PZ0HrOUYooZMrlS-dHDnb9AdvgXqhB0JPOn2ttjAIQ1p4L94VhRWNvrBVb_QDMG4bEl4-PxTLQQktTEHnzkxI2IUU5ZxnZUQ-5fmcvpcoQITprr8_K4HTwW1PeaAhlbs0J94F3PyZ4EJgo8EJb8-fMIMOJdtPEiYoC44FmARlR0Yz5S3At1brdyHB7w";
//	        String e = "AQAB";
//	        String d = "TWDENYUHb_fA5AUM6ypXMi10f066-x_og6iDKyr8_T7TrN0GF8dqTHrNJNwMqHwV7E_0frPfy65udhvYgKbovzLYZKgITJQT7CUQYuTEh_xoC0N7KaShAzbHrJ5pp26hAL1s-Joa1yyQsXCINBjrNp3dYEgOxY60m7HFtoDOzm3fyfuGS7NBrBFmDudAOjJR_mPQb3JQ6LNkXhVLeYcQBw6qCJAUbYmpUbySNNNDYEWjqdqc-v1PAtWMjZxiKw-48VAAfZV4SlQIM_bkvPAiVPZAvgDjDR0WQcwoD4UtJqfPcagzLfwOb4DmJtPNii3oUSARTlsYmaU4flwkabLjwQ";
//	        String p = "-hc2ciE3zePMu-SBn2bQT6USyuQga5OT4opLP-b7gLCgeALXfBfZEMcY3MpNvmPaUVEZUkOj13ogBcZYMZ94Tbp8EMq90XJ8tufmmCdOLGEKYyJdJ5ocpufYTD_oXnvL5rF0-tIXAzWdPJTctcv7n9cLO2U8FOYvpb9-O2_YSH8";
//	        String q = "nRn37dm9sUWEeYxzziPkj4nLSyM89atdU-Vg0IpJe25HpnS-FIem45dVC9EbCdmpHldnUNh0AzvBbwfld8-QI0OxFsQXiEzGiKLROpyUIhyAMS1E2GVneKhb1wbgEda5GwvlXb0-l2R2niLnBDclicnFj3u0YKEDotnPtbN2TpE";
//	        String dp = "kKtS5IyQsnp_WYWi8inQgPIVv-ZdVr2lA5xKUWn1vQjvmtzR-Ef3WjxCBp7EgElU5ktKoYrdQW21DoIhTHtaZWRmFnShf4KB4HCftQ6vqv5rutMLHjiJMIfXWKxzaAym9AldiSZ1B3dBQOVAE64vHdSQ-8fhDJnnoDL4-7sEW1M";
//	        String dq = "LRNqY8B6AQclP4rhH2CFD_pFbkWqFAMbQBsscCl9dADsZgJRF6rkY6DkMgPJckYVlDMX3cZ9YwAePDFT0dCoVrXxdcrFxcHhpQqyZdLPXgo3beTDQCO4UJJd55B9ciDd87iyEvddoiqdLjptdnQsPSC7orOnyaHpXSyJwGTBqUE";
//	        String qi = "prGi5Zxssk1TlByOpbWHZwxECZpQLUu07njqVLPJtLmngd8tj_-ilPiC-j1OwuhffbIaCfOjEzY65hu7pGxhJHvTW8GA6u0k4z3-lNHQrVFhbQKlPsDtDzfdQi4w7IPkALF-LdAgbro_xNL8jRt4mvvYlwRUelj3MfS05dvYXn4";
//
//	        BigInteger modulus = new BigInteger(1, Base64.getUrlDecoder().decode(n));
//	        BigInteger publicExponent = new BigInteger(1, Base64.getUrlDecoder().decode(e));
//	        BigInteger privateExponent = new BigInteger(1, Base64.getUrlDecoder().decode(d));
//	        BigInteger primeP = new BigInteger(1, Base64.getUrlDecoder().decode(p));
//	        BigInteger primeQ = new BigInteger(1, Base64.getUrlDecoder().decode(q));
//	        BigInteger primeExponentP = new BigInteger(1, Base64.getUrlDecoder().decode(dp));
//	        BigInteger primeExponentQ = new BigInteger(1, Base64.getUrlDecoder().decode(dq));
//	        BigInteger crtCoefficient = new BigInteger(1, Base64.getUrlDecoder().decode(qi));

//	        RSAPrivateCrtKeySpec spec = new RSAPrivateCrtKeySpec(
//	                modulus, publicExponent, privateExponent, primeP, primeQ, primeExponentP, primeExponentQ, crtCoefficient);
//	        KeyFactory factory = KeyFactory.getInstance("RSA");
	        //PrivateKey privateKey = factory.generatePrivate(spec);
	       

			// Convert PrivateKey to PEM format
			pem = convertToPem(privateKey);
			// System.out.println(pem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pem;

	}

	// Convert PrivateKey to PEM format
	private static String convertToPem(PrivateKey privateKey) throws Exception {
		StringWriter stringWriter = new StringWriter();
		PemWriter pemWriter = new PemWriter(stringWriter);
		pemWriter.writeObject(new PemObject("PRIVATE KEY", privateKey.getEncoded()));
		pemWriter.flush();
		pemWriter.close();
		return stringWriter.toString();
	}

	public static void main(String[] args) {
		try {
			// Example payload
			String payload = "{\r\n"
					+ "    \"ver\": \"2.0.0\",\r\n"
					+ "    \"timestamp\": \"2024-12-16T05:23:55.384Z\",\r\n"
					+ "    \"txnid\": \"644d2aff-e43b-4bb9-9047-498cbb98dr\",\r\n"
					+ "    \"ConsentDetail\": {\r\n"
					+ "        \"consentStart\": \"2024-12-16T05:23:55.384Z\",\r\n"
					+ "        \"consentExpiry\": \"2025-01-01T00:00:00.000Z\",\r\n"
					+ "        \"consentMode\": \"STORE\",\r\n"
					+ "        \"fetchType\": \"PERIODIC\",\r\n"
					+ "        \"consentTypes\": [\r\n"
					+ "            \"PROFILE\",\r\n"
					+ "            \"TRANSACTIONS\",\r\n"
					+ "            \"SUMMARY\"\r\n"
					+ "        ],\r\n"
					+ "        \"fiTypes\": [\r\n"
					+ "            \"DEPOSIT\"\r\n"
					+ "        ],\r\n"
					+ "        \"DataConsumer\": {\r\n"
					+ "            \"id\": \"lucidledger-01-fiu\",\r\n"
					+ "            \"type\": \"FIU\"\r\n"
					+ "        },\r\n"
					+ "        \"Customer\": {\r\n"
					+ "            \"Identifiers\": [\r\n"
					+ "                {\r\n"
					+ "                    \"type\": \"MOBILE\",\r\n"
					+ "                    \"value\": \"9980111773\"\r\n"
					+ "                }\r\n"
					+ "            ]\r\n"
					+ "        },\r\n"
					+ "        \"Purpose\": {\r\n"
					+ "            \"code\": \"101\",\r\n"
					+ "            \"refUri\": \"https://api.rebit.org.in/aa/purpose/101.xml\",\r\n"
					+ "            \"text\": \"To provide your asset insights\",\r\n"
					+ "            \"Category\": {\r\n"
					+ "                \"type\": \"Personal Finance\"\r\n"
					+ "            }\r\n"
					+ "        },\r\n"
					+ "        \"FIDataRange\": {\r\n"
					+ "            \"from\": \"2023-01-01T00:00:00.000Z\",\r\n"
					+ "            \"to\": \"2025-01-01T00:00:00.000Z\"\r\n"
					+ "        },\r\n"
					+ "        \"DataLife\": {\r\n"
					+ "            \"unit\": \"YEAR\",\r\n"
					+ "            \"value\": 3\r\n"
					+ "        },\r\n"
					+ "        \"Frequency\": {\r\n"
					+ "            \"unit\": \"DAY\",\r\n"
					+ "            \"value\": 10\r\n"
					+ "        }\r\n"
					+ "    }\r\n"
					+ "}";

			// Private key (replace with your actual private key in PKCS#8 format)
			String privateKeyPem = privatekey();
			System.out.println("privatekey" +privateKeyPem);
			// Remove PEM headers and decode the key
			privateKeyPem = privateKeyPem.replace("-----BEGIN PRIVATE KEY-----", "")
					.replace("-----END PRIVATE KEY-----", "").replaceAll("\\s", "");
			byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyPem);

			// Generate PrivateKey object
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));

			// Create a signing algorithm using the private key
			Algorithm algorithm = Algorithm.RSA256(null, (RSAPrivateKey) privateKey);

			// Create the JWS (x-jws-signature) 
			String jwsSignature = JWT.create().withPayload(payload) // Add your payload
					.sign(algorithm); // Sign with private key

			// Print the JWS
			System.out.println("x-jws-signature: " + jwsSignature);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
