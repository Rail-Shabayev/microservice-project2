package org.rail.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.rail.project.annotation.PhoneNumber;

@Entity
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Shipper {
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


}

