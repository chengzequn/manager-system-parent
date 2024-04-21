package cn.threeant.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseWrapper<T> {

    private final int status;
    private final String message;
    private T data;


    public static <T> ResponseWrapper<T> ok() {
        return new ResponseWrapper<>(HttpStatus.OK.value(), "Success");
    }

    public static <T> ResponseWrapper<T> ok(T data) {
        return new ResponseWrapper<>(HttpStatus.OK.value(), "Success", data);
    }


    public static <T> ResponseWrapper<T> error(int status, String message) {
        return new ResponseWrapper<>(status, message);
    }
}
