package kr.co.erang.voucher.presentation.rest.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor()
public class VoucherGenerateResponse {

    private String code;
    private String voucherType;

}
