package com.virtuslab.internship.product;

import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.math.BigDecimal;

@Embeddable
@Table(name = "product")
public record Product(
        String name,
        Type type,
        BigDecimal price
) {
    public enum Type {
        DAIRY, FRUITS, VEGETABLES, MEAT, GRAINS
    }
}
