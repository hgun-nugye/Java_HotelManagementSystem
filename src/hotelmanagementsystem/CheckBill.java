package hotelmanagementsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CheckBill extends JFrame implements ActionListener {
    private final JButton back;

    public CheckBill() throws HeadlessException {
        this.getContentPane().setBackground(Color.white);
        this.setForeground(Color.BLACK);
        this.setFont(new Font("Tahoma", Font.PLAIN, 16));
        this.setSize(1200, 600);
        this.setLayout(null);
        this.setTitle("Statistics Bill ");

        // Table section
        JTable jTable = new JTable();
        jTable.setBounds(50, 20, 1100, 450);
        jTable.setRowHeight(30);

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        jTable.setDefaultRenderer(Object.class, leftRenderer); // Căn trái cho tất cả các ô
        jTable.setGridColor(Color.GRAY);


        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setBounds(50, 20, 1100, 450);
        this.add(scrollPane); // Thêm JScrollPane vào JFrame thay vì JTable trực tiếp

        try {
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("select hd.MaHD as \"Mã Hóa đơn\", hd.CCCD, hd.SoPhong as \"Số Phòng\"," +
                    "hd.NgayNhan as \"Ngày Nhận\", hd.NgayTra as \"Ngày Trả\", " +
                    "hd.TrangThai as \"Trạng Thái\", " +
                    "format(datediff(hd.NgayTra, hd.NgayNhan)*p.GiaMacDinh,0) " +
                    "as 'Tổng Tiền' from HoaDon hd join Phong p on p.SoPhong=hd.SoPhong;");
            jTable.setModel(DbUtils.resultSetToTableModel(rs));

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
        }
    }
}
