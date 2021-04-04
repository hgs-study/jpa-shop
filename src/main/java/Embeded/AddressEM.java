package Embeded;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class AddressEM {
    private String city;
    private String street;
    @Column(name="ZIP_CODE")
    private String zipCode;

    public AddressEM(){

    }

    public AddressEM(String city, String street, String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

//    public void setCity(String city) {
//        this.city = city;
//    }
 
    public String getStreet() {
        return street;
    }

//    public void setStreet(String street) {
//        this.street = street;
//    }

    public String getZipCode() {
        return zipCode;
    }

//    public void setZipCode(String zipCode) {
//        this.zipCode = zipCode;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEM address = (AddressEM) o;
        return Objects.equals(city, address.city) &&
                Objects.equals(street, address.street) &&
                Objects.equals(zipCode, address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipCode);
    }
}
