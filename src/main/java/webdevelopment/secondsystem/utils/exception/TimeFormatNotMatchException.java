package webdevelopment.secondsystem.utils.exception;

public class TimeFormatNotMatchException extends ServiceException{
    public TimeFormatNotMatchException() {
        super();
    }
    public TimeFormatNotMatchException(String message) {
        super(message);
    }
    public TimeFormatNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }
    public TimeFormatNotMatchException(Throwable cause) {
        super(cause);
    }
    public TimeFormatNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
