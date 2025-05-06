package hotelmanagementsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CustomerInfor extends JFrame implements ActionListener {
    private final JButton back;
    private JTable jTable;

    public CustomerInfor() throws HeadlessException {
        this.getContentPane().setBackground(Color.white);
        this.setSize(1000, 600);
        this.setLayout(null);
        this.setTitle("Customer Information");

        // Heading label section
        JLabel jLabel_cnum = new JLabel("CCCD");
        jLabel_cnum.setBounds(80, 10, 100, 20);
        this.add(jLabel_cnum);

        JLabel jLabel_cname = new JLabel("Họ tên");
        jLabel_cname.setBounds(200, 10, 100, 20);
        this.add(jLabel_cname);

        JLabel jLabel_cgender = new JLabel("Giới tính");
        jLabel_cgender.setBounds(300, 10, 100, 20);
        this.add(jLabel_cgender);

        JLabel jLabel_ccountry = new JLabel("Quốc tịch");
        jLabel_ccountry.setBounds(420, 10, 100, 20);
        this.add(jLabel_ccountry);

        JLabel jLabel_cphone = new JLabel("Số điện thoại");
        jLabel_cphone.setBounds(520, 10, 100, 20);
        this.add(jLabel_cphone);

        JLabel jLabel_croom = new JLabel("Số phòng");
        jLabel_croom.setBounds(630, 10, 100, 20);
        this.add(jLabel_croom);

        JLabel jLabel_ccheckin = new JLabel("Checkin");
        jLabel_ccheckin.setBounds(750, 10, 100, 20);
        this.add(jLabel_ccheckin);

        JLabel jLabel_cdeposit = new JLabel("Tiền gửi trước");
        jLabel_cdeposit.setBounds(850, 10, 100, 20);
        this.add(jLabel_cdeposit);

        // Table section
        jTable = new JTable();
        jTable.setBounds(40, 40, 900, 400);
        this.add(jTable);

        try {
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("select KH.CCCD, KH.HoTen, KH.GioiTinh, KH.QuocTich, KH.SDT_KH, " +
                    "P.SoPhong, HD.NgayNhan, KH.DuaTruoc from HoaDon HD join KhachHang KH on KH.CCCD=HD.CCCD " +
                    "join Phong P on P.SoPhong= HD.SoPhong");
            jTable.setModel(DbUtils.resultSetToTableModel(rs));
            jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            jTable.setRowHeight(30);
            jTable.setShowVerticalLines(false); // Ẩn đường kẻ dọc
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            jTable.setGridColor(Color.GRAY); // Đặt màu cho đường kẻ


            for (int i = 0; i < jTable.getColumnCount(); i++) {
                jTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
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
        new CustomerInfor();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.setVisible(false);
        new Reception();
    }
}