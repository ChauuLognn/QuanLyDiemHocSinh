import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class StudentManagementSwing extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;

    // Input fields
    private JTextField txtId, txtName, txtClass, txtMath, txtPhysics, txtChemistry;

    public StudentManagementSwing() {
        setTitle("Quản lý điểm học sinh - Swing");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main layout
        setLayout(new BorderLayout(10, 10));

        // Input Panel (TOP)
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        // Table Panel (CENTER)
        JPanel tablePanel = createTablePanel();
        add(tablePanel, BorderLayout.CENTER);

        // Button Panel (BOTTOM)
        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        // Add sample data
        addSampleData();
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 6, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Thông tin học sinh"));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createTitledBorder("Thông tin học sinh")
        ));

        // Row 1
        panel.add(new JLabel("Mã SV:"));
        txtId = new JTextField();
        panel.add(txtId);

        panel.add(new JLabel("Họ tên:"));
        txtName = new JTextField();
        panel.add(txtName);

        panel.add(new JLabel("Lớp:"));
        txtClass = new JTextField();
        panel.add(txtClass);

        // Row 2
        panel.add(new JLabel("Điểm Toán:"));
        txtMath = new JTextField();
        panel.add(txtMath);

        panel.add(new JLabel("Điểm Lý:"));
        txtPhysics = new JTextField();
        panel.add(txtPhysics);

        panel.add(new JLabel("Điểm Hóa:"));
        txtChemistry = new JTextField();
        panel.add(txtChemistry);

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columns = {"Mã SV", "Họ tên", "Lớp", "Toán", "Lý", "Hóa", "Điểm TB"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        // Click row to edit
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    txtId.setText(tableModel.getValueAt(row, 0).toString());
                    txtName.setText(tableModel.getValueAt(row, 1).toString());
                    txtClass.setText(tableModel.getValueAt(row, 2).toString());
                    txtMath.setText(tableModel.getValueAt(row, 3).toString());
                    txtPhysics.setText(tableModel.getValueAt(row, 4).toString());
                    txtChemistry.setText(tableModel.getValueAt(row, 5).toString());
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnAdd = new JButton("Thêm");
        JButton btnUpdate = new JButton("Cập nhật");
        JButton btnDelete = new JButton("Xóa");
        JButton btnClear = new JButton("Làm mới");

        // Set button size
        Dimension btnSize = new Dimension(100, 30);
        btnAdd.setPreferredSize(btnSize);
        btnUpdate.setPreferredSize(btnSize);
        btnDelete.setPreferredSize(btnSize);
        btnClear.setPreferredSize(btnSize);

        btnAdd.addActionListener(e -> addStudent());
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnClear.addActionListener(e -> clearFields());

        panel.add(btnAdd);
        panel.add(btnUpdate);
        panel.add(btnDelete);
        panel.add(btnClear);

        return panel;
    }

    private void addStudent() {
        try {
            String id = txtId.getText().trim();
            String name = txtName.getText().trim();
            String className = txtClass.getText().trim();

            if (id.isEmpty() || name.isEmpty() || className.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double math = Double.parseDouble(txtMath.getText());
            double physics = Double.parseDouble(txtPhysics.getText());
            double chemistry = Double.parseDouble(txtChemistry.getText());
            double avg = (math + physics + chemistry) / 3;

            Object[] row = {id, name, className, math, physics, chemistry, String.format("%.2f", avg)};
            tableModel.addRow(row);

            clearFields();
            JOptionPane.showMessageDialog(this, "Thêm thành công!");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập điểm hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudent() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            try {
                tableModel.setValueAt(txtId.getText(), row, 0);
                tableModel.setValueAt(txtName.getText(), row, 1);
                tableModel.setValueAt(txtClass.getText(), row, 2);

                double math = Double.parseDouble(txtMath.getText());
                double physics = Double.parseDouble(txtPhysics.getText());
                double chemistry = Double.parseDouble(txtChemistry.getText());
                double avg = (math + physics + chemistry) / 3;

                tableModel.setValueAt(math, row, 3);
                tableModel.setValueAt(physics, row, 4);
                tableModel.setValueAt(chemistry, row, 5);
                tableModel.setValueAt(String.format("%.2f", avg), row, 6);

                clearFields();
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập điểm hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn học sinh cần cập nhật!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteStudent() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                tableModel.removeRow(row);
                clearFields();
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn học sinh cần xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtClass.setText("");
        txtMath.setText("");
        txtPhysics.setText("");
        txtChemistry.setText("");
        table.clearSelection();
    }

    private void addSampleData() {
        Object[][] data = {
                {"SV001", "Nguyễn Văn A", "IT01", 8.5, 7.0, 8.0, "7.83"},
                {"SV002", "Trần Thị B", "IT01", 9.0, 8.5, 9.0, "8.83"},
                {"SV003", "Lê Văn C", "IT02", 7.5, 6.5, 7.0, "7.00"}
        };

        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentManagementSwing().setVisible(true);
        });
    }
}