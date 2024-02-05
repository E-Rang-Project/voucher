package kr.co.erang.voucher.application.port.in;

import kr.co.erang.voucher.domain.model.Code;
import kr.co.erang.voucher.domain.model.Penalty;

public interface PenaltyAddUseCase {

    void execute(Input input);

    record Input(
            Code code,
            Penalty penalty
    ){
        public Input{
            if(code == null)
                throw new IllegalArgumentException("Code is  required for Penalty Add Command");
            if(penalty == null)
                throw new IllegalArgumentException("Penalty Info is required for Penalty Add Command");
        }
    }
}
