package hotelmanagementsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CustomerInfor extends JFrame implements ActionListener {
    private final JButton back;
    private JTable jTable;

    public CustomerInfor() throws HeadlessException {
        this.getContentPane().setBackground(Color.white);
        this.setSize(1000, 600);
        this.setLayout(null);
        this.setTitle("Customer Information");

        //Heading label section
        JLabel jLabel_doc_type = new JLabel("Document Type");
        jLabel_doc_type.setBounds(60, 10, 100, 20);
        this.add(jLabel_doc_type);

        JLabel jLabel_cnum = new JLabel("Number");
        jLabel_cnum.setBounds(200, 10, 100, 20);
        this.add(jLabel_cnum);

        JLabel jLabel_cname = new JLabel("Name");
        jLabel_cname.setBounds(300, 10, 100, 20);
        this.add(jLabel_cname);

        JLabel jLabel_cgender = new JLabel("Gender");
        jLabel_cgender.setBounds(420, 10, 100, 20);
        this.add(jLabel_cgender);

        JLabel jLabel_ccountry = new JLabel("Country");
        jLabel_ccountry.setBounds(520, 10, 100, 20);
        this.add(jLabel_ccountry);

        JLabel jLabel_croom = new JLabel("Room Number");
        jLabel_croom.setBounds(620, 10, 100, 20);
        this.add(jLabel_croom);

        JLabel jLabel_ccheckin = new JLabel("Checkin Time");
        jLabel_ccheckin.setBounds(750, 10, 100, 20);
        this.add(jLabel_ccheckin);

        JLabel jLabel_cdeposit = new JLabel("Deposit");
        jLabel_cdeposit.setBounds(870, 10, 100, 20);
        this.add(jLabel_cdeposit);

        //Table section
        jTable = new JTable();
        jTable.setBounds(50, 40, 900, 400);
        this.add(jTable);

        try {
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("select * from customer");
            jTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //back button section
        back = new JButton("BACK");
        back.setBounds(420, 500, 120, 30);
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
        new CustomerInfor();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.setVisible(false);
        new Reception();
    }
}
