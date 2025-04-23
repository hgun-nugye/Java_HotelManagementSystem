package hotelmanagementsystem;

import javax.swing.*;
import javax.swing.tree.AbstractLayoutCache;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.ResultSet;

public class AddCustomer extends JFrame implements ActionListener {
    private final JTextField jTextField_name;
    private final JComboBox jcomboBoxID;
    private final JTextField jTextField_number;
    private final JRadioButton rmale, rfemale;
    private final JTextField jTextField_country;
    private final JLabel jLabel_checkinTime;
    private final JTextField jTextField_deposit;
    private final JButton add;
    private final JButton back;
    private Choice choiceRoom;

    public AddCustomer() throws HeadlessException {
        this.setLayout(null);
        this.setSize(800, 550);
        this.getContentPane().setBackground(Color.WHITE);
        this.setTitle("Add Customer");

        //set heading
        JLabel text = new JLabel("NEW CUSTOMER FORM");
        text.setBounds(100, 20, 300, 30);
        text.setFont(new Font("Tahoma", Font.BOLD, 24));
        this.add(text);

        //Id Customer
        JLabel jLabel_ID = new JLabel("ID");
        jLabel_ID.setBounds(35, 80, 100, 20);
        jLabel_ID.setFont(new Font("Tahoma", Font.BOLD, 17));
        this.add(jLabel_ID);

        String options[] = {"ID Card", "Passport", "Driving License", "Ration Card"};
        jcomboBoxID = new JComboBox(options);
        jcomboBoxID.setBounds(200, 80, 150, 25);
        jcomboBoxID.setBackground(Color.WHITE);
        this.add(jcomboBoxID);

        //Number phone Customer
        JLabel jLabel_number = new JLabel("Number");
        jLabel_number.setBounds(35, 120, 100, 20);
        jLabel_number.setFont(new Font("Tahoma", Font.BOLD, 17));
        this.add(jLabel_number);

        jTextField_number = new JTextField();
        jTextField_number.setBounds(200, 120, 150, 25);
        this.add(jTextField_number);


        //Name Customer
        JLabel jLabel_name = new JLabel("Name");
        jLabel_name.setBounds(35, 160, 100, 20);
        jLabel_name.setFont(new Font("Tahoma", Font.BOLD, 17));
        this.add(jLabel_name);

        jTextField_name = new JTextField();
        jTextField_name.setBounds(200, 160, 150, 25);
        this.add(jTextField_name);

        //gender Customer
        JLabel jLabel_gender = new JLabel("Gender");
        jLabel_gender.setBounds(35, 200, 100, 20);
        jLabel_gender.setFont(new Font("Tahoma", Font.BOLD, 17));
        this.add(jLabel_gender);

        rmale = new JRadioButton("Male");
        rmale.setBackground(Color.WHITE);
        rmale.setBounds(200, 200, 60, 25);
        this.add(rmale);

        rfemale = new JRadioButton("Female");
        rfemale.setBackground(Color.WHITE);
        rfemale.setBounds(270, 200, 100, 25);
        this.add(rfemale);

        //Country Customer
        JLabel jLabel_country = new JLabel("Country");
        jLabel_country.setBounds(35, 240, 100, 20);
        jLabel_country.setFont(new Font("Tahoma", Font.BOLD, 17));
        this.add(jLabel_country);

        jTextField_country = new JTextField();
        jTextField_country.setBounds(200, 240, 150, 25);
        this.add(jTextField_country);

        //Room Customer
        JLabel jLabel_room = new JLabel("Room Number");
        jLabel_room.setBounds(35, 280, 165, 20);
        jLabel_room.setFont(new Font("Tahoma", Font.BOLD, 17));
        this.add(jLabel_room);

        choiceRoom = new Choice();
        try {
            Connect conn = new Connect();
            String query = "Select * from room";
            ResultSet rs = conn.s.executeQuery(query);
            while (rs.next()) {
                choiceRoom.add(rs.getString("room_number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        choiceRoom.setBounds(200, 280, 150, 25);
        this.add(choiceRoom);

        //Time Customer
        JLabel jLabel_time = new JLabel("Time");
        jLabel_time.setBounds(35, 320, 165, 20);
        jLabel_time.setFont(new Font("Tahoma", Font.BOLD, 17));
        this.add(jLabel_time);

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        String checkinTime = myDateObj.format(myFormatObj);

        jLabel_checkinTime = new JLabel(checkinTime);
        jLabel_checkinTime.setBounds(200, 320, 200, 20);
        jLabel_checkinTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
        this.add(jLabel_checkinTime);

        //Deposit Customer
        JLabel jLabel_deposit = new JLabel("Deposit");
        jLabel_deposit.setBounds(35, 360, 100, 20);
        jLabel_deposit.setFont(new Font("Tahoma", Font.BOLD, 17));
        this.add(jLabel_deposit);

        jTextField_deposit = new JTextField();
        jTextField_deposit.setBounds(200, 360, 150, 25);
        this.add(jTextField_deposit);

        //Add Button
        add = new JButton("ADD");
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.setBorderPainted(false);
        add.setBounds(50, 410, 120, 25);
        add.addActionListener(this);
        this.add(add);

        //Back Button
        back = new JButton("BACK");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBorderPainted(false);
        back.setBounds(200, 410, 120, 25);
        back.addActionListener(this);
        this.add(back);

        //Image Section
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fifth.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 50, 300, 380);
        this.add(image);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new AddCustomer();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("ADD")) {
            String ID = (String) jcomboBoxID.getSelectedItem();
            String number = jTextField_number.getText();
            String name = jTextField_name.getText();
            String gender = null;

            if (rmale.isSelected()) {
                gender = "Male";
            } else if (rfemale.isSelected()) {
                gender = "Female";
            }

            String country = jTextField_country.getText();
            String room = choiceRoom.getSelectedItem();
            String time = jLabel_checkinTime.getText();
            String deposit = jTextField_deposit.getText();


            try {
                String query = " insert into customer values ('" + ID + "','" + number + "','" + name +
                        "' ,'" + gender + "', '" + country + "','" + room + "', '" + time + "','" + deposit + "')";
                String query2 = "Update room set availability ='Ocupied' where room_number='" + room +
                        "' ";

                Connect conn = new Connect();
                conn.s.executeUpdate(query);
                conn.s.executeUpdate(query2);

                JOptionPane.showMessageDialog(null, "New Customer Added Succesfully");

                this.setVisible(false);
                new Reception();

            } catch (Exception e) {
                e.printStackTrace();

            }
        } else if (event.getActionCommand().equals("BACK")) {
            this.setVisible(false);
            new Reception();
        }
    }
}
