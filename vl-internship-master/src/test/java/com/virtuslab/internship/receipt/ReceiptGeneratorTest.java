package com.virtuslab.internship.receipt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.ProductDb;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


class ReceiptGeneratorTest {

    @Test
    void testReceiptGeneration() throws Exception {
        var productDb = new ProductDb();
        var cart = new Basket();
        var milk = productDb.getProduct("Milk");
        var bread = productDb.getProduct("Bread");
        var apple = productDb.getProduct("Apple");
        var expectedTotalPrice = milk.price().multiply(BigDecimal.valueOf(2)).add(bread.price()).add(apple.price());
        cart.addProduct(milk);
        cart.addProduct(milk);
        cart.addProduct(bread);
        cart.addProduct(apple);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(cart);
        System.out.println(json);
        var receiptGenerator = new ReceiptGenerator();
        var receipt = receiptGenerator.generate(cart);
        assertNotNull(receipt);
        assertEquals(3, receipt.getEntries().size());
        assertEquals(expectedTotalPrice, receipt.getTotalPrice());
        assertEquals(0, receipt.getDiscounts().size());
    }

}