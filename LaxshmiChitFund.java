import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

class Customer {
    String username;
    String password;
    double balance;

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0; // default balance
    }
}

public class LaxshmiChitFund extends JFrame {
    CardLayout card;
    JPanel mainPanel;
    boolean employeeLoggedIn = false;
    Customer currentCustomer = null;
    HashMap<String, Customer> customers = new HashMap<>();

    public LaxshmiChitFund() {
        setTitle("LAXSHMI CHIT FUND - Net Banking System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Default customers
        customers.put("Ram", new Customer("Ram", "1234"));
        customers.put("Tanu", new Customer("Tanu", "2345"));
        customers.put("Shantanu", new Customer("Shantanu", "3456"));
        customers.put("Shabnam", new Customer("Shabnam", "4567"));

        card = new CardLayout();
        mainPanel = new JPanel(card);

        mainPanel.add(homePanel(), "Home");
        mainPanel.add(productsPanel(), "Products");
        mainPanel.add(loginPanel(), "Login");
        mainPanel.add(registerPanel(), "Register");

        add(mainPanel);
        setVisible(true);
    }

    // ------------------- Home Page -------------------
    private JPanel homePanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(new Color(230, 240, 255));

        JLabel title = new JLabel(
                "<html><center><h1 style='color:#1E3C78;'>LAXSHMI CHIT FUND</h1></center></html>",
                SwingConstants.CENTER
        );
        title.setFont(new Font("Arial", Font.BOLD, 24));

        // ---- NEW BANK DESCRIPTION YOU REQUESTED ----
        JLabel desc = new JLabel("<html><center>"
                + "<h3><u>About Our Bank</u></h3>"
                + "<b>Our flagship venture:</b> 21 din me paisa double<br>"
                + "<b>Prime Investors:</b> Shyam, Raju, Babu-bhaiya<br>"
                + "<b>Employee of the Month:</b> Anuradha<br><br>"
                + "<b>CEOs:</b> Vishal Mishra, Santanu Pandit<br>"
                + "<b>CMDs:</b> Sabnam Sultana, Tanu Rai<br>"
                + "</center></html>",
                SwingConstants.CENTER);
        desc.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 15, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 50, 50));
        buttonPanel.setBackground(new Color(230, 240, 255));

        JButton homeBtn = styledButton("🏠 Home");
        JButton productBtn = styledButton("💼 Products");
        JButton loginBtn = styledButton("🔐 Login / Register");
        JButton aboutBtn = styledButton("ℹ About Us");
        JButton contactBtn = styledButton("📞 Contact Us");

        homeBtn.addActionListener(e -> card.show(mainPanel, "Home"));
        productBtn.addActionListener(e -> {
            if (employeeLoggedIn || currentCustomer != null) card.show(mainPanel, "Products");
            else JOptionPane.showMessageDialog(this, "Please login first!");
        });
        loginBtn.addActionListener(e -> card.show(mainPanel, "Login"));
        aboutBtn.addActionListener(e -> showAboutUs());
        contactBtn.addActionListener(e -> showContactUs());

        buttonPanel.add(homeBtn);
        buttonPanel.add(productBtn);
        buttonPanel.add(loginBtn);
        buttonPanel.add(aboutBtn);
        buttonPanel.add(contactBtn);

        p.add(title, BorderLayout.NORTH);
        p.add(desc, BorderLayout.CENTER);
        p.add(buttonPanel, BorderLayout.SOUTH);

        return p;
    }

    private void showAboutUs() {
        JFrame f = new JFrame("About Us");
        f.setSize(500, 250);
        f.setLocationRelativeTo(null);
        f.setLayout(new BorderLayout());

        JTextArea area = new JTextArea(
                "Credits & Thanks:\n\n" +
                        "- Dr. Ram Kumar (15+ years teaching experience)\n" +
                        "- ChatGPT AI\n" +
                        "- Reference Books\n\n" +
                        "We appreciate all contributions to this project!"
        );
        area.setEditable(false);
        area.setFont(new Font("Arial", Font.PLAIN, 14));
        f.add(new JScrollPane(area), BorderLayout.CENTER);

        JButton close = new JButton("Close");
        close.addActionListener(e -> f.dispose());
        f.add(close, BorderLayout.SOUTH);

        f.setVisible(true);
    }

    private void showContactUs() {
        JFrame f = new JFrame("Contact Us");
        f.setSize(500, 300);
        f.setLocationRelativeTo(null);
        f.setLayout(new BorderLayout());

        JTextArea area = new JTextArea(
                "Contact Information:\n\n" +
                        "1. Instagram: @example.insta\n" +
                        "2. Phone: 9992xxxxxxx\n" +
                        "3. WhatsApp: 992638xxxx\n" +
                        "4. Open new account: https://laxshmichitfund.fraud\n" +
                        "5. Our locations: https://google.maps.62528xxxxxxxx\n" +
                        "6. Regional manager: www.laxshmichitfund.regionalmanager.com"
        );
        area.setEditable(false);
        area.setFont(new Font("Arial", Font.PLAIN, 14));
        f.add(new JScrollPane(area), BorderLayout.CENTER);

        JButton close = new JButton("Close");
        close.addActionListener(e -> f.dispose());
        f.add(close, BorderLayout.SOUTH);

        f.setVisible(true);
    }

    // ------------------- Products Page -------------------
    private JPanel productsPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(new Color(210, 225, 250));

        JLabel label = new JLabel("<html><center><h2 style='color:#1E3C78;'>Our Products</h2></center></html>", SwingConstants.CENTER);
        p.add(label, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(3, 2, 20, 20));
        grid.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        grid.setBackground(new Color(210, 225, 250));

        JButton loanBtn = styledButton("Loan");
        JButton lockerBtn = styledButton("Locker");
        JButton insuranceBtn = styledButton("Insurance");
        JButton withdrawBtn = styledButton("Withdraw");
        JButton depositBtn = styledButton("Deposit");
        JButton viewCustomersBtn = styledButton("View Customers");

        // Employee-only buttons
        loanBtn.addActionListener(e -> {
            if (employeeLoggedIn) showLoanFrame();
            else JOptionPane.showMessageDialog(this, "Employee access only!");
        });
        lockerBtn.addActionListener(e -> {
            if (employeeLoggedIn) showLockerFrame();
            else JOptionPane.showMessageDialog(this, "Employee access only!");
        });
        insuranceBtn.addActionListener(e -> {
            if (employeeLoggedIn) showInsuranceFrame();
            else JOptionPane.showMessageDialog(this, "Employee access only!");
        });

        // Customer-only buttons
        withdrawBtn.addActionListener(e -> {
            if(currentCustomer != null) showWithdrawFrame();
            else JOptionPane.showMessageDialog(this, "Customer login required!");
        });
        depositBtn.addActionListener(e -> {
            if(currentCustomer != null) showDepositFrame();
            else JOptionPane.showMessageDialog(this, "Customer login required!");
        });
        viewCustomersBtn.addActionListener(e -> {
            if(employeeLoggedIn) showCustomerTable();
            else JOptionPane.showMessageDialog(this, "Employee access only!");
        });

        grid.add(loanBtn);
        grid.add(lockerBtn);
        grid.add(insuranceBtn);
        grid.add(withdrawBtn);
        grid.add(depositBtn);
        grid.add(viewCustomersBtn);

        JButton backBtn = styledButton("⬅ Back");
        backBtn.addActionListener(e -> card.show(mainPanel, "Home"));

        p.add(grid, BorderLayout.CENTER);
        p.add(backBtn, BorderLayout.SOUTH);
        return p;
    }

    // ------------------- Styled Button -------------------
    private JButton styledButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(50, 90, 180));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { if(btn.isEnabled()) btn.setBackground(new Color(30, 70, 150)); }
            public void mouseExited(MouseEvent e) { if(btn.isEnabled()) btn.setBackground(new Color(50, 90, 180)); }
        });
        return btn;
    }

    // ------------------- All Frames and Login/Register -------------------
    private JPanel loginPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(new Color(230, 240, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel roleLabel = new JLabel("Login As:");
        String[] roles = {"Customer", "Employee"};
        JComboBox<String> roleBox = new JComboBox<>(roles);

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();

        JButton loginBtn = styledButton("Login");
        JButton registerBtn = styledButton("Register New");
        JButton backBtn = styledButton("⬅ Back");

        loginBtn.addActionListener(e -> {
            String role = (String) roleBox.getSelectedItem();
            String user = userField.getText().trim();
            String pass = new String(passField.getPassword());

            if (role.equals("Employee")) {
                if (user.equals("Vishal Mishra") && pass.equals("Vishal1234")) {
                    employeeLoggedIn = true;
                    currentCustomer = null;
                    JOptionPane.showMessageDialog(this, "Employee login successful");
                    card.show(mainPanel, "Home");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid employee credentials");
                }
            } else {
                if (customers.containsKey(user) && customers.get(user).password.equals(pass)) {
                    currentCustomer = customers.get(user);
                    employeeLoggedIn = false;
                    JOptionPane.showMessageDialog(this, "Customer login successful");
                    card.show(mainPanel, "Home");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid customer credentials");
                }
            }
        });

        registerBtn.addActionListener(e -> card.show(mainPanel, "Register"));
        backBtn.addActionListener(e -> card.show(mainPanel, "Home"));

        gbc.gridx = 0; gbc.gridy = 0; p.add(roleLabel, gbc);
        gbc.gridx = 1; p.add(roleBox, gbc);
        gbc.gridx = 0; gbc.gridy = 1; p.add(userLabel, gbc);
        gbc.gridx = 1; p.add(userField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; p.add(passLabel, gbc);
        gbc.gridx = 1; p.add(passField, gbc);
        gbc.gridx = 0; gbc.gridy = 3; p.add(loginBtn, gbc);
        gbc.gridx = 1; p.add(registerBtn, gbc);
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; p.add(backBtn, gbc);

        return p;
    }

    private JPanel registerPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(new Color(230, 240, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Enter New Username:");
        JTextField nameField = new JTextField();
        JLabel passLabel = new JLabel("Enter Password:");
        JPasswordField passField = new JPasswordField();

        JButton registerBtn = styledButton("Register");
        JButton backBtn = styledButton("⬅ Back");

        registerBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String pass = new String(passField.getPassword());

            if(name.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
            } else if(customers.containsKey(name)) {
                JOptionPane.showMessageDialog(this, "Username already exists!");
            } else {
                customers.put(name, new Customer(name, pass));
                JOptionPane.showMessageDialog(this, "Account registered successfully!");
                card.show(mainPanel, "Login");
            }
        });

        backBtn.addActionListener(e -> card.show(mainPanel, "Login"));

        gbc.gridx = 0; gbc.gridy = 0; p.add(nameLabel, gbc);
        gbc.gridx = 1; p.add(nameField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; p.add(passLabel, gbc);
        gbc.gridx = 1; p.add(passField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; p.add(registerBtn, gbc);
        gbc.gridx = 1; p.add(backBtn, gbc);

        return p;
    }

    private void showWithdrawFrame() {
        JFrame f = new JFrame("Withdraw Money");
        f.setSize(400, 200);
        f.setLocationRelativeTo(null);
        f.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel amtLabel = new JLabel("Enter Amount to Withdraw:");
        JTextField amtField = new JTextField();
        JButton submit = new JButton("Withdraw");
        JButton close = new JButton("Close");

        submit.addActionListener(e -> {
            try {
                double amt = Double.parseDouble(amtField.getText());
                if (amt <= 0 || amt > currentCustomer.balance) {
                    JOptionPane.showMessageDialog(f, "Invalid amount!");
                } else {
                    currentCustomer.balance -= amt;
                    JOptionPane.showMessageDialog(f, "Withdrawal successful! New balance: ₹" + currentCustomer.balance);
                }
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(f, "Enter valid number!");
            }
        });

        close.addActionListener(e -> f.dispose());

        f.add(amtLabel); f.add(amtField); f.add(submit); f.add(close);
        f.setVisible(true);
    }

    private void showDepositFrame() {
        JFrame f = new JFrame("Deposit Money");
        f.setSize(400, 200);
        f.setLocationRelativeTo(null);
        f.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel amtLabel = new JLabel("Enter Amount to Deposit:");
        JTextField amtField = new JTextField();
        JButton submit = new JButton("Deposit");
        JButton close = new JButton("Close");

        submit.addActionListener(e -> {
            try {
                double amt = Double.parseDouble(amtField.getText());
                if (amt <= 0) {
                    JOptionPane.showMessageDialog(f, "Invalid amount!");
                } else {
                    currentCustomer.balance += amt;
                    JOptionPane.showMessageDialog(f, "Deposit successful! New balance: ₹" + currentCustomer.balance);
                }
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(f, "Enter valid number!");
            }
        });

        close.addActionListener(e -> f.dispose());

        f.add(amtLabel); f.add(amtField); f.add(submit); f.add(close);
        f.setVisible(true);
    }

    private void showLoanFrame() {
        JFrame f = new JFrame("Loans Available");
        f.setSize(400, 250);
        f.setLocationRelativeTo(null);
        f.setLayout(new GridLayout(4, 1, 5, 5));
        f.add(new JLabel("🏠 Home Loan - Up to ₹50,00,000"));
        f.add(new JLabel("🚗 Car Loan - Up to ₹15,00,000"));
        f.add(new JLabel("🎓 Education Loan - Up to ₹10,00,000"));
        JButton close = new JButton("Close");
        close.addActionListener(e -> f.dispose());
        f.add(close);
        f.setVisible(true);
    }

    private void showLockerFrame() {
        JFrame f = new JFrame("Locker Services");
        f.setSize(400, 200);
        f.setLocationRelativeTo(null);
        f.setLayout(new GridLayout(4, 1, 5, 5));
        f.add(new JLabel("Small Locker - ₹500/month"));
        f.add(new JLabel("Medium Locker - ₹1000/month"));
        f.add(new JLabel("Large Locker - ₹1500/month"));
        JButton close = new JButton("Close");
        close.addActionListener(e -> f.dispose());
        f.add(close);
        f.setVisible(true);
    }

    private void showInsuranceFrame() {
        JFrame f = new JFrame("Insurance Plans");
        f.setSize(400, 200);
        f.setLocationRelativeTo(null);
        f.setLayout(new GridLayout(3, 1, 5, 5));
        f.add(new JLabel("Life Insurance - Cover ₹10,00,000"));
        f.add(new JLabel("Health Insurance - Cover ₹5,00,000"));
        JButton close = new JButton("Close");
        close.addActionListener(e -> f.dispose());
        f.add(close);
        f.setVisible(true);
    }

    private void showCustomerTable() {
        JFrame f = new JFrame("Customer Info");
        f.setSize(500, 300);
        f.setLocationRelativeTo(null);

        String[] cols = {"Username", "Balance"};
        DefaultTableModel model= new DefaultTableModel(cols, 0);

        for(Customer c : customers.values()) {
            model.addRow(new Object[]{c.username, "₹" + c.balance});
        }

        JTable table = new JTable(model);
        f.add(new JScrollPane(table));
        f.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LaxshmiChitFund());
    }
}
