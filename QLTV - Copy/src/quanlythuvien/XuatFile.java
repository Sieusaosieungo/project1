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
 * @author ManhVD
 */
public class XuatFile {
    public XuatFile(){
        
    }
    
    public void output(JTable jtable, String fileName){
        try {
                InputStream file = new FileInputStream(fileName);
                XWPFDocument hdoc = new XWPFDocument(OPCPackage.open(file));
                Iterator bodyElementIterator = hdoc.getBodyElementsIterator();
                while (bodyElementIterator.hasNext()) {
                    IBodyElement element = (IBodyElement) bodyElementIterator.next();
                    if ("TABLE".equalsIgnoreCase(element.getElementType().name())) {
                        //Danh sách tất cả Table trong file word
                        List<XWPFTable> tableList = element.getBody().getTables();

                        //Duyệt qua danh sách tất cả các table
                        for (XWPFTable table : tableList) {
                            //Căn bảng ở giữa file
                            table.setTableAlignment(TableRowAlign.CENTER);
                            //  Xóa các dòng thừa trước khi thêm mới
                            while (table.getRow(1) != null) {
                                table.removeRow(1);
                            }
                            //Thêm các dòng từ jTable vào table trong word
                            for (int i = 1; i <= jtable.getRowCount(); i++) {
                                XWPFTableRow newRow = table.createRow();
                                for (int j = 0; j < table.getRow(i).getTableCells().size(); j++) {
                                    newRow.getCell(j).setText(jtable.getValueAt(i - 1, j).toString());
                                }
                            }
                        }
                    }
                }
                OutputStream out = new FileOutputStream(fileName);
                hdoc.write(out);
                out.close();
            } catch (IOException | InvalidFormatException ex) {
                Logger.getLogger(MyInterface.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Mở file
            try {
                File myFile = new File(fileName);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
                ex.printStackTrace();
            }
    }
}
