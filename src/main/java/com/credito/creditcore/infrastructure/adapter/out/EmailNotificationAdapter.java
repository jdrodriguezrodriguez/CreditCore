package com.credito.creditcore.infrastructure.adapter.out;

import java.time.LocalDate;
import java.util.logging.Logger;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.credito.creditcore.application.event.InstallmentPaidEvent;
import com.credito.creditcore.application.event.LateFeePaidEvent;
import com.credito.creditcore.application.event.LoanCompletedEvent;
import com.credito.creditcore.application.event.PartialInstallmentPaidEvent;
import com.credito.creditcore.domain.port.NotificationPort;

@Component
public class EmailNotificationAdapter implements NotificationPort {

    private static final Logger logger = Logger.getLogger(EmailNotificationAdapter.class.getName());

    private final JavaMailSender javaMailSender;

    public EmailNotificationAdapter(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendInstallmentPaid(InstallmentPaidEvent installmentPaidEvent) {

        try {
            String subject = "Pago recibido";

            String message = String.format("""
                    Hola,

                    Hemos registrado correctamente tu pago.

                    Detalles del pago:
                    • Cuota: #%d
                    • Valor pagado: $%s
                    • Forma de pago: %s
                    • Fecha de pago: %s

                    Gracias por realizar tu pago a tiempo.

                    CreditCore
                    """,
                    installmentPaidEvent.installmentNumber(),
                    installmentPaidEvent.paidAmount(),
                    installmentPaidEvent.paymentMethod(),
                    installmentPaidEvent.actualPaymentDate().toString());

            String recipient = installmentPaidEvent.recipient();

            SimpleMailMessage mail = new SimpleMailMessage();

            mail.setTo(recipient);
            mail.setSubject(subject);
            mail.setText(message);

            javaMailSender.send(mail);

        } catch (Exception e) {
            logger.severe("No se notifico el pago. " + e.getMessage());
        }
    }

    @Override
    public void sendPartialInstallmentPaid(PartialInstallmentPaidEvent partialInstallmentPaidEvent) {
        try {
            String subject = "Pago parcial recibido";

            String message = String.format("""
                    Hola,

                    Hemos registrado correctamente tu pago parcial.

                    Detalles del pago:
                    • Parcial
                    • Cuota: #%d
                    • Valor pagado: $%s
                    • Forma de pago: %s
                    • Fecha de pago: %s

                    Gracias por realizar tu pago a tiempo.

                    CreditCore
                    """,
                    partialInstallmentPaidEvent.installmentNumber(),
                    partialInstallmentPaidEvent.paidAmount(),
                    partialInstallmentPaidEvent.paymentMethod(),
                    partialInstallmentPaidEvent.actualPaymentDate().toString());

            String recipient = partialInstallmentPaidEvent.recipient();

            SimpleMailMessage mail = new SimpleMailMessage();

            mail.setTo(recipient);
            mail.setSubject(subject);
            mail.setText(message);

            javaMailSender.send(mail);

        } catch (Exception e) {
            logger.severe("No se notifico el pago. " + e.getMessage());
        }
    }

    @Override
    public void sendLoanComplete(LoanCompletedEvent loanCompletedEvent) {
        try {
            String subject = "Prestamo completado";

            String message = String.format("""
                    Hola,

                    Te informamos que has completado correctamente el pago de tu préstamo.

                    Detalles del préstamo:
                    • Préstamo: #%d
                    • Valor total pagado: $%s

                    Ya no tienes cuotas pendientes asociadas a este préstamo.

                    Gracias por confiar en CreditCore.

                    """,
                    loanCompletedEvent.loanId(),
                    loanCompletedEvent.totalPaid());

            String recipient = loanCompletedEvent.recipient();

            SimpleMailMessage mail = new SimpleMailMessage();

            mail.setTo(recipient);
            mail.setSubject(subject);
            mail.setText(message);

            javaMailSender.send(mail);

        } catch (Exception e) {
            logger.severe("No se notifico el pago. " + e.getMessage());
        }
    }

    @Override
    public void sendLateFeePaid(LateFeePaidEvent lateFeePaidEvent) {
        try {
            
            String subject = "Pago de mora registrado";

            String message = String.format("""
                    Hola,

                    Hemos registrado correctamente el pago de la mora asociada a tu cuota.

                    Detalles del pago:
                    • Cuota: #%d
                    • Valor pagado: $%s

                    La mora asociada a esta cuota ha sido pagada correctamente.

                    Gracias por realizar tu pago.

                    CreditCore
                    """,
                    lateFeePaidEvent.installmentNumber(),
                    lateFeePaidEvent.paidAmount());

            String recipient = lateFeePaidEvent.recipient();

            SimpleMailMessage mail = new SimpleMailMessage();

            mail.setTo(recipient);
            mail.setSubject(subject);
            mail.setText(message);

            javaMailSender.send(mail);

        } catch (Exception e) {
            logger.severe("No se notifico el pago. " + e.getMessage());
        }
    }
}
