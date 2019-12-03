package persistanceModel;

import javax.persistence.*;

@Entity(name="Companies")
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Company {
    @Id
    private String companyName;

    @Embedded
    private Address address;


    public Company(String companyName, String street, String buildingNumber, String city, String postalCode) {
        this.companyName = companyName;
        this.address = new Address(street, buildingNumber, city, postalCode);

    }



    public Company() {

    }

    public String getCompanyName() {
        return companyName;
    }

    public Address getAddress() {
        return address;
    }
}
