package kr.co.erang.voucher.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

/**
 * 바우처 발행한 농장정보
 */
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Publisher {
    @Comment("농장 ID")
    @Column(name = "publisher_id")
    private String farmId;

    public Publisher(String farmId) {
        if(farmId == null || farmId.isEmpty())
            throw new IllegalArgumentException("PublisherId null or empty!");
        this.farmId = farmId;
    }
}
