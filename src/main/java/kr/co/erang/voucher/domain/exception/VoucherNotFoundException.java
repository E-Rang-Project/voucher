package kr.co.erang.voucher.domain.exception;

public class VoucherNotFoundException extends RuntimeException {


    public VoucherNotFoundException() {
        super("Voucher Not Found...");
    }

    public VoucherNotFoundException(String message) {
        super(message);
    }
}
