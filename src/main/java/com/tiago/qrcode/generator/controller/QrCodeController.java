package com.tiago.qrcode.generator.controller;

import com.tiago.qrcode.generator.dto.QrCodeGenerateRequest;
import com.tiago.qrcode.generator.dto.QrCodeGenerateResponse;
import com.tiago.qrcode.generator.service.QrCodeGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    private final QrCodeGeneratorService qrCodeGeneratorService;

    /**
     * Construtor que injeta o serviço de geração de QR Code.
     * @param qrCodeGeneratorService O serviço para gerar e fazer upload de QR Codes.
     */
    public QrCodeController(QrCodeGeneratorService qrCodeGeneratorService) {
        this.qrCodeGeneratorService = qrCodeGeneratorService;
    }

    /**
     * Endpoint para gerar um QR Code a partir do texto fornecido.
     * @param request O objeto da requisição contendo o texto a ser codificado.
     * @return Um ResponseEntity contendo a URL do QR Code gerado ou um erro interno.
     */
    @PostMapping
    public ResponseEntity<QrCodeGenerateResponse> generate(@RequestBody QrCodeGenerateRequest request){
        try {
            QrCodeGenerateResponse response = this.qrCodeGeneratorService.generateAndUploadQrCode(request.text());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
