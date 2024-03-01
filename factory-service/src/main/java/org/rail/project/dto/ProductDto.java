package org.rail.project.dto;

import jakarta.validation.constraints.Positive;
import lombok.*;
import org.rail.project.model.Status;

import java.time.LocalDate;

//how to eliminate the need to use
// builder with only one constructor args parameter
// I've seen in one video about that
@RequiredArgsConstructor
@Builder
@Data
public class ProductDto {
    Status status;
    LocalDate deliveryDate;
    LocalDate dateCreated;
    Long shipperId;
}
