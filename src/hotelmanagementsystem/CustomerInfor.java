package hotelmanagementsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerInfor extends JFrame implements ActionListener {
    private final JButton back;
    private final JButton delete;
    private JTable jTable;
    private DefaultTableModel tableModel;

    public CustomerInfor() throws HeadlessException {
        this.getContentPane().setBackground(Color.white);
        this.setSize(1000, 600);
        this.setLayout(null);
        this.setTitle("Customer Information");

        // Table section
        jTable = new JTable();
        jTable.setBounds(50, 20, 900, 450);
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        jTable.setDefaultRenderer(Object.class, leftRenderer); // Căn trái cho tất cả các ô
        jTable.setGridColor(Color.GRAY);
        jTable.setRowHeight(30);
        this.add(jTable);

        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setBounds(50, 20, 900, 450);
        this.add(scrollPane);

        try {
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("select KH.CCCD, KH.HoTen as 'Họ Tên', KH.GioiTinh as 'Giới tính',  " +
                    "KH.QuocTich as 'Quốc tịch', KH.SDT_KH as 'Số điện thoại'," +
                    "P.SoPhong as 'Số Phòng', HD.NgayNhan as 'Ngày Nhận', KH.DuaTruoc as 'Đưa trước'" +
                    " from HoaDon HD join KhachHang KH on KH.CCCD=HD.CCCD" +
                    " join Phong P on P.SoPhong= HD.SoPhong;");
            // Lưu mô hình vào biến tableModel
            tableModel = (DefaultTableModel) DbUtils.resultSetToTableModel(rs);
            jTable.setModel(tableModel);
            jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

            // Chỉnh kích thước cột
            jTable.getColumnModel().getColumn(0).setPreferredWidth(100); // Cột CCCD
            jTable.getColumnModel().getColumn(1).setPreferredWidth(150); // Cột Tên
            jTable.getColumnModel().getColumn(2).setPreferredWidth(100);  // Cột Giới tính
            jTable.getColumnModel().getColumn(3).setPreferredWidth(100); // Cột Quốc tịch
            jTable.getColumnModel().getColumn(4).setPreferredWidth(100); // Cột Số điện thoại
            jTable.getColumnModel().getColumn(5).setPreferredWidth(50); // Cột Số Phòng
            jTable.getColumnModel().getColumn(6).setPreferredWidth(100); // Cột Ngày nhận
            jTable.getColumnModel().getColumn(7).setPreferredWidth(100); // Cột Tiền đưa trước


        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }

        // Back button section
        back = new JButton("Quay lại");
        back.setBounds(300, 500, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBorderPainted(false);
        back.addActionListener(this);
        back.setOpaque(true);
        this.add(back);


        // Tạo nút xóa
        delete = new JButton("Xóa");
        delete.setBounds(500, 500, 120, 30);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.setBorderPainted(false);
        delete.addActionListener(this);
        delete.setOpaque(true);
        this.add(delete);

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
        } else if (event.getSource() == delete) { //delete button action
            int selectedRow = jTable.getSelectedRow();
            if (selectedRow != -1) { // Kiểm tra có chọn hàng không
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không?", "Xác Nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {

                    try {
                        Connect conn = new Connect();

                        String deleteCustomer = "delete from KhachHang WHERE CCCD = ?";
                        // Lấy giá trị CCCD từ hàng đã chọn
                        String cccd = jTable.getValueAt(selectedRow, 0).toString();

                        // Tạo PreparedStatement
                        PreparedStatement deleteStmt = conn.c.prepareStatement(deleteCustomer);
                        deleteStmt.setString(1, cccd); // Thiết lập tham số

                        // Thực hiện câu lệnh xóa
                        deleteStmt.executeUpdate();

                        // Xóa hàng khỏi table
                        tableModel.removeRow(selectedRow);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một khách hàng để xóa.");
            }
        }
    }
}