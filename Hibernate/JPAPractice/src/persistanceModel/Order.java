package persistanceModel;

public class Order {
    private Product product;
    private int amount;

    public Order(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return amount + "  " + product.toString();
    }
}
