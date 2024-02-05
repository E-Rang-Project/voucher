package kr.co.erang.voucher.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

/**
 * 경고
 */
@Entity
@Getter
@Table(name = "tbl_voucher_penaltes", indexes = {
        @Index(columnList = "code, publish_date")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Penalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code")
    @Comment("바우처 코드")
    private Voucher voucher;

    @Comment("경고 제목")
    private String title;
    @Comment("경고 사유")
    private String reason;
    @Comment("내용")
    private String content;

    @Comment("경고 부여 시간")
    @CreatedDate
    private LocalDateTime publishDate;

    public Penalty(String title, String reason, String content) {
        this.title = title;
        this.reason = reason;
        this.content = content;
    }

    public void isVoucher(Voucher voucher){
        this.voucher = voucher;
    }


}
