package kr.co.erang.voucher.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Embeddable
@EqualsAndHashCode(of = "value")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Code implements Serializable {

    @Comment("바우처 코드")
    @Column(name = "code")
    private String value;

    public Code(String value) {
        this.value = value;
    }

    public static Code generator(int len){
        if(len < 1 || len > 32)
            throw new IllegalArgumentException("Code length 1 ~ 32");
        UUID uuid = UUID.randomUUID();
        String uuidStr =  uuid.toString().replaceAll("-", "");
        return new Code(uuidStr.substring(0, len));
    }
}
