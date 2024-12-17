package jwscreation;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Date;

import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

public class JwtValidator {

    public static void main(String[] args) throws Exception {
        // Example JWT token (replace with your token)
        String jwtToken = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJtckl2NGpsMVprMzZ4cEVITmRJUFBsZHo0WFVLaXBRT3VhcEdOQ1NRd1pNIn0.eyJleHAiOjE3MzQwNjk2NzUsImlhdCI6MTczMzk4MzI3NSwianRpIjoiNWFkMzgyMjYtNWIxMC00MTcyLTg3ZDctYTQ1ZGZiODIyMDYwIiwiaXNzIjoiaHR0cHM6Ly91YXR0b2tlbnMuc2FoYW1hdGkub3JnLmluL2F1dGgvcmVhbG1zL3NhaGFtYXRpIiwiYXVkIjpbImx1Y2lkbGVkZ2VyLTAxLWZpdSIsImFjY291bnQiXSwic3ViIjoiMzJmMWNmMWQtNTE0NS00OTdmLWFiZWItNzY4OWQ5YTA3ZWUxIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoibHVjaWRsZWRnZXItMDEtZml1IiwiYWNyIjoiMSIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLXNhaGFtYXRpIiwib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoiZW1haWwgbWljcm9wcm9maWxlLWp3dCBwcm9maWxlIGFkZHJlc3MgcGhvbmUgb2ZmbGluZV9hY2Nlc3MiLCJ1cG4iOiJzZXJ2aWNlLWFjY291bnQtbHVjaWRsZWRnZXItMDEtZml1IiwiYWRkcmVzcyI6e30sImNsaWVudEhvc3QiOiIyMC4yMDcuMTEyLjMxOjMwMTMiLCJjbGllbnRJZCI6Imx1Y2lkbGVkZ2VyLTAxLWZpdSIsInJvbGVzIjoiRklVIiwiZ3JvdXBzIjpbImRlZmF1bHQtcm9sZXMtc2FoYW1hdGkiLCJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl0sInNlY3JldC1leHBpcnktdHMiOiIyMDI1LTA1LTIxVDA2OjI3OjA0LjQzMzk4NDU3MyIsImNsaWVudEFkZHJlc3MiOiIyMC4yMDcuMTEyLjMxOjMwMTMifQ.il4967oclhqET-R0CNkMFjdsJ17mAefOKV_sMbue-YGFYCV2z-eUXO502bI3NfRySDWLPYAgELr9kAGI6wFyCdMIYvEPq-67lO2lV_clOakmN_ZVz_yR8-i5tdTD-js8EKNEiJ1LSIFMrpdIUO5mSy3MEIQuFbtqw8B0BuKhyFXgpO2wAoVl1a6O_PzuT7sTapcAi2st8MYKQ1xQhNAR_8WOwbmDd6EsuoLHVson0rgsyqWSAl1NAFAgdhqNdp3H-RrMknCmMlk_S6KMziEKHrfe1XxXcQUOpJSrl1HyVJk-n054sBc9NDdNnOW4ZCTYAPsTv-atDo99QF8vuIwXcA";

        // RSA Public Key Components (replace with your key details)
        String n = "g_hZZi-pBVXvLTB3iRPi6Hr1IeFAfaKaJH2c4OGPvyv4hstPA65dEkVEd0pO67OgnlqUg1s3j5bxBe_9JTPoWiQCb_bf-nyq_r0VebEtijc2qldS0sDa2ZZDqQ3uLVa0qXv7HbmKP3MDKJqyFC50BhgLeV9hnZ6Bb3pU0W5m_caMPkn7Gh2oMDn36TWr6MbzhLs3rwQQT4q5HyHGYppmdpLDlJSM3Um9gmsP1MaBuHCxtMkfQG4z-lcBmHgHiYjTbwk8h7GGv6ZMlG08ul4U0xJVSWEMXoDdb0gtPfbW3y0impYJjOOGJvLDtWZQXJjvPMK7c1GhsMFhN8rpyEDUwQ";
		String e = "AQAB";

        // Convert public key components into RSAPublicKey
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        BigInteger modulus = new BigInteger(1, java.util.Base64.getUrlDecoder().decode(n));
        BigInteger publicExponent = new BigInteger(1, java.util.Base64.getUrlDecoder().decode(e));
        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(modulus, publicExponent);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        // Parse the JWT token
        SignedJWT signedJWT = SignedJWT.parse(jwtToken);

        // Verify the token signature
        JWSVerifier verifier = new RSASSAVerifier((RSAPublicKey) publicKey);
        boolean isSignatureValid = signedJWT.verify(verifier);
        System.out.println("Signature valid: " + isSignatureValid);

        // Validate claims
        if (isSignatureValid) {
            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();

            // Check expiration
            Date expirationTime = claims.getExpirationTime();
            if (expirationTime != null && new Date().after(expirationTime)) {
                throw new RuntimeException("Token is expired");
            }

            // Check issuer
            String issuer = claims.getIssuer();
            if (!"myIssuer".equals(issuer)) {
                throw new RuntimeException("Invalid issuer");
            }

            // Check audience
            if (!claims.getAudience().contains("MyAudience")) {
                throw new RuntimeException("Invalid audience");
            }

            System.out.println("Token is valid. Claims: " + claims.toJSONObject());
        }
    }
}
