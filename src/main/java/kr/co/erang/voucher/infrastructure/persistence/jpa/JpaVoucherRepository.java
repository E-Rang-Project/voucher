package kr.co.erang.voucher.infrastructure.persistence.jpa;

import kr.co.erang.voucher.domain.data.VoucherData;
import kr.co.erang.voucher.domain.model.Code;
import kr.co.erang.voucher.domain.model.Item;
import kr.co.erang.voucher.domain.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface JpaVoucherRepository extends JpaRepository<Voucher, Code> {
    boolean existsByCode(Code code);


    //DAO용
    @Query("""
        select
            new kr.co.erang.voucher.domain.data.VoucherData(
                v.code.value, v.expirationDate, v.status, v.type
            )
        from
            Voucher v
        where
            v.code = :code
    """)
    VoucherData findVoucherDataByCode(Code code);

    @Query("""
        select
            new kr.co.erang.voucher.domain.data.VoucherData(
                v.code.value, v.expirationDate, v.status, v.type
            )
        from
            Voucher v
        join v.items i
        where i = :item
        
    """)
    List<VoucherData> findVoucherDataByItem(Item item);
}
