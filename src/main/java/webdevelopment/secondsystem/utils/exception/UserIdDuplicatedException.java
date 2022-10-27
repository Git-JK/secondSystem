package webdevelopment.secondsystem.utils.exception;

public class UserIdDuplicatedException extends ServiceException{
    public UserIdDuplicatedException() {
        super();
    }
    public UserIdDuplicatedException(String message) {
        super(message);
    }
    public UserIdDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }
    public UserIdDuplicatedException(Throwable cause) {
        super(cause);
    }
    public UserIdDuplicatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
