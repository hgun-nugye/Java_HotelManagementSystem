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
        jTable.setBounds(50, 150, 900, 300); // Adjusted to fit the frame
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        jTable.setDefaultRenderer(Object.class, leftRenderer);
        jTable.setGridColor(Color.GRAY);

        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setBounds(50, 150, 900, 300); // Adjusted to fit the frame
        this.add(scrollPane);

        // Load room data into JTable
        loadRoomData();

        // Submit button
        submit = new JButton("SUBMIT");
        submit.setBounds(300, 500, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBorderPainted(false);
        submit.addActionListener(this);
        this.add(submit);

        // Back button
        back = new JButton("BACK");
        back.setBounds(500, 500, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBorderPainted(false);
        back.addActionListener(this);
        this.add(back);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void loadRoomData() {
        try {
            Connect c = new Connect();
            ResultSet rs = c.s.executeQuery("SELECT * FROM Phong");
            jTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace(); // Better error handling
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if ("SUBMIT".equals(event.getActionCommand())) {
            searchRooms();
        } else {
            this.setVisible(false);
            new Reception();
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