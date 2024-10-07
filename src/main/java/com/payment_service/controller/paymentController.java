package com.payment_service.controller;

import com.payment_service.dto.TransactionDataDTO;
import com.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class paymentController {

    private final PaymentService paymentService;

    @PostMapping("/prepare")
    public ResponseEntity<String> preparePayment(@RequestBody TransactionDataDTO transactionDataDTO) {
        try{
            return new ResponseEntity<>(paymentService.preparePayment(transactionDataDTO),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error while preparing the payment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/commit")
    public ResponseEntity<String> commitPayment(@RequestBody TransactionDataDTO transactionDataDTO) {
        try{
            return new ResponseEntity<>(paymentService.commitPayment(transactionDataDTO),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error while preparing the payment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/rollback")
    public ResponseEntity<String> rollbackPayment(@RequestBody TransactionDataDTO transactionDataDTO) {
        try{
            return new ResponseEntity<>(paymentService.rollbackPayment(transactionDataDTO),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error while preparing the payment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
