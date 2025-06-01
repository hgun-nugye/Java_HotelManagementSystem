package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame implements ActionListener {
    public Dashboard() throws HeadlessException {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(null);
        this.setTitle("WELCOME");

        //set text title
        JLabel text = new JLabel("WELCOME TO LARANA HOTEL");
        text.setBounds(120, 120, 1200, 80);
        text.setFont(new Font("Tahoma", Font.BOLD, 70));
        text.setForeground(Color.decode("#4b3808"));
        this.add(text);

        //set background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/dashboard.png"));
        Image i2 = i1.getImage().getScaledInstance(1900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(-200, 0, 1900, 900);
        this.add(image);

        //menu bar
        JMenuBar menubar = new JMenuBar();
        menubar.setBounds(0, 0, 2500, 30);
        menubar.setBorderPainted(false);
        this.add(menubar);
        this.setJMenuBar(menubar);


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

        JMenuItem totalCost = new JMenuItem("REVENUE");
        totalCost.addActionListener(this);
        admin.add(totalCost);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ADD EMPLOYEE")) {
            this.setVisible(false);
            new Admin("ADD EMPLOYEE");
        } else if (e.getActionCommand().equals("ADD ROOM")) {
            this.setVisible(false);
            new Admin("ADD ROOM");
        } else if (e.getActionCommand().equals("REVENUE")) {
            this.setVisible(false);
            new Admin("REVENUE");
        } else if (e.getActionCommand().equals("RECEPTION")) {
            this.setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}