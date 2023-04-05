package com.app.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.GetMediaResponse;
import com.app.dto.MediaRequest;

@Service
public interface MediaService {
	String uploadFile(MultipartFile file);

	GetMediaResponse downloadFile(String id);

}
