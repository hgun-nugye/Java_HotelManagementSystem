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

public class Room extends JFrame implements ActionListener {
    private final JButton back;
    private final DefaultTableModel tableModel;
    private final JButton delete;
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
            ResultSet rs = c.s.executeQuery("select p.SoPhong as 'Số Phòng', p.SoGiuong as 'Số giường', " +
                    "p.Tiennghi as 'Tiện nghi', p.TrangThai as 'Trạng thái', p.GiaMacDinh as 'Giá mặc định'" +
                    "from Phong p;");

            // Lưu mô hình vào biến tableModel
            tableModel = (DefaultTableModel) DbUtils.resultSetToTableModel(rs);
            jTable.setModel(tableModel);
            jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

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
        back = new JButton("Quay lại");
        back.setBounds(120, 500, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBorderPainted(false);
        back.addActionListener(this);
        back.setOpaque(true);
        this.add(back);

        // Tạo nút xóa
        delete = new JButton("Xóa");
        delete.setBounds(300, 500, 120, 30);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.setBorderPainted(false);
        delete.addActionListener(this);
        delete.setOpaque(true);
        this.add(delete);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
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

                        String deleteCustomer = "delete from Phong WHERE SoPhong = ?";
                        // Lấy giá trị CCCD từ hàng đã chọn
                        String soPhong = jTable.getValueAt(selectedRow, 0).toString();

                        // Tạo PreparedStatement
                        PreparedStatement deleteStmt = conn.c.prepareStatement(deleteCustomer);
                        deleteStmt.setString(1, soPhong); // Thiết lập tham số

                        // Thực hiện câu lệnh xóa
                        deleteStmt.executeUpdate();

                        // Xóa hàng khỏi table
                        tableModel.removeRow(selectedRow);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một phòng để xóa.");
            }
        }
    }
}
