package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateCheckout extends JFrame implements ActionListener {
    private final Choice choice_customer;
    private final JTextField tfroom;
    private final JTextField tfname;
    private final JTextField tfcheckin;
    private final JTextField tfpaid;
    private final JTextField tfpending;
    private final JButton update, check, back;

    public UpdateCheckout() throws HeadlessException {
        this.getContentPane().setBackground(Color.white);
        this.setSize(980, 500);
        this.setLayout(null);
        this.setTitle("Update Checkout");

        //text heading
        JLabel text = new JLabel("Update Status");
        text.setFont(new Font("Tahoma", Font.BOLD, 20));
        text.setBounds(90, 20, 200, 30);
        text.setForeground(Color.BLACK);
        this.add(text);

        //id section
        JLabel jLabel_id = new JLabel("Customer ID");
        jLabel_id.setFont(new Font("Tahoma", Font.BOLD, 12));
        jLabel_id.setBounds(30, 80, 100, 20);
        jLabel_id.setForeground(Color.BLACK);
        this.add(jLabel_id);

        choice_customer = new Choice();
        choice_customer.setBounds(200, 80, 150, 25);
        this.add(choice_customer);

        try {
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("select * from Customer");
            while (rs.next()) {
                choice_customer.add(rs.getString("number"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //room section
        JLabel jLabel_room = new JLabel("Room number");
        jLabel_room.setFont(new Font("Tahoma", Font.BOLD, 12));
        jLabel_room.setBounds(30, 120, 120, 20);
        jLabel_room.setForeground(Color.BLACK);
        this.add(jLabel_room);

        tfroom = new JTextField();
        tfroom.setBounds(200, 120, 150, 25);
        this.add(tfroom);

        //name section
        JLabel jLabel_name = new JLabel("Name");
        jLabel_name.setFont(new Font("Tahoma", Font.BOLD, 12));
        jLabel_name.setBounds(30, 160, 120, 20);
        jLabel_name.setForeground(Color.BLACK);
        this.add(jLabel_name);

        tfname = new JTextField();
        tfname.setBounds(200, 160, 150, 25);
        this.add(tfname);

        //checkin section
        JLabel jLabel_checkin = new JLabel("Check in");
        jLabel_checkin.setFont(new Font("Tahoma", Font.BOLD, 12));
        jLabel_checkin.setBounds(30, 200, 120, 20);
        jLabel_checkin.setForeground(Color.BLACK);
        this.add(jLabel_checkin);

        tfcheckin = new JTextField();
        tfcheckin.setBounds(200, 200, 150, 25);
        this.add(tfcheckin);

        //paid section
        JLabel jLabel_paid = new JLabel("Amount Paid");
        jLabel_paid.setFont(new Font("Tahoma", Font.BOLD, 12));
        jLabel_paid.setBounds(30, 240, 120, 20);
        jLabel_paid.setForeground(Color.BLACK);
        this.add(jLabel_paid);

        tfpaid = new JTextField();
        tfpaid.setBounds(200, 240, 150, 25);
        this.add(tfpaid);

        //pending section
        JLabel jLabel_pending = new JLabel("Amount Pending");
        jLabel_pending.setFont(new Font("Tahoma", Font.BOLD, 12));
        jLabel_pending.setBounds(30, 280, 120, 20);
        jLabel_pending.setForeground(Color.BLACK);
        this.add(jLabel_pending);

        tfpending = new JTextField();
        tfpending.setBounds(200, 280, 150, 25);
        this.add(tfpending);

        //check Button
        check = new JButton("CHECK");
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.setBorderPainted(false);
        check.setBounds(30, 340, 100, 30);
        check.addActionListener(this);
        this.add(check);

        //update Button
        update = new JButton("UPDATE");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBorderPainted(false);
        update.setBounds(150, 340, 100, 30);
        update.addActionListener(this);
        this.add(update);

        //back Button
        back = new JButton("BACK");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBorderPainted(false);
        back.setBounds(270, 340, 100, 30);
        back.addActionListener(this);
        this.add(back);

        //image section
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400, 50, 500, 300);
        this.add(image);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new UpdateCheckout();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == check) {
            String id = choice_customer.getSelectedItem();
            String query = "select * from customer where number='" + id + "'";
            try {
                Connect c = new Connect();
                ResultSet rs = c.s.executeQuery(query);
                while (rs.next()) {
                    tfroom.setText(rs.getString("room"));
                    tfname.setText(rs.getString("name"));
                    tfcheckin.setText(rs.getString("checkintime"));
                    tfpaid.setText(rs.getString("deposit"));
                }

                ResultSet rs2 =
                        c.s.executeQuery("select * from room where room_number='" + tfroom.getText() + "'");
                while (rs2.next()) {
                    String price = rs2.getString("price");
                    int amountPaid = Integer.parseInt(price) - Integer.parseInt(tfpaid.getText());
                    tfpending.setText(("" + amountPaid));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (event.getSource() == update) {
            String number = choice_customer.getSelectedItem();
            String room = tfroom.getText();
            String name = tfname.getText();
            String checkin = tfcheckin.getText();
            String deposit = tfpaid.getText();

            try {
                Connect c = new Connect();
                c.s.executeUpdate("update customer set room='" + room + "', name='" + name + "', " +
                        "checkintime='" + checkin + "', deposit='" + deposit + "'");

                JOptionPane.showMessageDialog(null, "Data Updated Successfully");
                this.setVisible(false);
                new Reception();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (event.getSource() == back) {
            this.setVisible(false);
            new Reception();
        }

    }
}
