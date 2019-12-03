package persistanceModel;

import javax.persistence.*;
import java.util.*;

@Entity(name="Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryID;
    //@NaturalId
    private String name;

    @OneToMany//(mappedBy = "category")
    @JoinColumn
    private Set<Product> productSet;

    public Category() {}

    public Category(String name) {
        this.name = name;
        productSet = new HashSet<>();
    }

    public void addProductToProductSet(Product product)
    {
        productSet.add(product);
        product.setCategory(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return categoryID == category.categoryID &&
                Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryID, name);
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getName() {
        return name;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    @Override
    public String toString() {
        return  name;
    }
}