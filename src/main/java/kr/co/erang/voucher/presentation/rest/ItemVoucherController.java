package kr.co.erang.voucher.presentation.rest;

import kr.co.erang.voucher.application.port.in.GeneratePenaltyVoucherUseCase;
import kr.co.erang.voucher.application.port.in.GenerateVoucherUseCase;
import kr.co.erang.voucher.application.port.out.VoucherDao;
import kr.co.erang.voucher.domain.data.VoucherData;
import kr.co.erang.voucher.domain.model.Code;
import kr.co.erang.voucher.domain.model.Item;
import kr.co.erang.voucher.presentation.rest.request.PenaltyVoucherGenerateRequest;
import kr.co.erang.voucher.presentation.rest.request.VoucherGenerateRequest;
import kr.co.erang.voucher.presentation.rest.response.VoucherGenerateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemVoucherController {

    private final GenerateVoucherUseCase generateVoucherUseCase;
    private final GeneratePenaltyVoucherUseCase generatePenaltyVoucherUseCase;

    private final VoucherDao voucherDao;

    @PostMapping("/voucher/generate")
    public ResponseEntity<ApiResult> basicVoucherGenerateApi(@RequestBody VoucherGenerateRequest request){
        GenerateVoucherUseCase.Input command = request.toCommand();
        Code code = generateVoucherUseCase.execute(command);

        VoucherGenerateResponse response = new VoucherGenerateResponse(code.getValue(), "BASIC");
        ApiResult apiResult = new ApiDataResult<>(true, "바우처 생성", response);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResult);

    }

    @PostMapping("/penalty-voucher/generate")
    public ResponseEntity<ApiResult> penaltyVoucherGenerateApi(@RequestBody PenaltyVoucherGenerateRequest request){
        GeneratePenaltyVoucherUseCase.Input command = request.toCommand();
        Code code = generatePenaltyVoucherUseCase.execute(command);

        VoucherGenerateResponse response = new VoucherGenerateResponse(code.getValue(), "PENALTY");
        ApiResult apiResult = new ApiDataResult<>(true, "바우처 생성", response);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResult);
    }



    /**
     * 제품 아이디로 바우처 목록 조회
     * @param itemId
     */
    @GetMapping("/{itemId}/vouchers")
    public ResponseEntity<ApiResult> productIdByVouchersApi(@PathVariable("itemId") String itemId){
        Item item = new Item(itemId, Item.Type.ER_BOX);
        List<VoucherData> dataList = voucherDao.findVoucherDataByItem(item);
        ApiResult apiResult = new ApiDataResult<>(true, "Item에 설정 된 voucher 목록", dataList);
        return ResponseEntity.ok().body(apiResult);
    }



}
