package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.GetMediaResponse;
import com.app.dto.MediaRequest;
import com.app.service.MediaService;

@RestController
@RequestMapping("/file")
public class MediaController {
	@Autowired
	private MediaService fileService;

	/**
	 * Upload File and return unique Id.
	 * 
	 * @param file {@link MultipartFile}}
	 * @return {@link ResponseEntity}}
	 */

	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
		return new ResponseEntity<>(fileService.uploadFile(file), HttpStatus.OK);
	}

	/**
	 * DownLoad File by Saved UniqueId
	 * 
	 * @param id {@link String}}
	 * @return {@link ResponseEntity}}
	 */

	@GetMapping("/download/{id}")
	public ResponseEntity<ByteArrayResource> download(@PathVariable String id) {
		GetMediaResponse loadFile = fileService.downloadFile(id);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(loadFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadFile.getFilename() + "\"")
				.body(new ByteArrayResource(loadFile.getFile()));
	}
}
