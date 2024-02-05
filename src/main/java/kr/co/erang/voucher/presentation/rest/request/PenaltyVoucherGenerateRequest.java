package kr.co.erang.voucher.presentation.rest.request;

import kr.co.erang.voucher.application.port.in.GeneratePenaltyVoucherUseCase;
import kr.co.erang.voucher.domain.model.Item;
import kr.co.erang.voucher.domain.model.Publisher;
import kr.co.erang.voucher.domain.model.Receiver;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;

/**
 * 패널티 바우처 생성 요청 Dto
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PenaltyVoucherGenerateRequest {

    private LocalDate expirationDate;
    private String itemId;
    private Item.Type itemType;
    private String receiverName;
    private String receiverTel;
    private String publisherId;
    private Integer maxPenaltyCount;


    public GeneratePenaltyVoucherUseCase.Input toCommand(){

        return new GeneratePenaltyVoucherUseCase.Input(
                expirationDate,
                new HashSet<>(Collections.singletonList(new Item(this.itemId, this.itemType))),
                new Receiver(this.receiverName, this.receiverTel),
                new Publisher(this.publisherId),
                maxPenaltyCount
        );
    }
}
