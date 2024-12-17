package testz;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.RSAPrivateCrtKeySpec;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSASSASigner;

public class JWSCreationExample {

    public static void main(String[] args) throws Exception {
        // RSA private key components
        String n = "g_hZZi-pBVXvLTB3iRPi6Hr1IeFAfaKaJH2c4OGPvyv4hstPA65dEkVEd0pO67OgnlqUg1s3j5bxBe_9JTPoWiQCb_bf-nyq_r0VebEtijc2qldS0sDa2ZZDqQ3uLVa0qXv7HbmKP3MDKJqyFC50BhgLeV9hnZ6Bb3pU0W5m_caMPkn7Gh2oMDn36TWr6MbzhLs3rwQQT4q5HyHGYppmdpLDlJSM3Um9gmsP1MaBuHCxtMkfQG4z-lcBmHgHiYjTbwk8h7GGv6ZMlG08ul4U0xJVSWEMXoDdb0gtPfbW3y0impYJjOOGJvLDtWZQXJjvPMK7c1GhsMFhN8rpyEDUwQ";
        String e = "AQAB";
        String d = "DRQsz_rDNnsoxAK8pgIph76_0oa_XHQj8J_eT08rbtfNvpYhk4zRPoFM4sGjhqp7rZXuiAIfRITCCEObg_db_Kr67tu5oNqAG9TjbvcHO15zdXhIBgkE53GD7RorU6T3LKDT50M_F0CvvHNwWJ2t8_ioEnRIeVV_IGwwQb0-ScIgn0aFGCOscTmWEiLH80IO5eV9hSCBxBl4Ook8f9ZjlWrBqdjrDeoZoTyLhMqdj-9fH86mYnTmZ994CKBagm60DZrywOlttMGuP7crwz7P1tVBUUYy90I2WQinv4jL2oy0vvMaWBiI4hj9Q2PKJ868WixMWiZh1dHI4Jo6PSZv_Q";
        String p = "9hkl2PkN8UzvgDZ5kqvs3uUg2yefQLeUChVltxaohMeTZWMO-k1iO9vEaq3F9YHeyCH6zJIWiHl9XCbDzfjKTs9Hy4COYrEFFnG-6Q6uWbs6oZGsIxPP3qbhT2fu4EDdz0leDsENMqA7_LH9O4PazTSpl6n_9fjfvz4jkCobPJ8";
        String q = "iUepvEFFxM4p0gI9ksCdnKVry8XdiwbyX-SCMn3FDNm_0_EEGSu0ma5KYmjb9Oq8_ZbVzeNODuCYEEE_btziroyjq0nmJD82je-zbpMbuXOi4UzA7GMzUQg26DvvyWVVTgyabTW_-pTKRghf6mmi5BMwBOgm5_nMUjk50n0NEp8";
        String dp = "Vg1NEqVjnrCMPoTN1d-QIBB2gKtGIFcQyMXanz6pBmTSwWz128gbRVr_P14sDkCvKcPX8phSkL4Ke6KCbQ9FjnEkZaA6KYBEiyiS3ONpS68QPVa2nj1bPjuUJTPubzO_W7AH15jhiIZG84E5Two4A_EaLBIhklzHwBN2U_6lL3k";
        String dq = "GLLS__LCabEp5wXOLCwJb1h3t3bG8C90xfnnzsu_-xrmH2yabyjk2k14RpJVGJBvJjTQDLXbomOYGDyU_A4znnHhNH0cMeNJnmnE350J_OioIl7byuviHK8cqdW2w4Y-vccYtZNZEe1ZIxZ4o0UUMHKfThKyhUP1FcoD9DHZe0M";
        String qi = "t4lHvrGzvNGvnFeHz_q01NC2GZg9tafomAQsXRvnDTMiZSLJVuH1JYD387ijreBKrjDX7nNRXz31lJqmqTuCx7JRwIQvHIlWYqN7La8a6Xe0SuNd3lOlb2XNbrOGc6PM49V8M71cGZP3PVHkPa5Gi0NcjLZzM0XpEdEpCBb17DQ";

        // Convert components to BigInteger
        BigInteger modulus = new BigInteger(1, java.util.Base64.getUrlDecoder().decode(n));
        BigInteger publicExponent = new BigInteger(1, java.util.Base64.getUrlDecoder().decode(e));
        BigInteger privateExponent = new BigInteger(1, java.util.Base64.getUrlDecoder().decode(d));
        BigInteger primeP = new BigInteger(1, java.util.Base64.getUrlDecoder().decode(p));
        BigInteger primeQ = new BigInteger(1, java.util.Base64.getUrlDecoder().decode(q));
        BigInteger primeExponentP = new BigInteger(1, java.util.Base64.getUrlDecoder().decode(dp));
        BigInteger primeExponentQ = new BigInteger(1, java.util.Base64.getUrlDecoder().decode(dq));
        BigInteger crtCoefficient = new BigInteger(1, java.util.Base64.getUrlDecoder().decode(qi));

        // Create RSA PrivateKey
        RSAPrivateCrtKeySpec privateKeySpec = new RSAPrivateCrtKeySpec(
                modulus, publicExponent, privateExponent, primeP, primeQ, primeExponentP, primeExponentQ, crtCoefficient);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        RSAPrivateKey privateKey = (RSAPrivateKey) factory.generatePrivate(privateKeySpec);

        // Create payload
        String payload = "{\r\n"
        		+ "    \"ver\": \"2.0.0\",\r\n"
        		+ "    \"timestamp\": \"2024-12-12T05:23:55.384Z\",\r\n"
        		+ "    \"txnid\": \"644d2aff-e43b-4bb9-9047-498cbb98da\",\r\n"
        		+ "    \"ConsentDetail\": {\r\n"
        		+ "        \"consentStart\": \"2024-12-12T05:23:55.384Z\",\r\n"
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

        // Create JWS header
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256).keyID("1a8f6367-c554-45b4-887e-2ab66f00a7ce").build();

        // Create JWS object
        JWSObject jwsObject = new JWSObject(header, new Payload(payload));

        // Sign the JWS
        JWSSigner signer = new RSASSASigner(privateKey);
        jwsObject.sign(signer);

        // Output the JWS
        String jws = jwsObject.serialize();
        System.out.println("Generated JWS: " + jws);
    }
}
