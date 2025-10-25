import java.awt.*;
import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class frmHitungHari extends JFrame {

    private JComboBox<String> cmbBulan;
    private JSpinner spinTahun, spinTanggalAwal, spinTanggalAkhir;
    private JLabel lblJudul, lblJumlahHari, lblHariPertama, lblHariTerakhir, lblSelisihHari;
    private JLabel lblJudulJumlahHari, lblJudulHariPertama, lblJudulHariTerakhir, lblJudulSelisihHari;
    private JButton btnHitung, btnReset, btnKeluar;
    private JPanel panelUtama;

    public frmHitungHari() {
        setTitle("Aplikasi Penghitung Hari");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 550);
        setLocationRelativeTo(null);
        setResizable(false);

        panelUtama = new JPanel();
        panelUtama.setBackground(new Color(242, 242, 242));
        panelUtama.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Judul besar di tengah
        lblJudul = new JLabel("Aplikasi Penghitung Hari", SwingConstants.CENTER);
        lblJudul.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblJudul.setForeground(new Color(30, 60, 90));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panelUtama.add(lblJudul, gbc);
        gbc.gridwidth = 1;

        // Input Bulan
        gbc.gridx = 0; gbc.gridy = 1;
        panelUtama.add(new JLabel("Pilih Bulan:"), gbc);
        cmbBulan = new JComboBox<>(new String[] {
            "Januari","Februari","Maret","April","Mei","Juni",
            "Juli","Agustus","September","Oktober","November","Desember"
        });
        gbc.gridx = 1;
        panelUtama.add(cmbBulan, gbc);

        // Input Tahun
        gbc.gridx = 0; gbc.gridy = 2;
        panelUtama.add(new JLabel("Pilih Tahun:"), gbc);
        spinTahun = new JSpinner(new SpinnerNumberModel(2025, 1900, 3000, 1));
        gbc.gridx = 1;
        panelUtama.add(spinTahun, gbc);

        // Input Tanggal Awal
        gbc.gridx = 0; gbc.gridy = 3;
        panelUtama.add(new JLabel("Tanggal Awal:"), gbc);
        spinTanggalAwal = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        gbc.gridx = 1;
        panelUtama.add(spinTanggalAwal, gbc);

        // Input Tanggal Akhir
        gbc.gridx = 0; gbc.gridy = 4;
        panelUtama.add(new JLabel("Tanggal Akhir:"), gbc);
        spinTanggalAkhir = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        gbc.gridx = 1;
        panelUtama.add(spinTanggalAkhir, gbc);

        // Panel tombol
        JPanel panelTombol = new JPanel();
        panelTombol.setBackground(new Color(242, 242, 242));
        btnHitung = new JButton("Hitung");
        btnReset = new JButton("Reset");
        btnKeluar = new JButton("Keluar");

        JButton[] buttons = {btnHitung, btnReset, btnKeluar};
        for (JButton b : buttons) {
            b.setBackground(new Color(100, 149, 237));
            b.setForeground(Color.WHITE);
            b.setFont(new Font("Segoe UI", Font.BOLD, 13));
            b.setFocusPainted(false);
        }

        panelTombol.add(btnHitung);
        panelTombol.add(btnReset);
        panelTombol.add(btnKeluar);
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        panelUtama.add(panelTombol, gbc);

        // Label hasil
        lblJudulJumlahHari = new JLabel("Jumlah Hari:");
        lblJudulHariPertama = new JLabel("Hari Pertama:");
        lblJudulHariTerakhir = new JLabel("Hari Terakhir:");
        lblJudulSelisihHari = new JLabel("Selisih Hari:");
        lblJumlahHari = new JLabel("-", SwingConstants.LEFT);
        lblHariPertama = new JLabel("-", SwingConstants.LEFT);
        lblHariTerakhir = new JLabel("-", SwingConstants.LEFT);
        lblSelisihHari = new JLabel("-", SwingConstants.LEFT);

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 6; panelUtama.add(lblJudulJumlahHari, gbc);
        gbc.gridx = 1; panelUtama.add(lblJumlahHari, gbc);
        gbc.gridx = 0; gbc.gridy = 7; panelUtama.add(lblJudulHariPertama, gbc);
        gbc.gridx = 1; panelUtama.add(lblHariPertama, gbc);
        gbc.gridx = 0; gbc.gridy = 8; panelUtama.add(lblJudulHariTerakhir, gbc);
        gbc.gridx = 1; panelUtama.add(lblHariTerakhir, gbc);
        gbc.gridx = 0; gbc.gridy = 9; panelUtama.add(lblJudulSelisihHari, gbc);
        gbc.gridx = 1; panelUtama.add(lblSelisihHari, gbc);

        add(panelUtama);

        // Event
        btnHitung.addActionListener(e -> hitungHasil());
        btnReset.addActionListener(e -> resetForm());
        btnKeluar.addActionListener(e -> System.exit(0));
    }

    private void hitungHasil() {
        int bulan = cmbBulan.getSelectedIndex() + 1;
        int tahun = (int) spinTahun.getValue();
        LocalDate tanggal = LocalDate.of(tahun, bulan, 1);

        int jumlahHari = tanggal.lengthOfMonth();
        String hariPertama = ubahHariKeIndonesia(tanggal.getDayOfWeek().toString());
        String hariTerakhir = ubahHariKeIndonesia(tanggal.withDayOfMonth(jumlahHari).getDayOfWeek().toString());

        lblJumlahHari.setText(jumlahHari + " hari");
        lblHariPertama.setText(hariPertama);
        lblHariTerakhir.setText(hariTerakhir);

        int tglAwal = (int) spinTanggalAwal.getValue();
        int tglAkhir = (int) spinTanggalAkhir.getValue();
        if (tglAwal > jumlahHari) tglAwal = jumlahHari;
        if (tglAkhir > jumlahHari) tglAkhir = jumlahHari;

        LocalDate d1 = LocalDate.of(tahun, bulan, tglAwal);
        LocalDate d2 = LocalDate.of(tahun, bulan, tglAkhir);
        long selisih = ChronoUnit.DAYS.between(d1, d2);
        lblSelisihHari.setText(Math.abs(selisih) + " hari");
    }

    private void resetForm() {
        cmbBulan.setSelectedIndex(0);
        spinTahun.setValue(2025);
        spinTanggalAwal.setValue(1);
        spinTanggalAkhir.setValue(1);
        lblJumlahHari.setText("-");
        lblHariPertama.setText("-");
        lblHariTerakhir.setText("-");
        lblSelisihHari.setText("-");
    }

    private String ubahHariKeIndonesia(String day) {
        switch (day) {
            case "MONDAY": return "Senin";
            case "TUESDAY": return "Selasa";
            case "WEDNESDAY": return "Rabu";
            case "THURSDAY": return "Kamis";
            case "FRIDAY": return "Jumat";
            case "SATURDAY": return "Sabtu";
            case "SUNDAY": return "Minggu";
            default: return day;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new frmHitungHari().setVisible(true));
    }
}
