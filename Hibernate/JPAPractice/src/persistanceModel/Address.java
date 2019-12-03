package persistanceModel;

import org.apache.derby.jdbc.AutoloadedDriver;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String buildingNumber;
    private String city;
    private String postalCode;

    public Address(){};

    public Address(String street, String buildingNumber, String city, String postalCode) {
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.city = city;
        this.postalCode = postalCode;
    }

    public String deliverInfo()
    {
        return "<html>" +
                street + " " + buildingNumber +
                "<br>" + city + " " + postalCode +
                "</html>";

    }
}
