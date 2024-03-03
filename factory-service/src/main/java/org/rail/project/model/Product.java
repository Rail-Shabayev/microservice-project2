package org.rail.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    private Status status;

    private LocalDate deliveryDate;
    @CreatedDate
    private LocalDate dateCreated;

    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE) //changed cascadeType from All to merge
    @JoinColumn(name = "shipper_id")
    private Shipper shipper;
//    https://habr.com/ru/articles/714704/

}
