package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BothDiscounts {
    @Test
    void shouldApplyDiscount() throws Exception {

        var productDb = new ProductDb();
        var cheese = productDb.getProduct("Cheese");
        var bread = productDb.getProduct("Bread");
        var cereals = productDb.getProduct("Cereals");

        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cheese, 3));
        receiptEntries.add(new ReceiptEntry(bread, 3));
        receiptEntries.add(new ReceiptEntry(cereals, 2));

        var fifteenPercentDiscount = new FifteenPercentDiscount();
        var tenPercentDiscount = new TenPercentDiscount();
        var receipt = new Receipt(receiptEntries);
        var receiptAfterDiscount = tenPercentDiscount.apply(fifteenPercentDiscount.apply(receipt));
        var expectedTotalPrice = (cheese.price().multiply(BigDecimal.valueOf(3))
                .add(bread.price().multiply(BigDecimal.valueOf(3))
                        .add(cereals.price().multiply(BigDecimal.valueOf(2)))))
                .multiply(BigDecimal.valueOf(0.85)).multiply(BigDecimal.valueOf(0.90));
        System.out.println(expectedTotalPrice);
        assertEquals(expectedTotalPrice, receiptAfterDiscount.getTotalPrice());
        assertEquals(2, receiptAfterDiscount.getDiscounts().size());
        System.out.println(receipt.getDiscounts());
    }

    @Test
    void shouldNotApplyDiscount() throws Exception {

        int quantity = 2;
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Cereals");

        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, quantity));

        var receipt = new Receipt(receiptEntries);
        var fifteenPercentDiscount = new FifteenPercentDiscount();
        var receiptAfterDiscount = fifteenPercentDiscount.apply(receipt);
        var expectedTotalPrice = bread.price().multiply(BigDecimal.valueOf(quantity));

        assertEquals(expectedTotalPrice, receiptAfterDiscount.getTotalPrice());
        assertEquals(0, receiptAfterDiscount.getDiscounts().size());
    }
}
