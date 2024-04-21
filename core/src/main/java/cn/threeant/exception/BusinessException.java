package cn.threeant.exception;

public class BusinessException extends BaseException{

    private static final String DEFAULT_CODE = "BUSINESS_EXCEPTION";

    public BusinessException(String message) {
        super(DEFAULT_CODE, message);
    }
}
