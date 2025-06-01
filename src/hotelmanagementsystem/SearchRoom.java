package hotelmanagementsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class SearchRoom extends JFrame implements ActionListener {
    private final JButton back;
    private final JButton submit;
    private final JTextField jTextField_bed;
    private final JButton reset;
    private JTable jTable;
    private JCheckBox available;

    public SearchRoom() {
        // Setup JFrame properties
        this.getContentPane().setBackground(Color.white);
        this.setSize(1000, 600);
        this.setLayout(null);
        this.setTitle("Search Room");

        // Title label
        JLabel jLabel_text = new JLabel("TÌM PHÒNG");
        jLabel_text.setFont(new Font("Tahoma", Font.BOLD, 20));
        jLabel_text.setBounds(400, 30, 200, 30);
        this.add(jLabel_text);

        // Bed number label and text field
        JLabel jLabel_bed = new JLabel("Số giường");
        jLabel_bed.setBounds(50, 100, 100, 20);
        this.add(jLabel_bed);

        jTextField_bed = new JTextField();
        jTextField_bed.setBounds(150, 100, 200, 30);
        this.add(jTextField_bed);

        // Checkbox for available rooms
        available = new JCheckBox("Chỉ hiển thị phòng Trống");
        available.setBounds(550, 100, 300, 25);
        available.setBackground(Color.WHITE);
        this.add(available);

        // JTable setup
        jTable = new JTable();
        jTable.setRowHeight(30);
        jTable.setBounds(50, 150, 900, 300);
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        jTable.setDefaultRenderer(Object.class, leftRenderer);
        jTable.setGridColor(Color.GRAY);

        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setBounds(50, 150, 900, 300);
        this.add(scrollPane);

        // Load room data into JTable
        loadRoomData();

        // Submit button
        submit = new JButton("Tìm");
        submit.setBounds(200, 500, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBorderPainted(false);
        submit.addActionListener(this);
        this.add(submit);

        // Back button
        back = new JButton("Quay lại");
        back.setBounds(400, 500, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBorderPainted(false);
        back.addActionListener(this);
        this.add(back);

        // Back button
        reset = new JButton("Reset");
        reset.setBounds(600, 500, 120, 30);
        reset.setBackground(Color.BLACK);
        reset.setForeground(Color.WHITE);
        reset.setBorderPainted(false);
        reset.addActionListener(this);
        this.add(reset);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new SearchRoom();
    }

    private void loadRoomData() {
        try {
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("select p.SoPhong as 'Số Phòng', p.SoGiuong as 'Số giường', " +
                    "p.Tiennghi as 'Tiện nghi', p.TrangThai as 'Trạng thái', p.GiaMacDinh as 'Giá mặc định'" +
                    "from Phong p;");
            jTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == submit) {
            searchRooms();
        } else if( event.getSource() == back) {
            this.setVisible(false);
            new Reception();
        } else if (event.getSource() == reset) {
            jTextField_bed.setText("");
            available.setSelected(false);
            loadRoomData();
        }
    }

    private void searchRooms() {
        try {
            String query = available.isSelected()
                    ? "SELECT * FROM Phong WHERE TrangThai='Trống' AND SoGiuong='" + jTextField_bed.getText() + "'"
                    : "SELECT * FROM Phong WHERE SoGiuong='" + jTextField_bed.getText() + "'";

            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery(query);
            jTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}