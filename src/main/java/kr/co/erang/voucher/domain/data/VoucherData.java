package kr.co.erang.voucher.domain.data;

import kr.co.erang.voucher.domain.model.VoucherStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 기본 View 모델
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class VoucherData {
    private String code;
    private LocalDate expirationDate;
    private VoucherStatus status;
    private String type;
}
