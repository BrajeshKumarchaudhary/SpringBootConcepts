package com.erp.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.erp.store.entity.Unit;
import com.erp.store.service.ErpService;
import com.erp.store.util.ExcelHelper;
import com.erp.store.util.ResponseMessage;

@RestController
@RequestMapping(value = "/erp")
public class ERPController {

	@Autowired
	private ErpService erpService;

	@Autowired
	private ExcelHelper helper;

	@PostMapping(value = "/importSheet")
	public ResponseEntity<ResponseMessage> importSheet(@RequestParam("file") MultipartFile file) {
		String message = "";
		if (ExcelHelper.hasExcelFormat(file)) {
			try {
				erpService.saveSheet(file);

				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}
		message = "Please upload an excel file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));

	}

	@GetMapping(value = "/getUnits")
	public Page<Unit> getUnits(@RequestParam(value = "pageNo") int pageNo,
			@RequestParam(value = "pageSize") int pageSize,
			@RequestParam(value = "unitName", required = false) String unitName,
			@RequestParam(value = "unitType", required = false) String unitType) {
		return erpService.getUnitsData(pageNo, pageSize, unitName, unitType);

	}

}
