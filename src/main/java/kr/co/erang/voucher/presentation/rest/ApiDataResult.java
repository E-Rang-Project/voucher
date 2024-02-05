package kr.co.erang.voucher.presentation.rest;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiDataResult<T> extends ApiResult{

    private T data;

    public ApiDataResult (boolean success, String message, T data) {
        super (success, message);
        this.data = data;
    }

    public ApiDataResult (boolean success, String message, T data, String errorType) {
        super (success, message, errorType);
        this.data = data;
    }
}
