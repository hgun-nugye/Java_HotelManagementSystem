package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class Reception extends JFrame implements ActionListener {

    private final JButton newCustomer;
    private final JButton rooms;
    private final JButton department;
    private final JButton allEmployees;
    private final JButton customers;
    private final JButton manager;
    private final JButton checkout;
    private final JButton update;
    private final JButton roomStatus;
    private final JButton pickup;
    private final JButton searchRoom;
    private final JButton logOut;

    public Reception() {
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(null);
        this.setSize(800, 570);
        this.setTitle("Reception");

        //Button new Customer section
        newCustomer = new JButton("New Customer Form");
        newCustomer.setBounds(10, 30, 200, 30);
        newCustomer.setBackground(Color.BLACK);
        newCustomer.setForeground(Color.WHITE);
        newCustomer.setBorderPainted(false);
        newCustomer.setOpaque(true);
        newCustomer.addActionListener(this);
        this.add(newCustomer);

        //Button new rooms section
        rooms = new JButton("Rooms");
        rooms.setBounds(10, 70, 200, 30);
        rooms.setBackground(Color.BLACK);
        rooms.setForeground(Color.WHITE);
        rooms.setBorderPainted(false);
        rooms.setOpaque(true);
        rooms.addActionListener(this);
        this.add(rooms);

        //Button new department section
        department = new JButton("Department");
        department.setBounds(10, 110, 200, 30);
        department.setBackground(Color.BLACK);
        department.setForeground(Color.WHITE);
        department.setBorderPainted(false);
        department.setOpaque(true);
        department.addActionListener(this);
        this.add(department);

        //Button employees section
        allEmployees = new JButton("All Employees");
        allEmployees.setBounds(10, 150, 200, 30);
        allEmployees.setBackground(Color.BLACK);
        allEmployees.setForeground(Color.WHITE);
        allEmployees.setBorderPainted(false);
        allEmployees.setOpaque(true);
        this.add(allEmployees);

        //Button customer infor section
        customers = new JButton("Customer Infor");
        customers.setBounds(10, 190, 200, 30);
        customers.setBackground(Color.BLACK);
        customers.setForeground(Color.WHITE);
        customers.setBorderPainted(false);
        customers.setOpaque(true);
        this.add(customers);

        //Button manager infor section
        manager = new JButton("Manager Infor");
        manager.setBounds(10, 230, 200, 30);
        manager.setBackground(Color.BLACK);
        manager.setForeground(Color.WHITE);
        manager.setBorderPainted(false);
        manager.setOpaque(true);
        this.add(manager);

        //Button check out section
        checkout = new JButton("Check out");
        checkout.setBounds(10, 270, 200, 30);
        checkout.setBackground(Color.BLACK);
        checkout.setForeground(Color.WHITE);
        checkout.setBorderPainted(false);
        checkout.setOpaque(true);
        this.add(checkout);

        //Button customer infor section
        update = new JButton("Update Status");
        update.setBounds(10, 310, 200, 30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBorderPainted(false);
        update.setOpaque(true);
        this.add(update);


        //Button update room status section
        roomStatus = new JButton("Update Room Status");
        roomStatus.setBounds(10, 350, 200, 30);
        roomStatus.setBackground(Color.BLACK);
        roomStatus.setForeground(Color.WHITE);
        roomStatus.setBorderPainted(false);
        roomStatus.setOpaque(true);
        this.add(roomStatus);

        //Button pickup service section
        pickup = new JButton("Pickup Service");
        pickup.setBounds(10, 390, 200, 30);
        pickup.setBackground(Color.BLACK);
        pickup.setForeground(Color.WHITE);
        pickup.setBorderPainted(false);
        pickup.setOpaque(true);
        this.add(pickup);

        //Button search room section
        searchRoom = new JButton("Search Room");
        searchRoom.setBounds(10, 430, 200, 30);
        searchRoom.setBackground(Color.BLACK);
        searchRoom.setForeground(Color.WHITE);
        searchRoom.setBorderPainted(false);
        searchRoom.setOpaque(true);
        this.add(searchRoom);

        //Button log out section
        logOut = new JButton("Log Out");
        logOut.setBounds(10, 470, 200, 30);
        logOut.setBackground(Color.BLACK);
        logOut.setForeground(Color.WHITE);
        logOut.setBorderPainted(false);
        logOut.setOpaque(true);
        logOut.addActionListener(this);
        this.add(logOut);

        //Image section
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fourth.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(250, 30, 500, 470);
        this.add(image);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Reception();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("New Customer Form")) {
            this.setVisible(false);
            new AddCustomer();
        }
        else if (event.getActionCommand().equals("Rooms")) {
            this.setVisible(false);
            new Room();
        }
        else if (event.getActionCommand().equals("Department")) {
            this.setVisible(false);
            new Department();
        }
        else if (event.getActionCommand().equals("Log Out")) {
            this.setVisible(false);
        }
    }
}
