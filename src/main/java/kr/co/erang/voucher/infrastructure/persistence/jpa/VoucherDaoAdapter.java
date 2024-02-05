package kr.co.erang.voucher.infrastructure.persistence.jpa;

import kr.co.erang.voucher.application.port.out.VoucherDao;
import kr.co.erang.voucher.domain.data.VoucherData;
import kr.co.erang.voucher.domain.data.VoucherItemsData;
import kr.co.erang.voucher.domain.model.Code;
import kr.co.erang.voucher.domain.model.Item;
import kr.co.erang.voucher.domain.model.Voucher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class VoucherDaoAdapter implements VoucherDao {

    private final JpaVoucherRepository jpaVoucherRepository;

    @Override
    public VoucherData findVoucherDataByCode(Code code) {
        return jpaVoucherRepository.findVoucherDataByCode(code);
    }

    @Override
    public List<VoucherData> findVoucherDataByItem(Item item) {
        return jpaVoucherRepository.findVoucherDataByItem(item);
    }

    @Override
    public VoucherItemsData findVoucherItemsByCode(Code code) {
        return null;
    }

}
