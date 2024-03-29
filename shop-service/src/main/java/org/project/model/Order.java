package org.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.Set;

/**
 * Product entity
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "Orders")
public class Order {

    /**
     * Unique identifier
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Status status;

    /**
     * date of delivery
     */
    private LocalDate deliveryDate;

    /**
     * date of order creation
     */
    @CreatedDate
    private LocalDate dateCreated;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetails> orderDetails;

    /**
     * shipper
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "shipper_id")
    private Shipper shipper;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;
}
