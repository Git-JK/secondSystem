package webdevelopment.secondsystem.utils.exception;

public class RedisSetErrorException extends ServiceException{
    public RedisSetErrorException() {
        super();
    }
    public RedisSetErrorException(String message) {
        super(message);
    }
    public RedisSetErrorException(String message, Throwable cause) {
        super(message, cause);
    }
    public RedisSetErrorException(Throwable cause) {
        super(cause);
    }
    public RedisSetErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
