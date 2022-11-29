package com.app.s3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.s3.Service.S3Service;

/**
 * Basic S3 Object CRUD Operation
 * 
 * @author brajesh
 *
 */

@RestController
@RequestMapping(value = "/storage")
public class S3StorageController {

	@Autowired
	S3Service s3Service;

	/**
	 * upload an Object into AWS S3 Bucket
	 * 
	 * @param file
	 * @return
	 */
	@PostMapping("/uploadFile")
	public String uploadFile(@RequestParam(value = "file") MultipartFile file) {
		return this.s3Service.uploadFile(file);
	}

	/**
	 * Delete File from AWS S3 Bucket
	 * 
	 * @param fileName
	 * @return
	 */

	@DeleteMapping("/deleteFile")
	public String deleteFile(@RequestParam(value = "fileName") String fileName) {
		return this.s3Service.deleteFileFromS3Bucket(fileName);
	}

	/**
	 * Download Object from AWS S3 Bucket
	 * 
	 * @param fileName
	 * @return
	 */

	@GetMapping("/download/{fileName}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
		byte[] data = s3Service.downloadFile(fileName);
		ByteArrayResource resource = new ByteArrayResource(data);
		return ResponseEntity.ok().contentLength(data.length).header("Content-type", "application/octet-stream")
				.header("Content-disposition", "attachment; filename=\"" + fileName + "\"").body(resource);
	}

}
