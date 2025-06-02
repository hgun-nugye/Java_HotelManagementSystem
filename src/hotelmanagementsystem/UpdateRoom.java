package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateRoom extends JFrame implements ActionListener {
    private final JButton update, check, back;
    private final JTextField tfavail;
    private final JTextField tfbed;
    private final JCheckBox[] amenitiesCheckBoxes;
    private final Choice roomNumber;
    private final JTextField tfprice;

    public UpdateRoom() throws HeadlessException {
        this.getContentPane().setBackground(Color.white);
        this.setSize(980, 500);
        this.setLayout(null);
        this.setTitle("Update Room");

        //text heading
        JLabel text = new JLabel("Cập nhật phòng");
        text.setFont(new Font("Tahoma", Font.BOLD, 25));
        text.setBounds(30, 20, 500, 30);
        text.setForeground(Color.BLACK);
        this.add(text);

        //id section
        JLabel jLabel_id = new JLabel("Số phòng");
        jLabel_id.setFont(new Font("Tahoma", Font.BOLD, 12));
        jLabel_id.setBounds(30, 80, 100, 20);
        jLabel_id.setForeground(Color.BLACK);
        this.add(jLabel_id);

        roomNumber = new Choice();
        roomNumber.setBounds(200, 80, 150, 25);
        this.add(roomNumber);

        try {
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("select * from Phong");
            while (rs.next()) {
                roomNumber.add(rs.getString("SoPhong"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        //bed section
        JLabel jLabel_bed = new JLabel("Số giường");
        jLabel_bed.setFont(new Font("Tahoma", Font.BOLD, 12));
        jLabel_bed.setBounds(30, 130, 120, 20);
        jLabel_bed.setForeground(Color.BLACK);
        this.add(jLabel_bed);

        tfbed = new JTextField();
        tfbed.setBounds(200, 130, 150, 25);
        this.add(tfbed);

        //availability section
        JLabel jLabel_avail = new JLabel("Trạng thái");
        jLabel_avail.setFont(new Font("Tahoma", Font.BOLD, 12));
        jLabel_avail.setBounds(30, 180, 120, 20);
        jLabel_avail.setForeground(Color.BLACK);
        this.add(jLabel_avail);

        tfavail = new JTextField();
        tfavail.setBounds(200, 180, 150, 25);
        this.add(tfavail);

        //price section
        JLabel jLabel_price = new JLabel("Giá phòng");
        jLabel_price.setFont(new Font("Tahoma", Font.BOLD, 12));
        jLabel_price.setBounds(30, 230, 120, 20);
        jLabel_price.setForeground(Color.BLACK);
        this.add(jLabel_price);

        tfprice = new JTextField();
        tfprice.setBounds(200, 230, 150, 25);
        this.add(tfprice);

        // Amenities Section
        JLabel jLabel_type = new JLabel("Tiện nghi");
        jLabel_type.setFont(new Font("Tahoma", Font.BOLD, 12));
        jLabel_type.setBounds(30, 280, 120, 30);
        this.add(jLabel_type);

        JPanel amenitiesPanel = new JPanel();
        amenitiesPanel.setBounds(200, 280, 150, 100);
        amenitiesPanel.setLayout(new GridLayout(0, 1));
        amenitiesPanel.setBackground(Color.white);

        String[] amenitiesOptions = {"Điều hòa", "Wifi", "Tivi", "Tủ lạnh"};
        amenitiesCheckBoxes = new JCheckBox[amenitiesOptions.length];

        for (int i = 0; i < amenitiesOptions.length; i++) {
            amenitiesCheckBoxes[i] = new JCheckBox(amenitiesOptions[i]);
            amenitiesPanel.add(amenitiesCheckBoxes[i]);
        }

        this.add(amenitiesPanel);

        //check Button
        check = new JButton("Check");
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.setBorderPainted(false);
        check.setBounds(30, 400, 100, 30);
        check.addActionListener(this);
        this.add(check);

        //update Button
        update = new JButton("Cập nhật");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBorderPainted(false);
        update.setBounds(150, 400, 100, 30);
        update.addActionListener(this);
        this.add(update);

        //back Button
        back = new JButton("Quay lại");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBorderPainted(false);
        back.setBounds(270, 400, 100, 30);
        back.addActionListener(this);
        this.add(back);

        //image section
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 350, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 50, 500, 350);
        this.add(image);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new   UpdateRoom();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String id = roomNumber.getSelectedItem().toString();
        String query = "select * from Phong where SoPhong='" + id + "'";
        Connect c = new Connect();

        if (event.getSource() == check) {
            try {
                ResultSet rs = c.s.executeQuery(query);
                if (rs.next()) {
                    tfbed.setText(rs.getString("SoGiuong"));
                    tfavail.setText(rs.getString("TrangThai"));
                    tfprice.setText(rs.getString("GiaMacDinh"));

                    //set amenities
                    String amenitiesQuery = "SELECT TienNghi FROM Phong WHERE SoPhong='" + id + "'";
                    ResultSet amenitiesRs = c.s.executeQuery(amenitiesQuery);
                    if (amenitiesRs.next()) {
                        String[] amenitiesArray = amenitiesRs.getString("TienNghi").split(", ");
                        for (JCheckBox checkBox : amenitiesCheckBoxes) {
                            checkBox.setSelected(false); // Đặt lại tất cả checkbox
                            for (String amenity : amenitiesArray) {
                                if (checkBox.getText().equals(amenity)) {
                                    checkBox.setSelected(true);
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (event.getSource() == update) {
            try {
                // Collect selected amenities
                StringBuilder amenities = new StringBuilder();
                for (JCheckBox checkBox : amenitiesCheckBoxes) {
                    if (checkBox.isSelected()) {
                        if (amenities.length() > 0) {
                            amenities.append(", ");
                        }
                        amenities.append(checkBox.getText());
                    }
                }
                c.s.executeUpdate("UPDATE Phong SET TrangThai='" + tfavail.getText() +
                        "', SoGiuong='" + tfbed.getText() +
                        "', GiaMacDinh='" + tfprice.getText() +
                        "', TienNghi='" + amenities +
                        "' WHERE SoPhong='" + id + "'");
                JOptionPane.showMessageDialog(null, "Cập nhật thông tin Phòng thành công!");
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
