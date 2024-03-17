package org.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @Min(value = 1)
    private BigDecimal price;
    @CreatedDate
    private LocalDate dateCreated;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "products")
    private Manufacturer manufacturer;

    @OneToMany
    @JoinColumn(name = "product")
    private Set<OrderDetails> orderDetails;
}
