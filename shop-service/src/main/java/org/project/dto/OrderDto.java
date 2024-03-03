package org.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.project.model.Shipper;
import org.project.model.Status;

import java.time.LocalDate;

/**
 * Dto object of product entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private Shipper shipper;

    private Status status;

    /**
     * last quantity updated date
     * it can't be set by the user
     */
    @Setter(AccessLevel.NONE)
    @Schema(description = "date and time when quantity was last updated")
    private LocalDate deliveryDate;

    /**
     * date of product creation
     * it can't be set by the user
     */
    @Setter(AccessLevel.NONE)
    @Schema(description = "date of product creation")
    private LocalDate dateCreated;
}
