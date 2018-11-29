/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.TableRowAlign;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 *
 * @author Manh VU
 */
public final class MyInterface extends javax.swing.JFrame {

    /**
     * Creates new form MyInterface
     */
    private boolean isUpdate = false;
    PreparedStatement ps = null;
    MyConnect myConnect = new MyConnect();
    private boolean fOut = false;

    public MyInterface() {
        initComponents();
        myConnect.connectDatabase();
        loadDataTable(bangSach);
        loadDataTable(bangNhanVien);
        loadDataTable(bangDocGia);
        loadDataTable(bangMuonTra);
        loadDataTable(bangCtMuonTra);

        getDataTableToComboBox();

        addDbSach.setEnabled(false);
        addDbDocGia.setEnabled(false);
        addDbNhanVien.setEnabled(false);
        bangCtMuonTra.setEnabled(false);

    }

    public void getDataTableToComboBox() {

        TableModel model1 = bangDocGia.getModel();
        TableModel model2 = bangNhanVien.getModel();
        TableModel model3 = bangSach.getModel();
        int rowCount1 = model1.getRowCount();
        int rowCount2 = model2.getRowCount();
        int rowCount3 = model3.getRowCount();
        for (int i = 0; i < rowCount1; i++) {
            for (int j = 0; j < 1; j++) {
                comboMaDG.addItem(model1.getValueAt(i, j).toString());
            }
        }

        for (int i = 0; i < rowCount2; i++) {
            for (int j = 0; j < 1; j++) {
                comboMaNV.addItem(model2.getValueAt(i, j).toString());
            }
        }

        for (int i = 0; i < rowCount3; i++) {
            for (int j = 0; j < 1; j++) {
                comboMaSach.addItem(model3.getValueAt(i, j).toString());
            }
        }
    }

    //load dữ liệu tới bảng
    public void loadDataTable(JTable table) {

        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = null;
        if (table.equals(bangSach)) {
            rs = myConnect.getData("sach");
        } else if (table.equals(bangDocGia)) {
            rs = myConnect.getData("docgia");
        } else if (table.equals(bangNhanVien)) {
            rs = myConnect.getData("nhanvien");
        } else if (table.equals(bangMuonTra)) {
            rs = myConnect.getData("muontra");
        } else {
            rs = myConnect.getData("ctmuontra");
        }

        try {
            ResultSetMetaData rsMD = rs.getMetaData();
            int colNumber = rsMD.getColumnCount();
            String[] arr = new String[colNumber];

            for (int i = 0; i < colNumber; i++) {
                arr[i] = rsMD.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(arr);

            while (rs.next()) {
                for (int i = 0; i < colNumber; i++) {
                    arr[i] = rs.getString(i + 1);
                }
                model.addRow(arr);
            }
        } catch (SQLException e) {

        }
        table.setModel(model);
    }

    public void delete(JTable table, String sqlCommand) {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Bạn phải chọn 1 hàng trong bảng", "Error Delete", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int select = JOptionPane.showOptionDialog(null, "Bạn có muốn xóa?", "Delete", 0, JOptionPane.YES_NO_OPTION, null, null, 1);
        if (select == 0) {
            myConnect.deleteDataById((String) table.getValueAt(row, 0), sqlCommand);
            loadDataTable(table);
        }
    }

    public void deleteCtMuonTra() {
        int row = -1;
        TableModel model = bangCtMuonTra.getModel();
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < 1; j++) {
                if (model.getValueAt(i, j).toString().equals(inputMaCTMT.getText().toString())) {
                    row = i;
                }
            }
        }

        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Bạn phải chọn 1 hàng trong bảng Mượn Trả ", "Error Delete", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int select = JOptionPane.showOptionDialog(null, "Bạn có muốn xóa?", "Delete", 0, JOptionPane.YES_NO_OPTION, null, null, 1);
        if (select == 0) {
            myConnect.deleteDataByIdCtMuonTra((String) bangCtMuonTra.getValueAt(row, 0), (String) bangCtMuonTra.getValueAt(row, 1));
            loadDataTable(bangMuonTra);
        }

        String id = inputMaCTMT.getText();
        ResultSet rs = null;
        DefaultTableModel model1 = new DefaultTableModel();

        rs = myConnect.getDataById(id, "select * from ctmuontra where MaMuon_Manhvd_20162679 = ?");

        try {
            ResultSetMetaData rsMD = rs.getMetaData();
            int colNumber = rsMD.getColumnCount();
            String[] arr = new String[colNumber];

            for (int i = 0; i < colNumber; i++) {
                arr[i] = rsMD.getColumnName(i + 1);
            }
            model1.setColumnIdentifiers(arr);

            while (rs.next()) {
                for (int i = 0; i < colNumber; i++) {
                    arr[i] = rs.getString(i + 1);
                }
                model1.addRow(arr);
            }
        } catch (SQLException e) {

        }
        bangCtMuonTra.setModel(model1);
        clearCtMuonTra();
    }

    /**
     * Các hàm lấy dữ liệu
     *
     */
    public Sach getSach() {
        String maSach = inputMaSach.getText();
        String tenSach = inputTenSach.getText();
        String tenTG = inputTenTacGia.getText();
        String nxb = inputNXB.getText();
        String namXB = inputNamXB.getText();
        int giaTien = Integer.parseInt(inputGiaTien.getText());
        String theLoai = inputTheLoai.getText();
        int soLuongCon = Integer.parseInt(inputSoLuongCon.getText());
        int soLuong = Integer.parseInt(inputSoLuong.getText());

        Sach s = new Sach(maSach, tenSach, tenTG, nxb, namXB, giaTien, theLoai, soLuongCon, soLuong);
        return s;
    }

    public DocGia getDocGia() {
        String maDG = inputMaDG.getText();

        String tenDG = inputTenDG.getText();
        String gt = inputGioiTinhDG.getText();
        String ngaySinh = inputNgaySinhDG.getText();
        String diaChi = inputDiaChiDG.getText();
        String sdt = inputSDTDG.getText();
        String cmnd = inputCMNDDG.getText();
        DocGia dg = new DocGia(maDG, tenDG, gt, ngaySinh, diaChi, sdt, cmnd);

        return dg;
    }

    public NhanVien getNhanVien() {
        String maNV = inputMaNV.getText();
        String tenNV = inputTenNV.getText();
        String ngaySinh = inputNgaySinhNV.getText();
        String gioiTinh = inputGioiTinhNV.getText();
        String diaChi = inputDiaChiNV.getText();
        String email = inputEmailNV.getText();
        String sdt = inputSDTNV.getText();
        NhanVien nv = new NhanVien(maNV, tenNV, ngaySinh, gioiTinh, diaChi, email, sdt);
        return nv;
    }

    public MuonTra getMuonTra() {
        String maMuon = inputMaMT.getText();
        String maDG = comboMaDG.getSelectedItem().toString();
        String maNV = comboMaNV.getSelectedItem().toString();
        String ngayMuon = inputNgayMuon.getText();
        String hanTra = inputHanTra.getText();

        MuonTra mt = new MuonTra(maMuon, maDG, maNV, ngayMuon, hanTra);
        return mt;
    }

    public CtMuonTra getCtMuonTra() {
        TinhSoNgay calculate = new TinhSoNgay();

        String maMuon = inputMaMT.getText();
        String maSach = comboMaSach.getSelectedItem().toString();
        int soLuong = Integer.parseInt(inputSoLuongMT.getText());
        String ngayTra = inputNgayTra.getText();
        int tienCoc = Integer.parseInt(inputTienCoc.getText());
        long tienPhat;
        if (!ngayTra.equals("")) {
            long soNgay = calculate.soNgay(inputHanTra.getText().toString(), ngayTra);
            tienPhat = soLuong * 100 * soNgay;
            if (tienPhat < 0) {
                tienPhat = 0;
            }
        } else {
            tienPhat = 0;
        }
        String ghiChu = inputGhiChu.getText();

        CtMuonTra mt = new CtMuonTra(maMuon, maSach, soLuong, ngayTra, tienCoc, tienPhat, ghiChu);
        return mt;
    }

    public void checkAdd(JTable table, JTextField input) {
        TableModel model = table.getModel();
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < 1; j++) {
                if (input.getText().equals(model.getValueAt(i, j).toString())) {
                    JOptionPane.showMessageDialog(null, "Khoá chính này đã tồn tại ! Đề nghị bạn nhập lại !");
                    return;
                }
            }
        }
    }

    public void addSach() {
        checkAdd(bangSach, inputMaSach);
        DocGia dg = getDocGia();
        myConnect.insertDocGia(dg);
        loadDataTable(bangDocGia);
        clearDocGia();
    }

    public void addDocGia() {
        checkAdd(bangDocGia, inputMaDG);
        DocGia dg = getDocGia();
        myConnect.insertDocGia(dg);
        loadDataTable(bangDocGia);
        clearDocGia();
    }

    public void addNhanVien() {
        checkAdd(bangNhanVien, inputMaNV);
        NhanVien nv = getNhanVien();
        myConnect.insertNhanVien(nv);
        loadDataTable(bangNhanVien);
        clearNhanVien();
    }

    public void addMuonTra() {
        checkAdd(bangMuonTra, inputMaMT);
        MuonTra mt = getMuonTra();
        myConnect.insertMuonTra(mt);
        loadDataTable(bangMuonTra);
        clearMuonTra();
    }

    public void addCtMuonTra() {
        int[] columns = bangCtMuonTra.getSelectedColumns();
        TableModel model = bangCtMuonTra.getModel();
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {

            if (inputMaMT.getText().equals(model.getValueAt(i, 0).toString()) && comboMaSach.getSelectedItem().toString().equals(model.getValueAt(i, 1))) {
                JOptionPane.showMessageDialog(null, "Khoá chính này đã tồn tại ! Đề nghị bạn nhập lại !");
                return;
            }

        }
        CtMuonTra mt = getCtMuonTra();
        myConnect.insertCtMuonTra(mt);
        String id = inputMaCTMT.getText();
        ResultSet rs = null;
        DefaultTableModel model1 = new DefaultTableModel();

        rs = myConnect.getDataById(id, "select * from ctmuontra where MaMuon_Manhvd_20162679 = ?");

        try {
            ResultSetMetaData rsMD = rs.getMetaData();
            int colNumber = rsMD.getColumnCount();
            String[] arr = new String[colNumber];

            for (int i = 0; i < colNumber; i++) {
                arr[i] = rsMD.getColumnName(i + 1);
            }
            model1.setColumnIdentifiers(arr);

            while (rs.next()) {
                for (int i = 0; i < colNumber; i++) {
                    arr[i] = rs.getString(i + 1);
                }
                model1.addRow(arr);
            }
        } catch (SQLException e) {

        }
        bangCtMuonTra.setModel(model1);
        clearCtMuonTra();
    }

    public void update(JTable table) {

        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Bạn phải chọn 1 hàng trong bảng", "Error Update", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (table.equals(bangSach)) {
            Sach sach = getSach();
            if (myConnect.updateSach(table.getValueAt(row, 0).toString(), sach)) {
                JOptionPane.showMessageDialog(null, "Update thành công");
            } else {
                JOptionPane.showMessageDialog(null, "Update thất bại");
                return;
            }
            clearSach();

        } else if (table.equals(bangDocGia)) {
            DocGia dg = getDocGia();
            if (myConnect.updateDocGia(table.getValueAt(row, 0).toString(), dg)) {
                JOptionPane.showMessageDialog(null, "Update thành công");
            } else {
                JOptionPane.showMessageDialog(null, "Update thất bại");
                return;
            }
            clearDocGia();
        } else if (table.equals(bangNhanVien)) {
            NhanVien nv = getNhanVien();
            if (myConnect.updateNhanVien(table.getValueAt(row, 0).toString(), nv)) {
                JOptionPane.showMessageDialog(null, "Update thành công");
            } else {
                JOptionPane.showMessageDialog(null, "Update thất bại");
                return;
            }
            clearNhanVien();
        } else if (table.equals(bangMuonTra)) {
            MuonTra mt = getMuonTra();
            if (myConnect.updateMuonTra(table.getValueAt(row, 0).toString(), mt)) {
                JOptionPane.showMessageDialog(null, "Update thành công");
            } else {
                JOptionPane.showMessageDialog(null, "Update thất bại");
                return;
            }
            clearMuonTra();
        } else {
            
            row = -1;
            TableModel model = bangCtMuonTra.getModel();
            int rowCount = model.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < 1; j++) {
                    if (model.getValueAt(i, j).toString().equals(inputMaCTMT.getText().toString())) {
                        row = i;
                    }
                }
            }
            if (row < 0) {
                JOptionPane.showMessageDialog(null, "Bạn phải chọn 1 hàng trong bảng" + table.getName().toString(), "Error Update", JOptionPane.ERROR_MESSAGE);
                return;
            }
            CtMuonTra mt = getCtMuonTra();
            if (myConnect.updateCtMuonTra(table.getValueAt(row, 0).toString(), table.getValueAt(row, 1).toString(), mt)) {
                JOptionPane.showMessageDialog(null, "Update thành công");
            } else {
                JOptionPane.showMessageDialog(null, "Update thất bại");
                return;
            }

            //in ra những dòn liên quan !
            String id = inputMaCTMT.getText();
            ResultSet rs = null;
            DefaultTableModel model1 = new DefaultTableModel();

            rs = myConnect.getDataById(id, "select * from ctmuontra where MaMuon_Manhvd_20162679 = ?");

            try {
                ResultSetMetaData rsMD = rs.getMetaData();
                int colNumber = rsMD.getColumnCount();
                String[] arr = new String[colNumber];

                for (int i = 0; i < colNumber; i++) {
                    arr[i] = rsMD.getColumnName(i + 1);
                }
                model1.setColumnIdentifiers(arr);

                while (rs.next()) {
                    for (int i = 0; i < colNumber; i++) {
                        arr[i] = rs.getString(i + 1);
                    }
                    model1.addRow(arr);
                }
            } catch (SQLException e) {

            }
            bangCtMuonTra.setModel(model1);
            clearCtMuonTra();
            return;
        }
        loadDataTable(table);
    }

    public void clearSach() {
        inputMaSach.setText("");
        inputTenSach.setText("");
        inputTenTacGia.setText("");
        inputNXB.setText("");
        inputNamXB.setText("");
        inputGiaTien.setText("");
        inputTheLoai.setText("");
        inputSoLuongCon.setText("");
        inputSoLuong.setText("");
    }

    public void clearDocGia() {
        inputMaDG.setText("");
        inputTenDG.setText("");
        inputGioiTinhDG.setText("");
        inputNgaySinhDG.setText("");
        inputDiaChiDG.setText("");
        inputSDTDG.setText("");
        inputCMNDDG.setText("");
    }

    public void clearNhanVien() {
        inputMaNV.setText("");
        inputTenNV.setText("");
        inputNgaySinhNV.setText("");
        inputGioiTinhNV.setText("");
        inputDiaChiNV.setText("");
        inputEmailNV.setText("");
        inputSDTNV.setText("");
    }

    public void clearMuonTra() {
        inputMaMT.setText("");
        inputNgayMuon.setText("");
        inputHanTra.setText("");
        inputMaCTMT.setText("");
    }

    public void clearCtMuonTra() {
        inputMaCTMT.setText("");
        inputSoLuongMT.setText("");
        inputNgayTra.setText("");
        inputTienCoc.setText("");
        inputGhiChu.setText("");
    }

    public void removeAllRow(JTable table) {
        DefaultTableModel modelDelete = (DefaultTableModel) table.getModel();
        modelDelete.setRowCount(0);
        if (modelDelete.getRowCount() != 0) {
            modelDelete.setRowCount(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inputMaSach = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        inputTenSach = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        inputTenTacGia = new javax.swing.JTextField();
        inputNXB = new javax.swing.JTextField();
        inputNamXB = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        inputGiaTien = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        inputTheLoai = new javax.swing.JTextField();
        inputSoLuong = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        themSach = new javax.swing.JButton();
        suaSach = new javax.swing.JButton();
        xoaSach = new javax.swing.JButton();
        findSach = new javax.swing.JButton();
        inputFindSach = new javax.swing.JTextField();
        searchSachByMaSach = new javax.swing.JRadioButton();
        searchSachByTenTG = new javax.swing.JRadioButton();
        searchSachByTheLoai = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        bangSach = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        comboSach = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        inputSoLuongCon = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        addDbSach = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        inputMaDG = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        inputTenDG = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        inputGioiTinhDG = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        inputNgaySinhDG = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        inputDiaChiDG = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        inputSDTDG = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        inputCMNDDG = new javax.swing.JTextField();
        comboDG = new javax.swing.JComboBox<>();
        inputFindDG = new javax.swing.JTextField();
        searchDocGiaByTenDG = new javax.swing.JRadioButton();
        searchDGByMaDG = new javax.swing.JRadioButton();
        searchDGByDiaChi = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        bangDocGia = new javax.swing.JTable();
        themDG = new javax.swing.JButton();
        suaDG1 = new javax.swing.JButton();
        xoaDG = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        findDG = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        addDbDocGia = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        inputMaNV = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        inputTenNV = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        inputNgaySinhNV = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        inputGioiTinhNV = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        inputDiaChiNV = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        inputEmailNV = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        inputSDTNV = new javax.swing.JTextField();
        comboNV = new javax.swing.JComboBox<>();
        inputFindNV = new javax.swing.JTextField();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        bangNhanVien = new javax.swing.JTable();
        themNV = new javax.swing.JButton();
        suaNV = new javax.swing.JButton();
        xoaNV = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        findDG1 = new javax.swing.JButton();
        reset1 = new javax.swing.JButton();
        addDbNhanVien = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        inputMaMT = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        inputMaCTMT = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        inputNgayMuon = new javax.swing.JTextField();
        inputHanTra = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        bangMuonTra = new javax.swing.JTable();
        themMT = new javax.swing.JButton();
        suaMT = new javax.swing.JButton();
        xoaMT = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        comboMT = new javax.swing.JComboBox<>();
        findMT = new javax.swing.JButton();
        inputFindMT = new javax.swing.JTextField();
        jRadioButton19 = new javax.swing.JRadioButton();
        jRadioButton20 = new javax.swing.JRadioButton();
        jRadioButton21 = new javax.swing.JRadioButton();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        comboMaDG = new javax.swing.JComboBox<>();
        comboMaNV = new javax.swing.JComboBox<>();
        inputSoLuongMT = new javax.swing.JTextField();
        inputNgayTra = new javax.swing.JTextField();
        inputTienCoc = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        inputGhiChu = new javax.swing.JTextArea();
        jScrollPane12 = new javax.swing.JScrollPane();
        bangCtMuonTra = new javax.swing.JTable();
        themCTMT = new javax.swing.JButton();
        suaCTMT = new javax.swing.JButton();
        xoaCTMT = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        comboMaSach = new javax.swing.JComboBox<>();
        reset2 = new javax.swing.JButton();
        hienTenNhanVien = new javax.swing.JLabel();
        hienTenDocGia = new javax.swing.JLabel();
        hienTenSach = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Mã Sách");

        inputMaSach.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Tên Sách");

        inputTenSach.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 255));
        jLabel3.setText("Tên Tác Giả");

        inputTenTacGia.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        inputNXB.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        inputNamXB.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        inputNamXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputNamXBActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 255));
        jLabel5.setText("Năm Xuất Bản");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 255));
        jLabel6.setText("Giá Tiền");

        inputGiaTien.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 255));
        jLabel7.setText("Thể Loại");

        inputTheLoai.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        inputSoLuong.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("Nhà Xuất Bản");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 255));
        jLabel8.setText("Số Lượng");

        themSach.setBackground(new java.awt.Color(153, 255, 153));
        themSach.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        themSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/add.png"))); // NOI18N
        themSach.setText("Thêm");
        themSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themSachActionPerformed(evt);
            }
        });

        suaSach.setBackground(new java.awt.Color(255, 255, 102));
        suaSach.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        suaSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/edit.png"))); // NOI18N
        suaSach.setText("Sửa");
        suaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suaSachActionPerformed(evt);
            }
        });

        xoaSach.setBackground(new java.awt.Color(255, 102, 102));
        xoaSach.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        xoaSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/delete.png"))); // NOI18N
        xoaSach.setText("Xóa");
        xoaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaSachActionPerformed(evt);
            }
        });

        findSach.setBackground(new java.awt.Color(0, 204, 255));
        findSach.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        findSach.setForeground(new java.awt.Color(0, 0, 255));
        findSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/search.png"))); // NOI18N
        findSach.setText("Tìm Kiếm");
        findSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findSachActionPerformed(evt);
            }
        });

        inputFindSach.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        buttonGroup1.add(searchSachByMaSach);
        searchSachByMaSach.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        searchSachByMaSach.setSelected(true);
        searchSachByMaSach.setText("Theo Mã Sách");
        searchSachByMaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchSachByMaSachActionPerformed(evt);
            }
        });

        buttonGroup1.add(searchSachByTenTG);
        searchSachByTenTG.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        searchSachByTenTG.setText("Theo Tên Tác Giả");

        buttonGroup1.add(searchSachByTheLoai);
        searchSachByTheLoai.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        searchSachByTheLoai.setText("Theo Thể Loại");

        bangSach.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        bangSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        bangSach.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        bangSach.setRowHeight(25);
        bangSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bangSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bangSach);

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/statistic.png"))); // NOI18N
        jLabel9.setText("Thống Kê");

        comboSach.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        comboSach.setForeground(new java.awt.Color(153, 153, 153));
        comboSach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo Tên Sách", "Theo Thể Loại", "Theo Nhà xuất bản", " " }));
        comboSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSachActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 0, 255));
        jLabel29.setText("Số Lượng Còn");

        inputSoLuongCon.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/input.png"))); // NOI18N
        jButton1.setText("Nhập file");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/output.png"))); // NOI18N
        jButton2.setText("Xuất file");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        addDbSach.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        addDbSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/addDatabase.png"))); // NOI18N
        addDbSach.setText("Thêm Vào CSDL");
        addDbSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDbSachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(themSach)
                                        .addGap(80, 80, 80)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(suaSach)
                                                .addGap(89, 89, 89)
                                                .addComponent(xoaSach))
                                            .addComponent(comboSach, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel4))
                                        .addGap(93, 93, 93)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(inputMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(inputTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(inputTenTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(inputNXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 521, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(inputNamXB, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(inputSoLuongCon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(searchSachByMaSach)
                                .addGap(49, 49, 49)
                                .addComponent(searchSachByTenTG)
                                .addGap(58, 58, 58)
                                .addComponent(searchSachByTheLoai))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel5))
                                .addGap(123, 123, 123)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(inputSoLuong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(inputTheLoai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(inputGiaTien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(findSach)
                                .addGap(78, 78, 78)
                                .addComponent(inputFindSach, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)))))
                .addGap(79, 79, 79))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addDbSach)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(62, 62, 62)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1))
                .addGap(22, 22, 22))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {inputMaSach, inputNXB, inputTenSach, inputTenTacGia});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel29, jLabel5, jLabel6, jLabel7, jLabel8});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {suaSach, themSach, xoaSach});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {inputGiaTien, inputNamXB, inputSoLuong, inputSoLuongCon, inputTheLoai});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(inputMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(inputNamXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(inputTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(inputGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputTenTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(inputTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(inputNXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(inputSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(themSach)
                            .addComponent(suaSach)
                            .addComponent(xoaSach)))
                    .addComponent(inputSoLuongCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchSachByTenTG)
                            .addComponent(searchSachByTheLoai)
                            .addComponent(searchSachByMaSach)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(comboSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(findSach)
                            .addComponent(inputFindSach, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(89, 89, 89)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(addDbSach, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {inputMaSach, inputNXB, inputTenSach, inputTenTacGia});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel29, jLabel5, jLabel6, jLabel7, jLabel8});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {suaSach, themSach, xoaSach});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {inputGiaTien, inputNamXB, inputSoLuong, inputSoLuongCon, inputTheLoai});

        jTabbedPane1.addTab("Quản Lý Sách", jPanel1);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 255));
        jLabel10.setText("Mã Độc Giả");

        inputMaDG.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 255));
        jLabel11.setText("Tên Độc Giả");

        inputTenDG.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 255));
        jLabel12.setText("Giới Tính");

        inputGioiTinhDG.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 255));
        jLabel13.setText("Ngày Sinh");

        inputNgaySinhDG.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 255));
        jLabel14.setText("Địa Chỉ");

        inputDiaChiDG.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 255));
        jLabel15.setText("Số điện thoại");

        inputSDTDG.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 255));
        jLabel16.setText("CMND/CCCD");

        inputCMNDDG.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        comboDG.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        comboDG.setForeground(new java.awt.Color(153, 153, 153));
        comboDG.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo giới tính", "Theo địa chỉ", "Theo tên độc giả", " ", " " }));
        comboDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDGActionPerformed(evt);
            }
        });

        inputFindDG.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        buttonGroup2.add(searchDocGiaByTenDG);
        searchDocGiaByTenDG.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        searchDocGiaByTenDG.setSelected(true);
        searchDocGiaByTenDG.setText("Theo tên độc giả");
        searchDocGiaByTenDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchDocGiaByTenDGActionPerformed(evt);
            }
        });

        buttonGroup2.add(searchDGByMaDG);
        searchDGByMaDG.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        searchDGByMaDG.setText("Theo mã độc giả");

        buttonGroup2.add(searchDGByDiaChi);
        searchDGByDiaChi.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        searchDGByDiaChi.setText("Theo địa chỉ");

        bangDocGia.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        bangDocGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Độc Giả", "Tên Độc Giả", "Giới Tính", "Ngày Sinh ", "Địa Chỉ", "Số điện thoại", "CMND"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        bangDocGia.setRowHeight(25);
        bangDocGia.setSelectionBackground(new java.awt.Color(153, 153, 153));
        bangDocGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bangDocGiaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(bangDocGia);

        themDG.setBackground(new java.awt.Color(153, 255, 153));
        themDG.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        themDG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/add.png"))); // NOI18N
        themDG.setText("Thêm");
        themDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themDGActionPerformed(evt);
            }
        });

        suaDG1.setBackground(new java.awt.Color(255, 255, 102));
        suaDG1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        suaDG1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/edit.png"))); // NOI18N
        suaDG1.setText("Sửa");
        suaDG1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suaDG1ActionPerformed(evt);
            }
        });

        xoaDG.setBackground(new java.awt.Color(255, 102, 102));
        xoaDG.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        xoaDG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/delete.png"))); // NOI18N
        xoaDG.setText("Xóa");
        xoaDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaDGActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/statistic.png"))); // NOI18N
        jLabel17.setText("Thống Kê");

        findDG.setBackground(new java.awt.Color(0, 204, 255));
        findDG.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        findDG.setForeground(new java.awt.Color(0, 0, 255));
        findDG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/search.png"))); // NOI18N
        findDG.setText("Tìm Kiếm");
        findDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findDGActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/input.png"))); // NOI18N
        jButton3.setText("Nhập file");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/output.png"))); // NOI18N
        jButton4.setText("Xuất file");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        reset.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/reset.png"))); // NOI18N
        reset.setText("Reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        addDbDocGia.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        addDbDocGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/addDatabase.png"))); // NOI18N
        addDbDocGia.setText("Thêm Vào CSDL");
        addDbDocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDbDocGiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(themDG)
                .addGap(41, 41, 41)
                .addComponent(suaDG1)
                .addGap(51, 51, 51)
                .addComponent(xoaDG)
                .addGap(43, 43, 43)
                .addComponent(reset)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(inputMaDG, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(219, 219, 219))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(inputTenDG, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(inputNgaySinhDG, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(inputGioiTinhDG, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel17)
                        .addGap(82, 82, 82)
                        .addComponent(comboDG, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(findDG))
                .addGap(87, 87, 87)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputFindDG, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputSDTDG, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addComponent(inputDiaChiDG)
                    .addComponent(inputCMNDDG, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(185, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(666, Short.MAX_VALUE)
                .addComponent(searchDocGiaByTenDG)
                .addGap(31, 31, 31)
                .addComponent(searchDGByMaDG)
                .addGap(44, 44, 44)
                .addComponent(searchDGByDiaChi)
                .addGap(205, 205, 205))
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addDbDocGia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(30, 30, 30)
                .addComponent(jButton4)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel13});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {inputGioiTinhDG, inputMaDG, inputNgaySinhDG, inputTenDG});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel14, jLabel15, jLabel16});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {inputCMNDDG, inputDiaChiDG, inputSDTDG});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {reset, suaDG1, themDG, xoaDG});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(inputMaDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(inputDiaChiDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(inputTenDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(inputSDTDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(inputGioiTinhDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(inputCMNDDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(inputNgaySinhDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(themDG)
                            .addComponent(suaDG1)
                            .addComponent(xoaDG)
                            .addComponent(reset))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputFindDG, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(findDG))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(comboDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchDocGiaByTenDG)
                    .addComponent(searchDGByMaDG)
                    .addComponent(searchDGByDiaChi))
                .addGap(83, 83, 83)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(addDbDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel13});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {inputGioiTinhDG, inputMaDG, inputNgaySinhDG, inputTenDG});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel14, jLabel15, jLabel16});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {inputCMNDDG, inputDiaChiDG, inputSDTDG});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {reset, suaDG1, themDG, xoaDG});

        jTabbedPane1.addTab("Quản Lý Độc Giả", jPanel2);

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 255));
        jLabel18.setText("Mã nhân viên ");

        inputMaNV.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 255));
        jLabel19.setText("Tên nhân viên");

        inputTenNV.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 255));
        jLabel20.setText("Ngày sinh ");

        inputNgaySinhNV.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 255));
        jLabel21.setText("Giới tính");

        inputGioiTinhNV.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 255));
        jLabel22.setText("Địa Chỉ");

        inputDiaChiNV.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 255));
        jLabel23.setText("Email");

        inputEmailNV.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 255));
        jLabel24.setText("Số điện thoại");

        inputSDTNV.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        comboNV.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        comboNV.setForeground(new java.awt.Color(153, 153, 153));
        comboNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo địa chỉ", "Theo giới tính", "Theo tên nhân viên", " ", " " }));

        inputFindNV.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        buttonGroup3.add(jRadioButton7);
        jRadioButton7.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        jRadioButton7.setSelected(true);
        jRadioButton7.setText("Theo mã nhân viên ");

        buttonGroup3.add(jRadioButton8);
        jRadioButton8.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        jRadioButton8.setText("Theo tên nhân viên");

        buttonGroup3.add(jRadioButton9);
        jRadioButton9.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        jRadioButton9.setText("Theo số điện thoại");

        bangNhanVien.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        bangNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên ", "Tên nhân viên", "Ngày sinh", "Giới tính", "Địa chỉ", "Email", "Số điện thoại"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        bangNhanVien.setRowHeight(25);
        bangNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bangNhanVienMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(bangNhanVien);

        themNV.setBackground(new java.awt.Color(153, 255, 153));
        themNV.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        themNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/add.png"))); // NOI18N
        themNV.setText("Thêm");
        themNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themNVActionPerformed(evt);
            }
        });

        suaNV.setBackground(new java.awt.Color(255, 255, 102));
        suaNV.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        suaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/edit.png"))); // NOI18N
        suaNV.setText("Sửa");
        suaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suaNVActionPerformed(evt);
            }
        });

        xoaNV.setBackground(new java.awt.Color(255, 102, 102));
        xoaNV.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        xoaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/delete.png"))); // NOI18N
        xoaNV.setText("Xóa");
        xoaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaNVActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 255));
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/statistic.png"))); // NOI18N
        jLabel25.setText("Thống Kê");

        jButton5.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/output.png"))); // NOI18N
        jButton5.setText("Xuất file");

        jButton6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/input.png"))); // NOI18N
        jButton6.setText("Nhập file");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        findDG1.setBackground(new java.awt.Color(0, 204, 255));
        findDG1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        findDG1.setForeground(new java.awt.Color(0, 0, 255));
        findDG1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/search.png"))); // NOI18N
        findDG1.setText("Tìm Kiếm");
        findDG1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findDG1ActionPerformed(evt);
            }
        });

        reset1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        reset1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/reset.png"))); // NOI18N
        reset1.setText("Reset");
        reset1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset1ActionPerformed(evt);
            }
        });

        addDbNhanVien.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        addDbNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/addDatabase.png"))); // NOI18N
        addDbNhanVien.setText("Thêm Vào CSDL");
        addDbNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDbNhanVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton7)
                .addGap(31, 31, 31)
                .addComponent(jRadioButton8)
                .addGap(44, 44, 44)
                .addComponent(jRadioButton9)
                .addGap(90, 90, 90))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(suaNV)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19)
                        .addComponent(jLabel21)))
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(inputGioiTinhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputNgaySinhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(204, 204, 204)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(findDG1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputEmailNV, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                            .addComponent(inputDiaChiNV)
                            .addComponent(inputSDTNV, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputFindNV, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(133, 133, 133))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(988, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(46, 46, 46)
                .addComponent(jButton5)
                .addGap(27, 27, 27))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(jLabel25))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(themNV)))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboNV, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addComponent(xoaNV)
                                .addGap(49, 49, 49)
                                .addComponent(reset1))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(addDbNhanVien)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel18, jLabel19, jLabel20, jLabel21});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {inputGioiTinhNV, inputMaNV, inputNgaySinhNV, inputTenNV});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel22, jLabel23, jLabel24});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {inputDiaChiNV, inputEmailNV, inputSDTNV});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {suaNV, themNV, xoaNV});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(inputMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(inputDiaChiNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(inputTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(inputEmailNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(inputNgaySinhNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(inputSDTNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(inputGioiTinhNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themNV)
                    .addComponent(suaNV)
                    .addComponent(xoaNV)
                    .addComponent(reset1))
                .addGap(24, 24, 24)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputFindNV, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(findDG1))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton7)
                    .addComponent(jRadioButton8)
                    .addComponent(jRadioButton9))
                .addGap(98, 98, 98)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(addDbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel18, jLabel19, jLabel20, jLabel21});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {inputGioiTinhNV, inputMaNV, inputNgaySinhNV, inputTenNV});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel22, jLabel23, jLabel24});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {inputDiaChiNV, inputEmailNV, inputSDTNV});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {suaNV, themNV, xoaNV});

        jTabbedPane1.addTab("Quản Lý Nhân Viên", jPanel6);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel45.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 0, 255));
        jLabel45.setText("Mã mượn trả ");

        inputMaMT.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        inputMaMT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputMaMTActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 0, 255));
        jLabel46.setText("Mã độc giả");

        jLabel47.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 0, 255));
        jLabel47.setText("Mã nhân viên");

        inputMaCTMT.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel48.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(0, 0, 255));
        jLabel48.setText("Ngày mượn");

        jLabel49.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 0, 255));
        jLabel49.setText("Hạn trả");

        inputNgayMuon.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        inputNgayMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputNgayMuonActionPerformed(evt);
            }
        });

        inputHanTra.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        bangMuonTra.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        bangMuonTra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã mượn trả ", "Mã độc giả", "Mã nhân viên", "Ngày mượn", "Hạn trả"
            }
        ));
        bangMuonTra.setRowHeight(25);
        bangMuonTra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bangMuonTraMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(bangMuonTra);

        themMT.setBackground(new java.awt.Color(153, 255, 153));
        themMT.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        themMT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/add.png"))); // NOI18N
        themMT.setText("Thêm");
        themMT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themMTActionPerformed(evt);
            }
        });

        suaMT.setBackground(new java.awt.Color(255, 255, 102));
        suaMT.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        suaMT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/edit.png"))); // NOI18N
        suaMT.setText("Sửa");
        suaMT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suaMTActionPerformed(evt);
            }
        });

        xoaMT.setBackground(new java.awt.Color(255, 102, 102));
        xoaMT.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        xoaMT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/delete.png"))); // NOI18N
        xoaMT.setText("Xóa");
        xoaMT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaMTActionPerformed(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(0, 0, 255));
        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/statistic.png"))); // NOI18N
        jLabel50.setText("Thống Kê");

        comboMT.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        comboMT.setForeground(new java.awt.Color(153, 153, 153));
        comboMT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo ngày mượn", "Theo hạn trả", " ", " " }));

        findMT.setBackground(new java.awt.Color(0, 204, 255));
        findMT.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        findMT.setForeground(new java.awt.Color(0, 0, 255));
        findMT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/search.png"))); // NOI18N
        findMT.setText("Tìm Kiếm");

        inputFindMT.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        buttonGroup4.add(jRadioButton19);
        jRadioButton19.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        jRadioButton19.setSelected(true);
        jRadioButton19.setText("Theo mã mượn trả ");

        buttonGroup4.add(jRadioButton20);
        jRadioButton20.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        jRadioButton20.setText("Theo mã độc giả");

        buttonGroup4.add(jRadioButton21);
        jRadioButton21.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        jRadioButton21.setText("Theo mã nhân viên");

        jLabel51.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(0, 0, 255));
        jLabel51.setText("Mã mượn trả ");

        jLabel52.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(0, 0, 255));
        jLabel52.setText("Mã Sách");

        jLabel53.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(0, 0, 255));
        jLabel53.setText("Số Lượng");

        jLabel54.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(0, 0, 255));
        jLabel54.setText("Ngày Trả");

        jLabel56.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(0, 0, 255));
        jLabel56.setText("Tiền cọc");

        jLabel57.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 0, 255));
        jLabel57.setText("Ghi chú");

        comboMaDG.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        comboMaDG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboMaDGMouseClicked(evt);
            }
        });
        comboMaDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMaDGActionPerformed(evt);
            }
        });

        comboMaNV.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        comboMaNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboMaNVMouseClicked(evt);
            }
        });
        comboMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMaNVActionPerformed(evt);
            }
        });

        inputSoLuongMT.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        inputNgayTra.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        inputTienCoc.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        inputGhiChu.setColumns(20);
        inputGhiChu.setRows(5);
        jScrollPane11.setViewportView(inputGhiChu);

        bangCtMuonTra.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        bangCtMuonTra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Mượn", "Mã Sách", "Số Lượng", "Ngày Trả", "Tiền Cọc", "Tiền phạt", "Ghi Chú"
            }
        ));
        bangCtMuonTra.setRowHeight(25);
        bangCtMuonTra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bangCtMuonTraMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(bangCtMuonTra);

        themCTMT.setBackground(new java.awt.Color(153, 255, 153));
        themCTMT.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        themCTMT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/add.png"))); // NOI18N
        themCTMT.setText("Thêm");
        themCTMT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themCTMTActionPerformed(evt);
            }
        });

        suaCTMT.setBackground(new java.awt.Color(255, 255, 102));
        suaCTMT.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        suaCTMT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/edit.png"))); // NOI18N
        suaCTMT.setText("Sửa");
        suaCTMT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suaCTMTActionPerformed(evt);
            }
        });

        xoaCTMT.setBackground(new java.awt.Color(255, 102, 102));
        xoaCTMT.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        xoaCTMT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/delete.png"))); // NOI18N
        xoaCTMT.setText("Xóa");
        xoaCTMT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaCTMTActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/output.png"))); // NOI18N
        jButton8.setText("Xuất file ");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        comboMaSach.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        comboMaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMaSachActionPerformed(evt);
            }
        });

        reset2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        reset2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/reset.png"))); // NOI18N
        reset2.setText("Reset");
        reset2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset2ActionPerformed(evt);
            }
        });

        hienTenNhanVien.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        hienTenNhanVien.setForeground(new java.awt.Color(102, 102, 102));
        hienTenNhanVien.setText("hiện tên nhân viên");

        hienTenDocGia.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        hienTenDocGia.setForeground(new java.awt.Color(102, 102, 102));
        hienTenDocGia.setText("hiện tên độc giả");

        hienTenSach.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        hienTenSach.setForeground(new java.awt.Color(102, 102, 102));
        hienTenSach.setText("hiện tên sách");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(themMT)
                        .addGap(43, 43, 43)
                        .addComponent(suaMT)
                        .addGap(44, 44, 44)
                        .addComponent(xoaMT)
                        .addGap(44, 44, 44)
                        .addComponent(reset2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel46)
                                    .addComponent(jLabel47)
                                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel48)
                                    .addComponent(jLabel49))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(hienTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(inputHanTra)
                                    .addComponent(inputNgayMuon, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                    .addComponent(comboMaDG, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(inputMaMT, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                    .addComponent(comboMaNV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(hienTenDocGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(57, 57, 57)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel54)
                                            .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(68, 68, 68)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                            .addComponent(inputNgayTra)
                                            .addComponent(inputTienCoc)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel51)
                                            .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(68, 68, 68)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(inputMaCTMT)
                                            .addComponent(comboMaSach, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(inputSoLuongMT, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(hienTenSach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(themCTMT)
                                        .addGap(43, 43, 43)
                                        .addComponent(suaCTMT)
                                        .addGap(55, 55, 55)
                                        .addComponent(xoaCTMT)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(jRadioButton19)
                                                .addGap(18, 18, 18)
                                                .addComponent(jRadioButton20)
                                                .addGap(33, 33, 33)
                                                .addComponent(jRadioButton21))
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(jLabel50)
                                                .addGap(80, 80, 80)
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(inputFindMT, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(comboMT, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(findMT))
                                        .addGap(132, 132, 132)))))))
                .addGap(23, 23, 23))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {inputHanTra, inputMaMT, inputNgayMuon});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel45, jLabel46, jLabel47, jLabel48, jLabel49});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel51, jLabel52, jLabel53, jLabel54, jLabel56, jLabel57});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {suaMT, themMT, xoaMT});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {suaCTMT, themCTMT, xoaCTMT});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel45)
                            .addComponent(inputMaMT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(comboMaDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(hienTenDocGia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47))
                        .addGap(18, 18, 18)
                        .addComponent(hienTenNhanVien)
                        .addGap(7, 7, 7)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jButton8)
                        .addGap(54, 54, 54))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(inputNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel49)
                            .addComponent(inputHanTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(themMT)
                            .addComponent(suaMT)
                            .addComponent(xoaMT)
                            .addComponent(reset2))
                        .addGap(72, 72, 72)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51)
                            .addComponent(inputMaCTMT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52)
                            .addComponent(comboMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(hienTenSach)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputSoLuongMT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel53))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54)
                            .addComponent(inputNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel56)
                            .addComponent(inputTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(themCTMT)
                            .addComponent(suaCTMT)
                            .addComponent(xoaCTMT))
                        .addGap(206, 206, 206))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel50)
                            .addComponent(comboMT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(findMT)
                            .addComponent(inputFindMT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton19)
                            .addComponent(jRadioButton20)
                            .addComponent(jRadioButton21))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {inputHanTra, inputMaCTMT, inputMaMT, inputNgayMuon});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel45, jLabel46, jLabel47, jLabel48, jLabel49});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel51, jLabel52, jLabel53, jLabel54, jLabel56, jLabel57});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {inputNgayTra, inputSoLuongMT, inputTienCoc});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {suaMT, themMT, xoaMT});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {suaCTMT, themCTMT, xoaCTMT});

        jTabbedPane1.addTab("Quản Lý Mượn Trả", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void xoaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaNVActionPerformed
        delete(bangNhanVien, "delete from nhanvien where MaNV_Manhvd_20162679 = ?");
    }//GEN-LAST:event_xoaNVActionPerformed

    private void xoaDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaDGActionPerformed
        delete(bangDocGia, "delete from docgia where MaDG_Manhvd_20162679 = ?");
    }//GEN-LAST:event_xoaDGActionPerformed

    private void themDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themDGActionPerformed

        addDocGia();
    }//GEN-LAST:event_themDGActionPerformed

    private void xoaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaSachActionPerformed
        delete(bangSach, "delete from sach where MaSach_Manhvd_20162679 = ?");
    }//GEN-LAST:event_xoaSachActionPerformed

    private void suaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaSachActionPerformed
        update(bangSach);
    }//GEN-LAST:event_suaSachActionPerformed

    private void themSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themSachActionPerformed
        addSach();
    }//GEN-LAST:event_themSachActionPerformed

    private void themMTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themMTActionPerformed
        addMuonTra();
    }//GEN-LAST:event_themMTActionPerformed

    private void xoaMTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaMTActionPerformed
        delete(bangMuonTra, "delete from muontra where MaMuon_Manhvd_20162679 = ?");
    }//GEN-LAST:event_xoaMTActionPerformed

    private void themCTMTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themCTMTActionPerformed
        addCtMuonTra();
    }//GEN-LAST:event_themCTMTActionPerformed

    private void xoaCTMTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaCTMTActionPerformed
        deleteCtMuonTra();
    }//GEN-LAST:event_xoaCTMTActionPerformed

    private void inputNgayMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputNgayMuonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputNgayMuonActionPerformed

    private void inputMaMTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputMaMTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputMaMTActionPerformed

    private void inputNamXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputNamXBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputNamXBActionPerformed


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // đường dẫn
        addDbSach.setEnabled(true);
        DefaultTableModel model;
        model = (DefaultTableModel) bangSach.getModel();
        ReadExcel re = new ReadExcel();
        re.chooseExcel(model);
        // chỗ này sẽ delete hết các dòng trước khi nhập dữ liệu từ file 
        removeAllRow(bangSach);
        re.readExcel(model);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bangDocGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bangDocGiaMouseClicked
        int row = bangDocGia.getSelectedRow();
        inputMaDG.setText((String) bangDocGia.getValueAt(row, 0));
        inputTenDG.setText((String) bangDocGia.getValueAt(row, 1));
        inputGioiTinhDG.setText((String) bangDocGia.getValueAt(row, 2));
        inputNgaySinhDG.setText((String) bangDocGia.getValueAt(row, 3));
        inputDiaChiDG.setText((String) bangDocGia.getValueAt(row, 4));
        inputSDTDG.setText((String) bangDocGia.getValueAt(row, 5));
        inputCMNDDG.setText((String) bangDocGia.getValueAt(row, 6));
    }//GEN-LAST:event_bangDocGiaMouseClicked

    private void suaDG1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaDG1ActionPerformed
        update(bangDocGia);
    }//GEN-LAST:event_suaDG1ActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        clearDocGia();
    }//GEN-LAST:event_resetActionPerformed

    private void reset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset1ActionPerformed
        clearNhanVien();
    }//GEN-LAST:event_reset1ActionPerformed

    private void bangNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bangNhanVienMouseClicked
        int row = bangNhanVien.getSelectedRow();
        inputMaNV.setText((String) bangNhanVien.getValueAt(row, 0));
        inputTenNV.setText((String) bangNhanVien.getValueAt(row, 1));
        inputNgaySinhNV.setText((String) bangNhanVien.getValueAt(row, 2));
        inputGioiTinhNV.setText((String) bangNhanVien.getValueAt(row, 3));
        inputDiaChiNV.setText((String) bangNhanVien.getValueAt(row, 4));
        inputEmailNV.setText((String) bangNhanVien.getValueAt(row, 5));
        inputSDTNV.setText((String) bangNhanVien.getValueAt(row, 6));
    }//GEN-LAST:event_bangNhanVienMouseClicked

    private void themNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themNVActionPerformed
        addNhanVien();
    }//GEN-LAST:event_themNVActionPerformed

    private void suaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaNVActionPerformed
        update(bangNhanVien);
    }//GEN-LAST:event_suaNVActionPerformed

    private void comboMaDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMaDGActionPerformed
         int row3 = -1;
        TableModel modelDocGia = bangDocGia.getModel();
        int rowCount = modelDocGia.getRowCount();
        for (int i = 0; i < rowCount; i++) {

            if (modelDocGia.getValueAt(i, 0).toString().equals((String)comboMaDG.getSelectedItem().toString())) {
                row3 = i;
                break;
            }

        }
        hienTenDocGia.setText((String) bangDocGia.getValueAt(row3, 1)); 
    }//GEN-LAST:event_comboMaDGActionPerformed

    private void suaMTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaMTActionPerformed
        update(bangMuonTra);
    }//GEN-LAST:event_suaMTActionPerformed

    private void bangMuonTraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bangMuonTraMouseClicked
        bangCtMuonTra.setEnabled(true);
        int row = bangMuonTra.getSelectedRow();
        inputMaMT.setText((String) bangMuonTra.getValueAt(row, 0));
        comboMaDG.setSelectedItem((String) bangMuonTra.getValueAt(row, 1));
        comboMaNV.setSelectedItem((String) bangMuonTra.getValueAt(row, 2));
        inputNgayMuon.setText((String) bangMuonTra.getValueAt(row, 3));
        inputHanTra.setText((String) bangMuonTra.getValueAt(row, 4));
        inputMaCTMT.setText((String) bangMuonTra.getValueAt(row, 0));
              
        inputSoLuongMT.setText("");
        inputNgayTra.setText("");
        inputTienCoc.setText("");
        inputGhiChu.setText("");

        String id = inputMaCTMT.getText();
        ResultSet rs = null;
        DefaultTableModel model = new DefaultTableModel();

        rs = myConnect.getDataById(id, "select * from ctmuontra where MaMuon_Manhvd_20162679 = ?");

        try {
            ResultSetMetaData rsMD = rs.getMetaData();
            int colNumber = rsMD.getColumnCount();
            String[] arr = new String[colNumber];

            for (int i = 0; i < colNumber; i++) {
                arr[i] = rsMD.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(arr);

            while (rs.next()) {
                for (int i = 0; i < colNumber; i++) {
                    arr[i] = rs.getString(i + 1);
                }
                model.addRow(arr);
            }
        } catch (SQLException e) {

        }
        bangCtMuonTra.setModel(model);

//        int row2 = -1;
//        TableModel model = bangCtMuonTra.getModel();
//        int rowCount = model.getRowCount();
//        for (int i = 0; i < rowCount; i++) {
//            for (int j = 0; j < 1; j++) {
//                if (model.getValueAt(i, j).toString().equals((String) bangMuonTra.getValueAt(row, 0))) {
//                    row2 = i;
//                    break;
//                }
//            }
//        }
//
//        //System.out.println(row2);
//        if (row2 == -1) {
//
//            inputSoLuongMT.setText("");
//            inputNgayTra.setText("");
//            inputTienCoc.setText("");
//            inputGhiChu.setText("");
//            return;
//        }
//
//        inputMaCTMT.setText((String) bangCtMuonTra.getValueAt(row2, 0));
//        comboMaSach.setSelectedItem((String) bangCtMuonTra.getValueAt(row2, 1));
//        inputSoLuongMT.setText((String) bangCtMuonTra.getValueAt(row2, 2));
//        inputNgayTra.setText((String) bangCtMuonTra.getValueAt(row2, 3));
//        inputTienCoc.setText((String) bangCtMuonTra.getValueAt(row2, 4));
//        inputGhiChu.setText((String) bangCtMuonTra.getValueAt(row2, 6));
    }//GEN-LAST:event_bangMuonTraMouseClicked

    private void reset2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset2ActionPerformed
        clearMuonTra();
        clearCtMuonTra();
    }//GEN-LAST:event_reset2ActionPerformed

    private void addDbSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDbSachActionPerformed
        DefaultTableModel model;
        model = (DefaultTableModel) bangSach.getModel();
        Sach s = new Sach();
        int n = bangSach.getRowCount();
        String sqlCommand = "insert into sach " + " value (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        for (int index = 0; index < n; index++) {
            s.setMaSach(bangSach.getValueAt(index, 0).toString());
            s.setTenSach(bangSach.getValueAt(index, 1).toString());
            s.setTenTG(bangSach.getValueAt(index, 2).toString());
            s.setNXB(bangSach.getValueAt(index, 3).toString());
            s.setNamXB(bangSach.getValueAt(index, 4).toString());
            s.setGiaTien(Integer.parseInt(bangSach.getValueAt(index, 5).toString()));
            s.setTheLoai(bangSach.getValueAt(index, 6).toString());
            s.setSoLuongCon(Integer.parseInt(bangSach.getValueAt(index, 7).toString()));
            s.setSoLuong(Integer.parseInt(bangSach.getValueAt(index, 8).toString()));
            myConnect.connectDatabase();
            PreparedStatement ps = null;
            try {
                ps = myConnect.connection.prepareStatement(sqlCommand);
                ps.setString(1, s.getMaSach());
                ps.setString(2, s.getTenSach());
                ps.setString(3, s.getTenTG());
                ps.setString(4, s.getNXB());
                ps.setString(5, s.getNamXB());
                ps.setInt(6, s.getGiaTien());
                ps.setString(7, s.getTheLoai());
                ps.setInt(8, s.getSoLuongCon());
                ps.setInt(9, s.getSoLuong());
                int kt = ps.executeUpdate();
                if (kt != 0) {
                    // Dòng bắt đầu đếm từ 0 nhưng cột lại bắt đàu đếm từ 1
                    model.removeRow(index);
                    n--;
                    index--;
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error ! " + ex.getMessage()
                        + " \". Hãy sửa trực tiếp trên bảng và ấn \"Thêm vào CSDL\" để tiếp tục thêm.");
            }
        }
        loadDataTable(bangSach);
        addDbSach.setEnabled(false);
    }//GEN-LAST:event_addDbSachActionPerformed

    private void addDbDocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDbDocGiaActionPerformed
        DefaultTableModel model;
        model = (DefaultTableModel) bangDocGia.getModel();
        DocGia dg = new DocGia();
        int n = bangDocGia.getRowCount();
        String sqlCommand = "insert into docgia value (?, ?, ?, ?, ?, ?, ? )";
        for (int index = 0; index < n; index++) {
            dg.setMaDG(bangDocGia.getValueAt(index, 0).toString());
            dg.setTenDG(bangDocGia.getValueAt(index, 1).toString());
            dg.setGT(bangDocGia.getValueAt(index, 2).toString());
            dg.setNgaySinh(bangDocGia.getValueAt(index, 3).toString());
            dg.setDiaChi(bangDocGia.getValueAt(index, 4).toString());
            dg.setSdt(bangDocGia.getValueAt(index, 5).toString());
            dg.setCmnd(bangDocGia.getValueAt(index, 6).toString());

            myConnect.connectDatabase();
            PreparedStatement ps = null;
            try {
                ps = myConnect.connection.prepareStatement(sqlCommand);
                ps.setString(1, dg.getMaDG());
                ps.setString(2, dg.getTenDG());
                ps.setString(3, dg.getGT());
                ps.setString(4, dg.getNgaySinh());
                ps.setString(5, dg.getDiaChi());
                ps.setString(6, dg.getSdt());
                ps.setString(7, dg.getCmnd());

                int kt = ps.executeUpdate();
                if (kt != 0) {
                    // Dòng bắt đầu đếm từ 0 nhưng cột lại bắt đàu đếm từ 1
                    model.removeRow(index);
                    n--;
                    index--;
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error ! " + ex.getMessage()
                        + " \". Hãy sửa trực tiếp trên bảng và ấn \"Thêm vào CSDL\" để tiếp tục thêm.");
            }
        }

        addDbDocGia.setEnabled(false);
        loadDataTable(bangDocGia);
    }//GEN-LAST:event_addDbDocGiaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // đường dẫn
        addDbDocGia.setEnabled(true);
        DefaultTableModel model;
        model = (DefaultTableModel) bangDocGia.getModel();
        ReadExcel re = new ReadExcel();
        re.chooseExcel(model);
        // chỗ này sẽ delete hết các dòng trước khi nhập dữ liệu từ file 
        removeAllRow(bangDocGia);
        re.readExcel(model);


    }//GEN-LAST:event_jButton3ActionPerformed

    private void addDbNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDbNhanVienActionPerformed
        DefaultTableModel model;
        model = (DefaultTableModel) bangNhanVien.getModel();
        NhanVien dg = new NhanVien();
        int n = bangNhanVien.getRowCount();
        String sqlCommand = "insert into nhanvien value (?, ?, ?, ?, ?, ?, ? )";
        for (int index = 0; index < n; index++) {
            dg.setMaNV(bangNhanVien.getValueAt(index, 0).toString());
            dg.setTenNV(bangNhanVien.getValueAt(index, 1).toString());
            dg.setNgaySinh(bangNhanVien.getValueAt(index, 2).toString());
            dg.setGT(bangNhanVien.getValueAt(index, 3).toString());
            dg.setDiaChi(bangNhanVien.getValueAt(index, 4).toString());
            dg.setEmail(bangNhanVien.getValueAt(index, 5).toString());
            dg.setSdt(bangNhanVien.getValueAt(index, 6).toString());

            myConnect.connectDatabase();
            PreparedStatement ps = null;
            try {
                ps = myConnect.connection.prepareStatement(sqlCommand);
                ps.setString(1, dg.getMaNV());
                ps.setString(2, dg.getTenNV());
                ps.setString(3, dg.getNgaySinh());
                ps.setString(4, dg.getGT());
                ps.setString(5, dg.getDiaChi());
                ps.setString(6, dg.getEmail());
                ps.setString(7, dg.getSdt());

                int kt = ps.executeUpdate();
                if (kt != 0) {
                    // Dòng bắt đầu đếm từ 0 nhưng cột lại bắt đàu đếm từ 1
                    model.removeRow(index);
                    n--;
                    index--;
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error ! " + ex.getMessage()
                        + " \". Hãy sửa trực tiếp trên bảng và ấn \"Thêm vào CSDL\" để tiếp tục thêm.");
            }
        }

        addDbNhanVien.setEnabled(false);
        loadDataTable(bangNhanVien);

    }//GEN-LAST:event_addDbNhanVienActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        addDbNhanVien.setEnabled(true);
        DefaultTableModel model;
        model = (DefaultTableModel) bangNhanVien.getModel();
        ReadExcel re = new ReadExcel();
        re.chooseExcel(model);
        // chỗ này sẽ delete hết các dòng trước khi nhập dữ liệu từ file 
        removeAllRow(bangNhanVien);
        re.readExcel(model);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void bangCtMuonTraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bangCtMuonTraMouseClicked
        int row2 = bangCtMuonTra.getSelectedRow();
        if (inputMaCTMT.getText().equals("")) {
            inputMaCTMT.setText((String) bangCtMuonTra.getValueAt(row2, 0));
        }

        comboMaSach.setSelectedItem((String) bangCtMuonTra.getValueAt(row2, 1));
        inputSoLuongMT.setText((String) bangCtMuonTra.getValueAt(row2, 2));
        inputNgayTra.setText((String) bangCtMuonTra.getValueAt(row2, 3));
        inputTienCoc.setText((String) bangCtMuonTra.getValueAt(row2, 4));
        inputGhiChu.setText((String) bangCtMuonTra.getValueAt(row2, 6));
    }//GEN-LAST:event_bangCtMuonTraMouseClicked

    private void suaCTMTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaCTMTActionPerformed
        update(bangCtMuonTra);
    }//GEN-LAST:event_suaCTMTActionPerformed

    private void searchSachByMaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchSachByMaSachActionPerformed

    }//GEN-LAST:event_searchSachByMaSachActionPerformed

    private void searchDocGiaByTenDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchDocGiaByTenDGActionPerformed

    }//GEN-LAST:event_searchDocGiaByTenDGActionPerformed

    private void findSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findSachActionPerformed
        fOut = false;
        if (inputFindSach.getText().equals("")) {
            loadDataTable(bangSach);
            return;
        } else {
            String id = inputFindSach.getText();
            ResultSet rs = null;
            DefaultTableModel model = new DefaultTableModel();
            if (buttonGroup1.isSelected(searchSachByMaSach.getModel())) {
                rs = myConnect.getDataById(id, "select * from sach where MaSach_Manhvd_20162679 = ?");
            } else if (buttonGroup1.isSelected(searchSachByTenTG.getModel())) {
                rs = myConnect.find(id, "select * from sach where TenTG_Manhvd_20162679 = ?");
            } else {
                rs = myConnect.find(id, "select * from sach where TheLoai_Manhvd_20162679 = ?");
            }
            try {
                ResultSetMetaData rsMD = rs.getMetaData();
                int colNumber = rsMD.getColumnCount();
                String[] arr = new String[colNumber];

                for (int i = 0; i < colNumber; i++) {
                    arr[i] = rsMD.getColumnName(i + 1);
                }
                model.setColumnIdentifiers(arr);

                while (rs.next()) {
                    for (int i = 0; i < colNumber; i++) {
                        arr[i] = rs.getString(i + 1);
                    }
                    model.addRow(arr);
                }
            } catch (SQLException e) {

            }
            bangSach.setModel(model);
        }
    }//GEN-LAST:event_findSachActionPerformed

    private void findDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findDGActionPerformed
        if (inputFindDG.getText().equals("")) {
            loadDataTable(bangDocGia);
            return;
        } else {
            String id = inputFindDG.getText();
            ResultSet rs = null;
            DefaultTableModel model = new DefaultTableModel();
            if (buttonGroup2.isSelected(searchDGByMaDG.getModel())) {

                rs = myConnect.getDataById(id, "select * from sach where MaSach_Manhvd_20162679 = ?");

            } else if (buttonGroup2.isSelected(searchDocGiaByTenDG.getModel())) {
                rs = myConnect.find(id, "select * from docgia where TenDG_Manhvd_20162679 = ?");
            } else {
                rs = myConnect.find(id, "select * from docgia where DiaChi_Manhvd_20162679 = ?");
            }
            try {
                ResultSetMetaData rsMD = rs.getMetaData();
                int colNumber = rsMD.getColumnCount();
                String[] arr = new String[colNumber];

                for (int i = 0; i < colNumber; i++) {
                    arr[i] = rsMD.getColumnName(i + 1);
                }
                model.setColumnIdentifiers(arr);

                while (rs.next()) {
                    for (int i = 0; i < colNumber; i++) {
                        arr[i] = rs.getString(i + 1);
                    }
                    model.addRow(arr);
                }
            } catch (SQLException e) {

            }
            bangDocGia.setModel(model);
        }
    }//GEN-LAST:event_findDGActionPerformed

    private void findDG1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findDG1ActionPerformed
        if (inputFindNV.getText().equals("")) {
            loadDataTable(bangNhanVien);
            return;
        } else {
            String id = inputFindNV.getText();
            ResultSet rs = null;
            DefaultTableModel model = new DefaultTableModel();
            if (buttonGroup3.isSelected(jRadioButton7.getModel())) {

                rs = myConnect.getDataById(id, "select * from nhanvien where MaNV_Manhvd_20162679 = ?");

            } else if (buttonGroup3.isSelected(jRadioButton8.getModel())) {
                rs = myConnect.find(id, "select * from nhanvien where TenNV_Manhvd_20162679 = ?");
            } else {
                rs = myConnect.find(id, "select * from nhanvien where SDT_Manhvd_20162679 = ?");
            }
            try {
                ResultSetMetaData rsMD = rs.getMetaData();
                int colNumber = rsMD.getColumnCount();
                String[] arr = new String[colNumber];

                for (int i = 0; i < colNumber; i++) {
                    arr[i] = rsMD.getColumnName(i + 1);
                }
                model.setColumnIdentifiers(arr);

                while (rs.next()) {
                    for (int i = 0; i < colNumber; i++) {
                        arr[i] = rs.getString(i + 1);
                    }
                    model.addRow(arr);
                }
            } catch (SQLException e) {

            }
            bangNhanVien.setModel(model);
        }
    }//GEN-LAST:event_findDG1ActionPerformed


    private void comboSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSachActionPerformed
        fOut = true;
        ArrayList<Sach> sach = new ArrayList<>();
        Object obj = evt.getSource();
        DefaultTableModel modelTK = (DefaultTableModel) bangSach.getModel();
        if (obj == comboSach) {
            int index = comboSach.getSelectedIndex();
            switch (index) {
                case 0:
                    sach = myConnect.tKeTheoTenSach();
                    break;
                case 1:
                    sach = myConnect.tKeTheoTheLoai();
                    break;
                case 2:

                    sach = myConnect.tKeTheoNXB();
                    break;
            }
            removeAllRow(bangSach);
            if (index == 0) {
                int i = 0;
                modelTK.setColumnIdentifiers(new Object[]{
                    "    STT", "      Tên Sách", "     Số Lượng Còn ", "  Tổng Số Lượng"
                });
                for (Sach s : sach) {
                    modelTK.addRow(new Object[]{
                        ++i, s.getTenSach(), s.getSoLuongCon(), s.getSoLuong()
                    });
                }
            } else if (index == 1) {
                int i = 0;
                modelTK.setColumnIdentifiers(new Object[]{
                    "     STT", "     Thể Loại", "    Số Lượng Còn", " Tổng Số Lượng"
                });
                for (Sach s : sach) {
                    modelTK.addRow(new Object[]{
                        ++i, s.getTheLoai(), s.getSoLuongCon(), s.getSoLuong()
                    });
                }
            } else if (index == 2) {
                int i = 0;
                modelTK.setColumnIdentifiers(new Object[]{
                    "      STT", "   Nhà Xuất Bản", "    Số Lượng Còn", "  Tổng Sô Lượng"
                });
                for (Sach s : sach) {
                    modelTK.addRow(new Object[]{
                        ++i, s.getTheLoai(), s.getSoLuongCon(), s.getSoLuong()
                    });
                }
            }

        }


    }//GEN-LAST:event_comboSachActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String fileName = System.getProperty("user.home");
        String f1 = "\\Documents\\NetBeansProjects\\QLTV\\Quản Lý Sách\\";
        String f2 = null;
        XuatFile output = new XuatFile();
        if (fOut == true) {
            if (comboSach.getSelectedIndex() == 0) {
                f2 = "Thống Kê Sách Theo Tên Sách.docx";
            }
            if (comboSach.getSelectedIndex() == 1) {
                f2 = "Thống Kê Sách Theo Thể Loại.docx";
            }
            if (comboSach.getSelectedIndex() == 2) {
                f2 = "Thống Kê Sách Theo NXB.docx";
            }
            fileName = fileName + f1 + f2;

        } else {
            if (searchSachByMaSach.isSelected()) {
                f2 = "Tìm Kiếm Sách Theo Mã Sách.docx";
            }
            if (searchSachByTenTG.isSelected()) {
                f2 = "Tìm Kiếm Sách Theo Tên Sách.docx";
            }
            if (searchSachByTheLoai.isSelected()) {
                f2 = "Tìm Kiếm Sách Theo Thể Loại.docx";
            }
            fileName = fileName + f1 + f2;

        }
        output.output(bangSach, fileName);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void bangSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bangSachMouseClicked
        int row = bangSach.getSelectedRow();
        inputMaSach.setText((String) bangSach.getValueAt(row, 0));
        inputTenSach.setText((String) bangSach.getValueAt(row, 1));
        inputTenTacGia.setText((String) bangSach.getValueAt(row, 2));
        inputNXB.setText((String) bangSach.getValueAt(row, 3));
        inputNamXB.setText((String) bangSach.getValueAt(row, 4));
        inputGiaTien.setText((String) bangSach.getValueAt(row, 5));
        inputTheLoai.setText((String) bangSach.getValueAt(row, 6));
        inputSoLuong.setText((String) bangSach.getValueAt(row, 7));
        inputSoLuongCon.setText((String) bangSach.getValueAt(row, 8));
    }//GEN-LAST:event_bangSachMouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

    }//GEN-LAST:event_jButton4ActionPerformed

    private void comboDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDGActionPerformed

    }//GEN-LAST:event_comboDGActionPerformed

    private void comboMaNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboMaNVMouseClicked

    }//GEN-LAST:event_comboMaNVMouseClicked

    private void comboMaDGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboMaDGMouseClicked

    }//GEN-LAST:event_comboMaDGMouseClicked

    private void comboMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMaNVActionPerformed
        int row3 = -1;
        TableModel modelNhanVien = bangNhanVien.getModel();
        int rowCount = modelNhanVien.getRowCount();
        for (int i = 0; i < rowCount; i++) {

            if (modelNhanVien.getValueAt(i, 0).toString().equals((String)comboMaNV.getSelectedItem().toString())) {
                row3 = i;
                break;
            }

        }
        hienTenNhanVien.setText((String) bangNhanVien.getValueAt(row3, 1));
    }//GEN-LAST:event_comboMaNVActionPerformed

    private void comboMaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMaSachActionPerformed
        int row3 = -1;
        TableModel modelSach = bangSach.getModel();
        int rowCount = modelSach.getRowCount();
        for (int i = 0; i < rowCount; i++) {

            if (modelSach.getValueAt(i, 0).toString().equals((String)comboMaSach.getSelectedItem().toString())) {
                row3 = i;
                break;
            }

        }
        hienTenSach.setText((String) bangSach.getValueAt(row3, 1));
    }//GEN-LAST:event_comboMaSachActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MyInterface.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MyInterface.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MyInterface.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MyInterface.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addDbDocGia;
    private javax.swing.JButton addDbNhanVien;
    private javax.swing.JButton addDbSach;
    private javax.swing.JTable bangCtMuonTra;
    private javax.swing.JTable bangDocGia;
    private javax.swing.JTable bangMuonTra;
    private javax.swing.JTable bangNhanVien;
    public static javax.swing.JTable bangSach;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JComboBox<String> comboDG;
    private javax.swing.JComboBox<String> comboMT;
    private javax.swing.JComboBox<String> comboMaDG;
    private javax.swing.JComboBox<String> comboMaNV;
    private javax.swing.JComboBox<String> comboMaSach;
    private javax.swing.JComboBox<String> comboNV;
    private javax.swing.JComboBox<String> comboSach;
    private javax.swing.JButton findDG;
    private javax.swing.JButton findDG1;
    private javax.swing.JButton findMT;
    private javax.swing.JButton findSach;
    private javax.swing.JLabel hienTenDocGia;
    private javax.swing.JLabel hienTenNhanVien;
    private javax.swing.JLabel hienTenSach;
    private javax.swing.JTextField inputCMNDDG;
    private javax.swing.JTextField inputDiaChiDG;
    private javax.swing.JTextField inputDiaChiNV;
    private javax.swing.JTextField inputEmailNV;
    private javax.swing.JTextField inputFindDG;
    private javax.swing.JTextField inputFindMT;
    private javax.swing.JTextField inputFindNV;
    public javax.swing.JTextField inputFindSach;
    private javax.swing.JTextArea inputGhiChu;
    public javax.swing.JTextField inputGiaTien;
    private javax.swing.JTextField inputGioiTinhDG;
    private javax.swing.JTextField inputGioiTinhNV;
    private javax.swing.JTextField inputHanTra;
    private javax.swing.JTextField inputMaCTMT;
    private javax.swing.JTextField inputMaDG;
    private javax.swing.JTextField inputMaMT;
    private javax.swing.JTextField inputMaNV;
    public javax.swing.JTextField inputMaSach;
    public javax.swing.JTextField inputNXB;
    public javax.swing.JTextField inputNamXB;
    private javax.swing.JTextField inputNgayMuon;
    private javax.swing.JTextField inputNgaySinhDG;
    private javax.swing.JTextField inputNgaySinhNV;
    private javax.swing.JTextField inputNgayTra;
    private javax.swing.JTextField inputSDTDG;
    private javax.swing.JTextField inputSDTNV;
    public javax.swing.JTextField inputSoLuong;
    public javax.swing.JTextField inputSoLuongCon;
    private javax.swing.JTextField inputSoLuongMT;
    private javax.swing.JTextField inputTenDG;
    private javax.swing.JTextField inputTenNV;
    public javax.swing.JTextField inputTenSach;
    public javax.swing.JTextField inputTenTacGia;
    public javax.swing.JTextField inputTheLoai;
    private javax.swing.JTextField inputTienCoc;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButton19;
    private javax.swing.JRadioButton jRadioButton20;
    private javax.swing.JRadioButton jRadioButton21;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton reset;
    private javax.swing.JButton reset1;
    private javax.swing.JButton reset2;
    private javax.swing.JRadioButton searchDGByDiaChi;
    private javax.swing.JRadioButton searchDGByMaDG;
    private javax.swing.JRadioButton searchDocGiaByTenDG;
    private javax.swing.JRadioButton searchSachByMaSach;
    private javax.swing.JRadioButton searchSachByTenTG;
    private javax.swing.JRadioButton searchSachByTheLoai;
    private javax.swing.JButton suaCTMT;
    private javax.swing.JButton suaDG1;
    private javax.swing.JButton suaMT;
    private javax.swing.JButton suaNV;
    public javax.swing.JButton suaSach;
    private javax.swing.JButton themCTMT;
    private javax.swing.JButton themDG;
    private javax.swing.JButton themMT;
    private javax.swing.JButton themNV;
    public javax.swing.JButton themSach;
    private javax.swing.JButton xoaCTMT;
    private javax.swing.JButton xoaDG;
    private javax.swing.JButton xoaMT;
    private javax.swing.JButton xoaNV;
    public javax.swing.JButton xoaSach;
    // End of variables declaration//GEN-END:variables
}
