package hotelmanagementsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ManagerInfor extends JFrame implements ActionListener {
    private final JButton back;
    private JTable jTable;

    public ManagerInfor() throws HeadlessException {
        this.getContentPane().setBackground(Color.white);
        this.setSize(1000, 600);
        this.setLayout(null);
        this.setTitle("Manager Information");

        //Heading label section
        JLabel jLabel_ename = new JLabel("Name");
        jLabel_ename.setBounds(80, 10, 100, 20);
        this.add(jLabel_ename);

        JLabel jLabel_eage = new JLabel("Age");
        jLabel_eage.setBounds(190, 10, 100, 20);
        this.add(jLabel_eage);

        JLabel jLabel_egender = new JLabel("Gender");
        jLabel_egender.setBounds(300, 10, 100, 20);
        this.add(jLabel_egender);

        JLabel jLabel_job = new JLabel("Job");
        jLabel_job.setBounds(410, 10, 100, 20);
        this.add(jLabel_job);

        JLabel jLabel_salary = new JLabel("Salary");
        jLabel_salary.setBounds(530, 10, 100, 20);
        this.add(jLabel_salary);

        JLabel jLabel_ephone = new JLabel("Phone");
        jLabel_ephone.setBounds(640, 10, 100, 20);
        this.add(jLabel_ephone);

        JLabel jLabel_email = new JLabel("Email");
        jLabel_email.setBounds(760, 10, 100, 20);
        this.add(jLabel_email);

        JLabel jLabel_ID = new JLabel("ID");
        jLabel_ID.setBounds(870, 10, 100, 20);
        this.add(jLabel_ID);

        //Table section
        jTable = new JTable();
        jTable.setBounds(50, 40, 900, 400);
        this.add(jTable);

        try {
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("select * from employee where job= 'Manager'");
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
        new ManagerInfor();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.setVisible(false);
        new Reception();
    }
}
