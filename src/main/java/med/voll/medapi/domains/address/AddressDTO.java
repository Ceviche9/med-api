package med.voll.medapi.domains.address;

import jakarta.validation.constraints.NotBlank;

public record AddressDTO(
        @NotBlank
        String street,
        @NotBlank
        String number,
        @NotBlank
        String city
) {
}
