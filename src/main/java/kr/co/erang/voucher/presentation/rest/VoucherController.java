package kr.co.erang.voucher.presentation.rest;


import kr.co.erang.voucher.domain.data.VoucherData;
import kr.co.erang.voucher.domain.model.Code;
import kr.co.erang.voucher.infrastructure.persistence.jpa.JpaVoucherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/voucher")
@Slf4j
public class VoucherController {

    private final JpaVoucherRepository  jpaVoucherRepository;

    @GetMapping("/{code}")
    public Object voucherInfoApi(@PathVariable String code){
        VoucherData data = jpaVoucherRepository.findVoucherDataByCode(new Code(code));
        return data;
    }


    @GetMapping("/{code}/items")
    public void voucherItemsApi(){

    }
}
