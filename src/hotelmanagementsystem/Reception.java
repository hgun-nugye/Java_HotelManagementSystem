package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reception extends JFrame implements ActionListener {

    private final JButton newCustomer;
    private final JButton rooms;
    private final JButton allEmployees;
    private final JButton customers;
    private final JButton manager;
    private final JButton checkout;
    private final JButton update;
    private final JButton roomStatus;
    private final JButton searchRoom;
    private final JButton logOut;
    private final JButton back;
    private final JButton bill;

    public Reception() {
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(null);
        this.setSize(810, 650);
        this.setTitle("Reception");

        //Button new Customer section
        newCustomer = new JButton("Thêm Khách hàng");
        newCustomer.setBounds(30, 30, 200, 30);
        newCustomer.setBackground(Color.BLACK);
        newCustomer.setForeground(Color.WHITE);
        newCustomer.setBorderPainted(false);
        newCustomer.setOpaque(true);
        newCustomer.setFocusPainted(false);
        newCustomer.addActionListener(this);
        this.add(newCustomer);

        //Button Customer infor section
        customers = new JButton("Danh sách Khách hàng");
        customers.setBounds(30, 75, 200, 30);
        customers.setBackground(Color.BLACK);
        customers.setForeground(Color.WHITE);
        customers.setBorderPainted(false);
        customers.setOpaque(true);
        customers.setFocusPainted(false);
        customers.addActionListener(this);
        this.add(customers);

        //Button new rooms section
        rooms = new JButton("Danh sách Phòng");
        rooms.setBounds(30, 125, 200, 30);
        rooms.setBackground(Color.BLACK);
        rooms.setForeground(Color.WHITE);
        rooms.setBorderPainted(false);
        rooms.setOpaque(true);
        rooms.setFocusPainted(false);
        rooms.addActionListener(this);
        this.add(rooms);

        //Button update room status section
        roomStatus = new JButton("Cập nhật Phòng");
        roomStatus.setBounds(30, 170, 200, 30);
        roomStatus.setBackground(Color.BLACK);
        roomStatus.setForeground(Color.WHITE);
        roomStatus.setBorderPainted(false);
        roomStatus.setOpaque(true);
        roomStatus.setFocusPainted(false);
        roomStatus.addActionListener(this);
        this.add(roomStatus);

        //Button search room section
        searchRoom = new JButton("Tìm phòng");
        searchRoom.setBounds(30, 215, 200, 30);
        searchRoom.setBackground(Color.BLACK);
        searchRoom.setForeground(Color.WHITE);
        searchRoom.setBorderPainted(false);
        searchRoom.setOpaque(true);
        searchRoom.setFocusPainted(false);
        searchRoom.addActionListener(this);
        this.add(searchRoom);

        //Button employees section
        allEmployees = new JButton("Danh sách Nhân viên");
        allEmployees.setBounds(30, 260, 200, 30);
        allEmployees.setBackground(Color.BLACK);
        allEmployees.setForeground(Color.WHITE);
        allEmployees.setBorderPainted(false);
        allEmployees.setOpaque(true);
        allEmployees.setFocusPainted(false);
        allEmployees.addActionListener(this);
        this.add(allEmployees);

        //Button manager infor section
        manager = new JButton("Thông tin Quản lý");
        manager.setBounds(30, 305, 200, 30);
        manager.setBackground(Color.BLACK);
        manager.setForeground(Color.WHITE);
        manager.setBorderPainted(false);
        manager.setOpaque(true);
        manager.setFocusPainted(false);
        manager.addActionListener(this);
        this.add(manager);

        //Button check out section
        checkout = new JButton("Check out");
        checkout.setBounds(30, 350, 200, 30);
        checkout.setBackground(Color.BLACK);
        checkout.setForeground(Color.WHITE);
        checkout.setBorderPainted(false);
        checkout.setOpaque(true);
        checkout.setFocusPainted(false);
        checkout.addActionListener(this);
        this.add(checkout);

        //Button update checkin section
        update = new JButton("Cập nhật Checkin");
        update.setBounds(30, 395, 200, 30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBorderPainted(false);
        update.setOpaque(true);
        update.setFocusable(false);
        update.addActionListener(this);
        this.add(update);

        //Button list bills section
        bill = new JButton("Hóa đơn");
        bill.setBounds(30, 440, 200, 30);
        bill.setBackground(Color.BLACK);
        bill.setForeground(Color.WHITE);
        bill.setBorderPainted(false);
        bill.setOpaque(true);
        bill.setFocusable(false);
        bill.addActionListener(this);
        this.add(bill);

        //Button back section
        back = new JButton("Back");
        back.setBounds(30, 485, 200, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBorderPainted(false);
        back.setOpaque(true);
        back.setFocusPainted(false);
        back.addActionListener(this);
        this.add(back);

        //Button log out section
        logOut = new JButton("Log Out");
        logOut.setBounds(30, 530, 200, 30);
        logOut.setBackground(Color.BLACK);
        logOut.setForeground(Color.WHITE);
        logOut.setBorderPainted(false);
        logOut.setOpaque(true);
        logOut.setFocusPainted(false);
        logOut.addActionListener(this);
        this.add(logOut);


        //Image section
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/reception.gif"));
        Image i2 = i1.getImage().getScaledInstance(750, 630, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(250, 30, 700, 530);
        this.add(image);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == newCustomer) {
            this.dispose();
            new AddCustomer();
        } else if (event.getSource() == rooms) {
            this.dispose();
            new Room();
        } else if (event.getSource() == allEmployees) {
            this.dispose();
            new EmployeeInfor();
        } else if (event.getSource() == customers) {
            this.dispose();
            new CustomerInfor();
        } else if (event.getSource() == manager) {
            this.dispose();
            new ManagerInfor();
        } else if (event.getSource() == searchRoom) {
            this.dispose();
            new SearchRoom();
        } else if (event.getSource() == update) {
            this.dispose();
            new UpdateCheckin();
        } else if (event.getSource() == roomStatus) {
            this.dispose();
            new UpdateRoom();
        } else if (event.getSource() == checkout) {
            this.dispose();
            new Checkout();
        } else if (event.getSource() == bill) {
            this.dispose();
            new CheckBill();
        } else if (event.getSource() == back) {
            this.dispose();
            new Dashboard();
        } else if (event.getSource() == logOut) {
            this.dispose();
            new HotelManagementSystem();
        }
    }
}
