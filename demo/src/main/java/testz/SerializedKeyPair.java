package testz;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SerializedKeyPair {
	private final String privateKey = "";
	@NonNull
	@JsonProperty("KeyMaterial")
	KeyMaterial keyMaterial;
	@Nullable
	ErrorInfo errorInfo;
}
