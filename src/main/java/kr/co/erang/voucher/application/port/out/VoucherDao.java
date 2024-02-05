package kr.co.erang.voucher.application.port.out;

import kr.co.erang.voucher.domain.data.VoucherData;
import kr.co.erang.voucher.domain.data.VoucherItemsData;
import kr.co.erang.voucher.domain.model.Code;
import kr.co.erang.voucher.domain.model.Item;

import java.util.List;

public interface VoucherDao {

    VoucherData findVoucherDataByCode(Code code);

    List<VoucherData> findVoucherDataByItem(Item item);

    VoucherItemsData findVoucherItemsByCode(Code code);

}
