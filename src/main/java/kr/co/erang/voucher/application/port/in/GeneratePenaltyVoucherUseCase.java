package kr.co.erang.voucher.application.port.in;

import kr.co.erang.voucher.domain.model.Code;
import kr.co.erang.voucher.domain.model.Item;
import kr.co.erang.voucher.domain.model.Publisher;
import kr.co.erang.voucher.domain.model.Receiver;

import java.time.LocalDate;
import java.util.Set;

/**
 * 패널티용 바우처 모댈 생성 UseCase
 */
public interface GeneratePenaltyVoucherUseCase {

    Code execute(Input input);

    /**
     *
     * @param expirationDate
     * @param items
     * @param publisher
     * @param maxPenaltyCount
     */
    record Input(
            LocalDate expirationDate,
            Set<Item> items,
            Receiver receiver,
            Publisher publisher,
            Integer maxPenaltyCount
    ){


    }
}
