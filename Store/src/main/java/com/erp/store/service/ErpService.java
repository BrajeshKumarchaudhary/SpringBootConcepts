package com.erp.store.service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.erp.store.entity.Unit;
import com.erp.store.repo.UnitConversionMappingRepo;
import com.erp.store.repo.UnitRepo;
import com.erp.store.repo.UnitTypeRepo;
import com.erp.store.util.ExcelHelper;

@Service
public class ErpService {

	@Autowired
	private UnitRepo unitRepo;

	@Autowired
	private UnitConversionMappingRepo unitConversionMappingRepo;

	@Autowired
	private UnitTypeRepo unitTypeRepo;

	/**
	 * Responsible for getUnitsData
	 * @param pageNo
	 * @param pageSize
	 * @param unitName
	 * @param unitType
	 * @return
	 */
	public Page<Unit> getUnitsData(int pageNo, int pageSize, String unitName, String unitType) {
		List<Unit> allUnits = new LinkedList<Unit>();
		Pageable pageable = null;
		if (pageNo < 0 || pageSize < 0) {
			pageable = PageRequest.of(0, 0);
			allUnits = unitRepo.findAll();
		} else {
			pageable = PageRequest.of(pageNo, pageSize);
			Page<Unit> unitWithPage = unitRepo.findAll(pageable);
			allUnits = unitWithPage.getContent();
		}
		if (unitName != null) {
			allUnits.stream().filter(u -> u.equals(unitName)).collect(Collectors.toList());
		}
		return new PageImpl<Unit>(allUnits, pageable, allUnits.size());
	}

	/**
	 * Responsible for save excelSheetData 
	 * @param file
	 */
	public void saveSheet(MultipartFile file) {
		List<Unit> units;
		try {
			//TODO:Here need to handle some CODE
			units = ExcelHelper.excelToUnit(file.getInputStream());
			unitRepo.saveAll(units);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
