package jwscreation;

import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwx.HeaderParameterNames;
import org.jose4j.lang.JoseException;

public class JwsDetachedExample {

	public static void main(String[] args) {
		try {
			// Step 1: Define the payload (JSON String)
			String payload = "{\r\n" + "    \"ver\": \"2.0.0\",\r\n"
					+ "    \"timestamp\": \"2024-12-17T05:23:55.384Z\",\r\n"
					+ "    \"txnid\": \"644d2aff-e43b-4bb9-9876-498cbb98dr\",\r\n" + "    \"ConsentDetail\": {\r\n"
					+ "        \"consentStart\": \"2024-12-17T05:23:55.384Z\",\r\n"
					+ "        \"consentExpiry\": \"2025-01-01T00:00:00.000Z\",\r\n"
					+ "        \"consentMode\": \"STORE\",\r\n" + "        \"fetchType\": \"PERIODIC\",\r\n"
					+ "        \"consentTypes\": [\r\n" + "            \"PROFILE\",\r\n"
					+ "            \"TRANSACTIONS\",\r\n" + "            \"SUMMARY\"\r\n" + "        ],\r\n"
					+ "        \"fiTypes\": [\r\n" + "            \"DEPOSIT\"\r\n" + "        ],\r\n"
					+ "        \"DataConsumer\": {\r\n" + "            \"id\": \"lucidledger-01-fiu\",\r\n"
					+ "            \"type\": \"FIU\"\r\n" + "        },\r\n" + "        \"Customer\": {\r\n"
					+ "            \"Identifiers\": [\r\n" + "                {\r\n"
					+ "                    \"type\": \"MOBILE\",\r\n"
					+ "                    \"value\": \"9980111773\"\r\n" + "                }\r\n"
					+ "            ]\r\n" + "        },\r\n" + "        \"Purpose\": {\r\n"
					+ "            \"code\": \"101\",\r\n"
					+ "            \"refUri\": \"https://api.rebit.org.in/aa/purpose/101.xml\",\r\n"
					+ "            \"text\": \"To provide your asset insights\",\r\n"
					+ "            \"Category\": {\r\n" + "                \"type\": \"Personal Finance\"\r\n"
					+ "            }\r\n" + "        },\r\n" + "        \"FIDataRange\": {\r\n"
					+ "            \"from\": \"2023-01-01T00:00:00.000Z\",\r\n"
					+ "            \"to\": \"2025-01-01T00:00:00.000Z\"\r\n" + "        },\r\n"
					+ "        \"DataLife\": {\r\n" + "            \"unit\": \"YEAR\",\r\n"
					+ "            \"value\": 3\r\n" + "        },\r\n" + "        \"Frequency\": {\r\n"
					+ "            \"unit\": \"DAY\",\r\n" + "            \"value\": 10\r\n" + "        }\r\n"
					+ "    }\r\n" + "}";

			// Step 2: RSA Private Key (JWK Format) - Parse it
			String privateKeyJson = "{\r\n"
					+ "    \"p\": \"9hkl2PkN8UzvgDZ5kqvs3uUg2yefQLeUChVltxaohMeTZWMO-k1iO9vEaq3F9YHeyCH6zJIWiHl9XCbDzfjKTs9Hy4COYrEFFnG-6Q6uWbs6oZGsIxPP3qbhT2fu4EDdz0leDsENMqA7_LH9O4PazTSpl6n_9fjfvz4jkCobPJ8\",\r\n"
					+ "    \"kty\": \"RSA\",\r\n"
					+ "    \"q\": \"iUepvEFFxM4p0gI9ksCdnKVry8XdiwbyX-SCMn3FDNm_0_EEGSu0ma5KYmjb9Oq8_ZbVzeNODuCYEEE_btziroyjq0nmJD82je-zbpMbuXOi4UzA7GMzUQg26DvvyWVVTgyabTW_-pTKRghf6mmi5BMwBOgm5_nMUjk50n0NEp8\",\r\n"
					+ "    \"d\": \"DRQsz_rDNnsoxAK8pgIph76_0oa_XHQj8J_eT08rbtfNvpYhk4zRPoFM4sGjhqp7rZXuiAIfRITCCEObg_db_Kr67tu5oNqAG9TjbvcHO15zdXhIBgkE53GD7RorU6T3LKDT50M_F0CvvHNwWJ2t8_ioEnRIeVV_IGwwQb0-ScIgn0aFGCOscTmWEiLH80IO5eV9hSCBxBl4Ook8f9ZjlWrBqdjrDeoZoTyLhMqdj-9fH86mYnTmZ994CKBagm60DZrywOlttMGuP7crwz7P1tVBUUYy90I2WQinv4jL2oy0vvMaWBiI4hj9Q2PKJ868WixMWiZh1dHI4Jo6PSZv_Q\",\r\n"
					+ "    \"e\": \"AQAB\",\r\n" + "    \"use\": \"sig\",\r\n"
					+ "    \"kid\": \"1a8f6367-c554-45b4-887e-2ab66f00a7ce\",\r\n"
					+ "    \"qi\": \"t4lHvrGzvNGvnFeHz_q01NC2GZg9tafomAQsXRvnDTMiZSLJVuH1JYD387ijreBKrjDX7nNRXz31lJqmqTuCx7JRwIQvHIlWYqN7La8a6Xe0SuNd3lOlb2XNbrOGc6PM49V8M71cGZP3PVHkPa5Gi0NcjLZzM0XpEdEpCBb17DQ\",\r\n"
					+ "    \"dp\": \"Vg1NEqVjnrCMPoTN1d-QIBB2gKtGIFcQyMXanz6pBmTSwWz128gbRVr_P14sDkCvKcPX8phSkL4Ke6KCbQ9FjnEkZaA6KYBEiyiS3ONpS68QPVa2nj1bPjuUJTPubzO_W7AH15jhiIZG84E5Two4A_EaLBIhklzHwBN2U_6lL3k\",\r\n"
					+ "    \"alg\": \"RS256\",\r\n"
					+ "    \"dq\": \"GLLS__LCabEp5wXOLCwJb1h3t3bG8C90xfnnzsu_-xrmH2yabyjk2k14RpJVGJBvJjTQDLXbomOYGDyU_A4znnHhNH0cMeNJnmnE350J_OioIl7byuviHK8cqdW2w4Y-vccYtZNZEe1ZIxZ4o0UUMHKfThKyhUP1FcoD9DHZe0M\",\r\n"
					+ "    \"n\": \"g_hZZi-pBVXvLTB3iRPi6Hr1IeFAfaKaJH2c4OGPvyv4hstPA65dEkVEd0pO67OgnlqUg1s3j5bxBe_9JTPoWiQCb_bf-nyq_r0VebEtijc2qldS0sDa2ZZDqQ3uLVa0qXv7HbmKP3MDKJqyFC50BhgLeV9hnZ6Bb3pU0W5m_caMPkn7Gh2oMDn36TWr6MbzhLs3rwQQT4q5HyHGYppmdpLDlJSM3Um9gmsP1MaBuHCxtMkfQG4z-lcBmHgHiYjTbwk8h7GGv6ZMlG08ul4U0xJVSWEMXoDdb0gtPfbW3y0impYJjOOGJvLDtWZQXJjvPMK7c1GhsMFhN8rpyEDUwQ\"\r\n"
					+ "}";

			// Step 3: Parse the RSA JWK
			RsaJsonWebKey rsaJsonWebKey = (RsaJsonWebKey) JsonWebKey.Factory.newJwk(privateKeyJson);

			// Step 4: Create a new JsonWebSignature object for signing
			JsonWebSignature jws = new JsonWebSignature();
			jws.setPayload(payload);
			jws.setKey(rsaJsonWebKey.getPrivateKey());
			jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

			// Set the "b64" header to false (RFC 7797)
			jws.getHeaders().setObjectHeaderValue(HeaderParameterNames.BASE64URL_ENCODE_PAYLOAD, false);
			jws.getHeaders().setObjectHeaderValue(HeaderParameterNames.CRITICAL, new String[] { "b64" });

			// Generate compact serialization (detached payload)
			String detachedJws = jws.getDetachedContentCompactSerialization();

			// Step 5: Print the signed JWS
			System.out.println("Detached JWS Signature:");
			System.out.println(detachedJws);
			// Step 3: Verify the signature using the public key
			JsonWebSignature verifierJws = new JsonWebSignature();
			verifierJws.setCompactSerialization(detachedJws);
			verifierJws.setKey(rsaJsonWebKey.getRsaPublicKey());
			verifierJws.setPayload(payload);

			boolean isSignatureValid = verifierJws.verifySignature();
			System.out.println("Is Signature Valid: " + isSignatureValid);
		} catch (JoseException e) {
			e.printStackTrace();
		}
	}
}
