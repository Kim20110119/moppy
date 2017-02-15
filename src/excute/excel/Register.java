package excute.excel;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import excute.bean.RegisterBean;


public class Register {

	/** ファイル入出力ストリーム */
	FileInputStream filein;
	/** EXCELワークブック */
	XSSFWorkbook workbook;
	/** EXCELシート */
	XSSFSheet sheet;

	/**
	 * コンストラクタ
	 */
	public Register() {
	}

	/**
	 * =================================================================================================================
	 * EXCELからアカウント情報を抽出してアカウントBeanに設定する
	 * =================================================================================================================
	 *
	 * @return List<AccountBean> アカウントｓ情報リスト
	 *
	 * @author kimC
	 *
	 */
	public List<RegisterBean> execute() {
		List<RegisterBean> list = new ArrayList<RegisterBean>();
		try {
			filein = new FileInputStream("excel/登録用アカウント.xlsx");
			workbook = new XSSFWorkbook(filein);
			// 「データ」シート
			sheet = workbook.getSheet("データ");
			Iterator<Row> rows = sheet.rowIterator();
			int index = 0;
			while(rows.hasNext()) {
				Row row = rows.next();
				if(index > 0){
					RegisterBean bean = new RegisterBean();
					// メールアドレス
					Cell cell_0 = row.getCell(0);
					bean.setMail(this.getCellValue(cell_0));
					// ニックネーム
					Cell cell_1 = row.getCell(1);
					bean.setNick(this.getCellValue(cell_1));
					// パスワード
					Cell cell_2 = row.getCell(2);
					bean.setPassword(this.getCellValue(cell_2));
					// 性別
					Cell cell_3 = row.getCell(3);
					bean.setSex(this.getCellValue(cell_3));
					// 年
					Cell cell_4 = row.getCell(4);
					bean.setYear(this.getCellValue(cell_4));
					// 月
					Cell cell_5 = row.getCell(5);
					bean.setMonth(this.getCellValue(cell_5));
					// 都道府県
					Cell cell_6 = row.getCell(6);
					bean.setPref(this.getCellValue(cell_6));
					// 未既婚
					Cell cell_7 = row.getCell(7);
					bean.setMarried(this.getCellValue(cell_7));
					// 秘密の質問
					Cell cell_8 = row.getCell(8);
					bean.setCodeid(this.getCellValue(cell_8));
					// 秘密の回答
					Cell cell_9 = row.getCell(9);
					bean.setCodeword(this.getCellValue(cell_9));
					list.add(bean);
				}
				index++;
			}
			workbook.close();
			filein.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("処理が失敗しました");
		}
		return list;
	}

	/**
	 * =================================================================================================================
	 * EXCELからアカウント情報を抽出してアカウントBeanに設定する
	 * =================================================================================================================
	 *
	 * @return List<AccountBean> アカウント情報リスト
	 *
	 * @author kimC
	 *
	 */
	public String getCellValue(Cell cell) {
		if(cell != null){
			if(cell.getCellTypeEnum() == CellType.NUMERIC){
				int int_value = (int)cell.getNumericCellValue();
				return String.valueOf(int_value);
			}else{
				return cell.getStringCellValue();
			}
		}else{
			return StringUtils.EMPTY;
		}

	}

}
