import java.util.ArrayList;
public class Invoice {
    private Address address;
    private ArrayList<LineItem> items;

    public Invoice(Address address) {
        this.address = address;
        this.items = new ArrayList<>();
    }

    public void add(Product product,int quantity) {
        items.add(new LineItem(quantity, product));
    }
    public double getAmountDue() {
        double amountDue = 0;
        for (LineItem item : items) {
            amountDue += item.getTotalPrice();
        }
        return amountDue;
    }
    public String format(){
        String result = "INVOICE\n\n";
        result = result + address.formatedAddress() + "\n\n";
        result = result + String.format("%-15s %5s %10s %10s\n", "Item", "Qty", "Price", "Total");
        for (LineItem item : items) {
            result = result + String.format("%-15s %5d %10.2f %10.2f\n",
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getProduct().getPrice(),
                    item.getTotalPrice());
        }
        result = result + String.format("\n AMOUNT DUE: $%.2f", getAmountDue());
        return result;
    }

}
