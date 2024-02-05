package kr.co.erang.voucher.application.service;

import kr.co.erang.voucher.application.port.in.PenaltyAddUseCase;
import kr.co.erang.voucher.application.port.out.VoucherRepository;
import kr.co.erang.voucher.domain.exception.VoucherTypeException;
import kr.co.erang.voucher.domain.model.PenaltyVoucher;
import kr.co.erang.voucher.domain.model.Voucher;
import kr.co.erang.voucher.domain.exception.VoucherNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PenaltyVoucherService implements PenaltyAddUseCase {

    private final VoucherRepository voucherRepository;

    @Override
    @Transactional
    public void execute(Input input) {
        Voucher voucher = voucherRepository.findByCode(input.code())
                .orElseThrow(VoucherNotFoundException::new);
        if(voucher instanceof PenaltyVoucher penaltyVoucher){
            penaltyVoucher.addPenalty(input.penalty());
            voucherRepository.save(penaltyVoucher);
        } else{
            throw new VoucherTypeException("VoucherAddPenalty Type Error.");
        }

    }
}
