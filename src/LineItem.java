public class LineItem {
    private int quantity;
    private Product product;

    public LineItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
    public Product getProduct() {
        return product;
    }
}
