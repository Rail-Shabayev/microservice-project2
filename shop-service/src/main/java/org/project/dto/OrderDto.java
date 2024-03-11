package org.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
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

    @OneToMany(mappedBy = "order")
    private Set<OrderDetails> orderDetails;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;
}
