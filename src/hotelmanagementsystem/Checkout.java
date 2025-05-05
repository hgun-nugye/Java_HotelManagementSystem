package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Checkout extends JFrame implements ActionListener {
    private final Choice choice_customer;
    private final JLabel jtext_room;
    private final JLabel jtext_checkin;
    private final JLabel jlabel_checkout;
    private final JButton checkout;
    private final JButton back;

    public Checkout() throws HeadlessException {
        this.getContentPane().setBackground(Color.white);
        this.setSize(850, 400);
        this.setLayout(null);
        this.setTitle("Checkout");

        //text heading
        JLabel text = new JLabel("Checkout");
        text.setBounds(100, 20, 100, 30);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.add(text);

        //ID section
        JLabel jLabel_id = new JLabel("Customer ID");
        jLabel_id.setBounds(30, 80, 150, 30);
        jLabel_id.setForeground(Color.BLACK);
        jLabel_id.setBackground(Color.WHITE);
        jLabel_id.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.add(jLabel_id);

        choice_customer = new Choice();
        choice_customer.setBounds(200, 80, 150, 30);
        this.add(choice_customer);

        //tick icon section
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"));
        Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel tick = new JLabel(image);
        tick.setBounds(360, 80, 20, 20);
        this.add(tick);

        //room number section
        JLabel jLabel_room = new JLabel("Room Number");
        jLabel_room.setBounds(30, 130, 150, 30);
        jLabel_room.setForeground(Color.BLACK);
        jLabel_room.setBackground(Color.WHITE);
        jLabel_room.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.add(jLabel_room);

        jtext_room = new JLabel();
        jtext_room.setBounds(200, 130, 150, 30);
        jtext_room.setForeground(Color.BLACK);
        jtext_room.setBackground(Color.WHITE);
        jtext_room.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.add(jtext_room);

        //checkin section
        JLabel jLabel_checkin = new JLabel("Checkin Time");
        jLabel_checkin.setBounds(30, 180, 150, 30);
        jLabel_checkin.setForeground(Color.BLACK);
        jLabel_checkin.setBackground(Color.WHITE);
        jLabel_checkin.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.add(jLabel_checkin);

        jtext_checkin = new JLabel();
        jtext_checkin.setBounds(200, 180, 170, 30);
        jtext_checkin.setForeground(Color.BLACK);
        jtext_checkin.setBackground(Color.WHITE);
        jtext_checkin.setFont(new Font("Tahoma", Font.BOLD, 13));
        this.add(jtext_checkin);

        //checkout section
        JLabel jLabel_checkout = new JLabel("Checkout Time");
        jLabel_checkout.setBounds(30, 230, 150, 30);
        jLabel_checkout.setForeground(Color.BLACK);
        jLabel_checkout.setBackground(Color.WHITE);
        jLabel_checkout.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.add(jLabel_checkout);

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        String checkoutTime = myDateObj.format(myFormatObj);
        jlabel_checkout = new JLabel(checkoutTime);
        jlabel_checkout.setBounds(200, 230, 160, 30);
        jlabel_checkout.setForeground(Color.BLACK);
        jlabel_checkout.setBackground(Color.WHITE);
        jlabel_checkout.setFont(new Font("Tahoma", Font.BOLD, 13));
        this.add(jlabel_checkout);

        try {
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) {
                choice_customer.add(rs.getString("number"));
                jtext_room.setText(rs.getString("room"));
                jtext_checkin.setText(rs.getString("checkintime"));

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        //check out button
        checkout = new JButton("CHECKOUT");
        checkout.setBackground(Color.BLACK);
        checkout.setForeground(Color.WHITE);
        checkout.setBorderPainted(false);
        checkout.setBounds(30, 280, 120, 30);
        checkout.addActionListener(this);
        this.add(checkout);

        //back button
        back = new JButton("BACK");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBorderPainted(false);
        back.setBounds(200, 280, 120, 30);
        back.addActionListener(this);
        this.add(back);

        //image section
        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icons/checkout.gif"));
        Image i4 = i3.getImage().getScaledInstance(700, 400, Image.SCALE_DEFAULT);
        ImageIcon i5 = new ImageIcon(i4);
        JLabel image2 = new JLabel(i5);
        image2.setBounds(450, 50, 600, 300);
        this.add(image2);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Checkout();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == checkout) {
            String query = "delete from customer where number ='" + choice_customer.getSelectedItem() + "'";
            String query2 =
                    "update room set availability='Available' where room_number ='" + jtext_room.getText() +
                            "'";
            try {
                Connect c = new Connect();
                c.s.executeUpdate(query);
                c.s.executeUpdate(query2);

                JOptionPane.showMessageDialog(null, "Checkout Successfully!");

                this.setVisible(false);
                new Reception();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else {
            this.setVisible(false);
            new Reception();
        }

    }
}

