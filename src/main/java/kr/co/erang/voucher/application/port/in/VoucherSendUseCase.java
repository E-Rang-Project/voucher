package kr.co.erang.voucher.application.port.in;

import kr.co.erang.voucher.domain.model.Code;
import kr.co.erang.voucher.domain.model.Receiver;

/**
 * 바우처 전송 UseCase
 */
public interface VoucherSendUseCase {

    void execute(Code code, Receiver receiver);



}
