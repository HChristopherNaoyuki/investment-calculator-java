package Solutions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class InvestmentCalculator extends JFrame
{
    // GUI Components
    private JTextField txtCustomerName;
    private JTextField txtAmount;
    private JComboBox<String> cmbInvestmentType;
    private JRadioButton rb5Years, rb10Years, rb15Years;
    private ButtonGroup termGroup;
    private JMenuItem mnuCalculate, mnuClear, mnuExit;

    // Constructor to set up the GUI
    public InvestmentCalculator()
    {
        super("Investment Calculator");
        setLookAndFeel();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Initialize components with updated sizes
        txtCustomerName = new JTextField(20);
        txtAmount = new JTextField(10);
        cmbInvestmentType = new JComboBox<>(new String[]{"Moderate", "Aggressive"});
        
        rb5Years = new JRadioButton("5 Years");
        rb10Years = new JRadioButton("10 Years");
        rb15Years = new JRadioButton("15 Years");
        rb15Years.setSelected(true);  // Default selection

        // Group radio buttons
        termGroup = new ButtonGroup();
        termGroup.add(rb5Years);
        termGroup.add(rb10Years);
        termGroup.add(rb15Years);

        // Create menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu toolsMenu = new JMenu("Tools");
        
        mnuExit = new JMenuItem("Exit");
        mnuCalculate = new JMenuItem("Calculate");
        mnuClear = new JMenuItem("Clear");

        fileMenu.add(mnuExit);
        toolsMenu.add(mnuCalculate);
        toolsMenu.add(mnuClear);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);

        setJMenuBar(menuBar);

        // Set up layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Customer Name:"), gbc);

        gbc.gridx = 1;
        add(txtCustomerName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Enter Amount:"), gbc);

        gbc.gridx = 1;
        add(txtAmount, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Select Type:"), gbc);

        gbc.gridx = 1;
        add(cmbInvestmentType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Select Term:"), gbc);

        gbc.gridx = 1;
        JPanel termPanel = new JPanel();
        termPanel.add(rb5Years);
        termPanel.add(rb10Years);
        termPanel.add(rb15Years);
        add(termPanel, gbc);

        // Action listeners for menu items
        mnuExit.addActionListener(e -> System.exit(0));
        mnuClear.addActionListener(e -> clearFields());
        mnuCalculate.addActionListener(e -> calculateInvestment());

        // Set frame properties
        setSize(400, 350);
        setResizable(false);  // Prevent resizing
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Set a modern look and feel
    private void setLookAndFeel()
    {
        try
        {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.put("control", new Color(255, 255, 255));
            UIManager.put("info", new Color(255, 255, 255));
            UIManager.put("text", Color.black);
            UIManager.put("nimbusBase", new Color(230, 230, 230));
            UIManager.put("nimbusBlueGrey", new Color(150, 150, 150));
            UIManager.put("nimbusLightBackground", new Color(245, 245, 245));
            UIManager.put("nimbusFocus", new Color(51, 153, 255));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Method to clear all fields
    private void clearFields()
    {
        txtCustomerName.setText("");
        txtAmount.setText("");
        cmbInvestmentType.setSelectedIndex(0);
        rb15Years.setSelected(true);  // Reset to default selection
    }

    // Method to calculate investment based on user inputs
    private void calculateInvestment()
    {
        try
        {
            String customerName = txtCustomerName.getText();
            double amount = Double.parseDouble(txtAmount.getText());

            // Determine interest rate based on investment type
            double interestRate;
            if (cmbInvestmentType.getSelectedItem().equals("Moderate"))
            {
                interestRate = 0.10;
            }
            else if (cmbInvestmentType.getSelectedItem().equals("Aggressive"))
            {
                interestRate = 0.15;
            }
            else
            {
                throw new IllegalArgumentException("Invalid investment type selected.");
            }

            // Determine term in years
            int years = rb5Years.isSelected() ? 5 : rb10Years.isSelected() ? 10 : 15;

            // Calculate compound interest
            double finalAmount = amount * Math.pow((1 + interestRate), years);
            DecimalFormat df = new DecimalFormat("R #,##0.00");

            // Display results in a message dialog
            JOptionPane.showMessageDialog(this,
                "INVESTMENT REPORT\n" +
                "CUSTOMER NAME: " + customerName + "\n" +
                "ORIGINAL AMOUNT: R " + df.format(amount) + "\n" +
                "YEARS INVESTED: " + years + "\n" +
                "FINAL AMOUNT: " + df.format(finalAmount),
                "Message",
                JOptionPane.INFORMATION_MESSAGE);
        }
        catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(this,
                "Please enter a valid number for the amount.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
        }
        catch (IllegalArgumentException ex)
        {
            JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this,
                "An unexpected error occurred.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    // Main method to run the application
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new InvestmentCalculator());
    }
}
