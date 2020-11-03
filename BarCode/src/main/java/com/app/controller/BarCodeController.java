package com.app.controller;

import java.awt.image.BufferedImage;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.genrator.ZxingBarcodeGenerator;

@RestController
@RequestMapping("/barcodes")
public class BarCodeController {
	@GetMapping(value = "/zxing/upca/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingUPCABarcode(@PathVariable("barcode") String barcode) throws Exception {
        return okResponse(ZxingBarcodeGenerator.generateUPCABarcodeImage(barcode));
    }

    @GetMapping(value = "/zxing/ean13/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingEAN13Barcode(@PathVariable("barcode") String barcode) throws Exception {
        return okResponse(ZxingBarcodeGenerator.generateEAN13BarcodeImage(barcode));
    }

    @PostMapping(value = "/zxing/code128", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingCode128Barcode(@RequestBody String barcode) throws Exception {
        return okResponse(ZxingBarcodeGenerator.generateCode128BarcodeImage(barcode));
    }

    @PostMapping(value = "/zxing/pdf417", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingPDF417Barcode(@RequestBody String barcode) throws Exception {
        return okResponse(ZxingBarcodeGenerator.generatePDF417BarcodeImage(barcode));
    }

    @PostMapping(value = "/zxing/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingQRCode(@RequestBody String barcode) throws Exception {
        System.out.println("barcode========="+barcode);
    	return okResponse(ZxingBarcodeGenerator.generateQRCodeImage(barcode));
    }
    private ResponseEntity<BufferedImage> okResponse(BufferedImage image) {
        return new ResponseEntity<>(image, HttpStatus.OK);
    }
}
