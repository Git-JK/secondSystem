package webdevelopment.secondsystem.utils.exception;

public class UserHadRoomException extends ServiceException{
    public UserHadRoomException() {
        super();
    }
    public UserHadRoomException(String message) {
        super(message);
    }
    public UserHadRoomException(String message, Throwable cause) {
        super(message, cause);
    }
    public UserHadRoomException(Throwable cause) {
        super(cause);
    }
    public UserHadRoomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
