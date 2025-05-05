package hotelmanagementsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Department extends JFrame implements ActionListener {
    private final JButton back;
    private JTable jTable;

    public Department() throws HeadlessException {
        this.getContentPane().setBackground(Color.white);
        this.setSize(700, 480);
        this.setLayout(null);
        this.setTitle("Department");

        //Image section
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/department.gif"));
        Image i2 = i1.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500, 0, 300, 600);
        this.add(image);

        //Heading label section
        JLabel jLabel_room_num = new JLabel("Department");
        jLabel_room_num.setBounds(120, 10, 100, 20);
        this.add(jLabel_room_num);

        JLabel jLabel_availability = new JLabel("Budget");
        jLabel_availability.setBounds(330, 10, 100, 20);
        this.add(jLabel_availability);

        //Table section
        jTable = new JTable();
        jTable.setBounds(50, 40, 400, 350);
        this.add(jTable);

        try {
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("select * from department");
            jTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //back button section
        back = new JButton("BACK");
        back.setBounds(280, 400, 120, 30);
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
        new Department();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.setVisible(false);
        new Reception();

    }
}
