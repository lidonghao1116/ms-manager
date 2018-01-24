package com.jiacer.modules.common.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.LocalizedResourceHelper;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * 对类文件的描叙
 *
 * @author hezp
 * @version 1.0 2014年2月25日
 * @since 1.0
 */
public class ExcelOutput {

	private ApplicationContext applicationContext;

	private static final String EXTENSION = ".xls";

	// 设置cell编码解决中文高位字节截断
	// private static short XLS_ENCODING = HSSFCell.ENCODING_UTF_16;

	// 定制浮点数格式
	private static String NUMBER_FORMAT = "#,##0.00";

	// 定制日期格式
	private static String DATE_FORMAT = "yyyy-mm-dd hh:mm:ss"; // "m/d/yy h:mm"
	private OutputStream out = null;
	private HSSFWorkbook workbook = null;
	private HSSFSheet sheet = null;
	private HSSFRow row = null;
	/**
	 * 样式列表
	 */
	private Map<String, CellStyle> styles;

	public ExcelOutput() {
	}

	public void setApplicationContext(ApplicationContext context) {
		this.applicationContext = context;
	}

	/**
	 * 取得报表模版
	 * 
	 * @param url
	 *            String<br>
	 *            Workbook book = null; try { book = new
	 *            XSSFWorkbook(excelFile); } catch (Exception ex) { book = new
	 *            HSSFWorkbook(new FileInputStream(excelFile)); }
	 * @param request
	 *            HttpServletRequest<br>
	 * @return HSSFWorkbook
	 */
	protected HSSFWorkbook getTemplateSource(String url, HttpServletRequest request) throws Exception {
		LocalizedResourceHelper helper = new LocalizedResourceHelper(this.applicationContext);
		Locale userLocale = RequestContextUtils.getLocale(request);
		Resource inputFile = helper.findLocalizedResource(url, EXTENSION, userLocale);
		POIFSFileSystem fs = new POIFSFileSystem(inputFile.getInputStream());
		return new HSSFWorkbook(fs);
	}

	/**
	 * 初始化Excel
	 *
	 */
	public ExcelOutput(OutputStream out) {
		this.out = out;
		this.workbook = new HSSFWorkbook();
		this.sheet = workbook.createSheet();
		this.styles = createStyles(this.workbook);
	}

	/**
	 * 初始化样式
	 * 
	 * @param wb
	 *            工作薄对象
	 * @return 样式列表
	 */
	private Map<String, CellStyle> createStyles(HSSFWorkbook wb) {
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

		HSSFDataFormat format = wb.createDataFormat();
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setDataFormat(format.getFormat(DATE_FORMAT)); // 设置cell样式为定制的日期格式
		styles.put("dateStyle", cellStyle);

		cellStyle = wb.createCellStyle();
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(DATE_FORMAT)); // 设置cell样式为定制的日期格式
		styles.put("dateStyle2", cellStyle);

		cellStyle = wb.createCellStyle();
		cellStyle.setDataFormat(format.getFormat(NUMBER_FORMAT)); // 设置cell样式为定制的浮点数格式
		styles.put("doubleStyle", cellStyle);

		return styles;
	}

	/**
	 * 初始化Excel 带模板的excel
	 *
	 */
	public ExcelOutput(OutputStream out, String path, HttpServletRequest request) {
		try {
			setApplicationContext(
					WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext()));
			this.out = out;
			this.workbook = getTemplateSource(path, request);
			this.sheet = workbook.getSheet("Sheet1");
			this.styles = createStyles(this.workbook);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出Excel文件
	 *
	 * @throws java.io.IOException
	 */
	public void export() throws FileNotFoundException, IOException {
		try {
			workbook.write(out);
		} catch (FileNotFoundException e) {
			throw new IOException(" 生成导出Excel文件出错! ", e);
		} catch (IOException e) {
			throw new IOException(" 写入Excel文件出错! ", e);
		}finally {
			out.flush();
			out.close();
		}

	}

	/**
	 * 增加一行
	 *
	 * @param index
	 *            行号
	 */
	public void createRow(int index) {
		this.row = this.sheet.createRow(index);
	}

	/**
	 * 获取excel模板的行
	 * 
	 * @param index
	 */
	public void getRow(int index) {
		this.row = this.sheet.getRow(index);
	}

	/**
	 * 获取单元格的值
	 *
	 * @param index
	 *            列号
	 */
	public String getCell(int index) {
		// @SuppressWarnings("deprecation")
		HSSFCell cell = this.row.getCell(index);
		String strExcelCell = "";
		if (cell != null) { // add this condition
			// judge
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_FORMULA:
				strExcelCell = "FORMULA ";
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				strExcelCell = String.valueOf(cell.getNumericCellValue());
				break;
			case HSSFCell.CELL_TYPE_STRING:
				strExcelCell = cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				strExcelCell = "";
				break;
			default:
				strExcelCell = "";
				break;
			}
		}
		return strExcelCell;
	}

	/**
	 * 设置单元格
	 *
	 * @param index
	 *            列号
	 * @param value
	 *            单元格填充值
	 */
	public void setCell(int index, int value) {
		// @SuppressWarnings("deprecation")
		HSSFCell cell = this.row.createCell(index);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
	}

	/**
	 * 设置单元格
	 *
	 * @param index
	 *            列号
	 * @param value
	 *            单元格填充值
	 */
	// @SuppressWarnings("deprecation")
	public void setCell(int index, double value) {
		HSSFCell cell = this.row.createCell(index);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
		cell.setCellStyle(styles.get("doubleStyle")); // 设置该cell浮点数的显示格式
	}

	public void setCellNoFormat(int index, double value) {
		HSSFCell cell = this.row.createCell(index);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
	}

	public void getCell(int index, double value) {
		HSSFCell cell = this.row.getCell(index);
		cell.setCellValue(value);
	}

	/**
	 * 设置单元格
	 *
	 * @param index
	 *            列号
	 * @param value
	 *            单元格填充值
	 */
	public void setCell(int index, String value) {
		HSSFCell cell = this.row.createCell(index);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(value);
	}

	public void getCell(int index, String value) {
		HSSFCell cell = this.row.getCell(index);
		cell.setCellValue(value);
	}

	/**
	 * 设置单元格
	 *
	 * @param index
	 *            列号
	 * @param value
	 *            单元格填充值
	 */
	public void setCell(int index, Calendar value) {
		// @SuppressWarnings("deprecation")
		HSSFCell cell = this.row.createCell(index);
		// cell.setEncoding(XLS_ENCODING);
		cell.setCellValue(value.getTime());
		cell.setCellStyle(styles.get("dateStyle2")); // 设置该cell日期的显示格式
	}

	/**
	 * 设置单元格
	 *
	 * @param index
	 *            列号
	 * @param value
	 *            单元格填充值
	 */
	public void setDateCell(int index, Calendar value) {
		HSSFCell cell = this.row.createCell(index);
		cell.setCellValue(value.getTime());
		cell.setCellStyle(styles.get("dateStyle")); // 设置该cell日期的显示格式
	}

	/**
	 * 初始化Excel 带模板的excel
	 *
	 */
	public HSSFSheet createHssfSheet(String sheetName) {
		try {
			this.sheet = workbook.createSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sheet;
	}

	/**
	 * 创建表头
	 */
	public void createHead(String[] head) {
		if (head == null || head.length < 1) {
			return;
		}
		int len = head.length;
		for (int i = 0; i < len; i++) {
			HSSFCell cell = this.row.createCell(i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(head[i]);
		}
	}
}
