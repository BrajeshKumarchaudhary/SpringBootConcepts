package com.app.serviceImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.app.service.QRCodeService;
import net.glxn.qrgen.javase.QRCode;
@Service
public class QRCodeServiceImpl implements QRCodeService {

	  @Override
	  public byte[] generate(String text, int width, int height) {

	    try (ByteArrayOutputStream bos = QRCode.from(text).withSize(width, height).stream(); ) {

	      return bos.toByteArray();

	    } catch (IOException e) {
	      e.printStackTrace();
	      return null;
	    }
	  }
}
