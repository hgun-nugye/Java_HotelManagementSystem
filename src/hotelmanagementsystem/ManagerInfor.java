package hotelmanagementsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ManagerInfor extends JFrame implements ActionListener {
    private final JButton back;
    private JTable jTable;

    public ManagerInfor() throws HeadlessException {
        this.getContentPane().setBackground(Color.white);
        this.setSize(1200, 600);
        this.setLayout(null);
        this.setTitle("Manager Information");

        //Table section
        jTable = new JTable();
        jTable.setBounds(50, 20, 900, 450);
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        jTable.setDefaultRenderer(Object.class, leftRenderer); // Căn trái cho tất cả các ô
        jTable.setGridColor(Color.GRAY);
        jTable.setRowHeight(30);
        this.add(jTable);

        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setBounds(50, 20, 1100, 450);
        this.add(scrollPane);


        try {
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("SELECT e.CCCD," +
                    "       e.HoTen AS 'Họ Tên'" +
                    "       e.Tuoi AS 'Tuổi'," +
                    "       e.GioiTinh AS 'Giới tính'," +
                    "       e.ChucVu AS 'Chức vụ'," +
                    "       e.Luong AS 'Lương'," +
                    "       e.SDT_NV AS 'Số điện thoại'," +
                    "       e.Email_NV AS 'Email'" +
                    "FROM NhanVien e" +
                    "WHERE e.ChucVu = 'Quản lý';");
            jTable.setModel(DbUtils.resultSetToTableModel(rs));
            jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

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

        //back button section
        back = new JButton("Quay lại");
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
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.setVisible(false);
        new Reception();
    }
}
