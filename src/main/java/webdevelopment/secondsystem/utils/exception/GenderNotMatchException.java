package webdevelopment.secondsystem.utils.exception;

public class GenderNotMatchException extends ServiceException{
    public GenderNotMatchException() {
        super();
    }
    public GenderNotMatchException(String message) {
        super(message);
    }
    public GenderNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }
    public GenderNotMatchException(Throwable cause) {
        super(cause);
    }
    public GenderNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
