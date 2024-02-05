package kr.co.erang.voucher.presentation.rest.request;

import kr.co.erang.voucher.application.port.in.PenaltyAddUseCase;
import kr.co.erang.voucher.domain.model.Code;
import kr.co.erang.voucher.domain.model.Penalty;
import lombok.*;

/**
 * 경고 추가 요청 Dto
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PenaltyAddRequest {

    private String code;
    private String title;
    private String region;
    private String content;

    public PenaltyAddUseCase.Input toCommand(){
        return new PenaltyAddUseCase.Input(
                new Code(this.code),
                new Penalty(this.title, this.region, this.content)
        );
    }
}
