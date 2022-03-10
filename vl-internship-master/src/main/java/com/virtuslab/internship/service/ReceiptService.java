package com.virtuslab.internship.service;

import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceiptService {

    private final ReceiptRepository repository;

    public Receipt createNewReceipt(@RequestBody Receipt receipt) {
        return repository.save(receipt);
    }

    public Receipt replaceReceipt(@RequestBody Receipt receipt, @PathVariable Long id) {

        return repository.findById(id)
                .map(newReceipt -> {
                    return repository.save(newReceipt);
                })
                .orElseGet(() -> repository.save(receipt));

    }

    public List<Receipt> getReceipts() {
        return repository.findAll();
    }

    public Receipt getReceipt(Long id) {
        return repository.findById(id).
                orElseThrow();
    }
}
