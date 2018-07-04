package nc.ui.pub.imp.util;

import java.awt.Container;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIFileChooser;
import nc.ui.trade.excelimport.BillItemImportSetDlg;
import nc.ui.trade.excelimport.ImportTypeDismatchException;
import nc.ui.trade.excelimport.io.DataFileManagerFactory;
import nc.ui.trade.excelimport.io.XLSFileUtil;
import nc.ui.trade.excelimport.parser.CSVLineParser2;
import nc.ui.trade.excelimport.parser.CSVUtil;
import nc.ui.trade.excelimport.parser.IFileParserConstants;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * excel������
 * 
 * @author ����ΰ modified by hulianga ��Ӷ�.xlsx��ʽ��֧��
 * 
 *         �������ڣ�2008-11-19
 */
public class MyExcelImporter {

	private BillItemImportSetDlg dlg;
	private File exportFile;

	public static final String KEY_DEFAULT_DIR = "default";

	private static final String CSV_SUFFIX = IFileParserConstants.CSV_SUFFIX;
	private static final String XLS_SUFFIX = IFileParserConstants.XLS_SUFFIX;
	private static final String XLSX_SUFFIX = IFileParserConstants.XLSX_SUFFIX;

	private static final CSVLineParser2 LINE_FORMAT_STRATEGE = new CSVLineParser2();

	// Ϊ�����ɺ͵����ļ�ͬһĿ¼�µĵ�����־�ļ�����
	private String importFileFullName = ".";

	private UIFileChooser chooser;
	// Ϊ���Զ���ס�ϴ��û�ѡ����ļ�Ŀ¼(ʹ��ƫ��)
	private Preferences preferences;

	private String funcode = null;

	private String pk_import = null;

	/**
	 * ��excel�ļ��е������ݣ��γ�EAVO�б�
	 */
	public List<Map<String, String>> importFromExcel(Container guiParent)
			throws FileNotFoundException, IOException,
			ImportTypeDismatchException, BusinessException {
		File selectedFile = getOpenExcelFile(guiParent);
		if (selectedFile == null) {
			return null;
		}
		List<Map<String, String>> infos = doImport(selectedFile);
		if (CollectionUtils.isEmpty(infos)) {
			MessageDialog.showWarningDlg(
					guiParent,
					NCLangRes.getInstance().getStrByID("excelimport",
							"ExcelImporter-000006")/* "��ʾ" */,
					NCLangRes.getInstance().getStrByID("excelimport",
							"ExcelImporter-000015")/* "���ļ�û�����ݣ����������������ٵ���" */);
			return null;
		}
		return infos;
	}

	private File getOpenExcelFile(Container parent) {
		while (getFileChooser().showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = getFileChooser().getSelectedFile();
			if (selectedFile != null) {
				if (selectedFile.exists()) {
					if (!isSuffixRight(selectedFile)) {
						MessageDialog
								.showErrorDlg(
										parent,
										NCLangRes.getInstance().getStrByID(
												"excelimport",
												"ExcelImporter-000008")/* "����" */,
										NCLangRes.getInstance().getStrByID(
												"excelimport",
												"ExcelImporter-000014")/* "Ŀǰ����ֻ֧��CSV��XLS��XLSX��ʽ�������ļ�" */);
						continue;
					}
					getPreferences().put(KEY_DEFAULT_DIR,
							selectedFile.getParent());
					// ��ȡ�����ļ�ȫ����Ϣ���Ա��ں�������
					String path = selectedFile.getPath().toLowerCase();
					String suffix = null;
					if (path.indexOf(CSV_SUFFIX) != -1) {
						suffix = CSV_SUFFIX;
					} else if (path.indexOf(XLSX_SUFFIX) != -1) {
						suffix = XLSX_SUFFIX;
					} else {
						suffix = XLS_SUFFIX;
					}
					importFileFullName = path
							.substring(0, path.indexOf(suffix));
					return selectedFile;
				}
			}
		}
		return null;
	}

	/**
	 * ��ָ���ļ����ж�ȡ�ͽ��������ؽ������EAVO�б�
	 */
	public List<Map<String, String>> doImport(File file)
			throws FileNotFoundException, IOException,
			ImportTypeDismatchException, BusinessException {
//		List<String> lines = DataFileManagerFactory.create(file).readData(file);
		List<List<String>> lines = readFile(file);
		/*
		int lastLine = 0;
		// �п������һ�к��м��б���ȡ����Ϊ��Ч���У�����ȥ��
		for (String string : lines) {
			String[] values = string.split(",");
			if (values.length == 0) {
				break;
			}
			lastLine += 1;
		}
		*/

		List<Map<String, String>> resultListMap = new ArrayList<Map<String, String>>();
		List<String> colnames = lines.get(0);
		for (int i = 1; i < lines.size(); i++) {
			List<String> line = lines.get(i);
			Map<String, String> valueMap = new HashMap<String, String>();
			for (int j = 0; j < line.size(); j++) {
				valueMap.put(colnames.get(j), line.get(j));
			}
			resultListMap.add(valueMap);
		}

		return resultListMap;
	}

	private static boolean isCSVFile(File file) {
		String name = file.getName().toLowerCase();
		return name.endsWith(CSV_SUFFIX);
	}

	private static boolean isXLSFile(File file) {
		String name = file.getName().toLowerCase();
		return name.endsWith(XLS_SUFFIX);
	}

	private static boolean isXLSXFile(File file) {
		String name = file.getName().toLowerCase();
		return name.endsWith(XLSX_SUFFIX);
	}

	protected static boolean isSuffixXLSRight(File file) {
		return isXLSFile(file);
	}

	protected static boolean isSuffixCSVRight(File file) {
		return isCSVFile(file);
	}

	protected static boolean isSuffixXLSXRight(File file) {
		return isXLSXFile(file);
	}

	private static boolean isSuffixRight(File file) {
		return isXLSXFile(file) || isCSVFile(file) || isXLSFile(file);
	}

	public BillItemImportSetDlg getDlg() {
		return dlg;
	}

	public void setDlg(BillItemImportSetDlg dlg) {
		this.dlg = dlg;
	}

	protected UIFileChooser getFileChooser() {
		if (chooser == null) {
			chooser = new ExcelUIFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.addChoosableFileFilter(new FileFilter() {

				public boolean accept(File f) {
					return f.isDirectory() || isSuffixCSVRight(f);
				}

				@Override
				public String getDescription() {
					return NCLangRes.getInstance().getStrByID("excelimport",
							"ExcelImporter-000011")/* "CSV�ļ�" */;
				}
			});

			chooser.addChoosableFileFilter(new FileFilter() {

				public boolean accept(File f) {
					return f.isDirectory() || isSuffixXLSRight(f);
				}

				@Override
				public String getDescription() {
					return NCLangRes.getInstance().getStrByID("excelimport",
							"ExcelImporter-000012")/* "Excel97-2003������(*.xls)" */;
				}
			});

			chooser.addChoosableFileFilter(new FileFilter() {

				public boolean accept(File f) {
					return f.isDirectory() || isSuffixXLSXRight(f);
				}

				@Override
				public String getDescription() {
					return NCLangRes.getInstance().getStrByID("excelimport",
							"ExcelImporter-000013")/* "Excel������(*.xlsx)" */;
				}
			});
		}
		// �û�ѡ���ļ�Ŀ¼ƫ��
		String preferencesDir = getPreferences().get(KEY_DEFAULT_DIR,
				System.getProperty("user.dir"));
		chooser.setCurrentDirectory(new File(preferencesDir));
		return chooser;
	}

	protected Preferences getPreferences() {
		if (preferences == null) {
			preferences = Preferences.userNodeForPackage(this.getClass());
		}
		return preferences;
	}

	// /**
	// * ���ص����ļ���(ȫ·��������������׺)
	// */
	// public String getImportFileName() {
	// return importFileFullName;
	// }

	/**
	 * ���ص�����־�ļ���(ȫ·����)
	 * 
	 * ��ʽΪ�������ļ���_log_����ʱ��
	 */
	public String getLogFileName() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		String name = sdf.format(new Date()).toString();
		return importFileFullName + "_log_" + name;
	}

	public void setFuncode(String funcode) {

		this.funcode = funcode;
	}

	public void setPk_import(String pk_import) {

		this.pk_import = pk_import;
	}

	public File getExportFile() {
		return exportFile;
	}

	public void setExportFile(File exportFile) {
		this.exportFile = exportFile;
	}

	public UIFileChooser getChooser() {
		return chooser;
	}

	public void setChooser(UIFileChooser chooser) {
		this.chooser = chooser;
	}

	public List<List<String>> readFile(File file) throws IOException, BusinessException {
		if (file == null)
			return null;
		String filename = file.getName().toLowerCase();
		if (filename.endsWith(IFileParserConstants.CSV_SUFFIX)) {
			throw new BusinessException("CSV��ʽ�ݲ�֧�֣�");
		} else if (filename.endsWith(IFileParserConstants.XLS_SUFFIX)) {
			return readData2(file);
		} else if (filename.endsWith(IFileParserConstants.XLSX_SUFFIX)) {
			throw new BusinessException("XLSX��ʽ�ݲ�֧�֣�");
		}
		return null;
	}

	/*
	public List<List<String>> readData1(File dataFile) throws IOException {
		List<String> lines = new ArrayList<String>();
		BufferedReader r = null;
		try {
			r = new BufferedReader(new FileReader(dataFile));
			String s = null;
			while ((s = r.readLine()) != null) {
				if (HSSFWorkbookUtil.isStartWithNoticeLineFlag(s)) {
					continue;
				}
				lines.add(s);
			}
			return lines;
		} finally {
			if (r != null) {
				r.close();
			}
		}
	}
	*/
	
	private List<String> getParsedLine(String[] values){
		List<String> resultList = new ArrayList<String>();
		for (int i = 0; i < values.length; i++) {
			resultList.add(CSVUtil.csvEncode(values[i]));
		}
		return resultList;
	}
	
	/**
	 * ȡ��������Ϣ(����)
	 * 
	 * @param sheet
	 *            sheet
	 * @param rowNum
	 *            ������
	 * @param colNum
	 *            ��(����)����
	 * @return String[]
	 * @throws BusinessException 
	 */
	private String[] getSingleLineData(HSSFSheet sheet, int rowNum, int colFrom, int colTo) throws BusinessException {
		String[] dataLine = new String[colTo - colFrom + 1];
		if (rowNum < 0 || colFrom < 0 || colTo < 0) {
			return dataLine;
		}
		HSSFRichTextString richString = null;
		for (int col = 0; col < colTo - colFrom + 1; col++) {
			try{
				richString = getCell(sheet, rowNum, colFrom + col).getRichStringCellValue();
			}catch(Exception e){
				throw new BusinessException(NCLangRes.getInstance().getStrByID("excelimport", "ExcelImporter-000025")/*��ȷ�����ݸ�ʽ�����ı���ʽ*/);
			}
			if (richString == null) {
				dataLine[col] = StringUtils.EMPTY;
			} else {
				dataLine[col] = richString.toString().trim();
			}
		}
		return dataLine;
	}
	
	/**
	 * ȡ��Cell
	 * 
	 * @param sheet
	 *            sheet
	 * @param rowNum
	 *            ������
	 * @param colNum
	 *            ������
	 * @return HSSFCell
	 */
	private HSSFCell getCell(HSSFSheet sheet, int rowNum, int colNum) {
		if (rowNum < 0 || colNum < 0) {
			return null;
		}
		HSSFRow row = getRow(sheet, rowNum);
		HSSFCell cell = row.getCell(colNum);
		if (cell == null) {
			cell = row.createCell(colNum);
		}
		return cell;
	}
	
	/**
	 * ȡ��Row
	 * 
	 * @param sheet
	 *            sheet
	 * @param rowNum
	 *            ������
	 * @return HSSFRow
	 */
	private HSSFRow getRow(HSSFSheet sheet, int rowNum) {
		if (rowNum < 0) {
			return null;
		}
		HSSFRow row = sheet.getRow(rowNum);
		if (row == null) {
			row = sheet.createRow(rowNum);
		}
		return row;
	}
	
	public List<List<String>> readData2(File dataFile) throws IOException,
			BusinessException {
		List<List<String>> lines = new ArrayList<List<String>>();
		HSSFWorkbook workbook = (HSSFWorkbook) XLSFileUtil.read(dataFile);
		//lines = HSSFWorkbookUtil.read(workbook);
		
		if (workbook == null) return lines;
		
		HSSFSheet sheet = workbook.getSheet("sheet1");

		// Sheet1�����ڵ����
		if (sheet == null) {
			throw new BusinessException(NCLangRes.getInstance().getStrByID("excelimport", "ExcelImporter-000018")/*��Sheet1�������ڵ����!*/);
		}
		
		//int firstDataRowNum = getDataStartIndex(sheet);
		int firstDataRowNum = 0;
		
		int num = sheet.getLastRowNum();// ĩ���к�
		HSSFRow row = sheet.getRow(firstDataRowNum);// �����к�
		for (int i = firstDataRowNum; i < num + 1; i++) {
			if (row == null) {
				continue;
			}
			boolean isNullRow = sheet.getRow(i) == null;// �Ƿ��ǿ���
			String[] values = getSingleLineData(sheet, i, 0, row.getLastCellNum() - 1);
			List<String> line = getParsedLine(values);
			lines.add(line);
			boolean isBlankRow = isBlankRow(isNullRow, values);
			if (isBlankRow) {// ������ζ����һ�м����ӱ�������
				row = sheet.getRow(i + 1);
			}
		}
		return lines;
	}

	private static boolean isBlankRow(boolean isNullRow, String[] values) {
		if (isNullRow) {
			return true;
		}
		if (ArrayUtils.isEmpty(values)) {
			return true;
		}
		boolean result = true;
		for (String value : values) {
			if (!StringUtil.isEmpty(value)) {
				result = false;
				break;
			}
		}
		return result;
	}

	
	/**
	 * 
	 * ����jdk1.7�ı仯�����µ�����ʽ��ѡ����CSV����д��������������
	 * 
	 * @author hulianga
	 */
	private class ExcelUIFileChooser extends UIFileChooser {

		private static final long serialVersionUID = 5573628911848324562L;

		@Override
		public void addChoosableFileFilter(FileFilter filter) {

			super.addChoosableFileFilter(filter);
			setFileFilter(filter);
		}
	}
}