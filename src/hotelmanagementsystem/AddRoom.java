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
    private final JComboBox typeCombo;
    private final JComboBox availableCombo;
    private final JComboBox cleanCombo;

    public AddRoom() {
        this.setLayout(null);
        this.setTitle("Add Room");
        this.setSize(940, 470);
        this.getContentPane().setBackground(Color.WHITE);

        //heading section
        JLabel heading = new JLabel("ADD ROOM");
        heading.setFont(new Font("Tahoma", Font.BOLD, 18));
        heading.setBounds(150, 20, 200, 20);
        this.add(heading);

        //room section
        JLabel jLabel_room_num = new JLabel("Room Number");
        jLabel_room_num.setFont(new Font("Tahoma", Font.BOLD, 15));
        jLabel_room_num.setBounds(60, 80, 120, 30);
        this.add(jLabel_room_num);

        jTextField_room_num = new JTextField();
        jTextField_room_num.setBounds(200, 80, 200, 30);
        this.add(jTextField_room_num);

        //status room section
        JLabel jLabel_avaliable = new JLabel("Available");
        jLabel_avaliable.setFont(new Font("Tahoma", Font.BOLD, 15));
        jLabel_avaliable.setBounds(60, 130, 120, 30);
        this.add(jLabel_avaliable);

        String availableOptions[] = {"Available", "Occupied"};
        availableCombo = new JComboBox(availableOptions);
        availableCombo.setBounds(200, 130, 200, 30);
        availableCombo.setBackground(Color.white);
        this.add(availableCombo);

        //cleaning room section
        JLabel jLabel_clean = new JLabel("Cleaning Status");
        jLabel_clean.setFont(new Font("Tahoma", Font.BOLD, 15));
        jLabel_clean.setBounds(60, 180, 120, 30);
        this.add(jLabel_clean);

        String cleanOptions[] = {"Cleaned", "Dirty"};
        cleanCombo = new JComboBox(cleanOptions);
        cleanCombo.setBounds(200, 180, 200, 30);
        cleanCombo.setBackground(Color.white);
        this.add(cleanCombo);

        //price section
        JLabel jLabel_price = new JLabel("Price");
        jLabel_price.setFont(new Font("Tahoma", Font.BOLD, 15));
        jLabel_price.setBounds(60, 230, 120, 30);
        this.add(jLabel_price);

        jTextField_price = new JTextField();
        jTextField_price.setBounds(200, 230, 200, 30);
        this.add(jTextField_price);

        //type room section
        JLabel jLabel_type = new JLabel("Type Room");
        jLabel_type.setFont(new Font("Tahoma", Font.BOLD, 15));
        jLabel_type.setBounds(60, 280, 120, 30);
        this.add(jLabel_type);

        String typeOptions[] = {"Single Bed", "Double Bed"};
        typeCombo = new JComboBox(typeOptions);
        typeCombo.setBounds(200, 280, 200, 30);
        typeCombo.setBackground(Color.white);
        this.add(typeCombo);

        //add button
        add = new JButton("ADD ROOM");
        add.setForeground(Color.WHITE);
        add.setBackground(Color.BLACK);
        add.setBorderPainted(false);
        add.setBounds(60, 350, 130, 30);
        add.addActionListener(this);
        this.add(add);

        //cancel button
        cancel = new JButton("CANCEL");
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.BLACK);
        cancel.setBorderPainted(false);
        cancel.setBounds(230, 350, 130, 30);
        cancel.addActionListener(this);
        this.add(cancel);

        //image right
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/twelve.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(450, 30, 450, 300);
        this.add(image);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new AddRoom();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String room = jTextField_room_num.getText();
            String available = (String) availableCombo.getSelectedItem();
            String status = (String) cleanCombo.getSelectedItem();
            String price = jTextField_price.getText();
            String type = (String) typeCombo.getSelectedItem();
            try {
                Connect conn = new Connect();
                String query =
                        "insert into room values('" + room + "','" + available + "','" + status + "', '" + price + "', '" + type + "')";
                conn.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "New Room Added Successdully!");

                this.setVisible(false);

            } catch (Exception exception) {
                exception.printStackTrace();
            }

        } else {
            this.setVisible(false);
        }
    }
}
