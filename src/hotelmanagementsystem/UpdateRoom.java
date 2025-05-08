package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateRoom extends JFrame implements ActionListener {
    private final Choice cccd;
    private final JTextField tfroom;
    private final JButton update, check, back;
    private final JTextField tfavail;
//    private final JTextField tfcleaning;

    public UpdateRoom() throws HeadlessException {
        this.getContentPane().setBackground(Color.white);
        this.setSize(980, 450);
        this.setLayout(null);
        this.setTitle("Update Room");

        //text heading
        JLabel text = new JLabel("Update Room Status");
        text.setFont(new Font("Tahoma", Font.BOLD, 25));
        text.setBounds(90, 20, 300, 30);
        text.setForeground(Color.BLACK);
        this.add(text);

        //id section
        JLabel jLabel_id = new JLabel("Customer ID");
        jLabel_id.setFont(new Font("Tahoma", Font.BOLD, 12));
        jLabel_id.setBounds(30, 80, 100, 20);
        jLabel_id.setForeground(Color.BLACK);
        this.add(jLabel_id);

        cccd = new Choice();
        cccd.setBounds(200, 80, 150, 25);
        this.add(cccd);

        try {
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("select * from KhachHang");
            while (rs.next()) {
                cccd.add(rs.getString("CCCD"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //room section
        JLabel jLabel_room = new JLabel("Số phòng");
        jLabel_room.setFont(new Font("Tahoma", Font.BOLD, 12));
        jLabel_room.setBounds(30, 130, 120, 20);
        jLabel_room.setForeground(Color.BLACK);
        this.add(jLabel_room);

        tfroom = new JTextField();
        tfroom.setBounds(200, 130, 150, 25);
        this.add(tfroom);

        //availability section
        JLabel jLabel_avail = new JLabel("Trạng thái");
        jLabel_avail.setFont(new Font("Tahoma", Font.BOLD, 12));
        jLabel_avail.setBounds(30, 180, 120, 20);
        jLabel_avail.setForeground(Color.BLACK);
        this.add(jLabel_avail);

        tfavail = new JTextField();
        tfavail.setBounds(200, 180, 150, 25);
        this.add(tfavail);

//        //cleaning section
//        JLabel jLabel_cleaning = new JLabel("Cleaning Status");
//        jLabel_cleaning.setFont(new Font("Tahoma", Font.BOLD, 12));
//        jLabel_cleaning.setBounds(30, 230, 120, 20);
//        jLabel_cleaning.setForeground(Color.BLACK);
//        this.add(jLabel_cleaning);
//
//        tfcleaning = new JTextField();
//        tfcleaning.setBounds(200, 230, 150, 25);
//        this.add(tfcleaning);


        //check Button
        check = new JButton("CHECK");
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.setBorderPainted(false);
        check.setBounds(30, 300, 100, 30);
        check.addActionListener(this);
        this.add(check);

        //update Button
        update = new JButton("UPDATE");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBorderPainted(false);
        update.setBounds(150, 300, 100, 30);
        update.addActionListener(this);
        this.add(update);

        //back Button
        back = new JButton("BACK");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBorderPainted(false);
        back.setBounds(270, 300, 100, 30);
        back.addActionListener(this);
        this.add(back);

        //image section
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 50, 500, 300);
        this.add(image);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new UpdateRoom();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == check) {
            String id = cccd.getSelectedItem();
            String query = "select * from KhachHang join HoaDon on KhachHang.CCCD=HoaDon.CCCD where KhachHang.CCCD='" + id + "'";
            try {
                Connect c = new Connect();
                ResultSet rs = c.s.executeQuery(query);
                while (rs.next()) {
                    tfroom.setText(rs.getString("SoPhong"));
                }

                ResultSet rs2 =
                        c.s.executeQuery("select * from Phong where SoPhong='" + tfroom.getText() + "'");
                while (rs2.next()) {
                    tfavail.setText(rs2.getString("TrangThai"));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (event.getSource() == update) {
            String id = cccd.getSelectedItem();
            String room = tfroom.getText();
            String available = tfavail.getText();
//            String cleaning = tfcleaning.getText();

            try {
                Connect c = new Connect();
                c.s.executeUpdate("update Phong set TrangThai='" + available + "'where SoPhong='"+room+"'");

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
