package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;

import java.util.*;

public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        var receipt = getReceipt(basket);
        return receipt;
    }

    private Receipt getReceipt(Basket basket) {
        List<ReceiptEntry> receiptEntries = new ArrayList<>();

        //add every distinct item with quantity
        Set<Product> distinct = new HashSet<>(basket.getProducts());
        for (Product prod : distinct) {
            receiptEntries.add(new ReceiptEntry(prod, Collections.frequency(basket.getProducts(), prod)));
        }

        return new Receipt(receiptEntries);
    }
}
