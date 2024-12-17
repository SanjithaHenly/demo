package testz;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KeyMaterial {
	@NonNull
	String cryptoAlg;
	@NonNull
	String curve;
	@NonNull
	String params;
	@NonNull
	@JsonProperty("DHPublicKey")
	DHPublicKey dhPublicKey;
}
