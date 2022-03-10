package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FifteenPercentDiscountTest {
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

        var receipt = new Receipt(receiptEntries);
        var discount = new FifteenPercentDiscount();
        var receiptAfterDiscount = discount.apply(receipt);
        var expectedTotalPrice = (cheese.price().multiply(BigDecimal.valueOf(3))
                .add(bread.price().multiply(BigDecimal.valueOf(3))
                        .add(cereals.price().multiply(BigDecimal.valueOf(2))))).multiply(BigDecimal.valueOf(0.85));

        assertEquals(expectedTotalPrice, receiptAfterDiscount.getTotalPrice());
        assertEquals(1, receiptAfterDiscount.getDiscounts().size());
    }

    @Test
    void shouldNotApplyDiscount() throws Exception {

        int quantity = 2;
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Cereals");

        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, quantity));
        var receipt = new Receipt(receiptEntries);
        var discount = new FifteenPercentDiscount();
        var receiptAfterDiscount = discount.apply(receipt);
        var expectedTotalPrice = bread.price().multiply(BigDecimal.valueOf(quantity));
        System.out.println(receiptAfterDiscount);
        assertEquals(expectedTotalPrice, receiptAfterDiscount.getTotalPrice());
        assertEquals(0, receiptAfterDiscount.getDiscounts().size());
    }
}
