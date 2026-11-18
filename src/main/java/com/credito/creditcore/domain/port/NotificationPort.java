package com.credito.creditcore.domain.port;

import com.credito.creditcore.application.event.InstallmentPaidEvent;
import com.credito.creditcore.application.event.LateFeePaidEvent;
import com.credito.creditcore.application.event.LoanCompletedEvent;
import com.credito.creditcore.application.event.PartialInstallmentPaidEvent;

public interface NotificationPort {
    void sendInstallmentPaid(InstallmentPaidEvent installmentPaidEvent);
    void sendPartialInstallmentPaid(PartialInstallmentPaidEvent partialInstallmentPaidEvent);
    void sendLoanComplete(LoanCompletedEvent loanCompletedEvent);
    void sendLateFeePaid(LateFeePaidEvent lateFeePaidEvent);
}
