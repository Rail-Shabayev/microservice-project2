package org.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Size(min = 2)
    private String shipperName;
    //    @PhoneNumber(message = "Phone number is not valid")
    private String phoneNumber;
    @Email
    private String email;

    @OneToMany(mappedBy = "manufacturer")
    private Set<Product> products;

}

