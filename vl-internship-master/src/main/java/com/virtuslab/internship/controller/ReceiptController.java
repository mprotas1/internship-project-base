package com.virtuslab.internship.controller;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.FifteenPercentDiscount;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import com.virtuslab.internship.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReceiptController {

    private final ReceiptService service;

    @PostMapping(value="/receipts", consumes = {"application/json"}, produces = {"application/json"})
    public Receipt createNewReceipt(@RequestBody Basket cart) {
        var receipt = new ReceiptGenerator().generate(cart);
        return service.createNewReceipt(receipt);
    }

    @PutMapping("/receipts/(id)")
    public Receipt replaceReceipt(@RequestBody Receipt receipt, @PathVariable Long id) {
        return service.replaceReceipt(receipt, id);
    }

    @GetMapping("/receipts")
    public List<Receipt> getReceipts() {
        System.out.println(">> Jestem w metodzie get");
        for(Receipt receipt : service.getReceipts()) {
            System.out.println(receipt.getTotalPrice());
        }
        return service.getReceipts();
    }

    @GetMapping("/receipts/(id)")
    public Receipt getReceipt(@PathVariable Long id) {
        return service.getReceipt(id);
    }
}
