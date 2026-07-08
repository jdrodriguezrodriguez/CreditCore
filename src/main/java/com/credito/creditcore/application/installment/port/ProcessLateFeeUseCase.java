package com.credito.creditcore.application.installment.port;

import com.credito.creditcore.application.dto.installment.PayLateFeeRequestDto;

public interface ProcessLateFeeUseCase {
    void processLateFee(Integer installmentId, PayLateFeeRequestDto request);
}
