package cn.threeant.exception;

public class BadRequestException extends BaseException{
    private static final String DEFAULT_CODE = "BAD_REQUEST";

    public BadRequestException(String message) {
        super(DEFAULT_CODE, message);
    }
}
