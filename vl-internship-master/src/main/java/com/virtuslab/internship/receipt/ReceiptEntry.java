package com.virtuslab.internship.receipt;

import com.virtuslab.internship.product.Product;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class ReceiptEntry {
    @Id
    private Long entryId;

    private Product product;

    private int quantity;

    private BigDecimal totalPrice;

    public ReceiptEntry() {

    }

    public ReceiptEntry(
            Product product,
            int quantity,
            BigDecimal totalPrice) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public ReceiptEntry(Product product, int quantity) {
        this(product, quantity, product.price().multiply(BigDecimal.valueOf(quantity)));
    }

    public Product product() {
        return product;
    }

    public int quantity() {
        return quantity;
    }

    public BigDecimal totalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "ReceiptEntry{" +
                "id=" + entryId +
                ", product=" + product +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
