package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddCustomer extends JFrame implements ActionListener {
    private final JTextField jTextFieldName;
    private final JTextField jTextFieldCCCD;
    private final JRadioButton rmale, rfemale;
    private final JTextField jTextFieldCountry;
    private final JTextField jTextFieldDeposit;
    private final JTextField jTextFieldPhone;
    private final JTextField jTextFieldEmail;
    private final JLabel jLabelCheckinTime;
    private final Choice choiceRoom;

    public AddCustomer() {
        setLayout(null);
        setSize(800, 550);
        getContentPane().setBackground(Color.WHITE);
        setTitle("Add Customer");

        // Set heading
        JLabel text = new JLabel("KHÁCH HÀNG");
        text.setBounds(100, 20, 300, 30);
        text.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(text);

        // ID Customer
        JLabel jLabelCCCD = new JLabel("Số CCCD");
        jLabelCCCD.setBounds(35, 80, 100, 20);
        jLabelCCCD.setFont(new Font("Tahoma", Font.BOLD, 17));
        add(jLabelCCCD);

        jTextFieldCCCD = new JTextField();
        jTextFieldCCCD.setBounds(200, 80, 150, 25);
        add(jTextFieldCCCD);

        // Name Customer
        JLabel jLabelName = new JLabel("Tên KH");
        jLabelName.setBounds(35, 120, 100, 20);
        jLabelName.setFont(new Font("Tahoma", Font.BOLD, 17));
        add(jLabelName);

        jTextFieldName = new JTextField();
        jTextFieldName.setBounds(200, 120, 150, 25);
        add(jTextFieldName);

        // Gender Customer
        JLabel jLabelGender = new JLabel("Giới tính");
        jLabelGender.setBounds(35, 160, 100, 20);
        jLabelGender.setFont(new Font("Tahoma", Font.BOLD, 17));
        add(jLabelGender);

        rmale = new JRadioButton("Nam");
        rmale.setBackground(Color.WHITE);
        rmale.setBounds(200, 160, 80, 25);
        add(rmale);

        rfemale = new JRadioButton("Nữ");
        rfemale.setBackground(Color.WHITE);
        rfemale.setBounds(290, 160, 80, 25);
        add(rfemale);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rmale);
        genderGroup.add(rfemale);

        // Country Customer
        JLabel jLabelCountry = new JLabel("Quốc tịch");
        jLabelCountry.setBounds(35, 200, 100, 20);
        jLabelCountry.setFont(new Font("Tahoma", Font.BOLD, 17));
        add(jLabelCountry);

        jTextFieldCountry = new JTextField();
        jTextFieldCountry.setBounds(200, 200, 150, 25);
        add(jTextFieldCountry);

        // Email Customer
        JLabel jLabelEmail = new JLabel("Email");
        jLabelEmail.setBounds(35, 240, 100, 20);
        jLabelEmail.setFont(new Font("Tahoma", Font.BOLD, 17));
        add(jLabelEmail);

        jTextFieldEmail = new JTextField();
        jTextFieldEmail.setBounds(200, 240, 150, 25);
        add(jTextFieldEmail);

        // Phone number Customer
        JLabel jLabelPhone = new JLabel("SĐT");
        jLabelPhone.setBounds(35, 280, 100, 20);
        jLabelPhone.setFont(new Font("Tahoma", Font.BOLD, 17));
        add(jLabelPhone);

        jTextFieldPhone = new JTextField();
        jTextFieldPhone.setBounds(200, 280, 150, 25);
        add(jTextFieldPhone);

        // Room Customer
        JLabel jLabelRoom = new JLabel("Số phòng");
        jLabelRoom.setBounds(35, 320, 165, 20);
        jLabelRoom.setFont(new Font("Tahoma", Font.BOLD, 17));
        add(jLabelRoom);

        choiceRoom = new Choice();
        try {
            Connect conn = new Connect();
            String query = "SELECT * FROM Phong WHERE TrangThai=N'Trống'";
            ResultSet rs = conn.s.executeQuery(query);
            while (rs.next()) {
                choiceRoom.add(rs.getString("SoPhong"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        choiceRoom.setBounds(200, 320, 150, 25);
        add(choiceRoom);

        // Time Customer
        JLabel jLabelTime = new JLabel("Checkin");
        jLabelTime.setBounds(35, 360, 165, 20);
        jLabelTime.setFont(new Font("Tahoma", Font.BOLD, 17));
        add(jLabelTime);

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String checkinTime = myDateObj.format(myFormatObj);

        jLabelCheckinTime = new JLabel(checkinTime);
        jLabelCheckinTime.setBounds(200, 360, 200, 20);
        jLabelCheckinTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(jLabelCheckinTime);

        // Deposit Customer
        JLabel jLabelDeposit = new JLabel("Đưa trước");
        jLabelDeposit.setBounds(35, 400, 100, 20);
        jLabelDeposit.setFont(new Font("Tahoma", Font.BOLD, 17));
        add(jLabelDeposit);

        jTextFieldDeposit = new JTextField();
        jTextFieldDeposit.setBounds(200, 400, 150, 25);
        add(jTextFieldDeposit);

        // Add Button
        JButton addButton = new JButton("ADD");
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.WHITE);
        addButton.setBorderPainted(false);
        addButton.setBounds(50, 460, 120, 25);
        addButton.addActionListener(this);
        add(addButton);

        // Back Button
        JButton backButton = new JButton("BACK");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setBorderPainted(false);
        backButton.setBounds(200, 460, 120, 25);
        backButton.addActionListener(this);
        add(backButton);

        // Image Section
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/customer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(470, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(300, 50, 600, 400);
        add(image);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        new AddCustomer();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("ADD")) {
            String cccd = jTextFieldCCCD.getText();
            String hoTen = jTextFieldName.getText();
            String gioiTinh = rmale.isSelected() ? "Nam" : rfemale.isSelected() ? "Nữ" : null;
            String quocTich = jTextFieldCountry.getText();
            String email = jTextFieldEmail.getText();
            String sdt = jTextFieldPhone.getText();
            String soPhong = choiceRoom.getSelectedItem();
            String checkin = jLabelCheckinTime.getText();
            String duaTruoc = jTextFieldDeposit.getText();

            try {
                String query = "INSERT INTO KhachHang VALUES (?, ?, ?, ?, ?, ?, ?)";
                String query2 = "UPDATE Phong SET TrangThai=N'Đã đặt' WHERE SoPhong=?";
                String query3 = "INSERT INTO HoaDon VALUES (?, ?, NULL, N'Chưa Thanh toán')";

                Connect conn = new Connect();
                PreparedStatement pstmt = conn.c.prepareStatement(query);
                pstmt.setString(1, cccd);
                pstmt.setString(2, hoTen);
                pstmt.setString(3, gioiTinh);
                pstmt.setString(4, quocTich);
                pstmt.setString(5, email);
                pstmt.setString(6, sdt);
                pstmt.setString(7, duaTruoc);
                pstmt.executeUpdate();

                PreparedStatement pstmt2 = conn.c.prepareStatement(query2);
                pstmt2.setString(1, soPhong);
                pstmt2.executeUpdate();

                PreparedStatement pstmt3 = conn.c.prepareStatement(query3);
                pstmt3.setString(1, soPhong);
                pstmt3.setString(2, checkin);

                JOptionPane.showMessageDialog(null, "New Customer Added Successfully");
                setVisible(false);
                new Reception();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                e.printStackTrace();
            }
        } else if (event.getActionCommand().equals("BACK")) {
            setVisible(false);
            new Reception();
        }
    }
}