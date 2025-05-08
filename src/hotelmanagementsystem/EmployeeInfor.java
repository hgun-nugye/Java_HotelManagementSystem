package hotelmanagementsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class EmployeeInfor extends JFrame implements ActionListener {
    private final JButton back;
    private JTable jTable;

    public EmployeeInfor() throws HeadlessException {
        this.getContentPane().setBackground(Color.white);
        this.setSize(1200, 600);
        this.setLayout(null);
        this.setTitle("Employee Information");

        // Table section
        jTable = new JTable();
        jTable.setBounds(50, 40, 1100, 500);
        jTable.setRowHeight(30);

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        jTable.setDefaultRenderer(Object.class, leftRenderer); // Căn trái cho tất cả các ô
        jTable.setGridColor(Color.GRAY);

        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setBounds(50, 40, 1100, 400);
        this.add(scrollPane); // Thêm JScrollPane vào JFrame thay vì JTable trực tiếp

        try {
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("select * from NhanVien");
            jTable.setModel(DbUtils.resultSetToTableModel(rs));

            // Chỉnh kích thước cột
            jTable.getColumnModel().getColumn(0).setPreferredWidth(100); // Cột CCCD
            jTable.getColumnModel().getColumn(1).setPreferredWidth(150); // Cột Tên
            jTable.getColumnModel().getColumn(2).setPreferredWidth(50);  // Cột Tuổi
            jTable.getColumnModel().getColumn(3).setPreferredWidth(100); // Cột Giới tính
            jTable.getColumnModel().getColumn(4).setPreferredWidth(100); // Cột Chức vụ
            jTable.getColumnModel().getColumn(5).setPreferredWidth(100); // Cột Lương
            jTable.getColumnModel().getColumn(6).setPreferredWidth(100); // Cột Số điện thoại
            jTable.getColumnModel().getColumn(7).setPreferredWidth(200); // Cột Email

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Back button section
        back = new JButton("BACK");
        back.setBounds(420, 500, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBorderPainted(false);
        back.addActionListener(this);
        back.setOpaque(true);
        this.add(back);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }

    public static void main(String[] args) {
        new EmployeeInfor();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.setVisible(false);
        new Reception();
    }
}