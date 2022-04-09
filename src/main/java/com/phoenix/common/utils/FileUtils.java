package com.phoenix.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileUtils {

	/**
	 * 根据地址获得数据的字节流
	 * 
	 * @param strUrl
	 *            网络连接地址
	 * @return
	 */
	public static byte[] getFileFromNetByUrl(String strUrl) {
		try {
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
			byte[] btImg = readInputStream(inStream);// 得到图片的二进制数据
			return btImg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 从输入流中获取数据
	 * 
	 * @param inStream
	 *            输入流
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		try {
			while ((len = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			return outStream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				outStream.close();
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// @描述：是否是2003的excel，返回true是2003
	public static boolean isExcel2003(String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$");
	}

	// @描述：是否是2007的excel，返回true是2007
	public static boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}

	/**
	 * 验证EXCEL文件
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean validateExcel(String filePath) {
		if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
			return false;
		}
		return true;
	}
	
	public static void downloadTemplate(OutputStream out,String downloadUrl) throws Exception {
		byte[] btFile = getFileFromNetByUrl(downloadUrl);
		if (null != btFile && btFile.length > 0) {
			out.write(btFile);
			out.flush();
			out.close();
		}
	}
	
	public static void batchExport(String fileType, String[] title, String[] titleCode, List<Map<String, String>> dataset,
			OutputStream os) throws Exception {
		// 创建工作文档对象
		Workbook wb = null;
		if (fileType.equals("xls")) {
			wb = new HSSFWorkbook();
		} else if (fileType.equals("xlsx")) {
			wb = new XSSFWorkbook();
		}
		// 单元格样式
		CellStyle cellStyle = wb.createCellStyle();
		Font font = wb.createFont();
		// 字体加粗
		font.setBold(true);
		cellStyle.setFont(font);
		// 居中
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		// 创建sheet对象
		Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
		// 标题行
		Row titleRow = (Row) sheet1.createRow(0);

		int titleIndex = 0;
		for (String t : title) {
			Cell cell = titleRow.createCell(titleIndex++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(t);
		}

		// 循环写入行数据
		font.setBold(false);
		cellStyle.setFont(font);
		cellStyle.setAlignment(HorizontalAlignment.LEFT);

		Iterator<Map<String, String>> iterator = dataset.iterator();
		int index = 0;
		while (iterator.hasNext()) {
			Row row = (Row) sheet1.createRow(++index);
			Map<String, String> dataMap = iterator.next();
			Set<Entry<String, String>> dataMapEntries = dataMap.entrySet();
			int cellIndex = 0;
			for (String code : titleCode) {
				for (Entry<String, String> entry : dataMapEntries) {
					if (entry.getKey().toUpperCase().equals(code.toUpperCase())) {
						Cell cell = row.createCell(cellIndex++);
						cell.setCellStyle(cellStyle);
						cell.setCellValue(entry.getValue() == null ? "" : entry.getValue());
					} else {
						continue;
					}
				}
			}
		}
		try {
			wb.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		}
		os.flush();
		os.close();
	}
}
