package kr.co.erang.voucher.presentation.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult {

    private boolean success;

    /**
     * 메시지
     */
    private String message;

    /**
     * 에러 타입
     */
    private String errorType;

    public ApiResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResult(boolean success, String message, String errorType) {
        this.success = success;
        this.message = message;
        this.errorType = errorType;
    }
}
