package com.erp.store.util;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.erp.store.entity.Unit;
import com.erp.store.entity.UnitType;
import com.erp.store.repo.UnitTypeRepo;

@Service
public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "UnitName", "Status", "Description", "Type", "ConversionUid", "ConversionValue" };
	static String SHEET = "unit1";
	@Autowired
	private static UnitTypeRepo unitTypeRepo;

	public static boolean hasExcelFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static List<Unit> excelToUnit(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();

			List<Unit> units = new ArrayList<>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				Unit unit = new Unit();

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {
					case 0:
						unit.setName(currentCell.getStringCellValue());
						break;

					case 1:
						unit.setStatus(currentCell.getBooleanCellValue());
						break;

					case 2:
						unit.setDescription(currentCell.getStringCellValue());
						break;
					case 3:
						UnitType unitType = new UnitType();
						unitType.setName(currentCell.getStringCellValue());
						unit.setUnitType(unitType);
						break;
					case 4:
						unit.setDescription(currentCell.getStringCellValue());
						break;

					default:
						break;
					}

					cellIdx++;
				}

				units.add(unit);
			}

			workbook.close();
			return units;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}
}
