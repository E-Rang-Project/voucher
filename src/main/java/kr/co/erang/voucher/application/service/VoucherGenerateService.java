package kr.co.erang.voucher.application.service;

import kr.co.erang.voucher.application.port.in.GeneratePenaltyVoucherUseCase;
import kr.co.erang.voucher.application.port.in.GenerateVoucherUseCase;
import kr.co.erang.voucher.application.port.out.VoucherRepository;
import kr.co.erang.voucher.domain.model.Code;
import kr.co.erang.voucher.domain.model.PenaltyVoucher;
import kr.co.erang.voucher.domain.model.Voucher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 바우처 생성 서비스
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VoucherGenerateService implements GenerateVoucherUseCase, GeneratePenaltyVoucherUseCase {

    private final VoucherRepository voucherRepository;

    /**
     * 바우처 기본 모델 생성
     */
    @Override
    @Transactional
    public Code execute(GenerateVoucherUseCase.Input input) {
        Code code = this.generateVoucherCode(16);


        log.info("set : {}",input.items());
        Voucher voucher = Voucher.generate(code, input.expirationDate(), input.items(), input.receiver(), input.publisher());
        voucherRepository.save(voucher);
        return code;
    }

    @Override
    @Transactional
    public Code execute(GeneratePenaltyVoucherUseCase.Input input) {
        Code code = this.generateVoucherCode(16);
        PenaltyVoucher voucher = PenaltyVoucher.generate(code, input.expirationDate(), input.items(), input.receiver(), input.publisher(), input.maxPenaltyCount());
        voucherRepository.save(voucher);
        return code;
    }





    /**
     * 바우처 코드 생성
     */
    private Code generateVoucherCode(int len){
        Code newCode = Code.generator(len);
        // 재귀 호출
        if(voucherRepository.existsByCode(newCode)){
            this.generateVoucherCode(len);
        }
        return newCode;
    }


}
