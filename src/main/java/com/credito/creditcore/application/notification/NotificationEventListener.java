package com.credito.creditcore.application.notification;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.credito.creditcore.application.event.InstallmentPaidEvent;
import com.credito.creditcore.application.event.LateFeePaidEvent;
import com.credito.creditcore.application.event.LoanCompletedEvent;
import com.credito.creditcore.application.event.PartialInstallmentPaidEvent;
import com.credito.creditcore.domain.port.NotificationPort;

@Component
public class NotificationEventListener {

    private NotificationPort notificationPort;

    public NotificationEventListener(NotificationPort notificationPort) {
        this.notificationPort = notificationPort;
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void notifyInstallmentPaid(InstallmentPaidEvent installmentPaidEvent){
            notificationPort.sendInstallmentPaid(installmentPaidEvent);
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void notifyPartialInstallmentPaid(PartialInstallmentPaidEvent partialInstallmentPaidEvent){
        notificationPort.sendPartialInstallmentPaid(partialInstallmentPaidEvent);
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void notifyLoanComplete(LoanCompletedEvent loanCompletedEvent){
        notificationPort.sendLoanComplete(loanCompletedEvent);
    }
    
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void notifyLateFeePaid(LateFeePaidEvent lateFeePaidEvent){
        notificationPort.sendLateFeePaid(lateFeePaidEvent);
    }
}
