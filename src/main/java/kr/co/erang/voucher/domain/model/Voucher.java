package kr.co.erang.voucher.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Entity
@Table(name = "tbl_voucher")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "BASIC")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SecondaryTables({
        @SecondaryTable(name = "tbl_voucher_receiver", pkJoinColumns = @PrimaryKeyJoinColumn(name = "voucher_code"))
})
public class Voucher implements Persistable<Code> {

    @EmbeddedId
    protected Code code;

    @Comment("바우처 만료 일자")
    protected LocalDate expirationDate;

    /**
     * 바우처에 설정된 Item 목록
     */
    @ElementCollection(fetch = FetchType.LAZY, targetClass = Item.class)
    @CollectionTable(name = "tbl_voucher_items", joinColumns = @JoinColumn(name = "voucher_code"),
        indexes = @Index(columnList = "itemId")
    )
    protected Set<Item> items;

    /**
     * 바우처 발급 대상자
     */
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(table = "tbl_voucher_receiver")),
            @AttributeOverride(name = "tel", column = @Column(table = "tbl_voucher_receiver"))
    })
    protected Receiver receiver;


    /**
     * 바우처 발행 정보
     */
    protected Publisher publisher;

    
    @Comment("상태")
    @Enumerated(EnumType.STRING)
    protected VoucherStatus status;

    @Comment("바우처 Type")
    @Column(name = "type", insertable = false, updatable = false)
    private String type;

    @Comment("생성 시간")
    @CreatedDate
    private LocalDateTime createdAt;


    public Voucher(Code code, LocalDate expirationDate, Set<Item> items, Receiver receiver, Publisher publisher, VoucherStatus status) {
        this.code = code;
        this.expirationDate = expirationDate;
        this.items = items;
        this.receiver = receiver;
        this.publisher = publisher;
        this.status = status;
    }

    /**
     * 바우처 생성 함수
     */
    public static Voucher generate(Code code, LocalDate expirationDate, Set<Item> items, Receiver receiver, Publisher publisher){
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
        return new Voucher(code, expirationDate, items, receiver, publisher, VoucherStatus.AVAILABLE);
    }


    /**
     * 바우처 상태
     * @return
     */
    public VoucherStatus getStatus(){
        if(code == null)
            return VoucherStatus.DISABLED;
        if(this.expirationDate.isBefore(LocalDate.now()))
            return VoucherStatus.DISABLED_DEPRIVE;

        return this.status;
    }


    /**
     * 바우처 사용 가능 여부 확인
     * @return
     */
    public boolean isAvailable(){
        if(this.status.equals(VoucherStatus.AVAILABLE))
            return this.expirationDate.isAfter(LocalDate.now()) || this.expirationDate.isEqual(LocalDate.now());
        return false;
    }

    /**
     * 바우처 비활성화
     */
    public void disable(){
        this.status = VoucherStatus.DISABLED;
    }



    @Override
    public Code getId() {
        return this.code;
    }

    @Override
    public boolean isNew() {
        return createdAt == null;
    }
}
