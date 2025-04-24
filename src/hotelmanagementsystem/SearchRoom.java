package hotelmanagementsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class SearchRoom extends JFrame implements ActionListener {
    private final JButton back;
    private final JButton submit;
    private JTable jTable;
    private JComboBox bedType;
    private JCheckBox available;

    public SearchRoom() throws HeadlessException {
        this.getContentPane().setBackground(Color.white);
        this.setSize(1000, 600);
        this.setLayout(null);
        this.setTitle("Search Room");

        JLabel jLabel_text = new JLabel("Search for room");
        jLabel_text.setFont(new Font("Tahoma", Font.BOLD, 20));
        jLabel_text.setBounds(400, 30, 200, 30);
        this.add(jLabel_text);

        JLabel jLabel_bed = new JLabel("Bed Type");
        jLabel_bed.setBounds(50, 100, 100, 20);
        this.add(jLabel_bed);

        bedType = new JComboBox(new String[]{"Single Bed", "Double Bed"});
        bedType.setBounds(150, 100, 150, 25);
        bedType.setBackground(Color.WHITE);
        this.add(bedType);

        available = new JCheckBox("Only Display Available");
        available.setBounds(550, 100, 300, 25);
        available.setBackground(Color.WHITE);
        this.add(available);

        //Heading label section
        JLabel jLabel_room_num = new JLabel("Room number");
        jLabel_room_num.setBounds(100, 160, 100, 20);
        this.add(jLabel_room_num);

        JLabel jLabel_availability = new JLabel("Availability");
        jLabel_availability.setBounds(320, 160, 100, 20);
        this.add(jLabel_availability);

        JLabel jLabel_status = new JLabel("Cleaning Status");
        jLabel_status.setBounds(500, 160, 100, 20);
        this.add(jLabel_status);

        JLabel jLabel_price = new JLabel("Price");
        jLabel_price.setBounds(720, 160, 100, 20);
        this.add(jLabel_price);

        JLabel jLabel_bed_type = new JLabel("Bed Type");
        jLabel_bed_type.setBounds(920, 160, 100, 20);
        this.add(jLabel_bed_type);

        //Table section
        jTable = new JTable();
        jTable.setBounds(50, 190, 1000, 300);
        this.add(jTable);

        try {
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("select * from room");
            jTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //submit button section
        submit = new JButton("SUBMIT");
        submit.setBounds(300, 520, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBorderPainted(false);
        submit.addActionListener(this);
        submit.setOpaque(true);
        this.add(submit);

        //back button section
        back = new JButton("BACK");
        back.setBounds(500, 520, 120, 30);
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
        new SearchRoom();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("SUBMIT")) {
            try {
                String query1 = "select * from room where bed_type='" + bedType.getSelectedItem() + "'";
                String query2 = "select * from room where availability='Available' and bed_type='" + bedType.getSelectedItem() + "'";

                Connect c = new Connect();
                ResultSet rs;

                if (available.isSelected()) {
                    rs = c.s.executeQuery(query2);
                } else {
                    rs = c.s.executeQuery(query1);
                }
                jTable.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            this.setVisible(false);
            new Reception();
        }
    }
}
