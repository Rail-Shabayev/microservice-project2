package org.project.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@Immutable
public class OrderDetails {
    @Embeddable
    @RequiredArgsConstructor
    @EqualsAndHashCode
    public static class Id implements Serializable {
        @Column(name = "order_id")
        protected Long orderId;

        @Column(name = "product_id")
        protected Long productId;
    }

    @EmbeddedId
    protected Id id = new Id();

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    private BigDecimal price;
    private int qty;
}
