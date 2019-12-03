package persistanceModel;

import javax.persistence.*;
import java.util.*;

@Entity(name="Invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int invoiceNumber;
    private int quantity;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToMany(mappedBy = "invoiceSet",cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Product> productSet = new HashSet<Product>();



    public Invoice() {
        this.quantity = 0;
        productSet = new HashSet<>();
    }


    public void addProduct(Product product) {
        this.productSet.add(product);
        product.getInvoiceSet().add(this);
        this.quantity = 1;
        product.setUnitsOnStock(product.getUnitsOnStock()-1);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return invoiceNumber == invoice.invoiceNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceNumber);
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }
}