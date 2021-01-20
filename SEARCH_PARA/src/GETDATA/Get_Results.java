package GETDATA;

import java.io.*;
import java.text.*;
import java.util.Date;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import common.DB_Connection;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

@WebServlet("/Get_Results")
public class Get_Results extends HttpServlet {
    private static final long serialVersionUID = 1L;
	
//	public static void main(String[] args) {
//		Get_Results obj = new Get_Results();
//		obj.getsearchdata("rock", "songcat");
//	}
	
	private String getFileName(String baseName) {
		// the name of the Excel file is generated based on table name followed by the current date and time
        
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String dateTimeInfo = dateFormat.format(new Date());
        return baseName.concat(String.format("_%s.xlsx", dateTimeInfo));
    }
		
	public void doGet (HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException{
		String excelFilePath = getFileName("tblsongs_Export");
		rsp.setContentType("application/vnd.ms-excel");
		rsp.setHeader("Content-Disposition", "attachment;filename="+excelFilePath);
		
		String s_query = req.getParameter("s_query");
		String categ = req.getParameter("categ");
		
		DB_Connection db_obj_con = new DB_Connection();
		Connection con = db_obj_con.get_connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		try {
			String query = "SELECT * FROM dbmis2.tblsongs WHERE " + categ + " = '" + s_query + "' OR " + categ + " like '%" + s_query + "%'";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("tblsongs");
 
            writeHeaders(rs, sheet);
            writeData(rs, workbook, sheet);
 
//          FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(rsp.getOutputStream());
            workbook.close();
		}
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	private void writeHeaders(ResultSet result, XSSFSheet sheet) throws SQLException {
        // write into Excel sheet, the header line containing column names
        
		ResultSetMetaData metaData = result.getMetaData();
        int numberOfColumns = metaData.getColumnCount();
 
        Row headerRow = sheet.createRow(0);
 
        for (int i = 1; i <= numberOfColumns; i++) {
            String columnName = metaData.getColumnName(i);
            Cell headerCell = headerRow.createCell(i - 1);
            headerCell.setCellValue(columnName);
        }
    }
	
	private void writeData(ResultSet result, XSSFWorkbook workbook, XSSFSheet sheet) throws SQLException {
        // write into Excel sheet, the data from the search results
		
		ResultSetMetaData metaData = result.getMetaData();
        int numberOfColumns = metaData.getColumnCount();
        int rowCount = 1;
        while (result.next()) {
            Row row = sheet.createRow(rowCount++);
 
            for (int i = 1; i <= numberOfColumns; i++) {
                Object valueObject = result.getObject(i);
                Cell cell = row.createCell(i - 1);
 
                if (valueObject instanceof Boolean)
                    cell.setCellValue((Boolean) valueObject);
                else if (valueObject instanceof Double)
                    cell.setCellValue((double) valueObject);
                else if (valueObject instanceof Float)
                    cell.setCellValue((float) valueObject);
                else if (valueObject instanceof Date) {
                    cell.setCellValue((Date) valueObject);
                    formatCellData(workbook, cell);
                }
                else {
                	try{
                		cell.setCellValue((String) valueObject);
                	}
                	catch (Exception e) {
                		cell.setCellValue(String.valueOf(valueObject));
                	}
                }
            }
        }
    }
	
	private void formatCellData(XSSFWorkbook workbook, Cell cell) {
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
        cell.setCellStyle(cellStyle);
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      doGet(request, response);
	 }
}
