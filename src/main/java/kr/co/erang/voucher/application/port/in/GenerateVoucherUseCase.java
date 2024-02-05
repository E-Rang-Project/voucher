package kr.co.erang.voucher.application.port.in;

import kr.co.erang.voucher.domain.model.Code;
import kr.co.erang.voucher.domain.model.Item;
import kr.co.erang.voucher.domain.model.Publisher;
import kr.co.erang.voucher.domain.model.Receiver;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

/**
 * 바우처 기본 모델 생성 UseCase
 */
public interface GenerateVoucherUseCase {

    Code execute(Input input);

    /**
     *
     * @param expirationDate
     * @param items
     * @param receiver
     * @param publisher
     */
    record Input(
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate expirationDate,
            Set<Item> items,
            Receiver receiver,
            Publisher publisher
    ){
        public Input{
            if(expirationDate == null)
                throw new IllegalArgumentException("ExpirationDate is required for GenerateVoucher Command");
            if(items.isEmpty())
                throw new IllegalArgumentException("Items is required for GenerateVoucher Command");
            if(receiver == null)
                throw new IllegalArgumentException("Receiver is required for GenerateVoucher Command");
            if(publisher == null)
                throw new IllegalArgumentException("Publisher is required for GenerateVoucher Command");
        }
    }

}
