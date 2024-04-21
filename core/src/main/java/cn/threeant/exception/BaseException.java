package cn.threeant.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException{

    private final String code;
    private final String message;
}
