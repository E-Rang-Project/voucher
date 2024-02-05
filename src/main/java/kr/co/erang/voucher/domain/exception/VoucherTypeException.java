package kr.co.erang.voucher.domain.exception;

/**
 * Type에러
 */
public class VoucherTypeException extends RuntimeException{
    public VoucherTypeException() {
        super("Voucher type Exception...");
    }

    public VoucherTypeException(String message) {
        super(message);
    }

}
