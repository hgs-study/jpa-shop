package Embeded;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
public class AddressEntity {

    @Id
    @GeneratedValue
    private Long Id;

    private AddressEM address;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public AddressEM getAddress() {
        return address;
    }

    public void setAddress(AddressEM address) {
        this.address = address;
    }

    public AddressEntity() {
    }

    public AddressEntity(String city, String street, String zipcode) {
        this.address = new AddressEM(city,street,zipcode);
    }
}
