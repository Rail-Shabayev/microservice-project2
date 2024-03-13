package org.rail.project.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductMadeEvent {
    private String productName;
    private LocalDate dateCreated;
    private BigDecimal price;
}
