package testz;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.micrometer.common.lang.NonNull;


public class DHPublicKey{
    @NonNull
    String expiry;
    //Dont ask me why this is capital. I am just blindly following the spec ;)
    @NonNull
    @JsonProperty("Parameters")
    String parameters;
    @NonNull
    @JsonProperty("KeyValue")
    String keyValue;
}
