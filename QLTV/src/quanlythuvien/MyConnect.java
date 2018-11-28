/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class MyConnect {

    private final String className = "com.mysql.cj.jdbc.Driver";
    //database_url = "jdbc:mysql://localhost/Ten_cua_database?useUnicode=true&characterEncoding=UTF-8";
    private final String url = "jdbc:mysql://localhost:3306/quanlythuvien?useUnicode=true&characterEncoding=UTF-8";
    private final String user = "root";
    private final String pass = "";

    public String tableSach = "sach";
    public String tableDocGia = "docgia";
    public String tableNhanVien = "nhanvien";
    public String tableMuonTra = "muontra";
    public String tableCtMuonTra = "ctmuontra";

    public Connection connection;

    public void connectDatabase() {
        try {
            Class.forName(className);
            connection = DriverManager.getConnection(url, user, pass);
            
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        } catch (SQLException e) {
            System.out.println("Error Connection");
        }
    }

    //getData của các bảng
    public ResultSet getDataSach() {
        MyConnect connect = new MyConnect();
        connect.connectDatabase();
        ResultSet rs = null;
        String sqlCommand = "select * from " + connect.tableSach;	//Câu lệnh truy vấn CSDL
        Statement st;

        try {
            st = connect.connection.createStatement();//tạo ra cái Statement có nhiệm vụ thực thi câu lệnh sqlCommand để tác động đến CSDL
            rs = st.executeQuery(sqlCommand); //Thực thi câu lệnh sqlCommand, excuteQuery trả về 1 đối tượng ResultSet
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("select error" + e.toString());
        }

        return rs;
    }

    public ResultSet getDataDocGia() {
        MyConnect connect = new MyConnect();
        connect.connectDatabase();
        ResultSet rs = null;
        String sqlCommand = "select * from " + connect.tableDocGia;	//Câu lệnh truy vấn CSDL
        Statement st;

        try {
            st = connect.connection.createStatement();//tạo ra cái Statement có nhiệm vụ thực thi câu lệnh sqlCommand để tác động đến CSDL
            rs = st.executeQuery(sqlCommand); //Thực thi câu lệnh sqlCommand, excuteQuery trả về 1 đối tượng ResultSet
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("select error" + e.toString());
        }

        return rs;
    }

    public ResultSet getDataNhanVien() {
        MyConnect connect = new MyConnect();
        connect.connectDatabase();
        ResultSet rs = null;
        String sqlCommand = "select * from " + connect.tableNhanVien;	//Câu lệnh truy vấn CSDL
        Statement st;

        try {
            st = connect.connection.createStatement();//tạo ra cái Statement có nhiệm vụ thực thi câu lệnh sqlCommand để tác động đến CSDL
            rs = st.executeQuery(sqlCommand); //Thực thi câu lệnh sqlCommand, excuteQuery trả về 1 đối tượng ResultSet
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("select error" + e.toString());
        }

        return rs;
    }

    public ResultSet getDataMuonTra() {
        MyConnect connect = new MyConnect();
        connect.connectDatabase();
        ResultSet rs = null;
        String sqlCommand = "select * from " + connect.tableMuonTra;	//Câu lệnh truy vấn CSDL
        Statement st;

        try {
            st = connect.connection.createStatement();//tạo ra cái Statement có nhiệm vụ thực thi câu lệnh sqlCommand để tác động đến CSDL
            rs = st.executeQuery(sqlCommand); //Thực thi câu lệnh sqlCommand, excuteQuery trả về 1 đối tượng ResultSet
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("select error" + e.toString());
        }

        return rs;
    }

    public ResultSet getDataCtMuonTra() {
        MyConnect connect = new MyConnect();
        connect.connectDatabase();
        ResultSet rs = null;
        String sqlCommand = "select * from " + connect.tableCtMuonTra;	//Câu lệnh truy vấn CSDL
        Statement st;

        try {
            st = connect.connection.createStatement();//tạo ra cái Statement có nhiệm vụ thực thi câu lệnh sqlCommand để tác động đến CSDL
            rs = st.executeQuery(sqlCommand); //Thực thi câu lệnh sqlCommand, excuteQuery trả về 1 đối tượng ResultSet
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("select error" + e.toString());
        }

        return rs;
    }

    //lấy dữ liệu của bởi id
    public ResultSet getDataByIdSach(String id) {

        ResultSet rs = null;

        String sqlCommand = "select * from " + tableSach + " where MaSach_Manhvd_20162679 = ?";
        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, id);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println("select error" + e.toString());
        }

        return rs;
    }

    public ResultSet getDataByIdDocGia(String id) {

        ResultSet rs = null;

        String sqlCommand = "select * from " + tableDocGia + " where MaDG_Manhvd_20162679 = ?";
        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, id);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println("select error" + e.toString());
        }

        return rs;
    }

    public ResultSet getDataByIdNhanVien(String id) {

        ResultSet rs = null;

        String sqlCommand = "select * from " + tableNhanVien + " where MaNV_Manhvd_20162679 = ?";
        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, id);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println("select error" + e.toString());
        }

        return rs;
    }

    public ResultSet getDataByIdMuonTra(String id) {

        ResultSet rs = null;

        String sqlCommand = "select * from " + tableSach + " where MaMuon_Manhvd_20162679 = ?";
        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, id);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println("select error" + e.toString());
        }

        return rs;
    }

    public ResultSet getDataByIdCtMuonTra(String id) {

        ResultSet rs = null;

        String sqlCommand = "select * from " + tableCtMuonTra + " where MaMuon_Manhvd_20162679 = ?";
        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, id);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println("select error" + e.toString());
        }

        return rs;
    }

    
    //Hàm xóa dữ liệu 
    public void deleteDataByIdSach(String id) {
        //ko cần trả về dữ liệu select cho nên ko cần resultset

        String sqlCommand = "delete from " + tableSach + " where MaSach_Manhvd_20162679 = ?";	//dấu hỏi để tí nữa xét pst
        PreparedStatement pst = null;

        try {

            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, id);

            if (pst.executeUpdate() > 0) {
                System.out.println("Delete Successfully");
            } else {
                System.out.println("Delete failed");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("delete error" + e.toString());
        }
    }

    public void deleteDataByIdDocGia(String id) {
        String sqlCommand = "delete from " + tableDocGia + " where MaDG_Manhvd_20162679 = ?";
        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, id);

            if (pst.executeUpdate() > 0) {
                System.out.println("Delete Successfully");
            } else {
                System.out.println("Delete failed");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("delete error" + e.toString());
        }
    }

    public void deleteDataByIdNhanVien(String id) {
        String sqlCommand = "delete from " + tableNhanVien + " where MaNV_Manhvd_20162679 = ?";
        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, id);

            if (pst.executeUpdate() > 0) {
                System.out.println("Delete Successfully");
            } else {
                System.out.println("Delete failed");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("delete error" + e.toString());
        }
    }

    public void deleteDataByIdMuonTra(String id) {
        String sqlCommand = "delete from " + tableMuonTra + " where MaMuon_Manhvd_20162679 = ?";
        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, id);

            if (pst.executeUpdate() > 0) {
                System.out.println("Delete Successfully");
            } else {
                System.out.println("Delete failed");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("delete error" + e.toString());
        }
    }

    public void deleteDataByIdCtMuonTra(String id, String idSach) {
        String sqlCommand = "delete from " + tableCtMuonTra + " where MaMuon_Manhvd_20162679 = ? && MaSach_Manhvd_20162679 = ?";
        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, id);
            pst.setString(2, idSach);

            if (pst.executeUpdate() > 0) {
                System.out.println("Delete Successfully");
            } else {
                System.out.println("Delete failed");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("delete error" + e.toString());
        }
    }

    //Các hàm insert
    public void insertSach(Sach sach) {
        //ko cần trả về dữ liệu select cho nên ko cần resultset

        String sqlCommand = "insert into " + tableSach + " value (?, ?, ?, ?, ?, ?, ?, ?, ?)";	//dấu hỏi để tí nữa xét pst
        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, sach.getMaSach());
            pst.setString(2, sach.getTenSach());
            pst.setString(3, sach.getTenTG());
            pst.setString(4, sach.getNXB());
            pst.setString(5, sach.getNamXB());
            pst.setInt(6, sach.getGiaTien());
            pst.setString(7, sach.getTheLoai());
            pst.setInt(8, sach.getSoLuongCon());
            pst.setInt(9, sach.getSoLuong());

            if (pst.executeUpdate() > 0) {
                System.out.println("insert Successfully");
            } else {
                System.out.println("insert failed");
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            System.out.println("select error" + e1.toString());
        }
    }

    public void insertDocGia(DocGia dg) {
        //ko cần trả về dữ liệu select cho nên ko cần resultset

        String sqlCommand = "insert into " + tableDocGia + " value (?, ?, ?, ?, ?, ?, ?)";	//dấu hỏi để tí nữa xét pst
        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, dg.getMaDG());
            pst.setString(2, dg.getTenDG());
            pst.setString(3, dg.getGT());
            pst.setString(4, dg.getNgaySinh());
            pst.setString(5, dg.getDiaChi());
            pst.setString(6, dg.getSdt());
            pst.setString(7, dg.getCmnd());

            if (pst.executeUpdate() > 0) {
                System.out.println("insert Successfully");
            } else {
                System.out.println("insert failed");
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            System.out.println("select error" + e1.toString());
        }
    }

    public void insertNhanVien(NhanVien nv) {
        //ko cần trả về dữ liệu select cho nên ko cần resultset

        String sqlCommand = "insert into " + tableNhanVien + " value (?, ?, ?, ?, ?, ?, ?)";	//dấu hỏi để tí nữa xét pst
        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, nv.getMaNV());
            pst.setString(2, nv.getTenNV());
            pst.setString(3, nv.getNgaySinh());
            pst.setString(4, nv.getGT());
            pst.setString(5, nv.getDiaChi());
            pst.setString(6, nv.getEmail());
            pst.setString(7, nv.getSdt());

            if (pst.executeUpdate() > 0) {
                System.out.println("insert Successfully");
            } else {
                System.out.println("insert failed");
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            System.out.println("select error" + e1.toString());
        }
    }

    public void insertMuonTra(MuonTra mt) {
        //ko cần trả về dữ liệu select cho nên ko cần resultset

        String sqlCommand = "insert into " + tableMuonTra + " value (?, ?, ?, ?, ?)";	//dấu hỏi để tí nữa xét pst
        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, mt.getMaMuon());
            pst.setString(2, mt.getMaDG());
            pst.setString(3, mt.getMaNV());
            pst.setString(4, mt.getNgayMuon());
            pst.setString(5, mt.getHanTra());

            if (pst.executeUpdate() > 0) {
                System.out.println("insert Successfully");
            } else {
                System.out.println("insert failed");
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            System.out.println("select error" + e1.toString());
        }
    }

    public void insertCtMuonTra(CtMuonTra mt) {
        //ko cần trả về dữ liệu select cho nên ko cần resultset

        String sqlCommand = "insert into " + tableCtMuonTra + " value (?, ?, ?, ?, ?, ?, ?)";	//dấu hỏi để tí nữa xét pst
        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, mt.getMaMuon());
            pst.setString(2, mt.getMaSach());
            pst.setInt(3, mt.getSoLuong());
            pst.setString(4, mt.getNgayTra());
            pst.setInt(5, mt.getTienCoc());
            pst.setLong(6, mt.getTienPhat());
            pst.setString(7, mt.getGhiChu());

            if (pst.executeUpdate() > 0) {
                System.out.println("insert Successfully");
            } else {
                System.out.println("insert failed");
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            System.out.println("select error" + e1.toString());
        }
    }

    
    //Các hàm update
    public void updateSach(String id, Sach sach) {
        //ko cần trả về dữ liệu select cho nên ko cần resultset

        String sqlCommand = "update " + tableSach
                + " set TenSach_Manhvd_20162679 = ?, "
                + "TenTG_Manhvd_20162679 = ?, "
                + "NXB_Manhvd_20162679 = ?, "
                + "NamXB_Manhvd_20162679 = ?, "
                + "GiaTien_Manhvd_20162679 = ?, "
                + "TheLoai_Manhvd_20162679 = ?, "
                + "SoLuongCon_Manhvd_20162679 = ?, "
                + "SoLuong_Manhvd_20162679 = ? "
                + "where MaSach_Manhvd_20162679 = ?";	//dấu hỏi để tí nữa xét pst

        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);

            pst.setString(1, sach.getTenSach());
            pst.setString(2, sach.getTenTG());
            pst.setString(3, sach.getNXB());
            pst.setString(4, sach.getNamXB());
            pst.setInt(5, sach.getGiaTien());
            pst.setString(6, sach.getTheLoai());
            pst.setInt(7, sach.getSoLuongCon());
            pst.setInt(8, sach.getSoLuong());
            pst.setString(9, id);
            if (pst.executeUpdate() > 0) {
                System.out.println("update Successfully");
            } else {
                System.out.println("update failed");
            }
        } catch (SQLException e3) {
            // TODO Auto-generated catch block
            System.out.println("update lỗi nhé ! " + e3.toString());
        }
    }

    public void updateDocGia(String id, DocGia dg) {
        //ko cần trả về dữ liệu select cho nên ko cần resultset

        String sqlCommand = "update " + tableDocGia
                + " set TenDG_Manhvd_20162679 = ?, "
                + "GT_Manhvd_20162679 = ?, "
                + "NgaySinh_Manhvd_20162679 = ?, "
                + "DiaChi_Manhvd_20162679 = ?, "
                + "SDT_Manhvd_20162679 = ?, "
                + "CMND_Manhvd_20162679 = ? "
                + "where MaDG_Manhvd_20162679 = ?";        //dấu hỏi để tí nữa xét pst

        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);

            pst.setString(1, dg.getTenDG());
            pst.setString(2, dg.getGT());
            pst.setString(3, dg.getNgaySinh());
            pst.setString(4, dg.getDiaChi());
            pst.setString(5, dg.getSdt());
            pst.setString(6, dg.getCmnd());
            pst.setString(7, dg.getMaDG());
            if (pst.executeUpdate() > 0) {
                System.out.println("update Successfully");
            } else {
                System.out.println("update failed");
            }
        } catch (SQLException e3) {
            // TODO Auto-generated catch block
            System.out.println("update lỗi nhé ! " + e3.toString());
        }
    }

    public void updateNhanVien(String id, NhanVien nv) {
        //ko cần trả về dữ liệu select cho nên ko cần resultset

        String sqlCommand = "update " + tableNhanVien
                + " set TenNV_Manhvd_20162679 = ?, "
                + "NgaySinh_Manhvd_20162679 = ?, "
                + "GT_Manhvd_20162679 = ?, "
                + "DiaChi_Manhvd_20162679 = ?, "
                + "Email_Manhvd_20162679 = ?, "
                + "SDT_Manhvd_20162679 = ? "
                + "where MaNV_Manhvd_20162679 = ?";        //dấu hỏi để tí nữa xét pst

        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);

            pst.setString(1, nv.getTenNV());
            pst.setString(2, nv.getNgaySinh());
            pst.setString(3, nv.getGT());
            pst.setString(4, nv.getDiaChi());
            pst.setString(5, nv.getEmail());
            pst.setString(6, nv.getSdt());
            pst.setString(7, nv.getMaNV());
            if (pst.executeUpdate() > 0) {
                System.out.println("update Successfully");
            } else {
                System.out.println("update failed");
            }
        } catch (SQLException e3) {
            // TODO Auto-generated catch block
            System.out.println("update lỗi nhé ! " + e3.toString());
        }
    }

    public void updateMuonTra(String id, MuonTra mt) {
        //ko cần trả về dữ liệu select cho nên ko cần resultset

        String sqlCommand = "update " + tableMuonTra
                + " set MaDG_Manhvd_20162679 = ?, "
                + "MaNV_Manhvd_20162679 = ?, "
                + "NgayMuon_Manhvd_20162679 = ?, "
                + "HanTra_Manhvd_20162679 = ? "
                + "where MaMuon_Manhvd_20162679 = ?";        //dấu hỏi để tí nữa xét pst

        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);

            pst.setString(1, mt.getMaDG());
            pst.setString(2, mt.getMaNV());
            pst.setString(3, mt.getNgayMuon());
            pst.setString(4, mt.getHanTra());
            pst.setString(5, mt.getMaMuon());

            if (pst.executeUpdate() > 0) {
                System.out.println("update Successfully");
            } else {
                System.out.println("update failed");
            }
        } catch (SQLException e3) {
            // TODO Auto-generated catch block
            System.out.println("update lỗi nhé ! " + e3.toString());
        }
    }

    public void updateCtMuonTra(String id, CtMuonTra mt) {
        //ko cần trả về dữ liệu select cho nên ko cần resultset

        String sqlCommand = "update " + tableCtMuonTra
                + " set MaSach_Manhvd_20162679 = ?, "
                + "SoLuong_Manhvd_20162679 = ?, "
                + "NgayTra_Manhvd_20162679 = ?, "
                + "TienCoc_Manhvd_20162679 = ?, "
                + "TienPhat_Manhvd_20162679 = ?, "
                + "GhiChu_Manhvd_20162679 = ? "
                + "where MaMuon_Manhvd_20162679 = ?";        //dấu hỏi để tí nữa xét pst

        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);

            pst.setString(1, mt.getMaSach());
            pst.setInt(2, mt.getSoLuong());
            pst.setString(3, mt.getNgayTra());
            pst.setInt(4, mt.getTienCoc());
            pst.setLong(5, mt.getTienPhat());
            pst.setString(6, mt.getGhiChu());
            pst.setString(7, mt.getMaMuon());

            if (pst.executeUpdate() > 0) {
                System.out.println("update Successfully");
            } else {
                System.out.println("update failed");
            }
        } catch (SQLException e3) {
            // TODO Auto-generated catch block
            System.out.println("update lỗi nhé ! " + e3.toString());
        }
    }
    
    //Tìm kiếm sách theo tên tác giả
    public ResultSet getDataByTenTacGia(String id) {

        ResultSet rs = null;

        String sqlCommand = "select * from " + tableSach + " where TenTG_Manhvd_20162679 = ?";
        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, id);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println("select error" + e.toString());
        }

        return rs;
    }
    
    //Tìm kiếm sách theo thể loại
    public ResultSet getDataByTheLoai(String id) {

        ResultSet rs = null;

        String sqlCommand = "select * from " + tableSach + " where TheLoai_Manhvd_20162679 = ?";
        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, id);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println("select error" + e.toString());
        }

        return rs;
    }
    
    //Tìm kiếm Độc giả theo tên 
    public ResultSet getDataByTenDocGia(String id){
        ResultSet rs = null;

        String sqlCommand = "select * from " + tableDocGia + " where TenDG_Manhvd_20162679 = ?";
        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, id);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println("select error" + e.toString());
        }

        return rs;
    }
    
    //Tìm kiếm Độc giả theo địa chỉ
    public ResultSet getDataByDiaChiDG(String id){
        ResultSet rs = null;

        String sqlCommand = "select * from " + tableDocGia + " where DiaChi_Manhvd_20162679 = ?";
        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, id);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println("select error" + e.toString());
        }

        return rs;
    }
    
    //Tìm kiếm nhân viên theo tên 
    public ResultSet getDataByTenNhanVien(String id){
        ResultSet rs = null;

        String sqlCommand = "select * from " + tableNhanVien + " where TenNV_Manhvd_20162679 = ?";
        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, id);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println("select error" + e.toString());
        }

        return rs;
    }
    
    //Tìm kiếm nhân viên theo số điện thoại
    public ResultSet getDataBySdtNhanVien(String id){
        ResultSet rs = null;

        String sqlCommand = "select * from " + tableNhanVien + " where SDT_Manhvd_20162679 = ?";
        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, id);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println("select error" + e.toString());
        }

        return rs;
    }
}
