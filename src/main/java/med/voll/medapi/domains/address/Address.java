package med.voll.medapi.domains.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String number;
    private String city;

    public Address(AddressDTO address) {
        this.street = address.street();
        this.number = address.number();
        this.city = address.city();
    }
}
