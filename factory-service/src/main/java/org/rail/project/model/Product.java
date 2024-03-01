package org.rail.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

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
    private Status status;
    @NotBlank
    private LocalDate deliveryDate;
    @CreatedDate
    private LocalDate dateCreated;
    @ManyToOne(targetEntity = Shipper.class)
    @JoinColumn(name = "shipper_id")
    private Long shipperId;

}
