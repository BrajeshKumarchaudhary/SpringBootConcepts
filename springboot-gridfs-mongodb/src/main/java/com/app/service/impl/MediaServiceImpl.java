package com.app.service.impl;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.GetMediaResponse;
import com.app.dto.MediaRequest;
import com.app.service.MediaService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@Service
public class MediaServiceImpl implements MediaService {
	@Autowired
	private GridFsTemplate template;

	@Autowired
	private GridFsOperations operations;

	@Override
	public String uploadFile(MultipartFile file) {
		DBObject metadata = new BasicDBObject();
		metadata.put("fileSize", file.getSize());

		Object fileID = null;
		try {
			fileID = template.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), metadata);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return fileID.toString();
	}

	@Override
	public GetMediaResponse downloadFile(String id) {

		GridFSFile gridFSFile = template.findOne(new Query(Criteria.where("_id").is(id)));

		GetMediaResponse mediaResponse = new GetMediaResponse();

		if (gridFSFile != null && gridFSFile.getMetadata() != null) {
			mediaResponse.setFilename(gridFSFile.getFilename());

			mediaResponse.setFileType(gridFSFile.getMetadata().get("_contentType").toString());

			mediaResponse.setFileSize(gridFSFile.getMetadata().get("fileSize").toString());

			try {
				mediaResponse.setFile(IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()));
			} catch (IllegalStateException | IOException e) {

				e.printStackTrace();
			}
		}

		return mediaResponse;
	}

}
