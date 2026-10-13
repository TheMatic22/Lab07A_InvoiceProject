import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class InvoiceFrame extends JFrame {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;

    private JTextField amountField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipField;

    private JTextField productField;
    private JTextField priceField;
    private JTextField quantityField;
    private JButton addButton;
    private Invoice invoice;
    private JTextField outputArea;
    private JButton showButton;

    public InvoiceFrame() {
        setTitle("Invoice");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(createAddressPanel(), BorderLayout.NORTH);
        inputPanel.add(createItemPanel(), BorderLayout.SOUTH);
        add(createOutputPane(),BorderLayout.CENTER);
        add(createButtonPan(),BorderLayout.SOUTH);
        addButton = new JButton("Add");
        addButton.addActionListener(e -> {});
        setResizable(true);
    }
    private JPanel createAddressPanel() {
        amountField = new JTextField();
        streetField = new JTextField();
        cityField = new JTextField();
        stateField = new JTextField();
        zipField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(new JLabel("Store Name:"));
        panel.add(amountField);
        panel.add(new JLabel("Street:"));
        panel.add(streetField);
        panel.add(new JLabel("City:"));
        panel.add(cityField);
        panel.add(new JLabel("State:"));
        panel.add(stateField);
        panel.add(new JLabel("Zip:"));
        panel.add(zipField);
        return panel;
    }
    private JPanel createItemPanel() {
        productField = new JTextField(10);
        quantityField = new JTextField(4);
        priceField = new JTextField(6);
        addButton = new JButton("Add Item");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Product Name:"));
        panel.add(productField);
        panel.add(new JLabel("Price: "));
        panel.add(priceField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(addButton);
        return panel;
    }
    private void addItem() {
        if (invoice == null) {
            Address address = new Address(
                    amountField.getText(),
                    streetField.getText(),
                    cityField.getText(),
                    stateField.getText(),
                    zipField.getText());
            invoice = new Invoice(address);
        }

        String name = productField.getText();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());
        invoice.add(new Product(name, price), quantity);

        productField.setText("");
        priceField.setText("");
        quantityField.setText("");
    }
    private JScrollPane createOutputPane() {
        outputArea = new JTextField(10);
        outputArea.setEditable(false);
        outputArea.setFont(new Font(outputArea.getFont().getName(),Font.BOLD,12));
        return new JScrollPane(outputArea);
    }
    private JPanel createButtonPan() {
        showButton = new JButton("Show Invoice");
        showButton.addActionListener(event -> showInvoice());

        JPanel panel = new JPanel();
        panel.add(showButton);
        return panel;
    }
    private void showInvoice(){
        if (invoice == null) {
            outputArea.setText("No items added yet.");
        }else{
            outputArea.setText(invoice.format());
        }
    }

}
