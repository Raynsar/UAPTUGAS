import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

class SistemAbsensiMahasiswa extends JFrame {
    private JTextField txtNIM, txtNama;
    private JButton btnTambah, btnEdit, btnHapus, btnCari, btnReset;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblImage;
    private String selectedImagePath = "";
    private List<String[]> absensiData = new ArrayList<>();
    static boolean isLoggedIn = false;

    public SistemAbsensiMahasiswa() {
        setTitle("Sistem Absensi Mahasiswa");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Header
        JLabel lblHeader = new JLabel("SISTEM ABSENSI MAHASISWA", JLabel.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 24));
        lblHeader.setForeground(Color.DARK_GRAY);
        add(lblHeader, BorderLayout.NORTH);

        // Panel Input
        JPanel panelInput = new JPanel(new GridLayout(4, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createTitledBorder("Form Absensi"));
        panelInput.setBackground(new Color(245, 245, 245));

        txtNIM = new JTextField();
        txtNama = new JTextField();

        lblImage = new JLabel("No Image Selected", JLabel.CENTER);
        lblImage.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblImage.setPreferredSize(new Dimension(150, 150));

        JButton btnUpload = new JButton("Upload Foto");
        btnUpload.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                lblImage.setText("");
                lblImage.setIcon(new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage()
                        .getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
                selectedImagePath = ((java.io.File) file).getAbsolutePath();
            }
        });

        panelInput.add(new JLabel("NIM:"));
        panelInput.add(txtNIM);
        panelInput.add(new JLabel("Nama:"));
        panelInput.add(txtNama);
        panelInput.add(lblImage);
        panelInput.add(btnUpload);

        // Buttons
        btnTambah = new JButton("Tambah");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnCari = new JButton("Cari");
        btnReset = new JButton("Reset Form");

        JPanel panelButtons = new JPanel(new GridLayout(1, 5, 10, 10));
        panelButtons.setBackground(new Color(213, 161, 161));
        panelButtons.add(btnTambah);
        panelButtons.add(btnEdit);
        panelButtons.add(btnHapus);
        panelButtons.add(btnCari);
        panelButtons.add(btnReset);

        // Table
        String[] columnNames = {"NIM", "Nama", "Waktu", "Foto"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Layout
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.add(panelInput, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(panelButtons, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

        // Event Listeners
        btnTambah.addActionListener(e -> {
            if (!isLoggedIn) {
                JOptionPane.showMessageDialog(this, "Silakan login terlebih dahulu untuk menambah data.", "Kesalahan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            tambahData();
        });
        btnEdit.addActionListener(e -> editData());
        btnHapus.addActionListener(e -> hapusData());
        btnCari.addActionListener(e -> cariData());
        btnReset.addActionListener(e -> clearForm());

        refreshTable();
    }

    private void tambahData() {
        try {
            String nim = txtNIM.getText();
            String nama = txtNama.getText();

            if (nim.isEmpty() || nama.isEmpty()) {
                throw new IllegalArgumentException("NIM dan Nama tidak boleh kosong!");
            }

            String waktu = java.time.LocalDateTime.now().toString();
            absensiData.add(new String[]{nim, nama, waktu, selectedImagePath});
            refreshTable();
            clearForm();
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editData() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String nim = tableModel.getValueAt(selectedRow, 0).toString();
            String nama = tableModel.getValueAt(selectedRow, 1).toString();
            txtNIM.setText(nim);
            txtNama.setText(nama);
            lblImage.setIcon(null);
            lblImage.setText("No Image Selected");
            selectedImagePath = "";
            absensiData.removeIf(row -> row[0].equals(nim));
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin diedit!", "Kesalahan", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void hapusData() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String nim = tableModel.getValueAt(selectedRow, 0).toString();
            absensiData.removeIf(row -> row[0].equals(nim));
            refreshTable();
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!", "Kesalahan", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void cariData() {
        String nimCari = JOptionPane.showInputDialog(this, "Masukkan NIM yang ingin dicari:");
        if (nimCari != null && !nimCari.isEmpty()) {
            for (String[] row : absensiData) {
                if (row[0].equalsIgnoreCase(nimCari)) {
                    JOptionPane.showMessageDialog(this, "Data ditemukan:\nNIM: " + row[0] + "\nNama: " + row[1]);
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Data dengan NIM tersebut tidak ditemukan.");
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (String[] row : absensiData) {
            tableModel.addRow(row);
        }
    }

    private void clearForm() {
        txtNIM.setText("");
        txtNama.setText("");
        lblImage.setIcon(null);
        lblImage.setText("No Image Selected");
        selectedImagePath = "";
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}

class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle("Login Sistem Absensi");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblHeader = new JLabel("Login Sistem Absensi", JLabel.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblHeader, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtUsername = new JTextField();
        JPasswordField txtPassword = new JPasswordField();
        JButton btnLogin = new JButton("Login");

        panel.add(new JLabel("Username:"));
        panel.add(txtUsername);
        panel.add(new JLabel("Password:"));
        panel.add(txtPassword);
        panel.add(new JLabel(""));
        panel.add(btnLogin);

        add(panel, BorderLayout.CENTER);

        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());

            if (username.equals("admin") && password.equals("12345")) {
                SistemAbsensiMahasiswa.isLoggedIn = true;
                new SistemAbsensiMahasiswa().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Username atau password salah!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}
