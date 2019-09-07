/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlhocvien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JDialog;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.WindowListener;

/**
 *
 * @author Admin
 */
public class Dialog extends JDialog {

    JDialog Dialog;
    JLabel lblmakh, lbltenkh;
    JTextField txtmakh, txttenkh;
    JButton btnXacNhan;
    JPanel pnlMain;
    Connection cnt;
    int ChucNang;
    String MaKhoaHoc;

    public Dialog(Connection cn, String MaKH, int ChucNangKH) {
        MaKhoaHoc = MaKH;
        ChucNang = ChucNangKH;
        Dialog = new JDialog();
        cnt = cn;
        if (cnt == null) {
            JOptionPane.showMessageDialog(null, "Kết nối thất bại");
        }
        createComponent();
        setSize(500, 340);
        setVisible(true);
        setTitle("Thêm - sửa - xoá");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        add(pnlMain);
        pnlMain.add(lblmakh, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        pnlMain.add(lbltenkh, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        pnlMain.add(txtmakh, new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        pnlMain.add(txttenkh, new GridBagConstraints(1, 1, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        pnlMain.add(btnXacNhan, new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        act();
        switch (ChucNangKH) {
            case 1:
                txtmakh.setEditable(false);
                break;
            case 2:
                txtmakh.setText(MaKhoaHoc);
                txtmakh.setEditable(false);
                break;
        }
    }

    private void createComponent() {
        lblmakh = new JLabel("Mã Khóa Học");
        lbltenkh = new JLabel("Tên Khóa Học");
        txtmakh = new JTextField();
        txttenkh = new JTextField();
        TienIch.setSiz(txtmakh, 100, 20);
        TienIch.setSiz(txttenkh, 100, 20);
        btnXacNhan = new JButton("Xác nhận");
        pnlMain = new JPanel(new GridBagLayout());
    }

    public void Them() {
        try {
            String sql = "insert into KhoaHoc values(?)";
            PreparedStatement pst = cnt.prepareStatement(sql);
            pst.setString(1, txttenkh.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗ thêm mới: " + e);
        }
    }

    public void Sua() {
        try {
            String sql = "update KhoaHoc set TenKH=? where MaKH=?";
            PreparedStatement pst = cnt.prepareStatement(sql);
            pst.setString(1, txttenkh.getText());
            pst.setString(2, MaKhoaHoc);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sửa thành công");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi sửa: " + e);
        }
    }

    public void act() {
        btnXacNhan.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                switch (ChucNang) {
                    case 1:
                        Them();
                        break;
                    case 2:
                        Sua();
                        break;
                }
                QLHV ql = new QLHV();
                ql.fillToKhoaHoc(0);
                if (ql.tblKhoaHoc.getRowCount() > 0) {
                    ql.fillToHocVien(ql.tblKhoaHoc.getValueAt(ql.tblKhoaHoc.getSelectedRow(), 0).toString());
                    if (ql.tblHocvien.getRowCount() > 0) {
                        ql.showDetail(ql.tblHocvien.getValueAt(0, 0).toString());
                    } else {
                        ql.clearForm();
                    }
                }
                
            }
        });
    }
}
