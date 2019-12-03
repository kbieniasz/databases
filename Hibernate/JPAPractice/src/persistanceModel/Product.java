package persistanceModel;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name="Products")
public class Product {
    @Id
    private String productName;
    private int unitsOnStock;
    private double price;

    @ManyToOne
    @JoinColumn
    private Supplier supplier;

    @ManyToOne
    @JoinColumn
    private Category category;

    @ManyToMany(/*mappedBy = "productSet", */cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Invoice> invoiceSet = new HashSet<Invoice>();

    public Product() {}

    public Product(String productName, int unitsOnStock, double price) {
        this.productName = productName;
        this.unitsOnStock = unitsOnStock;
        this.supplier = null;
        this.price = price;
    }


    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public void setInvoiceSet(Set<Invoice> invoiceSet) {
        this.invoiceSet = invoiceSet;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return unitsOnStock == product.unitsOnStock &&
                productName.equals(product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, unitsOnStock);
    }

    public String getProductName() {
        return productName;
    }

    public int getUnitsOnStock() {
        return unitsOnStock;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Category getCategory() {
        return category;
    }


    public Set<Invoice> getInvoiceSet() {
        return invoiceSet;
    }



    public void setUnitsOnStock(int unitsOnStock) {
        this.unitsOnStock = unitsOnStock;
    }

    @Override
    public String toString() {
        return productName;
    }



    public String displayDescription() {
        return "<html>" +
                "product name: " + productName +
                "<br>units on stock:" + unitsOnStock +
                "<br>price:" + price +
                "<br>supplier:" + supplier.toString() +
                "<br>category:" + category.toString() +
                "</html>";
    }


}