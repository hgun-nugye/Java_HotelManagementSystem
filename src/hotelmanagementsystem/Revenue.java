package hotelmanagementsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Revenue extends JFrame implements ActionListener {
    private final JButton back;
    private final JComboBox choiceRoom;
    private final JTable jTable;

    public Revenue() throws HeadlessException {
        this.getContentPane().setBackground(Color.white);
        this.setForeground(Color.BLACK);
        this.setFont(new Font("Tahoma", Font.PLAIN, 16));
        this.setSize(1000, 600);
        this.setLayout(null);
        this.setTitle("Statistics Revenue ");

        // Room Customer
        JLabel jLabelRoom = new JLabel("Thống kê theo: ");
        jLabelRoom.setBounds(50, 50, 165, 20);
        jLabelRoom.setFont(new Font("Tahoma", Font.BOLD, 17));
        add(jLabelRoom);

        String[] billOptions = {"Tất cả Hóa đơn", "Ngày", "Tháng"};
        choiceRoom = new JComboBox(billOptions);
        choiceRoom.setBounds(200, 50, 150, 25);
        choiceRoom.setBackground(Color.white);
        choiceRoom.addActionListener(this);
        add(choiceRoom);

        // Table section
        jTable = new JTable();
        jTable.setBounds(50, 100, 1100, 300);
        jTable.setRowHeight(30);

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        jTable.setDefaultRenderer(Object.class, leftRenderer); // Căn trái cho tất cả các ô
        jTable.setGridColor(Color.GRAY);


        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setBounds(50, 100, 900, 300);
        this.add(scrollPane); // Thêm JScrollPane vào JFrame thay vì JTable trực tiếp


        try {
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("select hd.MaHD as \"Mã Hóa đơn\", hd.CCCD, hd.SoPhong as \"Số Phòng\"," +
                    "hd.NgayNhan as \"Ngày Nhận\", hd.NgayTra as \"Ngày Trả\", " +
                    "format(datediff(hd.NgayTra, hd.NgayNhan)*p.GiaMacDinh,0) " +
                    "as 'Tổng Tiền' from HoaDon hd join Phong p on p.SoPhong=hd.SoPhong;");
            jTable.setModel(DbUtils.resultSetToTableModel(rs));
            jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Back button section
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
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == back) {
            this.setVisible(false);
            new Reception();
        } else if (event.getSource() == choiceRoom) {
            if (choiceRoom.getSelectedItem().equals("Ngày")) {
                try {
                    Connect c = new Connect();
                    ResultSet rs = c.s.executeQuery("SELECT " +
                            "    DATE(HoaDon.NgayTra) AS 'Ngày', " +
                            "    FORMAT(SUM(DATEDIFF(HoaDon.NgayTra, HoaDon.NgayNhan) * Phong.GiaMacDinh), 0) AS 'Tổng Doanh Thu' " +
                            "FROM " +
                            "    HoaDon " +
                            "JOIN " +
                            "    Phong ON HoaDon.SoPhong = Phong.SoPhong " +
                            "WHERE " +
                            "    HoaDon.TrangThai = 'Đã thanh toán' " +
                            "GROUP BY " +
                            "    DATE(HoaDon.NgayTra) " +
                            "ORDER BY " +
                            "    DATE(HoaDon.NgayTra);" +
                            "    ");
                    jTable.setModel(DbUtils.resultSetToTableModel(rs));
                    jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                }

            } else if (choiceRoom.getSelectedItem().equals("Tháng")) {
                try {
                    Connect c = new Connect();
                    ResultSet rs = c.s.executeQuery("SELECT " +
                            "    YEAR(HoaDon.NgayTra) AS 'Năm', " +
                            "    MONTH(HoaDon.NgayTra) AS 'Tháng', " +
                            "    FORMAT(SUM(DATEDIFF(HoaDon.NgayTra, HoaDon.NgayNhan) * Phong.GiaMacDinh), 0) AS 'Tổng Doanh Thu' " +
                            "FROM " +
                            "    HoaDon " +
                            "JOIN " +
                            "    Phong ON HoaDon.SoPhong = Phong.SoPhong " +
                            "WHERE " +
                            "    HoaDon.TrangThai = 'Đã thanh toán' " +
                            "GROUP BY " +
                            "    YEAR(HoaDon.NgayTra), MONTH(HoaDon.NgayTra) " +
                            "ORDER BY " +
                            "    Năm, Tháng;");
                    jTable.setModel(DbUtils.resultSetToTableModel(rs));
                    jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                }

            } else if (choiceRoom.getSelectedItem().equals("Tất cả Hóa đơn")) {
                try {
                    Connect c = new Connect();
                    ResultSet rs = c.s.executeQuery("SELECT " +
                            "    hd.MaHD AS 'Mã Hóa đơn', " +
                            "    hd.CCCD, " +
                            "    hd.SoPhong AS 'Số Phòng', " +
                            "    hd.NgayNhan AS 'Ngày Nhận', " +
                            "    hd.NgayTra AS 'Ngày Trả', " +
                            "    FORMAT(DATEDIFF(hd.NgayTra, hd.NgayNhan) * p.GiaMacDinh, 0) AS 'Tổng Tiền' " +
                            "FROM " +
                            "    HoaDon hd " +
                            "JOIN " +
                            "    Phong p ON p.SoPhong = hd.SoPhong;");
                    jTable.setModel(DbUtils.resultSetToTableModel(rs));
                    jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                }
            }
        }
    }
}
