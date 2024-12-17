package testz;

import org.springframework.lang.Nullable;


public class ErrorInfo {
	@Nullable
	private String errorCode;
	@Nullable
	private String errorMessage;
	@Nullable
	private ErrorInfo errorInfo;
}
