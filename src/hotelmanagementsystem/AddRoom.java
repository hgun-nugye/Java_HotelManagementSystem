package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRoom extends JFrame implements ActionListener {

    private final JButton add;
    private final JButton cancel;
    private final JTextField jTextField_price;
    private final JTextField jTextField_room_num;
    private final JComboBox<String> availableCombo;
    private final JCheckBox[] amenitiesCheckBoxes;
    private final JTextField jTextField_bed;

    public AddRoom() {
        this.setLayout(null);
        this.setTitle("Add Room");
        this.setSize(900, 540);
        this.getContentPane().setBackground(Color.WHITE);

        // Heading Section
        JLabel heading = new JLabel("THÊM PHÒNG");
        heading.setFont(new Font("Tahoma", Font.BOLD, 18));
        heading.setBounds(150, 20, 200, 20);
        this.add(heading);

        // Room Section
        JLabel jLabel_room_num = new JLabel("Số phòng");
        jLabel_room_num.setFont(new Font("Tahoma", Font.BOLD, 15));
        jLabel_room_num.setBounds(60, 80, 120, 30);
        this.add(jLabel_room_num);

        jTextField_room_num = new JTextField();
        jTextField_room_num.setBounds(200, 80, 200, 30);
        this.add(jTextField_room_num);

        // Status Room Section
        JLabel jLabel_avaliable = new JLabel("Trạng thái");
        jLabel_avaliable.setFont(new Font("Tahoma", Font.BOLD, 15));
        jLabel_avaliable.setBounds(60, 130, 120, 30);
        this.add(jLabel_avaliable);

        String availableOptions[] = {"Trống", "Đã đặt", "Đang dọn dẹp"};
        availableCombo = new JComboBox<>(availableOptions);
        availableCombo.setBounds(200, 130, 200, 30);
        availableCombo.setBackground(Color.white);
        this.add(availableCombo);

        // Type Bed Section
        JLabel jLabel_bed = new JLabel("Số giường");
        jLabel_bed.setFont(new Font("Tahoma", Font.BOLD, 15));
        jLabel_bed.setBounds(60, 180, 120, 30);
        this.add(jLabel_bed);

        jTextField_bed = new JTextField();
        jTextField_bed.setBounds(200, 180, 200, 30);
        this.add(jTextField_bed);

        // Price Section
        JLabel jLabel_price = new JLabel("Giá phòng");
        jLabel_price.setFont(new Font("Tahoma", Font.BOLD, 15));
        jLabel_price.setBounds(60, 230, 120, 30);
        this.add(jLabel_price);

        jTextField_price = new JTextField();
        jTextField_price.setBounds(200, 230, 200, 30);
        this.add(jTextField_price);

        // Amenities Section
        JLabel jLabel_type = new JLabel("Tiện nghi");
        jLabel_type.setFont(new Font("Tahoma", Font.BOLD, 15));
        jLabel_type.setBounds(60, 280, 120, 30);
        this.add(jLabel_type);

        JPanel amenitiesPanel = new JPanel();
        amenitiesPanel.setBounds(200, 280, 200, 100);
        amenitiesPanel.setLayout(new GridLayout(0, 1));
        amenitiesPanel.setBackground(Color.white);

        String[] amenitiesOptions = {"Điều hòa", "Wifi", "Tivi", "Tủ lạnh"};
        amenitiesCheckBoxes = new JCheckBox[amenitiesOptions.length];

        for (int i = 0; i < amenitiesOptions.length; i++) {
            amenitiesCheckBoxes[i] = new JCheckBox(amenitiesOptions[i]);
            amenitiesPanel.add(amenitiesCheckBoxes[i]);
        }

        this.add(amenitiesPanel);

        // Add Button
        add = new JButton("ADD");
        add.setForeground(Color.WHITE);
        add.setBackground(Color.BLACK);
        add.setBorderPainted(false);
        add.setBounds(60, 400, 130, 30);
        add.addActionListener(this);
        this.add(add);

        // Cancel Button
        cancel = new JButton("CANCEL");
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.BLACK);
        cancel.setBorderPainted(false);
        cancel.setBounds(230, 400, 130, 30);
        cancel.addActionListener(this);
        this.add(cancel);

        // Image Right
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/room.gif"));
        Image i2 = i1.getImage().getScaledInstance(700, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(450, 80, 700, 300); // Sửa kích thước cho phù hợp
        this.add(image);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

    public static void main(String[] args) {
        new AddRoom();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String room = jTextField_room_num.getText();
            String available = (String) availableCombo.getSelectedItem();
            String type = jTextField_bed.getText();
            String price = jTextField_price.getText();

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

            try {
                Connect conn = new Connect();
                String query =
                        "INSERT INTO Phong (SoPhong, SoGiuong, TienNghi, TrangThai, GiaMacDinh) VALUES ('" +
                                room + "', '" + type + "', '" + amenities + "', '" + available + "', '" + price + "')";
                conn.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "New Room Added Successfully!");

                this.setVisible(false);
                new Reception();

            } catch (Exception exception) {
                exception.printStackTrace();
            }

        } else {
            this.setVisible(false);
            new Dashboard();
        }
    }
}