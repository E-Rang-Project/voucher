package kr.co.erang.voucher.domain.model;

import lombok.Getter;

@Getter
public enum VoucherStatus {
    AVAILABLE("사용 가능"),
    DISABLED("사용 불가"),
    DISABLED_EXPIRED("유효기간 만료"),
    DISABLED_DEPRIVE("권한 박탈"),
    DISABLED_OTHER_REASON("기타"),
    ;

    private final String strValue;

    VoucherStatus(String strValue) {
        this.strValue = strValue;
    }
}
