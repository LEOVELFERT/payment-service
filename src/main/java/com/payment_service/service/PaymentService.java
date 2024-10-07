package com.payment_service.service;

import com.payment_service.dto.TransactionDataDTO;
import com.payment_service.entity.Payement;
import com.payment_service.enums.PaymentStatusEnum;
import com.payment_service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    public final PaymentRepository paymentRepository;

    public String preparePayment(TransactionDataDTO transactionDataDTO) {
        paymentRepository.save(new Payement()
                .setItem(transactionDataDTO.getItem())
                .setPaymentMode(transactionDataDTO.getPaymentMode())
                .setPreparationStatus(PaymentStatusEnum.PENDING.name())
                .setPrice(transactionDataDTO.getPrice()));
        return "payment prepared successfully";
    }

    public String commitPayment(TransactionDataDTO transactionDataDTO) {
        if(isCommitFailed()){
            throw new RuntimeException("Commit failed");
        }
            return paymentRepository.findByItem(transactionDataDTO.getItem()).
                    filter(payment-> PaymentStatusEnum.PENDING.name().equalsIgnoreCase(payment.getPreparationStatus())).
                    map(payement -> {
                        payement.setPreparationStatus(PaymentStatusEnum.APPROVED.name());
                        paymentRepository.save(payement);
                        return "payment approved successfully";
                    }).orElseThrow(()->new RuntimeException("Exception happened while committing payment"));
    }

    private boolean isCommitFailed() {
        return false;
    }

    public String rollbackPayment(TransactionDataDTO transactionDataDTO) {
        return paymentRepository.findByItem(transactionDataDTO.getItem()).
                map(payement -> {
                    payement.setPreparationStatus(PaymentStatusEnum.ROLLBACK.name());
                    paymentRepository.save(payement);
                    return "payment approved successfully";
                }).orElseThrow(()->new RuntimeException("Exception happened while committing payment"));
    }
}
