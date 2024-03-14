package org.rail.project.dto;

import lombok.*;
import org.rail.project.model.Manufacturer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

//how to eliminate the need to use
// builder with only one constructor args parameter
// I've seen in one video about that
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductDto implements Serializable {
    private String name;
    private BigDecimal price;

    @Setter(AccessLevel.NONE)
    private LocalDate dateCreated;
    private Manufacturer manufacturer;
}
