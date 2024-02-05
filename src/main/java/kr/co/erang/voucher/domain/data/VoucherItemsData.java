package kr.co.erang.voucher.domain.data;

import kr.co.erang.voucher.domain.model.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;


/**
 * 바우처에 등록된 Item View Model
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VoucherItemsData {
    private String code;
    private Set<Item> items;

    public VoucherItemsData(String code, Set<Item> items) {
        this.code = code;
        this.items = items;
    }
}
