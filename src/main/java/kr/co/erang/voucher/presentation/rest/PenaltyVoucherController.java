package kr.co.erang.voucher.presentation.rest;

import kr.co.erang.voucher.application.port.in.PenaltyAddUseCase;
import kr.co.erang.voucher.domain.exception.VoucherNotFoundException;
import kr.co.erang.voucher.domain.exception.VoucherTypeException;
import kr.co.erang.voucher.presentation.rest.request.PenaltyAddRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/penalty-voucher")
@RequiredArgsConstructor
public class PenaltyVoucherController {

    private final PenaltyAddUseCase penaltyAddUseCase;

    @PostMapping("/add")
    public ResponseEntity<ApiResult> addPenaltyApi(@RequestBody PenaltyAddRequest request){
        PenaltyAddUseCase.Input command = request.toCommand();
        ApiResult apiResult;
        try{
            penaltyAddUseCase.execute(command);
            apiResult = new ApiResult(true, "경고 추가 성공");
            return ResponseEntity.ok().body(apiResult);
        } catch (VoucherTypeException | VoucherNotFoundException e){
            apiResult = new ApiResult(false, e.getMessage(), e.getClass().getSimpleName());
            return ResponseEntity.badRequest().body(apiResult);
        }

    }

}
