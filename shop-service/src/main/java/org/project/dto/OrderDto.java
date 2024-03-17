package org.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.project.model.OrderDetails;
import org.project.model.Shipper;
import org.project.model.Status;
import org.project.model.User;

import java.time.LocalDate;
import java.util.Set;

/**
 * Dto object of product entity
 */
@Data
@Builder
public class OrderDto {
    private Status status;
    private Shipper shipper;
    private User user;
    private Set<OrderDetails> orderDetails;

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
