package hotelmanagementsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Room extends JFrame implements ActionListener {
    private final JButton back;
    private JTable jTable;

    public Room() throws HeadlessException {
        this.getContentPane().setBackground(Color.white);
        this.setSize(1050, 600);
        this.setLayout(null);
        this.setTitle("Room");

        //Image section
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500, 0, 600, 600);
        this.add(image);

        //Heading label section
        JLabel jLabel_room_num = new JLabel("Room number");
        jLabel_room_num.setBounds(10, 10, 100, 20);
        this.add(jLabel_room_num);

        JLabel jLabel_availability = new JLabel("Availability");
        jLabel_availability.setBounds(110, 10, 100, 20);
        this.add(jLabel_availability);

        JLabel jLabel_status = new JLabel("Status");
        jLabel_status.setBounds(230, 10, 100, 20);
        this.add(jLabel_status);

        JLabel jLabel_price = new JLabel("Price");
        jLabel_price.setBounds(330, 10, 100, 20);
        this.add(jLabel_price);

        JLabel jLabel_bed_type = new JLabel("Bed Type");
        jLabel_bed_type.setBounds(420, 10, 100, 20);
        this.add(jLabel_bed_type);

        //Table section
        jTable = new JTable();
        jTable.setBounds(0, 40, 500, 400);
        this.add(jTable);

        try {
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("select * from room");
            jTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //back button section
        back=new JButton("BACK");
        back.setBounds(280,500,120,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBorderPainted(false);
        back.addActionListener(this);
        back.setOpaque(true);
        this.add(back);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Room();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.setVisible(false);
        new Reception();
    }
}
