package com.virtuslab.internship.receipt;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Receipt {
    @Id @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "entryId")
    private List<ReceiptEntry> entries;

    @ElementCollection
    private List<String> discounts;

    private BigDecimal totalPrice;

    public Receipt() {

    }

    public Receipt(List<ReceiptEntry> entries, List<String> discounts, BigDecimal totalPrice) {
        this.entries = entries;
        this.discounts = discounts;
        this.totalPrice = totalPrice;
    }

    public Receipt(List<ReceiptEntry> entries) {
        this.entries = entries;
        this.discounts = new ArrayList<>();
        totalPrice = entries.stream()
                .map(ReceiptEntry::totalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", entries=" + entries +
                ", discounts=" + discounts +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
