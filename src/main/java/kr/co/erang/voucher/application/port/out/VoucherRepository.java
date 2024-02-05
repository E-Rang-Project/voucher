package kr.co.erang.voucher.application.port.out;

import kr.co.erang.voucher.domain.model.Code;
import kr.co.erang.voucher.domain.model.Voucher;

import java.util.Optional;

public interface VoucherRepository {

    boolean existsByCode(Code code);

    Voucher save(Voucher voucher);

    Optional<Voucher> findByCode(Code code);

}
