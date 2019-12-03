package persistanceModel;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name="Customers")
public class Customer extends Company {
    private double discount;

    @OneToMany//(mappedBy = "customer")
    @JoinColumn
    private Set<Invoice> invoiceSet;

    public Customer() { };

    public Customer(String companyName, String street, String buildingNumber, String city, String postalCode, double discount) {
        super(companyName, street, buildingNumber, city, postalCode);
        this.discount = discount;
        invoiceSet = new HashSet<>();
    }

    public void addInvoiceToInvoiceSet(Invoice invoice)
    {
        invoiceSet.add(invoice);
        invoice.setCustomer(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return getCompanyName().equals(customer.getCompanyName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(discount) + Objects.hashCode(super.getCompanyName());
    }

}
