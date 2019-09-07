/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlhocvien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.awt.image.ImageObserver.WIDTH;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Admin
 */
public class QLHV extends JFrame {

    Connection cn;
    JPanel pnlFrame, pnlTop, pnlCenter, pnlTC, pnlCC, pnlBC, pnlLeft;
    JScrollPane scrKhoaHoc, scrHocvien;
    JTable tblKhoaHoc, tblHocvien;
    JButton btnThem, btnSua, btnXoa, btnTimKiem, btnLuu, btnBoQua;
    JTextField txtMaHV, txtTenHV, txtNgaySinh, txtSDT, txtEmail, txtGhiChu, txtTimKiem;
    JRadioButton radNam, radNu;
    JCheckBox chk;
    ButtonGroup btnGr;
    DefaultTableModel modelKhoaHoc, modelHocVien;
    JComboBox cbx;
    int ChucNang, ChucNangKH;
    JPopupMenu mnuPopup;
    JMenuItem mnuThem, mnuSua, mnuXoa;
    JDialog dialog;

    public void createComponent() {
        pnlFrame = new JPanel();
        pnlTop = new JPanel();
        pnlCenter = new JPanel();
        pnlTC = new JPanel();
        pnlCC = new JPanel();
        pnlBC = new JPanel();
        pnlLeft = new JPanel();
        pnlTop.setLayout(new FlowLayout());
        pnlCenter.setLayout(new BorderLayout());
        pnlFrame.setLayout(new BorderLayout());
        pnlBC.setLayout(new BorderLayout());
        pnlTC.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        pnlCC.setLayout(new GridBagLayout());
        pnlCC.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnlLeft.setLayout(new BorderLayout());
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xoá");
        btnTimKiem = new JButton("Tìm kiếm");
        btnLuu = new JButton("Lưu");
        btnBoQua = new JButton("Bỏ qua");
        TienIch.setSiz(btnThem, 100, 25);
        TienIch.setSiz(btnSua, 100, 25);
        TienIch.setSiz(btnXoa, 100, 25);
        TienIch.setSiz(btnLuu, 100, 25);
        TienIch.setSiz(btnBoQua, 100, 25);
        tblHocvien = new JTable();
        tblKhoaHoc = new JTable();
        scrHocvien = new JScrollPane();
        scrKhoaHoc = new JScrollPane();
        TienIch.setSiz(scrHocvien, 700, 220);
        TienIch.setSiz(scrKhoaHoc, 150, 500);
        txtMaHV = new JTextField();
        TienIch.setSiz(txtMaHV, 100, 20);
        txtTenHV = new JTextField();
        TienIch.setSiz(txtTenHV, 180, 20);
        txtNgaySinh = new JTextField();
        txtSDT = new JTextField();
        txtEmail = new JTextField();
        txtGhiChu = new JTextField();
        txtTimKiem = new JTextField();
        btnGr = new ButtonGroup();
        radNam = new JRadioButton("Nam");
        radNu = new JRadioButton("Nữ");
        btnGr.add(radNu);
        btnGr.add(radNam);
        chk = new JCheckBox("Chỉ hiển thị khoá học có học viên");
        cbx = new JComboBox();
        chk.setFont(new Font("Times New Roman", Font.TYPE1_FONT, 20));
        ChucNang = 0;
        ChucNangKH = 0;

        String headerKH[] = {"Mã KH", "Tên khoá học"};
        String dataKH[][] = {};
        String headerHV[] = {"Mã HV", "Tên học viên", "Giới tính", "Ngày sinh", "Số điện thoại", "Email", "Ghi chú"};
        String dataHV[][] = {};
        modelHocVien = new DefaultTableModel(dataHV, headerHV);
        modelKhoaHoc = new DefaultTableModel(dataKH, headerKH);
        tblHocvien.setModel(modelHocVien);
        tblKhoaHoc.setModel(modelKhoaHoc);

        mnuPopup = new JPopupMenu();
        mnuThem = new JMenuItem("Thêm");
        mnuSua = new JMenuItem("Sửa");
        mnuXoa = new JMenuItem("Xoá");
        mnuPopup.add(mnuThem);
        mnuPopup.add(mnuSua);
        mnuPopup.add(mnuXoa);

    }

    public QLHV() {
        createComponent();
        setSize(1000, 550);
        setVisible(true);
        setTitle("Quản lý học viên");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        cn = TienIch.Connections("QLHV");
        if (cn == null) {
            JOptionPane.showMessageDialog(this, "Kết nối thất bại");
        }
        add(pnlFrame);
        pnlFrame.add(pnlTop, BorderLayout.NORTH);
        JLabel title = new JLabel("_______________________________________Quản lý học viên_______________________________________");
        title.setFont(new Font("Times New Roman", Font.BOLD, 30));
        pnlTop.add(title);

        pnlFrame.add(pnlLeft, BorderLayout.WEST);
        pnlLeft.add(chk, BorderLayout.NORTH);
        scrKhoaHoc.getViewport().add(tblKhoaHoc);
        pnlLeft.add(scrKhoaHoc, BorderLayout.CENTER);

        pnlFrame.add(pnlCenter, BorderLayout.CENTER);
        pnlCenter.add(pnlTC, BorderLayout.NORTH);
        pnlTC.add(btnThem);
        pnlTC.add(btnSua);
        pnlTC.add(btnXoa);
        pnlTC.add(btnLuu);
        pnlTC.add(btnBoQua);

        pnlCenter.add(pnlCC, BorderLayout.CENTER);
        pnlCC.add(new JLabel("Khoá học"), new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        pnlCC.add(cbx, new GridBagConstraints(1, 0, 9, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        pnlCC.add(new JLabel("Mã học viên"), new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        pnlCC.add(txtMaHV, new GridBagConstraints(1, 1, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        pnlCC.add(new JLabel("Họ và tên"), new GridBagConstraints(3, 1, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        pnlCC.add(txtTenHV, new GridBagConstraints(4, 1, 6, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        pnlCC.add(new JLabel("Giới tính"), new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        pnlCC.add(radNam, new GridBagConstraints(1, 2, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        pnlCC.add(radNu, new GridBagConstraints(2, 2, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        pnlCC.add(new JLabel("Ngày sinh"), new GridBagConstraints(3, 2, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        pnlCC.add(txtNgaySinh, new GridBagConstraints(4, 2, 2, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        pnlCC.add(new JLabel("Số điện thoại"), new GridBagConstraints(0, 3, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        pnlCC.add(txtSDT, new GridBagConstraints(1, 3, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        pnlCC.add(new JLabel("Email"), new GridBagConstraints(3, 3, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        pnlCC.add(txtEmail, new GridBagConstraints(4, 3, 2, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        pnlCC.add(new JLabel("Ghi chú"), new GridBagConstraints(0, 4, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        pnlCC.add(txtGhiChu, new GridBagConstraints(1, 4, 5, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        pnlCC.add(txtTimKiem, new GridBagConstraints(0, 5, 2, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        pnlCC.add(btnTimKiem, new GridBagConstraints(2, 5, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        pnlCenter.add(pnlBC, BorderLayout.SOUTH);
        scrHocvien.getViewport().add(tblHocvien);
        pnlBC.add(scrHocvien, BorderLayout.CENTER);

        fillToKhoaHoc(0);
        setBtnEnable();
        act();
    }

    public void fillToKhoaHoc(int SoDongToiThieu) {
        try {
            String sql = "select * from KhoaHoc where (select COUNT(*) from HocVien where HocVien.MaKH=KhoaHoc.MaKH)>=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, SoDongToiThieu);
            ResultSet rs = pst.executeQuery();
            modelKhoaHoc.setRowCount(0);
            cbx.removeAllItems();
            cbx.addItem(">> Chọn khoá học <<");
            while (rs.next()) {
                modelKhoaHoc.addRow(new Object[]{rs.getInt(1), rs.getString(2)});
                cbx.addItem(rs.getString(2));
            }
            if (tblKhoaHoc.getRowCount() > 0) {
                fillToHocVien(tblKhoaHoc.getValueAt(0, 0).toString());
                tblKhoaHoc.setRowSelectionInterval(0, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public String getGT(Boolean GT) {
        if (GT == true) {
            return "Nam";
        } else {
            return "Nữ";
        }
    }

    public boolean setGT() {
        if (radNam.isSelected()) {
            return true;
        } else {
            return false;
        }
    }

    public void fillToHocVien(String MaKH) {
        try {
            String sql = "Select MaHV,HoTen,GioiTinh,Convert(varchar,NgaySinh,103),SDT,Email,GhiChu from HocVien where MaKH=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, MaKH);
            ResultSet rs = pst.executeQuery();
            modelHocVien.setRowCount(0);
            while (rs.next()) {
                modelHocVien.addRow(new Object[]{rs.getInt(1), rs.getString(2), getGT(rs.getBoolean(3)),
                    rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});
            }
            if (tblHocvien.getRowCount() > 0) {
                showDetail(tblHocvien.getValueAt(0, 0).toString());
                btnXoa.setEnabled(true);
                btnSua.setEnabled(true);
                tblHocvien.setRowSelectionInterval(0, 0);
            } else {
                btnXoa.setEnabled(false);
                btnSua.setEnabled(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void showDetail(String MaHV) {
        try {
            String sql = "Select MaHV,HoTen,GioiTinh,Convert(varchar,NgaySinh,103),SDT,Email,GhiChu, TenKH from HocVien join KhoaHoc on HocVien.MaKH= KhoaHoc.MaKH where MaHV=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, MaHV);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                txtMaHV.setText(Integer.toString(rs.getInt(1)));
                txtTenHV.setText(rs.getString(2));
                if (rs.getBoolean(3)) {
                    radNam.setSelected(true);
                } else {
                    radNu.setSelected(true);
                }
                txtNgaySinh.setText(rs.getString(4));
                txtSDT.setText(rs.getString(5));
                txtEmail.setText(rs.getString(6));
                txtGhiChu.setText(rs.getString(7));
                cbx.setSelectedItem(rs.getString(8));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void clearForm() {
        txtMaHV.setText("");
        txtTenHV.setText("");
        txtNgaySinh.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        txtGhiChu.setText("");
        txtTimKiem.setText("");
        cbx.setSelectedIndex(0);
        btnGr.clearSelection();
    }

    public void TimKiem() {
        try {
            String sql;
            modelHocVien.setRowCount(0);
            sql = "Select * from HocVien where MaHV like ? or MaKH like ? or Hoten like ? "
                    + "or GioiTinh like ? or Ngaysinh like ? or SDT like ? or Email like ? or GhiChu like ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, txtTimKiem.getText());
            pst.setString(2, txtTimKiem.getText());
            pst.setString(3, txtTimKiem.getText());
            if (txtTimKiem.getText().equalsIgnoreCase("Nam") || txtTimKiem.getText().equalsIgnoreCase("Nữ")) {
                boolean gt = true;
                if (txtTimKiem.getText().equalsIgnoreCase("Nam")) {
                    gt = true;
                } else {
                    gt = false;
                }
                pst.setBoolean(4, gt);
            } else {
                pst.setString(4, "");
            }
            pst.setString(5, txtTimKiem.getText());
            pst.setString(6, txtTimKiem.getText());
            pst.setString(7, txtTimKiem.getText());
            pst.setString(8, txtTimKiem.getText());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                modelHocVien.addRow(new Object[]{rs.getInt(1), rs.getString(3), getGT(rs.getBoolean(4)),
                    rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8)});
            }
            JOptionPane.showMessageDialog(this, "Có " + tblHocvien.getRowCount() + " kết quả phù hợp với từ khoá tìm kiếm");
            if (tblHocvien.getRowCount() > 0) {
                showDetail(tblHocvien.getValueAt(0, 0).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void Them() {
        try {
            String sql = "insert into HocVien values(?,?,?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(tblKhoaHoc.getValueAt(cbx.getSelectedIndex() - 1, 0).toString()));
            pst.setString(2, txtTenHV.getText());
            pst.setBoolean(3, setGT());
            pst.setString(4, ConvertDateJtoS());
            pst.setString(5, txtSDT.getText());
            pst.setString(6, txtEmail.getText());
            pst.setString(7, txtGhiChu.getText());
            pst.executeUpdate();
            fillToHocVien(tblKhoaHoc.getValueAt(cbx.getSelectedIndex() - 1, 0).toString());
            tblKhoaHoc.setRowSelectionInterval(cbx.getSelectedIndex() - 1, cbx.getSelectedIndex() - 1);
            tblHocvien.setRowSelectionInterval(tblHocvien.getRowCount() - 1, tblHocvien.getRowCount() - 1);

        } catch (Exception e) {
            System.out.println("Lỗi thêm mới: " + e);
            e.printStackTrace();
        }
    }

    public void Xoa() {
        try {
            String sql = "delete hocvien where MaHV=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, tblHocvien.getValueAt(tblHocvien.getSelectedRow(), 0).toString());
            int check = pst.executeUpdate();
            if (check == 0) {
                JOptionPane.showMessageDialog(null, "Xóa không thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi Xoá: " + e);
        }
        fillToHocVien(tblKhoaHoc.getValueAt(cbx.getSelectedIndex() - 1, 0).toString());
    }

    public void XoaKH(String MaKH) {
        try {
            String sql = "delete KhoaHoc where MaKH=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, MaKH);
            int check = pst.executeUpdate();
            if (check == 0) {
                JOptionPane.showMessageDialog(null, "Xóa không thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi Xoá: " + e);
        }
        fillToKhoaHoc(0);
    }

    public void Sua() {
        try {
            String MaHV = txtMaHV.getText();
            String sql = "update HocVien set MaKH=?, HoTen=?, GioiTinh=?,NgaySinh=?, SDT=?, Email=?, GhiChu=?  where MaHV=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(tblKhoaHoc.getValueAt(cbx.getSelectedIndex() - 1, 0).toString()));
            pst.setString(2, txtTenHV.getText());
            pst.setBoolean(3, setGT());
            pst.setString(4, ConvertDateJtoS());
            pst.setString(5, txtSDT.getText());
            pst.setString(6, txtEmail.getText());
            pst.setString(7, txtGhiChu.getText());
            pst.setString(8, txtMaHV.getText());
            pst.executeUpdate();
            fillToHocVien(tblKhoaHoc.getValueAt(cbx.getSelectedIndex() - 1, 0).toString());

            for (int i = 0; i < tblHocvien.getRowCount(); i++) {
                if (tblHocvien.getValueAt(i, 0).toString().equalsIgnoreCase(MaHV)) {
                    tblHocvien.setRowSelectionInterval(i, i);
                }
            }
            showDetail(MaHV);
        } catch (Exception e) {
            System.out.println("Lỗi sửa: " + e);
            e.printStackTrace();
        }
    }

    public void checkEmail() {
        String patE = "\\w+@\\w+\\.\\w+";
        if (!txtEmail.getText().matches(patE)) {
            JOptionPane.showMessageDialog(this, "Sai định dạng Email");
            txtEmail.requestFocus();
        }
    }

    public void checkSDT() {
        String patP = "0\\d{9,10}";
        if (!txtSDT.getText().matches(patP)) {
            JOptionPane.showMessageDialog(this, "Sai định dạng số điện thoại");
            txtSDT.requestFocus();
        }
    }

    public void checkNS() {
        try {
            if (txtNgaySinh.getText().length() != 10) {
                JOptionPane.showMessageDialog(null, "Hãy nhập ngày sinh theo định dạng dd/mm/yyyy");
                txtNgaySinh.requestFocus();
            } else if (txtNgaySinh.getText().substring(2, 3).equals("/") || txtNgaySinh.getText().substring(5, 6).equals("/")) {
                Integer.parseInt(txtNgaySinh.getText().substring(0, 2));
                Integer.parseInt(txtNgaySinh.getText().substring(3, 5));
                Integer.parseInt(txtNgaySinh.getText().substring(6, 10));
            } else {
                JOptionPane.showMessageDialog(null, "Sai định dạng ngày - tháng - năm (dd/mm/yyyy)");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ngày tháng năm phải là số");
        }

    }

    public String ConvertDateJtoS() {
        return txtNgaySinh.getText().substring(6, 10) + "/" + txtNgaySinh.getText().substring(3, 5)
                + "/" + txtNgaySinh.getText().substring(0, 2);
    }

    public boolean checkNull() {
        if (cbx.getSelectedIndex() == 0) {
            cbx.requestFocus();
            return false;
        } else if (txtTenHV.getText().length() == 0) {
            txtTenHV.requestFocus();
            return false;
        } else if (!radNam.isSelected() && !radNu.isSelected()) {
            radNam.requestFocus();
            return false;
        } else if (txtNgaySinh.getText().length() == 0) {
            txtNgaySinh.requestFocus();
            return false;
        } else if (txtSDT.getText().length() == 0) {
            cbx.requestFocus();
            return false;
        } else if (txtEmail.getText().length() == 0) {
            cbx.requestFocus();
            return false;
        } else if (txtGhiChu.getText().length() == 0) {
            cbx.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public void setBtnEnable() {

        switch (ChucNang) {
            case 0:
                btnBoQua.setEnabled(false);
                btnLuu.setEnabled(false);
                btnThem.setEnabled(true);
                btnSua.setEnabled(true);
                btnXoa.setEnabled(true);
                btnTimKiem.setEnabled(true);
                btnThem.setText("Thêm");
                btnSua.setText("Sửa");
                btnXoa.setText("Xoá");
                tblHocvien.setEnabled(true);
                tblKhoaHoc.setEnabled(true);

                cbx.setEnabled(false);
                txtEmail.setEditable(false);
                txtTenHV.setEditable(false);
                txtMaHV.setEditable(false);
                txtGhiChu.setEditable(false);
                txtNgaySinh.setEditable(false);
                radNam.setEnabled(false);
                radNu.setEnabled(false);
                txtSDT.setEditable(false);
                txtTimKiem.setEditable(true);
                break;
            default:
                btnThem.setEnabled(false);
                btnSua.setEnabled(false);
                btnXoa.setEnabled(false);
                btnBoQua.setEnabled(true);
                btnLuu.setEnabled(true);
                btnTimKiem.setEnabled(false);
                tblHocvien.setEnabled(false);
                tblKhoaHoc.setEnabled(false);

                cbx.setEnabled(true);
                txtEmail.setEditable(true);
                txtTenHV.setEditable(true);
                txtGhiChu.setEditable(true);
                txtNgaySinh.setEditable(true);
                txtSDT.setEditable(true);
                txtTimKiem.setEditable(false);
                radNam.setEnabled(true);
                radNu.setEnabled(true);
        }
    }

    public void act() {
        tblKhoaHoc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mevt) {
                if (tblKhoaHoc.isEnabled()) {
                    fillToHocVien(tblKhoaHoc.getValueAt(tblKhoaHoc.getSelectedRow(), 0).toString());
                    if (tblHocvien.getRowCount() > 0) {
                        showDetail(tblHocvien.getValueAt(0, 0).toString());
                        tblHocvien.setRowSelectionInterval(0, 0);
                    } else {
                        clearForm();
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent mevt) {
                if (mevt.getButton() == 3) {
                    tblKhoaHoc.setRowSelectionInterval(tblKhoaHoc.rowAtPoint(mevt.getPoint()), tblKhoaHoc.rowAtPoint(mevt.getPoint()));
                    mnuPopup.show(mevt.getComponent(), mevt.getX(), mevt.getY());
                }
            }

        });
        tblHocvien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mevt) {
                if (tblHocvien.isEnabled()) {
                    showDetail(tblHocvien.getValueAt(tblHocvien.getSelectedRow(), 0).toString());
                    tblKhoaHoc.clearSelection();
                }
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnThem.setText(">> THÊM <<");
                ChucNang = 1;
                clearForm();
                setBtnEnable();
            }
        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnSua.setText(">> SỬA <<");
                ChucNang = 2;
                setBtnEnable();
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.showConfirmDialog(null, "Bạn có thực sự muốn xoá", "Xác nhận xoá", JOptionPane.YES_NO_OPTION) == 0) {
                    Xoa();
                    if (tblHocvien.getRowCount() > 0) {
                        tblHocvien.setRowSelectionInterval(0, 0);
                    } else {
                        XoaKH(tblKhoaHoc.getValueAt(cbx.getSelectedIndex() - 1, 0).toString());
                        clearForm();
                    }
                }
            }
        });
        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (checkNull()) {
                    switch (ChucNang) {
                        case 1:
                            Them();
                            ChucNang = 0;
                            break;
                        case 2:
                            Sua();
                            ChucNang = 0;
                            break;
                    }
                    setBtnEnable();
                } else {
                    JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
                }

            }
        });
        btnBoQua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ChucNang = 0;
                fillToKhoaHoc(0);
                if (tblKhoaHoc.getRowCount() > 0) {
                    fillToHocVien(tblKhoaHoc.getValueAt(0, 0).toString());
                }
                setBtnEnable();
            }
        });
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                TimKiem();
            }
        });
        chk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (chk.isSelected()) {
                    fillToKhoaHoc(1);
                    if (tblHocvien.getRowCount() > 0) {
                        showDetail(tblHocvien.getValueAt(0, 0).toString());
                    }
                } else {
                    fillToKhoaHoc(0);
                    if (tblHocvien.getRowCount() > 0) {
                        showDetail(tblHocvien.getValueAt(0, 0).toString());
                    }
                }
            }
        });

        txtEmail.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                checkEmail();
            }
        });
        txtSDT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                checkSDT();
            }
        });
        txtNgaySinh.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                checkNS();
            }
        });
        mnuThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ChucNangKH = 1;
                dialog = new Dialog(cn, tblKhoaHoc.getValueAt(tblKhoaHoc.getSelectedRow(), 0).toString(), ChucNangKH);
            }
        });

        mnuSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ChucNangKH = 2;
                new Dialog(cn, tblKhoaHoc.getValueAt(tblKhoaHoc.getSelectedRow(), 0).toString(), ChucNangKH);
            }
        });
        mnuXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                XoaKH(tblKhoaHoc.getValueAt(tblKhoaHoc.getSelectedRow(), 0).toString());
            }
        });
    }

    public static void main(String[] args) {
        QLHV ql = new QLHV();
    }
}
