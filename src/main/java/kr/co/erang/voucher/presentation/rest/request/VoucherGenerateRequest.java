package kr.co.erang.voucher.presentation.rest.request;

import kr.co.erang.voucher.application.port.in.GenerateVoucherUseCase;
import kr.co.erang.voucher.domain.model.Item;
import kr.co.erang.voucher.domain.model.Publisher;
import kr.co.erang.voucher.domain.model.Receiver;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class VoucherGenerateRequest {

    private LocalDate expirationDate;
    private String itemId;
    private Item.Type itemType;

    private Set<Item> items;

    private String receiverName;
    private String receiverTel;
    private String publisherId;

    public GenerateVoucherUseCase.Input toCommand(){
        return new GenerateVoucherUseCase.Input(
                this.expirationDate,
                items,
//                new HashSet<>(Collections.singletonList(new Item(this.itemId, this.itemType))),
                new Receiver(this.receiverName, this.receiverTel),
                new Publisher(this.publisherId)
        );
    }

}
