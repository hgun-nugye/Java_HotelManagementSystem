package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateCheckin extends JFrame implements ActionListener {
    private final Choice CCCD;
    private final JTextField tfroom;
    private final JTextField tfname;
    private final JLabel jlbcheckin;
    private final JTextField tfpaid;
    private final JTextField tfpending;
    private final JButton update, check, back;
    private final JTextField textmaHD;

    public UpdateCheckin() {
        this.getContentPane().setBackground(Color.white);
        this.setSize(950, 500);
        this.setLayout(null);
        this.setTitle("Update Checkout");

        // Text heading
        JLabel text = new JLabel("CẬP NHẬT CHECKIN");
        text.setFont(new Font("Tahoma", Font.BOLD, 20));
        text.setBounds(50, 20, 250, 30);
        text.setForeground(Color.BLACK);
        this.add(text);

        // Customer ID section
        JLabel jLabel_id = new JLabel("CCCD");
        jLabel_id.setFont(new Font("Tahoma", Font.BOLD, 12));
        jLabel_id.setBounds(30, 80, 100, 20);
        jLabel_id.setForeground(Color.BLACK);
        this.add(jLabel_id);

        CCCD = new Choice();
        CCCD.setBounds(200, 80, 150, 25);
        this.add(CCCD);

        // Load customer IDs into the choice
        try {
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("SELECT * FROM KhachHang JOIN HoaDon WHERE HoaDon.CCCD=KhachHang.CCCD and HoaDon.TrangThai=N'Chưa thanh toán'");
            while (rs.next()) {
                CCCD.add(rs.getString("CCCD"));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Improved error handling
        }

        // MaHD section
        JLabel maHD = new JLabel("Mã Hóa đơn");
        maHD.setFont(new Font("Tahoma", Font.BOLD, 12));
        maHD.setBounds(30, 120, 120, 20);
        maHD.setForeground(Color.BLACK);
        this.add(maHD);

        textmaHD = new JTextField();
        textmaHD.setBounds(200, 120, 150, 25);
        this.add(textmaHD);

        // Room section
        JLabel jLabel_room = new JLabel("Số phòng");
        jLabel_room.setFont(new Font("Tahoma", Font.BOLD, 12));
        jLabel_room.setBounds(30, 160, 120, 20);
        jLabel_room.setForeground(Color.BLACK);
        this.add(jLabel_room);

        tfroom = new JTextField();
        tfroom.setBounds(200, 160, 150, 25);
        this.add(tfroom);

        // Name section
        JLabel jLabel_name = new JLabel("Tên");
        jLabel_name.setFont(new Font("Tahoma", Font.BOLD, 12));
        jLabel_name.setBounds(30, 200, 120, 20);
        jLabel_name.setForeground(Color.BLACK);
        this.add(jLabel_name);

        tfname = new JTextField();
        tfname.setBounds(200, 200, 150, 25);
        this.add(tfname);

        // Check-in section
        JLabel jLabel_checkin = new JLabel("Nhận phòng");
        jLabel_checkin.setFont(new Font("Tahoma", Font.BOLD, 12));
        jLabel_checkin.setBounds(30, 240, 120, 20);
        jLabel_checkin.setForeground(Color.BLACK);
        this.add(jLabel_checkin);

        jlbcheckin = new JLabel();
        jlbcheckin.setBounds(200, 240, 150, 25);
        this.add(jlbcheckin);

        // Paid section
        JLabel jLabel_paid = new JLabel("Đưa trước");
        jLabel_paid.setFont(new Font("Tahoma", Font.BOLD, 12));
        jLabel_paid.setBounds(30, 280, 120, 20);
        jLabel_paid.setForeground(Color.BLACK);
        this.add(jLabel_paid);

        tfpaid = new JTextField();
        tfpaid.setBounds(200, 280, 150, 25);
        this.add(tfpaid);

        // Pending section
        JLabel jLabel_pending = new JLabel("Còn lại");
        jLabel_pending.setFont(new Font("Tahoma", Font.BOLD, 12));
        jLabel_pending.setBounds(30, 320, 120, 20);
        jLabel_pending.setForeground(Color.BLACK);
        this.add(jLabel_pending);

        tfpending = new JTextField();
        tfpending.setBounds(200, 320, 150, 25);
        this.add(tfpending);

        // Check Button
        check = new JButton("CHECK");
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.setBorderPainted(false);
        check.setBounds(30, 370, 100, 30);
        check.addActionListener(this);
        this.add(check);

        // Update Button
        update = new JButton("UPDATE");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBorderPainted(false);
        update.setBounds(150, 370, 100, 30);
        update.addActionListener(this);
        this.add(update);

        // Back Button
        back = new JButton("BACK");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBorderPainted(false);
        back.setBounds(270, 370, 100, 30);
        back.addActionListener(this);
        this.add(back);

        // Image section
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400, 50, 500, 320);
        this.add(image);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == check) {
            String cccd = CCCD.getSelectedItem();
            String query = "SELECT * FROM KhachHang JOIN HoaDon ON HoaDon.CCCD = KhachHang.CCCD WHERE KhachHang.CCCD='" + cccd + "' AND HoaDon.TrangThai=N'Chưa thanh toán'";
            try {
                Connect c = new Connect();
                ResultSet rs = c.s.executeQuery(query);
                if (rs.next()) {
                    tfroom.setText(rs.getString("HoaDon.SoPhong"));
                    textmaHD.setText(rs.getString("HoaDon.MaHD"));
                    tfname.setText(rs.getString("KhachHang.HoTen"));
                    jlbcheckin.setText(rs.getString("HoaDon.NgayNhan"));
                    tfpaid.setText(rs.getString("KhachHang.DuaTruoc"));
                    ResultSet rs2 = c.s.executeQuery("SELECT * FROM Phong WHERE SoPhong='" + tfroom.getText() + "'");
                    if (rs2.next()) {
                        String price = rs2.getString("GiaMacDinh");
                        int amountPaid = Integer.parseInt(price) - Integer.parseInt(tfpaid.getText());
                        tfpending.setText(String.valueOf(amountPaid));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == update) {
            String cccd = CCCD.getSelectedItem();
            String room = tfroom.getText();
            String name = tfname.getText();
            String checkin = jlbcheckin.getText();
            String deposit = tfpaid.getText();

            try {
                Connect c = new Connect();
                c.s.executeUpdate("UPDATE KhachHang SET HoTen='" + name + "', DuaTruoc='" + deposit + "' WHERE CCCD='" + cccd + "'");
                c.s.executeUpdate("UPDATE HoaDon SET NgayNhan='" + checkin + "' WHERE CCCD='" + cccd + "'");
                c.s.executeUpdate("UPDATE Phong SET SoPhong='" + room + "' WHERE SoPhong='" + room + "'");

                JOptionPane.showMessageDialog(null, "Data Updated Successfully");
                this.setVisible(false);
                new Reception();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == back) {
            this.setVisible(false);
            new Reception();
        }
    }
}