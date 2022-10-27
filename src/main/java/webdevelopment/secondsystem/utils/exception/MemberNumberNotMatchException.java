package webdevelopment.secondsystem.utils.exception;

public class MemberNumberNotMatchException extends ServiceException{
    public MemberNumberNotMatchException() {
        super();
    }
    public MemberNumberNotMatchException(String message) {
        super(message);
    }
    public MemberNumberNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }
    public MemberNumberNotMatchException(Throwable cause) {
        super(cause);
    }
    public MemberNumberNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
