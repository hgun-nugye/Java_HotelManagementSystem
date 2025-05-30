package hotelmanagementsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Room extends JFrame implements ActionListener {
    private final JButton back;
    private JTable jTable;

    public Room() throws HeadlessException {
        this.getContentPane().setBackground(Color.white);
        this.setSize(1050, 600);
        this.setLayout(null);
        this.setTitle("Room");

        //Image section
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/department.gif"));
        Image i2 = i1.getImage().getScaledInstance(800, 500, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(570, 30, 650, 500);
        this.add(image);

        //Table section
        jTable = new JTable();
        jTable.setBounds(20, 20, 500, 450);
        jTable.setRowHeight(30);

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        jTable.setDefaultRenderer(Object.class, leftRenderer); // Căn trái cho tất cả các ô
        jTable.setGridColor(Color.GRAY);

        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setBounds(20, 20, 500, 450);
        this.add(scrollPane); // Thêm JScrollPane vào JFrame thay vì JTable trực tiếp

        try {
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("select * from Phong");
            jTable.setModel(DbUtils.resultSetToTableModel(rs));

            // Chỉnh kích thước cột
            jTable.getColumnModel().getColumn(0).setPreferredWidth(100); // Cột Số phòng
            jTable.getColumnModel().getColumn(1).setPreferredWidth(100); // Cột Số giường
            jTable.getColumnModel().getColumn(2).setPreferredWidth(200);  // Cột Tiện nghi
            jTable.getColumnModel().getColumn(3).setPreferredWidth(100); // Cột Trạng thái
            jTable.getColumnModel().getColumn(4).setPreferredWidth(100); // Cột Giá


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //back button section
        back = new JButton("BACK");
        back.setBounds(280, 500, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBorderPainted(false);
        back.addActionListener(this);
        back.setOpaque(true);
        this.add(back);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.setVisible(false);
        new Reception();
    }
}
