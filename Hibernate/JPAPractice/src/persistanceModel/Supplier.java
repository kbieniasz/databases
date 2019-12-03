package persistanceModel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;



@Entity(name="Suppliers")
//@SecondaryTable(name="Address")
public class Supplier extends  Company {

    /*
    @Id
    private String companyName;

    @Embedded
    private Address address;
    */
    private String bankAccount;

    /*
    @Column(table = "Address")
    private String street;
    @Column(table = "Address")
    private String buildingNumber;
    @Column(table = "Address")
    private String city;
    @Column(table = "addresses")
    private String postalCode;
    */

    @OneToMany//(mappedBy = "supplier")
    @JoinColumn
    private Set<Product> productSet ;




    public Supplier(String companyName, String street, String buildingNumber, String city, String postalCode,  String bankAccount) {
        super(companyName, street, buildingNumber, city, postalCode);
        this.bankAccount = bankAccount;
        productSet = new HashSet<>();
    }




    public Supplier() { }
    /*
    public Supplier(String companyName, String street, String buildingNumber, String city, String postalCode) {
        this.companyName = companyName;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.city = city;
        this.postalCode = postalCode;
        productSet = new HashSet<>();
    }

     */

    /*
    public Supplier(String companyName, String street, String buildingNumber, String city, String postalCode) {
        this.companyName = companyName;
        this.address = new Address(street, buildingNumber, city, postalCode);
        productSet = new HashSet<>();
    }

     */


    public void addProductToProductSet(Product product)
    {
        productSet.add(product);
        product.setSupplier(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return bankAccount.equals(supplier.bankAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankAccount) + 1;
    }

    @Override
    public String toString() {
        return getCompanyName();
    }
}