package webdevelopment.secondsystem.utils.exception;

public class VerificationCodeNotMatchException extends ServiceException {
    public VerificationCodeNotMatchException() {
        super();
    }
    public VerificationCodeNotMatchException(String message) {
        super(message);
    }
    public VerificationCodeNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }
    public VerificationCodeNotMatchException(Throwable cause) {
        super(cause);
    }
    public VerificationCodeNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
