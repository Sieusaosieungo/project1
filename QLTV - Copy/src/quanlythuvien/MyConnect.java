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
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MyConnect {

    private final String className = "com.mysql.cj.jdbc.Driver";
    //database_url = "jdbc:mysql://localhost/Ten_cua_database?useUnicode=true&characterEncoding=UTF-8";
    private final String url = "jdbc:mysql://localhost:3306/quanlythuvien?useUnicode=true&characterEncoding=UTF-8";
    private final String user = "root";
    private final String pass = "";

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
    public ResultSet getData(String table) {
        MyConnect connect = new MyConnect();
        connect.connectDatabase();
        ResultSet rs = null;
        String sqlCommand = "select * from " + table;	//Câu lệnh truy vấn CSDL
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
    public ResultSet getDataById(String id, String sqlCommand) {
        ResultSet rs = null;
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
    public void deleteDataById(String id, String sqlCommand) {
        //ko cần trả về dữ liệu select cho nên ko cần resultset

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
            System.out.println("delete error" + e.getMessage());
        }
    }

    public void deleteDataByIdCtMuonTra(String id, String idSach) {
        String sqlCommand = "delete from ctmuontra where MaMuon_Manhvd_20162679 = ? && MaSach_Manhvd_20162679 = ?";
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

        String sqlCommand = "insert into sach value (?, ?, ?, ?, ?, ?, ?, ?, ?)";	//dấu hỏi để tí nữa xét pst
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

        String sqlCommand = "insert into docgia value (?, ?, ?, ?, ?, ?, ?)";	//dấu hỏi để tí nữa xét pst
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

        String sqlCommand = "insert into nhanvien value (?, ?, ?, ?, ?, ?, ?)";	//dấu hỏi để tí nữa xét pst
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

        String sqlCommand = "insert into muontra value (?, ?, ?, ?, ?)";	//dấu hỏi để tí nữa xét pst
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

        String sqlCommand = "insert into ctmuontra value (?, ?, ?, ?, ?, ?, ?)";	//dấu hỏi để tí nữa xét pst
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
    public boolean updateSach(String id, Sach sach) {
        //ko cần trả về dữ liệu select cho nên ko cần resultset

        String sqlCommand = "update sach"
                + " set MaSach_Manhvd_20162679 = ?, "
                + "TenSach_Manhvd_20162679 = ?, "
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
            pst.setString(1, sach.getMaSach());
            pst.setString(2, sach.getTenSach());
            pst.setString(3, sach.getTenTG());
            pst.setString(4, sach.getNXB());
            pst.setString(5, sach.getNamXB());
            pst.setInt(6, sach.getGiaTien());
            pst.setString(7, sach.getTheLoai());
            pst.setInt(8, sach.getSoLuongCon());
            pst.setInt(9, sach.getSoLuong());
            pst.setString(10, id);
            if (pst.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e3) {
            // TODO Auto-generated catch block
            System.out.println("update lỗi nhé ! " + e3.toString());
        }
        return false;
    }

    public boolean updateDocGia(String id, DocGia dg) {
        //ko cần trả về dữ liệu select cho nên ko cần resultset

        String sqlCommand = "update docgia"
                + " set MaDG_Manhvd_20162679 = ?, "
                + "TenDG_Manhvd_20162679 = ?, "
                + "GT_Manhvd_20162679 = ?, "
                + "NgaySinh_Manhvd_20162679 = ?, "
                + "DiaChi_Manhvd_20162679 = ?, "
                + "SDT_Manhvd_20162679 = ?, "
                + "CMND_Manhvd_20162679 = ? "
                + "where MaDG_Manhvd_20162679 = ?";        //dấu hỏi để tí nữa xét pst

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
            pst.setString(8, id);
            if (pst.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e3) {
            // TODO Auto-generated catch block
            System.out.println("update lỗi nhé ! " + e3.toString());
        }
        return false;
    }

    public boolean updateNhanVien(String id, NhanVien nv) {
        //ko cần trả về dữ liệu select cho nên ko cần resultset

        String sqlCommand = "update nhanvien"
                + " set MaNV_Manhvd_20162679 = ?, "
                + "TenNV_Manhvd_20162679 = ?, "
                + "NgaySinh_Manhvd_20162679 = ?, "
                + "GT_Manhvd_20162679 = ?, "
                + "DiaChi_Manhvd_20162679 = ?, "
                + "Email_Manhvd_20162679 = ?, "
                + "SDT_Manhvd_20162679 = ? "
                + "where MaNV_Manhvd_20162679 = ?";        //dấu hỏi để tí nữa xét pst

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
            pst.setString(8, id);
            if (pst.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e3) {
            // TODO Auto-generated catch block
            System.out.println("update lỗi nhé ! " + e3.toString());
        }
        return false;
    }

    public boolean updateMuonTra(String id, MuonTra mt) {
        //ko cần trả về dữ liệu select cho nên ko cần resultset

        String sqlCommand = "update muontra"
                + " set MaMuon_Manhvd_20162679 = ?, "
                + "MaDG_Manhvd_20162679 = ?, "
                + "MaNV_Manhvd_20162679 = ?, "
                + "NgayMuon_Manhvd_20162679 = ?, "
                + "HanTra_Manhvd_20162679 = ? "
                + "where MaMuon_Manhvd_20162679 = ?";        //dấu hỏi để tí nữa xét pst

        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, mt.getMaMuon());
            pst.setString(2, mt.getMaDG());
            pst.setString(3, mt.getMaNV());
            pst.setString(4, mt.getNgayMuon());
            pst.setString(5, mt.getHanTra());
            pst.setString(6, id);

            if (pst.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e3) {
            // TODO Auto-generated catch block
            System.out.println("update lỗi nhé ! " + e3.toString());
        }
        return false;
    }

    public boolean updateCtMuonTra(String id1, String id2, CtMuonTra mt) {
        //ko cần trả về dữ liệu select cho nên ko cần resultset

        String sqlCommand = "update quanlythuvien.ctmuontra "
                + "set MaMuon_Manhvd_20162679 = ?, "
                + "MaSach_Manhvd_20162679 = ?, "
                + "SoLuong_Manhvd_20162679 = ?, "
                + "NgayTra_Manhvd_20162679 = ?, "
                + "TienCoc_Manhvd_20162679 = ?, "
                + "TienPhat_Manhvd_20162679 = ?, "
                + "GhiChu_Manhvd_20162679 = ? "
                + "where (MaMuon_Manhvd_20162679 = ?) AND (MaSach_Manhvd_20162679 = ?);";

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
            pst.setString(8, id1);
            pst.setString(9, id2);

            if (pst.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e3) {
            // TODO Auto-generated catch block
            System.out.println("update lỗi nhé ! " + e3.toString());
        }
        return false;
    }

    //Tìm kiếm chung cho tất 
    public ResultSet find(String id, String sqlCommand) {

        ResultSet rs = null;

        //String sqlCommand = "select * from " + tableSach + " where TenTG_Manhvd_20162679 = ?";
        //String sqlCommand = "select * from " + tableSach + " where TheLoai_Manhvd_20162679 = ?";
        //String sqlCommand = "select * from " + tableDocGia + " where TenDG_Manhvd_20162679 = ?";
        //String sqlCommand = "select * from " + tableDocGia + " where DiaChi_Manhvd_20162679 = ?";
        //String sqlCommand = "select * from " + tableNhanVien + " where TenNV_Manhvd_20162679 = ?";
        //String sqlCommand = "select * from " + tableNhanVien + " where SDT_Manhvd_20162679 = ?";
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

    //Hàm thống kê Sách theo Tên Sách
    public ArrayList<Sach> tKeTheoTenSach() {
        ArrayList<Sach> sach = new ArrayList<Sach>();
        String sql = "SELECT TenSach_Manhvd_20162679,Sum(SoLuongCon_Manhvd_20162679),Sum(SoLuong_Manhvd_20162679) "
                + "FROM SACH "
                + "Group By TenSach_Manhvd_20162679;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Sach s = new Sach();
                s.setTenSach(rs.getString("TenSach_Manhvd_20162679"));
                s.setSoLuongCon(rs.getInt("Sum(SoLuongCon_Manhvd_20162679)"));
                s.setSoLuong(rs.getInt("Sum(SoLuong_Manhvd_20162679)"));
                sach.add(s);    //thêm vào mảng
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
        return sach;
    }

    //Hàm thống kê sách theo Thể Loại
    public ArrayList<Sach> tKeTheoTheLoai() {
        ArrayList<Sach> sach = new ArrayList<Sach>();
        String sql = "Select TheLoai_Manhvd_20162679,Sum(SoLuongCon_Manhvd_20162679),Sum(SoLuong_Manhvd_20162679) "
                + "from quanlythuvien.sach "
                + "group by TheLoai_Manhvd_20162679;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Sach s = new Sach();
                s.setTheLoai(rs.getString("TheLoai_Manhvd_20162679"));
                s.setSoLuongCon(rs.getInt("Sum(SoLuongCon_Manhvd_20162679)"));
                s.setSoLuong(rs.getInt("Sum(SoLuong_Manhvd_20162679)"));
                sach.add(s);    //thêm vào mảng
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
        return sach;
    }

    //Hàm thống kê sách theo Nhà Xuất Bản
    public ArrayList<Sach> tKeTheoNXB() {
        ArrayList<Sach> sach = new ArrayList<Sach>();
        String sql = "Select NXB_Manhvd_20162679,Sum(SoLuongCon_Manhvd_20162679),Sum(SoLuong_Manhvd_20162679) "
                + "from quanlythuvien.sach "
                + "group by NXB_Manhvd_20162679;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Sach s = new Sach();
                s.setTheLoai(rs.getString("NXB_Manhvd_20162679"));
                s.setSoLuongCon(rs.getInt("Sum(SoLuongCon_Manhvd_20162679)"));
                s.setSoLuong(rs.getInt("Sum(SoLuong_Manhvd_20162679)"));
                sach.add(s);    //thêm vào mảng
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
        return sach;
    }
    
    //
}
