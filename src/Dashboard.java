import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame {
    private JPanel mainPanel;
    private String username = "ADMIN";
    private Color primaryColor = new Color(70, 70, 70);
    private Color secondaryColor = new Color(245, 245, 245);
    private Color accentColor = new Color(100, 100, 100);

    public Dashboard() {
        setTitle("Student Management System - Dashboard");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main layout
        setLayout(new BorderLayout());

        // Top Navigation Bar
        add(createTopNavBar(), BorderLayout.NORTH);

        // Left Sidebar Menu
        add(createSidebarMenu(), BorderLayout.WEST);

        // Main Content Area
        mainPanel = createMainContent();
        add(mainPanel, BorderLayout.CENTER);
    }

    // ============================================================
    // TOP NAVIGATION BAR
    // ============================================================
    private JPanel createTopNavBar() {
        JPanel navbar = new JPanel(new BorderLayout());
        navbar.setPreferredSize(new Dimension(0, 60));
        navbar.setBackground(primaryColor);
        navbar.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        // Left - Title
        JLabel title = new JLabel("QUẢN LÝ ĐIỂM HỌC SINH");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(Color.WHITE);
        navbar.add(title, BorderLayout.WEST);

        // Right - User Info
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        rightPanel.setBackground(primaryColor);

        JLabel userName = new JLabel(username);
        userName.setFont(new Font("Arial", Font.PLAIN, 13));
        userName.setForeground(Color.WHITE);
        rightPanel.add(userName);

        JLabel separator = new JLabel("|");
        separator.setForeground(new Color(150, 150, 150));
        rightPanel.add(separator);

        JButton btnLogout = new JButton("Đăng xuất");
        btnLogout.setFont(new Font("Arial", Font.PLAIN, 12));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setBackground(primaryColor);
        btnLogout.setBorder(null);
        btnLogout.setFocusPainted(false);
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogout.addActionListener(e -> logout());
        rightPanel.add(btnLogout);

        navbar.add(rightPanel, BorderLayout.EAST);

        return navbar;
    }

    // ============================================================
    // LEFT SIDEBAR MENU
    // ============================================================
    private JPanel createSidebarMenu() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(200, 0));
        sidebar.setBackground(Color.WHITE);
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(220, 220, 220)));

        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));

        // Menu items
        String[] menuItems = {
                "Trang chủ",
                "Quản lý học sinh",
                "Thống kê",
                "Môn học",
                "Lớp học",
                "Báo cáo"
        };

        for (int i = 0; i < menuItems.length; i++) {
            JButton menuBtn = createMenuButton(menuItems[i], i == 0);
            sidebar.add(menuBtn);
        }

        return sidebar;
    }

    private JButton createMenuButton(String text, boolean isActive) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(200, 40));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setFont(new Font("Arial", Font.PLAIN, 13));
        btn.setForeground(isActive ? primaryColor : new Color(100, 100, 100));
        btn.setBackground(isActive ? secondaryColor : Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setHorizontalAlignment(SwingConstants.LEFT);

        if (isActive) {
            btn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 3, 0, 0, primaryColor),
                    BorderFactory.createEmptyBorder(10, 22, 10, 25)
            ));
        }

        // Hover effect
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isActive) {
                    btn.setBackground(new Color(250, 250, 250));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (!isActive) {
                    btn.setBackground(Color.WHITE);
                }
            }
        });

        // Click action
        btn.addActionListener(e -> {
            if (text.equals("Quản lý học sinh")) {
                openStudentManagement();
            } else if (!isActive) {
                JOptionPane.showMessageDialog(this, "Chức năng: " + text);
            }
        });

        return btn;
    }

    // ============================================================
    // MAIN CONTENT AREA
    // ============================================================
    private JPanel createMainContent() {
        JPanel content = new JPanel(new BorderLayout(20, 20));
        content.setBackground(secondaryColor);
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Top section
        JPanel topSection = new JPanel(new BorderLayout());
        topSection.setBackground(secondaryColor);

        JLabel welcome = new JLabel("Tổng quan");
        welcome.setFont(new Font("Arial", Font.BOLD, 22));
        topSection.add(welcome, BorderLayout.WEST);

        content.add(topSection, BorderLayout.NORTH);

        // Stats cards
        JPanel statsPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        statsPanel.setBackground(secondaryColor);

        statsPanel.add(createSimpleCard("Tổng học sinh", "250"));
        statsPanel.add(createSimpleCard("Điểm trung bình", "7.85"));
        statsPanel.add(createSimpleCard("Học sinh giỏi", "45"));
        statsPanel.add(createSimpleCard("Tổng số lớp", "8"));

        content.add(statsPanel, BorderLayout.CENTER);

        // Bottom info
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        bottomPanel.setBackground(secondaryColor);
        bottomPanel.setPreferredSize(new Dimension(0, 180));

        bottomPanel.add(createInfoCard("Phân loại học lực", new String[]{
                "Giỏi: 45 học sinh (18%)",
                "Khá: 85 học sinh (34%)",
                "Trung bình: 75 học sinh (30%)",
                "Yếu: 45 học sinh (18%)"
        }));

        bottomPanel.add(createInfoCard("Hoạt động gần đây", new String[]{
                "Thêm học sinh: Nguyễn Văn A",
                "Cập nhật điểm: Trần Thị B",
                "Xóa học sinh: Lê Văn C",
                "Xuất báo cáo lớp IT01"
        }));

        content.add(bottomPanel, BorderLayout.SOUTH);

        return content;
    }

    // ============================================================
    // SIMPLE CARD
    // ============================================================
    private JPanel createSimpleCard(String title, String value) {
        JPanel card = new JPanel(new BorderLayout(15, 15));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 13));
        lblTitle.setForeground(new Color(120, 120, 120));
        card.add(lblTitle, BorderLayout.NORTH);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Arial", Font.BOLD, 36));
        lblValue.setForeground(primaryColor);
        card.add(lblValue, BorderLayout.CENTER);

        return card;
    }

    // ============================================================
    // INFO CARD
    // ============================================================
    private JPanel createInfoCard(String title, String[] items) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitle.setForeground(primaryColor);
        card.add(lblTitle, BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);

        for (String item : items) {
            JLabel lblItem = new JLabel("• " + item);
            lblItem.setFont(new Font("Arial", Font.PLAIN, 12));
            lblItem.setForeground(new Color(100, 100, 100));
            lblItem.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            listPanel.add(lblItem);
        }

        card.add(listPanel, BorderLayout.CENTER);

        return card;
    }

    // ============================================================
    // HELPER METHODS
    // ============================================================
    private void openStudentManagement() {
        this.dispose();
        SwingUtilities.invokeLater(() -> {
            new StudentManagementSwing().setVisible(true);
        });
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn đăng xuất?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
            SwingUtilities.invokeLater(() -> {
                new Login().setVisible(true);
            });
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new Dashboard().setVisible(true);
        });
    }
}