package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;

import java.math.BigDecimal;

public class FifteenPercentDiscount {

    public static String NAME = "FifteenPercentDiscount";

    public Receipt apply(Receipt receipt) {
        if (shouldApply(receipt)) {
            var totalPrice = receipt.getTotalPrice().multiply(BigDecimal.valueOf(0.85));
            var discounts = receipt.getDiscounts();
            discounts.add(NAME);
            return new Receipt(receipt.getEntries(), discounts, totalPrice);
        }
        return receipt;
    }

    private boolean shouldApply(Receipt receipt) {

        BigDecimal count = BigDecimal.valueOf(0);
        for(ReceiptEntry entry : receipt.getEntries()) {
            if(entry.product().type().equals(Product.Type.GRAINS)) {
                count = count.add(BigDecimal.valueOf(entry.quantity()));
                System.out.println("Grains products: " + count);
            }
        }
        return count.compareTo(BigDecimal.valueOf(3)) >= 0;
    }

}
