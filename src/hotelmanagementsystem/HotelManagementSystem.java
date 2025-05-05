package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelManagementSystem extends JFrame implements ActionListener {

    public HotelManagementSystem() {
        this.setSize(900, 545);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("HOTEL MANAGEMENT SYSTEM");
//        this.setLayout(null);

        //add text title
        JLabel text = new JLabel("<html>HOTEL <br><br>MANAGEMENT<br> <br>SYSTEM</html>");
        Font font = new Font("Tahoma", Font.BOLD, 50);
        text.setBounds(50, 100, 900, 300);
        text.setFont(font);
        text.setForeground(Color.decode("#4b3808"));
        this.add(text);

        //add next button
        JButton next = new JButton("Next →");
        next.setBounds(700, 455, 150, 30);
        next.setFont(new Font("serif", Font.BOLD, 22));
        next.setBackground(Color.black);
        next.setForeground(Color.white);
        next.setFocusPainted(false);
        next.setBorderPainted(false);
        next.addActionListener(this);
        this.add(next);


        // Setting up background picture
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/hotel.gif"));
        Image i2 = i1.getImage().getScaledInstance(900, 545, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 545); // Match frame size
        this.add(image);

        this.setVisible(true);

        //hide and show text animation
        while (true) {
            text.setVisible(false);

            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }

            text.setVisible(true);

            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new HotelManagementSystem();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //hide welcome window when click Next button
        this.setVisible(false);
        //show login when click Next button
        new Login();
    }
}