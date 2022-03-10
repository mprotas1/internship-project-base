package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

import java.math.BigDecimal;

public class TenPercentDiscount {

    public static String NAME = "TenPercentDiscount";

    public Receipt apply(Receipt receipt) {
        if (shouldApply(receipt)) {
            var totalPrice = receipt.getTotalPrice().multiply(BigDecimal.valueOf(0.9));
            var discounts = receipt.getDiscounts();
            discounts.add(NAME);
            return new Receipt(receipt.getEntries(), discounts, totalPrice);
        }
        return receipt;
    }

    private boolean shouldApply(Receipt receipt) {
        return receipt.getTotalPrice().compareTo(BigDecimal.valueOf(50)) > 0;
    }

}
