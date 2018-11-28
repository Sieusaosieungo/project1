/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ManhVD
 */
public class ReadExcel {

    //MyInterface itf=new MyInterface();
    public ReadExcel() {

    }
    String path = null;
    public void chooseExcel(DefaultTableModel model) {
        
        JFileChooser fileChooser = new JFileChooser();
        // show ra một bảng chọn file
        int rVal = fileChooser.showOpenDialog(null);
        // nếu nhấn nút ok (tuỳ chọn APPROVE_OPTION)
        if (rVal == JFileChooser.APPROVE_OPTION) {
            String fileName = fileChooser.getSelectedFile().getName();
            String dir = fileChooser.getCurrentDirectory().toString();
            path = dir + "\\" + fileName;
        } // nếu nhấn nút cancel trong bảng 
        else if (rVal == JFileChooser.CANCEL_OPTION) {
            return;
        }
    }    

    // vector lưu tên cột
    public void readExcel(DefaultTableModel model) {
        
        Vector columns = new Vector();
        try {
            FileInputStream file = new FileInputStream(new File(path));
            // tạo một file excel
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            // tạo một sheet trong excel có số thứ tự là 0
            XSSFSheet sheet = workbook.getSheetAt(0);
            // con trỏ duyệt hàng trong excel 
            Iterator<Row> rowIt = sheet.iterator();
            // nếu vẫn còn dòng trong file
            while (rowIt.hasNext()) {
                // tạo một dòng mới 
                Row row = rowIt.next();
                // con trỏ trỏ vào các ô trong một dòng 
                Iterator<Cell> cellIt = row.cellIterator();
                // nếu là hàng 0 
                if (row.getRowNum() != 0) {
                    //vector chứa dữ liệu trong 1 dòng để add vào bảng jtabel
                    Vector<String> rowData = new Vector<String>();
                    // nếu vẫn còn ô tiếp theo
                    while (cellIt.hasNext()) {
                        // lấy cell trong bảng excel
                        Cell cell = cellIt.next();
                        // nếu cell có kiểu dữ liệu là string
                        // if(cell("type",	D1)){

                        // }
                        if (cell.getCellType() == CellType.STRING) {
                            rowData.add(cell.getStringCellValue().toString());
                        } // nếu cell có kiểu dữ liệu là số thì chuyển thành xâu.
                        else if (DateUtil.isCellDateFormatted(cell)) {
                            // Format Date
                            String pattern = "yyyy-MM-dd";
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                            // Format cell.getDateCellValue()
                            String date = simpleDateFormat.format(cell.getDateCellValue());
                            rowData.add(date);
                        } else if (cell.getCellType() == CellType.NUMERIC) {
                            rowData.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
                        }
                    }
                    // add dữ liệu vào trong bảng jtable 
                    model.addRow(rowData);
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
