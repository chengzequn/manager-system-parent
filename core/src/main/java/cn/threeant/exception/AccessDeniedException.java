package cn.threeant.exception;

public class AccessDeniedException extends BaseException{

    private static final String DEFAULT_CODE = "ACCESS_DENIED";

    public AccessDeniedException(String message) {
        super(DEFAULT_CODE, message);
    }
}
