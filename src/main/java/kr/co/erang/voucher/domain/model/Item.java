package kr.co.erang.voucher.domain.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.Objects;

/**
 * Product 서비스의 객체
 */
@Embeddable
@Getter
@ToString
@EqualsAndHashCode(of = {"itemId", "type"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Comment("Item ID")
    private String itemId;

    @Comment("제품 Type")
    @Enumerated(EnumType.STRING)
    private Type type;

    public Item(String itemId, Type type) {
        if(itemId == null || itemId.isEmpty())
            throw new IllegalArgumentException("ItemId null or empty");
        if(type == null)
            throw new IllegalArgumentException("Type null...");
        this.itemId = itemId;
        this.type = type;
    }


    @Getter
    public enum Type{
        ER_BOX,
        PCB,
        CAMERA
    }
}
