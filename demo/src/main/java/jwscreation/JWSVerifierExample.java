package jwscreation;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.SignedJWT;

public class JWSVerifierExample {
	public static void main(String[] args) throws Exception {
		// JWS token
		String jws = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ2ZXIiOiIyLjAuMCIsInRpbWVzdGFtcCI6IjIwMjQtMTItMTZUMDU6MjM6NTUuMzg0WiIsInR4bmlkIjoiNjQ0ZDJhZmYtZTQzYi00YmI5LTkwNDctNDk4Y2JiOThkciIsIkNvbnNlbnREZXRhaWwiOnsiY29uc2VudFN0YXJ0IjoiMjAyNC0xMi0xNlQwNToyMzo1NS4zODRaIiwiY29uc2VudEV4cGlyeSI6IjIwMjUtMDEtMDFUMDA6MDA6MDAuMDAwWiIsImNvbnNlbnRNb2RlIjoiU1RPUkUiLCJmZXRjaFR5cGUiOiJQRVJJT0RJQyIsImNvbnNlbnRUeXBlcyI6WyJQUk9GSUxFIiwiVFJBTlNBQ1RJT05TIiwiU1VNTUFSWSJdLCJmaVR5cGVzIjpbIkRFUE9TSVQiXSwiRGF0YUNvbnN1bWVyIjp7ImlkIjoibHVjaWRsZWRnZXItMDEtZml1IiwidHlwZSI6IkZJVSJ9LCJDdXN0b21lciI6eyJJZGVudGlmaWVycyI6W3sidHlwZSI6Ik1PQklMRSIsInZhbHVlIjoiOTk4MDExMTc3MyJ9XX0sIlB1cnBvc2UiOnsiY29kZSI6IjEwMSIsInJlZlVyaSI6Imh0dHBzOi8vYXBpLnJlYml0Lm9yZy5pbi9hYS9wdXJwb3NlLzEwMS54bWwiLCJ0ZXh0IjoiVG8gcHJvdmlkZSB5b3VyIGFzc2V0IGluc2lnaHRzIiwiQ2F0ZWdvcnkiOnsidHlwZSI6IlBlcnNvbmFsIEZpbmFuY2UifX0sIkZJRGF0YVJhbmdlIjp7ImZyb20iOiIyMDIzLTAxLTAxVDAwOjAwOjAwLjAwMFoiLCJ0byI6IjIwMjUtMDEtMDFUMDA6MDA6MDAuMDAwWiJ9LCJEYXRhTGlmZSI6eyJ1bml0IjoiWUVBUiIsInZhbHVlIjozfSwiRnJlcXVlbmN5Ijp7InVuaXQiOiJEQVkiLCJ2YWx1ZSI6MTB9fX0.Iaj5jmt3Kqx11X8BFd8EpkPjyXdp5KIgWYV6_dDFqNEQ83tihf60FvjdmNgjVbsGZPWJaM6LMava2Kk-8KeuGCDSG--OTze2zJP7glL2N3HyqIqhk1ispaviOZNYFdBF4LLAnBwtWqIK5CzUVzJYMg2DR5RiLU6ngY3bnGTTchqxLTvd7EWDDyi_Ma7wnHorUY8LjxYMN7BQmXshhGoX5tPI4ck0FQs79_S382KemMW2_IafVyYiNiW6EsPHZpcTEbp9syWP4aXRg9lQFs3czwehYHekF-M03ia7ucKk8kx5yduGS55jyCk-PbF1IUAc8JUG-uGeTVEG33tTV7Ep1g";
		// Public key parameters (from the provided JSON object)
		String n = "g_hZZi-pBVXvLTB3iRPi6Hr1IeFAfaKaJH2c4OGPvyv4hstPA65dEkVEd0pO67OgnlqUg1s3j5bxBe_9JTPoWiQCb_bf-nyq_r0VebEtijc2qldS0sDa2ZZDqQ3uLVa0qXv7HbmKP3MDKJqyFC50BhgLeV9hnZ6Bb3pU0W5m_caMPkn7Gh2oMDn36TWr6MbzhLs3rwQQT4q5HyHGYppmdpLDlJSM3Um9gmsP1MaBuHCxtMkfQG4z-lcBmHgHiYjTbwk8h7GGv6ZMlG08ul4U0xJVSWEMXoDdb0gtPfbW3y0impYJjOOGJvLDtWZQXJjvPMK7c1GhsMFhN8rpyEDUwQ";
		String e = "AQAB";

		// Convert 'n' and 'e' to BigInteger
		BigInteger modulus = new BigInteger(1, java.util.Base64.getUrlDecoder().decode(n));
		BigInteger exponent = new BigInteger(1, java.util.Base64.getUrlDecoder().decode(e));

		// Create RSA PublicKey
		RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, exponent);
		KeyFactory factory = KeyFactory.getInstance("RSA");
		RSAPublicKey publicKey = (RSAPublicKey) factory.generatePublic(spec);

		// Parse the JWS
		SignedJWT signedJWT = SignedJWT.parse(jws);

		// Create the verifier
		JWSVerifier verifier = new RSASSAVerifier(publicKey);

		// Verify the signature
		boolean isVerified = signedJWT.verify(verifier); 

		// Output verification result
		if (isVerified) {
			System.out.println("JWS signature is valid.");
			System.out.println("Payload: " + signedJWT.getPayload().toString());
		} else {
			System.out.println("JWS signature is invalid.");
		}
	}
}
