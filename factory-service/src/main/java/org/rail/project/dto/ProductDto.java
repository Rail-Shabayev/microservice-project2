package org.rail.project.dto;

import lombok.*;
import org.rail.project.model.Shipper;
import org.rail.project.model.Status;

import java.time.LocalDate;

//how to eliminate the need to use
// builder with only one constructor args parameter
// I've seen in one video about that
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductDto {
    private Status status;
    private LocalDate deliveryDate;

    @Setter(AccessLevel.NONE)
    private LocalDate dateCreated;
    private Shipper shipper;
}
