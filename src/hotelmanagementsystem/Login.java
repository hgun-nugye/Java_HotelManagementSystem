package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    private final JTextField username;
    private final JPasswordField password;
    private final JButton login;
    private final JButton cancel;

    public Login() {
        this.getContentPane().setBackground(Color.white);
        this.setSize(600, 300);
        this.setLayout(null);
        this.setTitle("Login");

        //username section
        JLabel user = new JLabel("Username");
        user.setBounds(40, 20, 100, 30);
        this.add(user);

        username = new JTextField();
        username.setBounds(150, 20, 200, 30);
        this.add(username);

        //password section
        JLabel pass = new JLabel("Password:");
        pass.setBounds(40, 70, 100, 30);
        this.add(pass);

        password = new JPasswordField();
        password.setBounds(150, 70, 200, 30);
        this.add(password);

        //login button
        login = new JButton("Login");
        login.setBounds(40, 150, 120, 40);
        login.setFont(new Font("Arial", Font.BOLD, 15));
        login.setBackground(Color.BLACK);
        login.setBorderPainted(false);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        this.add(login);

        //cancel button
        cancel = new JButton("Cancel");
        cancel.setBounds(200, 150, 120, 40);
        cancel.setFont(new Font("Arial", Font.BOLD, 15));
        cancel.setBackground(Color.BLACK);
        cancel.setBorderPainted(false);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        this.add(cancel);

        //add icon image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 10, 200, 200);
        this.add(image);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == login) {
            String user = username.getText();
            String pass = password.getText();

            try {
                Connect c = new Connect();

                String query = "SELECT * FROM login WHERE username = ? AND password = ?";
                PreparedStatement pstmt = c.c.prepareStatement(query);
                pstmt.setString(1, user);
                pstmt.setString(2, pass);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    //hide this section and show dashboard
                    this.setVisible(false);
                    new Dashboard();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                    this.setVisible(false);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == cancel) {
            this.setVisible(false);
        }

    }
}
