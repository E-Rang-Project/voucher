package kr.co.erang.voucher.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@Table(name = "tbl_penalty_voucher")
@DiscriminatorValue("PENALTY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PenaltyVoucher extends Voucher{
    /**
     * 기본 권한 박탈 예정인 경고 횟수
     */
    private static final Integer DEFAULT_MAX_PENALTY_COUNT = 3;


    /**
     * 권한 박탈 되는 경고 횟수 설정값
     */
    @Comment("권한 박탈되는 경고 횟수 설정 값")
    private Integer maxPenaltyCount;

    @OneToMany(mappedBy = "voucher", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("publishDate desc")
    private List<Penalty> penalties;


    public PenaltyVoucher(Code code, LocalDate expirationDate, Set<Item> items, Receiver receiver, Publisher publisher, VoucherStatus status,
                          Integer maxPenaltyCount, List<Penalty> penalties
    ) {
        super(code, expirationDate, items, receiver, publisher, status);
        this.maxPenaltyCount = maxPenaltyCount;
        this.penalties = penalties;
    }


    public static PenaltyVoucher generate(Code code, LocalDate expirationDate, Set<Item> items, Receiver receiver, Publisher publisher, Integer maxPenaltyCount){
        if(code == null)
            throw new IllegalArgumentException("Code is required when Voucher generate");
        if(expirationDate == null)
            throw new IllegalArgumentException("ExpirationDate is required when Voucher generate");
        if(items.isEmpty())
            throw new IllegalArgumentException("Items is required when Voucher generate");
        if(receiver == null)
            throw new IllegalArgumentException("Receiver is required when Voucher generate");
        if(publisher == null)
            throw new IllegalArgumentException("Publisher is required when Voucher generate");
        if(maxPenaltyCount == null)
            maxPenaltyCount = DEFAULT_MAX_PENALTY_COUNT;
        if(maxPenaltyCount < 1)
            throw new IllegalArgumentException("Max Penalty Count 1 more...");

        return new PenaltyVoucher(
                code, expirationDate, items, receiver, publisher, VoucherStatus.AVAILABLE,
                maxPenaltyCount, new ArrayList<>()
        );
    }


    @Override
    public VoucherStatus getStatus() {
        if(penalties.size() > maxPenaltyCount)
            return VoucherStatus.DISABLED_DEPRIVE;
        return super.getStatus();
    }

    public void addPenalty(Penalty penalty){
        penalty.isVoucher(this);
        penalties.add(penalty);
        if(penalties.size() >= maxPenaltyCount)
            this.status = VoucherStatus.DISABLED_DEPRIVE;
    }


}
