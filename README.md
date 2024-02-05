# Voucher-Module


## 바우처?

### 도메인 목록
 * 바우처
 * 바우처 Model_1 -> 기본 바우처
 * 바우처 model_2 -> Penalty 바우처

### 참조 도메인
 * Farm
 * Product
 * User


### 패키지 구조

```
\---voucher
    |   VoucherApplication.java
    |
    +---application
    |   +---port
    |   |   +---in
    |   |   |       GeneratePenaltyVoucherUseCase.java
    |   |   |       GenerateVoucherUseCase.java
    |   |   |       PenaltyAddUseCase.java
    |   |   |       VoucherSendUseCase.java
    |   |   |
    |   |   \---out
    |   |           VoucherDao.java
    |   |           VoucherEventPort.java
    |   |           VoucherRepository.java
    |   |
    |   \---service
    |           PenaltyVoucherService.java
    |           VoucherGenerateService.java
    |
    +---domain
    |   +---data
    |   |       VoucherData.java
    |   |       VoucherItemsData.java
    |   |
    |   +---exception
    |   |       VoucherNotFoundException.java
    |   |       VoucherTypeException.java
    |   |
    |   \---model
    |           Code.java
    |           Item.java
    |           Penalty.java
    |           PenaltyVoucher.java
    |           Publisher.java
    |           Receiver.java
    |           Voucher.java
    |           VoucherStatus.java
    |
    +---infrastructure
    |   \---persistence
    |       \---jpa
    |               JpaVoucherRepository.java
    |               VoucherDaoAdapter.java
    |               VoucherRepositoryAdapter.java
    |
    \---presentation
        \---rest
            |   ApiDataResult.java
            |   ApiResult.java
            |   FarmVoucherController.java
            |   ItemVoucherController.java
            |   PenaltyVoucherController.java
            |   VoucherController.java
            |
            +---request
            |       PenaltyAddRequest.java
            |       PenaltyVoucherGenerateRequest.java
            |       VoucherGenerateRequest.java
            |
            \---response
                    VoucherGenerateResponse.java
```