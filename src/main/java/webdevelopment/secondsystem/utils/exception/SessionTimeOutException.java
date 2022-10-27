package webdevelopment.secondsystem.utils.exception;

public class SessionTimeOutException extends ServiceException {
    public SessionTimeOutException() {
        super();
    }
    public SessionTimeOutException(String message) {
        super(message);
    }
    public SessionTimeOutException(String message, Throwable cause) {
        super(message, cause);
    }
    public SessionTimeOutException(Throwable cause) {
        super(cause);
    }
    public SessionTimeOutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
