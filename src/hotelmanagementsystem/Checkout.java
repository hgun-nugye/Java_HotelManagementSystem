package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Checkout extends JFrame implements ActionListener {
    private final Choice CCCD; // Changed variable name for clarity
    private final JLabel jtext_room;
    private final JLabel jtext_checkin;
    private final JLabel jlabel_checkout;
    private final JButton checkout;
    private final JButton back;

    public Checkout() {
        this.getContentPane().setBackground(Color.white);
        this.setSize(850, 400);
        this.setLayout(null);
        this.setTitle("Checkout");

        // Text Heading
        JLabel text = new JLabel("CHECKOUT");
        text.setBounds(100, 20, 100, 30);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.add(text);

        // ID Section
        JLabel jLabel_id = new JLabel("Số CCCD");
        jLabel_id.setBounds(30, 80, 150, 30);
        jLabel_id.setForeground(Color.BLACK);
        jLabel_id.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.add(jLabel_id);

        // Choice for CCCD
        CCCD = new Choice();
        CCCD.setBounds(200, 80, 150, 30);
        CCCD.setBackground(Color.WHITE);
        CCCD.addItemListener(e -> loadCustomerDetails());
        this.add(CCCD);

        // Tick Icon Section
        ImageIcon tickIcon = new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"));
        Image tickImage = tickIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        JLabel tick = new JLabel(new ImageIcon(tickImage));
        tick.setBounds(360, 80, 20, 20);
        this.add(tick);

        // Room Number Section
        JLabel jLabel_room = new JLabel("Room Number");
        jLabel_room.setBounds(30, 130, 150, 30);
        jLabel_room.setForeground(Color.BLACK);
        jLabel_room.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.add(jLabel_room);

        jtext_room = new JLabel();
        jtext_room.setBounds(200, 130, 150, 30);
        jtext_room.setForeground(Color.BLACK);
        jtext_room.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.add(jtext_room);

        // Check-in Section
        JLabel jLabel_checkin = new JLabel("Checkin Time");
        jLabel_checkin.setBounds(30, 180, 150, 30);
        jLabel_checkin.setForeground(Color.BLACK);
        jLabel_checkin.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.add(jLabel_checkin);

        jtext_checkin = new JLabel();
        jtext_checkin.setBounds(200, 180, 170, 30);
        jtext_checkin.setForeground(Color.BLACK);
        jtext_checkin.setFont(new Font("Tahoma", Font.BOLD, 13));
        this.add(jtext_checkin);

        // Checkout Section
        JLabel jLabel_checkout = new JLabel("Checkout Time");
        jLabel_checkout.setBounds(30, 230, 150, 30);
        jLabel_checkout.setForeground(Color.BLACK);
        jLabel_checkout.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.add(jLabel_checkout);

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String checkoutTime = myDateObj.format(myFormatObj);
        jlabel_checkout = new JLabel(checkoutTime);
        jlabel_checkout.setBounds(200, 230, 160, 30);
        jlabel_checkout.setForeground(Color.BLACK);
        jlabel_checkout.setFont(new Font("Tahoma", Font.BOLD, 13));
        this.add(jlabel_checkout);

        // Load customers into choice
        loadCustomers();

        // Checkout Button
        checkout = new JButton("CHECKOUT");
        checkout.setBackground(Color.BLACK);
        checkout.setForeground(Color.WHITE);
        checkout.setBorderPainted(false);
        checkout.setBounds(30, 280, 120, 30);
        checkout.addActionListener(this);
        this.add(checkout);

        // Back Button
        back = new JButton("BACK");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBorderPainted(false);
        back.setBounds(200, 280, 120, 30);
        back.addActionListener(this);
        this.add(back);

        // Image Section
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/checkout.gif"));
        Image image = imageIcon.getImage().getScaledInstance(700, 400, Image.SCALE_DEFAULT);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(450, 50, 600, 300);
        this.add(imageLabel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }

    private void loadCustomers() {
        try {
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("SELECT * FROM KhachHang");
            while (rs.next()) {
                CCCD.add(rs.getString("CCCD"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerDetails() {
        try {
            Connect c = new Connect();
            String selectedCCCD = CCCD.getSelectedItem();
            String query = "SELECT KhachHang.*, HoaDon.SoPhong, HoaDon.NgayNhan "
                    + "FROM KhachHang "
                    + "JOIN HoaDon ON KhachHang.CCCD = HoaDon.CCCD "
                    + "WHERE KhachHang.CCCD = ?";
            PreparedStatement pstmt = c.c.prepareStatement(query);
            pstmt.setString(1, selectedCCCD);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                jtext_room.setText(rs.getString("SoPhong"));
                jtext_checkin.setText(rs.getString("NgayNhan"));
            } else {
                jtext_room.setText("N/A");
                jtext_checkin.setText("N/A");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Checkout();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == checkout) {
            String selectedCCCD = CCCD.getSelectedItem();
            String queryDelete = "DELETE FROM KhachHang WHERE CCCD = ?";
            String queryUpdateRoom = "UPDATE Phong SET TrangThai='Trống' WHERE SoPhong = ?";
            String queryUpdateBill = "UPDATE HoaDon SET NgayTra=? WHERE SoPhong = ?";

            try {
                Connect c = new Connect();
                PreparedStatement pstmtDelete = c.c.prepareStatement(queryDelete);
                pstmtDelete.setString(1, selectedCCCD);
                pstmtDelete.executeUpdate();

                PreparedStatement pstmtUpdateRoom = c.c.prepareStatement(queryUpdateRoom);
                pstmtUpdateRoom.setString(1, jtext_room.getText());
                pstmtUpdateRoom.executeUpdate();

                PreparedStatement pstmtUpdateBill = c.c.prepareStatement(queryUpdateBill);
                pstmtUpdateBill.setString(1, jlabel_checkout.getText());
                pstmtUpdateBill.setString(2, jtext_room.getText());
                pstmtUpdateBill.executeUpdate();

                JOptionPane.showMessageDialog(null, "Checkout Successfully!");
                this.setVisible(false);
                new Reception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.setVisible(false);
            new Reception();
        }
    }
}