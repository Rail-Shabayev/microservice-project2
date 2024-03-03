package org.rail.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.rail.project.annotation.PhoneNumber;

import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
//@NamedEntityGraph(name = "shipper-graph", attributeNodes = @NamedAttributeNode("products"))
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Size(min = 2)
    private String shipperName;
    @PhoneNumber(message = "Phone number is not valid")
    private String phoneNumber;
    @Email
    private String email;

    @OneToMany(
            mappedBy = "manufacturer",
            cascade = CascadeType.ALL
    )
    private List<Product> products;

}

