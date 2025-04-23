package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame implements ActionListener {
    public Dashboard() throws HeadlessException {
        this.setSize(1550, 1000);
        this.setLayout(null);
        this.setTitle("WELCOME");

        //set text title
        JLabel text = new JLabel("THE PAHAMA GROUP WELCOME YOU");
        text.setBounds(200, 80, 1000, 60);
        text.setFont(new Font("Tahoma", Font.BOLD, 50));
        text.setForeground(Color.WHITE);
        this.add(text);

        //set background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1550, 1000, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1550, 1000);
        this.add(image);

        //menu bar
        JMenuBar menubar = new JMenuBar();
        menubar.setBounds(0, 0, 1550, 30);
        menubar.setBorderPainted(false);
        image.add(menubar);

        //menu bar has hotel and admin
        JMenu hotel = new JMenu("HOTEL MANAGEMENT");
        hotel.setForeground(Color.RED);
        hotel.setBorderPainted(false);
        menubar.add(hotel);

        JMenuItem reception = new JMenuItem("RECEPTION");
        reception.addActionListener(this);
        hotel.add(reception);

        JMenu admin = new JMenu("ADMIN");
        admin.setForeground(Color.blue);
        admin.setBorderPainted(false);
        menubar.add(admin);

        JMenuItem addemployee = new JMenuItem("ADD EMPLOYEE");
        addemployee.addActionListener(this);
        admin.add(addemployee);

        JMenuItem addrooms = new JMenuItem("ADD ROOM");
        addrooms.addActionListener(this);
        admin.add(addrooms);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }


    public static void main(String[] args) {
        new Dashboard();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ADD EMPLOYEE")) {
            this.setVisible(false);
            new AddEmployee();
        } else if (e.getActionCommand().equals("ADD ROOM")) {
            this.setVisible(false);
            new AddRoom();
        } else if (e.getActionCommand().equals("RECEPTION")) {
            this.setVisible(false);
            new Reception();
        }
    }
}