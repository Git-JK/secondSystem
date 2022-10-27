package webdevelopment.secondsystem.utils.exception;

public class TimeNotMatchException extends ServiceException{
    public TimeNotMatchException() {
        super();
    }
    public TimeNotMatchException(String message) {
        super(message);
    }
    public TimeNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }
    public TimeNotMatchException(Throwable cause) {
        super(cause);
    }
    public TimeNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
