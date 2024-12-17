package testz;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

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

public class FIDataDecryptor {
	
	public static void main(String[] args) throws Exception {

		String encryptedFI = "-----BEGIN PRIVATE KEY-----MIICRwIBADCB6gYHKoZIzj0CATCB3gIBATArBgcqhkjOPQEBAiB/////////////////////////////////////////7TBEBCAqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqYSRShRAQge0Je0Je0Je0Je0Je0Je0Je0Je0Je0Je0JgtenHcQyGQEQQQqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq0kWiCuGaG4oIa04B7dLHdI0UySPU1+bXxhsinpxaJ+ztPZAiAQAAAAAAAAAAAAAAAAAAAAFN753qL3nNZYEmMaXPXT7QIBCASCAVMwggFPAgEBBCAA5LctR+bImJLmG4nrEpwB2UNbV9jWUxoAqL2sZpnZfKCB4TCB3gIBATArBgcqhkjOPQEBAiB/////////////////////////////////////////7TBEBCAqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqYSRShRAQge0Je0Je0Je0Je0Je0Je0Je0Je0Je0Je0JgtenHcQyGQEQQQqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq0kWiCuGaG4oIa04B7dLHdI0UySPU1+bXxhsinpxaJ+ztPZAiAQAAAAAAAAAAAAAAAAAAAAFN753qL3nNZYEmMaXPXT7QIBCKFEA0IABCuFX9RXVjl0vBIV7WzEtaUEje1XQ/yujqc3DX2rNTX2AhNW0OiEalw5K4fhdIxpDqHxuLhawGwIpkF97dRYZ7k=-----END PRIVATE KEY-----";
		// Clean the Base64 string and add padding if necessary
	    String cleanedBase64 = cleanBase64String(encryptedFI);
		//byte[] encryptedData = Base64.getDecoder().decode(encryptedFI);
		System.out.println(cleanedBase64);
	}
	
	private static String cleanBase64String(String input) {
		String cleanedBase64 = input.replaceAll("[^A-Za-z0-9+/=]", "");
		int paddingLengths = cleanedBase64.length() % 4;
		if (paddingLengths > 0) {
			cleanedBase64 += "====".substring(paddingLengths);
		}
		return cleanedBase64;
	}

	
}
