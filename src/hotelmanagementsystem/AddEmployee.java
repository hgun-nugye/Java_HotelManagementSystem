package hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployee extends JFrame implements ActionListener {
    private final JTextField jTextField_name;
    private final JTextField jTextField_age;
    private final JTextField jTextField_salary;
    private final JTextField jTextField_phone;
    private final JTextField jTextField_email;
    private final JRadioButton rbmale;
    private final JRadioButton rbfemale;
    private final JComboBox cbjob;
    private final JButton submit;
    private final JTextField jTextField_id;
    private final JButton back;

    public AddEmployee() {
        this.setLayout(null);
        this.setSize(850, 540);
        this.getContentPane().setBackground(Color.WHITE);
        this.setTitle("Add Employee");

        //IDNumber section
        JLabel jLabel_id = new JLabel("Số CCCD");
        jLabel_id.setBounds(60, 30, 120, 30);
        jLabel_id.setFont(new Font("Tahoma", Font.BOLD, 17));
        this.add(jLabel_id);

        jTextField_id = new JTextField();
        jTextField_id.setBounds(200, 30, 200, 30);
        this.add(jTextField_id);

        //name section
        JLabel jLabel_name = new JLabel("Tên");
        jLabel_name.setBounds(60, 80, 120, 30);
        jLabel_name.setFont(new Font("Tahoma", Font.BOLD, 17));
        this.add(jLabel_name);

        jTextField_name = new JTextField();
        jTextField_name.setBounds(200, 80, 200, 30);
        this.add(jTextField_name);

        //age section
        JLabel jLabel_age = new JLabel("Tuổi");
        jLabel_age.setBounds(60, 130, 120, 30);
        jLabel_age.setFont(new Font("Tahoma", Font.BOLD, 17));
        this.add(jLabel_age);

        jTextField_age = new JTextField();
        jTextField_age.setBounds(200, 130, 200, 30);
        this.add(jTextField_age);

        //gender section
        JLabel jLabel_gender = new JLabel("Giới tính");
        jLabel_gender.setBounds(60, 180, 120, 30);
        jLabel_gender.setFont(new Font("Tahoma", Font.BOLD, 17));
        this.add(jLabel_gender);

        rbmale = new JRadioButton("Nam");
        rbmale.setBounds(200, 180, 70, 30);
        rbmale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbmale.setBackground(Color.WHITE);
        this.add(rbmale);

        rbfemale = new JRadioButton("Nữ");
        rbfemale.setBounds(300, 180, 70, 30);
        rbfemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbfemale.setBackground(Color.WHITE);
        this.add(rbfemale);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rbmale);
        bg.add(rbfemale);

        //job section
        JLabel jLabel_job = new JLabel("Chức vụ");
        jLabel_job.setBounds(60, 230, 120, 30);
        jLabel_job.setFont(new Font("Tahoma", Font.BOLD, 17));
        this.add(jLabel_job);

        String str[] = {"Phục vụ", "Buồng phòng", "Bếp", "Bảo vệ",
                "Kế toán", "Quản lý", "Lễ tân"};
        cbjob = new JComboBox(str);
        cbjob.setBounds(200, 230, 200, 30);
        cbjob.setBackground(Color.WHITE);
        cbjob.setOpaque(true);
        this.add(cbjob);

        //salary section
        JLabel jLabel_salary = new JLabel("Lương");
        jLabel_salary.setBounds(60, 280, 120, 30);
        jLabel_salary.setFont(new Font("Tahoma", Font.BOLD, 17));
        this.add(jLabel_salary);

        jTextField_salary = new JTextField();
        jTextField_salary.setBounds(200, 280, 200, 30);
        this.add(jTextField_salary);

        //phone section
        JLabel jLabel_phone = new JLabel("Số điện thoại");
        jLabel_phone.setBounds(60, 330, 120, 30);
        jLabel_phone.setFont(new Font("Tahoma", Font.BOLD, 17));
        this.add(jLabel_phone);

        jTextField_phone = new JTextField();
        jTextField_phone.setBounds(200, 330, 200, 30);
        this.add(jTextField_phone);

        //email section
        JLabel jLabel_email = new JLabel("Email");
        jLabel_email.setBounds(60, 380, 120, 30);
        jLabel_email.setFont(new Font("Tahoma", Font.BOLD, 17));
        this.add(jLabel_email);

        jTextField_email = new JTextField();
        jTextField_email.setBounds(200, 380, 200, 30);
        this.add(jTextField_email);

        //submit button
        submit = new JButton("SUBMIT");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBorderPainted(false);
        submit.setBounds(70, 430, 150, 30);
        submit.addActionListener(this);
        this.add(submit);

        //back button
        back = new JButton("BACK");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBorderPainted(false);
        back.setBounds(250, 430, 150, 30);
        back.addActionListener(this);
        this.add(back);

        //image right
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/employee.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(300, 50, 660, 400);
        this.add(image);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }

    public static void main(String[] args) {
        new AddEmployee();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource()==submit) {
            String name = jTextField_name.getText();
            String age = jTextField_age.getText();
            String salary = jTextField_salary.getText();
            String phone = jTextField_phone.getText();
            String email = jTextField_email.getText();
            String id = jTextField_id.getText();

            String gender = null;

            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name should not be empty");
                return;
            }

            if (!(email.contains("@") && email.contains(".com"))) {
                JOptionPane.showMessageDialog(null, "Email is not correct form");
                return;
            }

            if (rbmale.isSelected()) {
                gender = "Nam";
            } else if (rbfemale.isSelected()) {
                gender = "Nữ";
            }

            String job = (String) cbjob.getSelectedItem();

            try {
                Connect conn = new Connect();
                String query =
                        "insert into NhanVien values ('" + id + "','" + name + "', '" + age + "', '" + gender + "'," +
                                " " + "'" + job + "', '" + salary + "', '" + phone + "', '" + email + "')";
                conn.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Employee added successfully");

                this.setVisible(false);
                new Reception();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }else if(event.getSource()==back){
            this.setVisible(false);
            new Dashboard();
        }
    }
}
