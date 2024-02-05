package kr.co.erang.voucher.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.regex.Pattern;

/**
 * 바우처 수신자
 */
@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Receiver {

    private static final String TEL_REGEXP = "^010?(\\d{4})?(\\d{4})$|^01(?:1|[6-9])?(\\d{3}|\\d{4})?(\\d{4})$";
    private static final Pattern TEL_PATTERN = Pattern.compile(TEL_REGEXP);

    @Comment("수신자 이름")
    private String name;
    @Comment("수신자 연락처")
    private String tel;

    public Receiver(String name, String tel) {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("name null or empty!");
        if(tel == null || tel.isEmpty())
            throw new IllegalArgumentException("tel null or empty!");
        if( !isValid(tel) )
            throw new IllegalArgumentException("tel patten is not valid...");
        this.name = name;
        this.tel = tel;
    }

    private boolean isValid(String tel){
        return TEL_PATTERN.matcher(tel).matches();
    }


}
