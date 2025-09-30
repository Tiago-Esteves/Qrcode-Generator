package com.tiago.qrcode.generator.controller;

import com.tiago.qrcode.generator.dto.QrCodeGenerateRequest;
import com.tiago.qrcode.generator.dto.QrCodeGenerateResponse;
import com.tiago.qrcode.generator.service.QrCodeGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.core.exception.SdkClientException;

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
        } catch (SdkClientException e) {
            System.err.println("Erro ao carregar credenciais AWS: " + e.getMessage());

            if (e.getMessage().contains("ProfileCredentialsProvider")) {
                System.err.println("Verifique se o perfil AWS especificado existe em ~/.aws/credentials e possui AccessKey/SecretKey.");
            }
            if (e.getMessage().contains("EnvironmentVariableCredentialsProvider")) {
                System.err.println("Verifique se as variáveis de ambiente AWS_ACCESS_KEY_ID e AWS_SECRET_ACCESS_KEY estão setadas corretamente.");
            }

            return ResponseEntity.internalServerError().build();
        } catch (Exception e) {
            // outros erros genéricos
            System.err.println("Erro inesperado: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }

    }
}
