package com.example.sincronizacaoreceita.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.example.sincronizacaoreceita.entities.ReceitaSync;

/*
 Classe auxiliar criada por mim - Felipe
*/
public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "Id", "Agencia", "Conta", "Saldo" };
	static String SHEET = "Receita Sincronizada";

	public static boolean hasExcelFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	/* Felipe - Leitura do excel pela api POI - Já utilizei muito está api para mexer com importação de listas que vinham com prefeituras e gravar no banco*/
	public static List<ReceitaSync> excelToTutorials(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();

			List<ReceitaSync> receitas = new ArrayList();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				ReceitaSync receitaSync = new ReceitaSync();

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {
					case 0:
						receitaSync.setId((long) currentCell.getNumericCellValue());
						break;

					case 1:
						receitaSync.setAgencia(currentCell.getStringCellValue());
						break;

					case 2:
						receitaSync.setConta(currentCell.getStringCellValue());
						break;

					case 3:
						receitaSync.setSaldo(currentCell.getStringCellValue());
						break;

					default:
						break;
					}

					cellIdx++;
				}

				receitas.add(receitaSync);
			}

			workbook.close();

			return receitas;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}
}
