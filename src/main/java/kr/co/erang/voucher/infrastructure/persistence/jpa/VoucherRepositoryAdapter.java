package kr.co.erang.voucher.infrastructure.persistence.jpa;

import kr.co.erang.voucher.application.port.out.VoucherRepository;
import kr.co.erang.voucher.domain.model.Code;
import kr.co.erang.voucher.domain.model.Voucher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VoucherRepositoryAdapter implements VoucherRepository {

    private final JpaVoucherRepository jpaVoucherRepository;
    @Override
    public boolean existsByCode(Code code) {
        return jpaVoucherRepository.existsByCode(code);
    }

    @Override
    public Voucher save(Voucher voucher) {
        return jpaVoucherRepository.save(voucher);
    }

    @Override
    public Optional<Voucher> findByCode(Code code) {
        return jpaVoucherRepository.findById(code);
    }
}
