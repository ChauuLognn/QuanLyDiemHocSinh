import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JCheckBox chkRemember;
    private JButton btnLogin;
    private JLabel lblForgotPassword;
    private boolean passwordVisible = false;

    public Login() {
        setTitle("Student Management System");
        setSize(400, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // 1. MAIN PANEL: Gradient Xám Porsche (Theo yêu cầu của bạn)
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                // Màu xám sang trọng (Porsche Grey Theme)
                Color color1 = new Color(70, 70, 70);   // Xám đậm hơn chút ở trên
                Color color2 = new Color(130, 130, 130); // Xám sáng ở dưới
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(null);

        // Nút đóng (X)
        JButton btnClose = createIconButton("×", 350, 10, 30, 30);
        btnClose.setFont(new Font("Arial", Font.BOLD, 24));
        btnClose.addActionListener(e -> System.exit(0));
        mainPanel.add(btnClose);

        // Tiêu đề LOGIN
        JLabel lblTitle = new JLabel("LOGIN");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(30, 70, 200, 40);
        mainPanel.add(lblTitle);

        // Label Username
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setBounds(30, 160, 100, 20);
        mainPanel.add(lblUsername);

        // --- KHUNG NHẬP USERNAME ---
        JPanel usernamePanel = createInputPanel(); // Dùng hàm đã fix lỗi
        usernamePanel.setBounds(30, 185, 340, 45);

        JLabel userIcon = new JLabel("👤");
        userIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20)); // Dùng font hỗ trợ icon tốt hơn
        userIcon.setForeground(Color.WHITE);
        userIcon.setBounds(10, 10, 30, 25);
        usernamePanel.add(userIcon);

        txtUsername = new JTextField("  ");
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsername.setForeground(Color.WHITE);
        txtUsername.setBackground(new Color(0,0,0,0)); // Nền rỗng
        txtUsername.setOpaque(false);          // FIX LỖI NHOÈ: Bắt buộc có
        txtUsername.setCaretColor(Color.WHITE); // Con trỏ màu trắng
        txtUsername.setBorder(BorderFactory.createEmptyBorder());
        txtUsername.setBounds(45, 10, 280, 25);
        usernamePanel.add(txtUsername);

        mainPanel.add(usernamePanel);

        // Label Password
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setBounds(30, 250, 100, 20);
        mainPanel.add(lblPassword);

        // --- KHUNG NHẬP PASSWORD ---
        JPanel passwordPanel = createInputPanel(); // Dùng hàm đã fix lỗi
        passwordPanel.setBounds(30, 275, 340, 45);

        JLabel lockIcon = new JLabel("🔒");
        lockIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        lockIcon.setForeground(Color.WHITE);
        lockIcon.setBounds(10, 10, 30, 25);
        passwordPanel.add(lockIcon);

        txtPassword = new JPasswordField("");
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPassword.setForeground(Color.WHITE);
        txtPassword.setBackground(new Color(0,0,0,0)); // Nền rỗng
        txtPassword.setOpaque(false);          // FIX LỖI NHOÈ: Bắt buộc có
        txtPassword.setCaretColor(Color.WHITE);
        txtPassword.setBorder(BorderFactory.createEmptyBorder());
        txtPassword.setEchoChar('●');
        txtPassword.setBounds(45, 10, 245, 25);
        passwordPanel.add(txtPassword);

        // Icon Mắt (Show/Hide)
        JLabel eyeIcon = new JLabel("👁");
        eyeIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        eyeIcon.setForeground(Color.WHITE);
        eyeIcon.setBounds(300, 10, 30, 25);
        eyeIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        eyeIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                passwordVisible = !passwordVisible;
                if (passwordVisible) {
                    txtPassword.setEchoChar((char) 0);
                } else {
                    txtPassword.setEchoChar('●');
                }
            }
        });
        passwordPanel.add(eyeIcon);

        mainPanel.add(passwordPanel);

        // Checkbox Remember Me
        chkRemember = new JCheckBox("Save Login");
        chkRemember.setFont(new Font("Arial", Font.PLAIN, 12));
        chkRemember.setForeground(Color.WHITE);
        chkRemember.setBackground(new Color(0, 0, 0, 0));
        chkRemember.setOpaque(false); // Fix lỗi nền
        chkRemember.setBounds(30, 335, 120, 25);
        chkRemember.setFocusPainted(false);
        mainPanel.add(chkRemember);

        // Forgot Password
        lblForgotPassword = new JLabel("Forgot Password?");
        lblForgotPassword.setFont(new Font("Arial", Font.PLAIN, 12));
        lblForgotPassword.setForeground(Color.WHITE);
        lblForgotPassword.setBounds(245, 335, 120, 25);
        lblForgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblForgotPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(Login.this,
                        "Please contact Admin to reset password.",
                        "Forgot Password", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        mainPanel.add(lblForgotPassword);

        // --- BUTTON LOGIN (Custom Paint) ---
        btnLogin = new JButton("LOGIN") {
            @Override
            protected void paintComponent(Graphics g) {
                // Vẽ nền nút bán trong suốt
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(new Color(255, 255, 255, 40)); // Màu trắng mờ
        btnLogin.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        btnLogin.setBounds(105, 385, 180, 45);
        btnLogin.setFocusPainted(false);
        btnLogin.setContentAreaFilled(false);
        btnLogin.setOpaque(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover Effect
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnLogin.setBackground(new Color(255, 255, 255, 80)); // Sáng hơn khi hover
                btnLogin.repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnLogin.setBackground(new Color(255, 255, 255, 40)); // Trở về cũ
                btnLogin.repaint();
            }
        });
        btnLogin.addActionListener(e -> handleLogin());
        mainPanel.add(btnLogin);

        add(mainPanel);

        // Enter key shortcut
        getRootPane().setDefaultButton(btnLogin);
    }

    // --- HÀM FIX LỖI GIAO DIỆN QUAN TRỌNG NHẤT ---
    private JPanel createInputPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                // Tự vẽ hình chữ nhật nền mờ
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        panel.setOpaque(false); // Báo trong suốt để vẽ đè lên Gradient chính
        panel.setLayout(null);
        panel.setBackground(new Color(255, 255, 255, 30)); // Độ mờ 30
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 100), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return panel;
    }

    private JButton createIconButton(String text, int x, int y, int w, int h) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, w, h);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(0,0,0,0));
        btn.setBorder(null);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.equals("CHAULONG") && password.equals("1011")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");

            // Chuyển trang
            this.dispose();
            SwingUtilities.invokeLater(() -> {
                // Mở màn hình chính mà bạn đã có code trong link chat
                new Dashboard().setVisible(true);
            });
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Username or Password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
}