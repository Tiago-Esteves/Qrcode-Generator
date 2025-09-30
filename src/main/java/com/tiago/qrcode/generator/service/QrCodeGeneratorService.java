package com.tiago.qrcode.generator.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.tiago.qrcode.generator.dto.QrCodeGenerateResponse;
import com.tiago.qrcode.generator.ports.StoragePort;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class QrCodeGeneratorService {

    private final StoragePort storagePort;

    /**
     * Construtor que injeta a implementação da porta de armazenamento.
     * @param storagePort A implementação do serviço de armazenamento.
     */
    public QrCodeGeneratorService(StoragePort storagePort) {
        this.storagePort = storagePort;
    }

    /**
     * Gera uma imagem de QR Code a partir de um texto e faz o upload para o serviço de armazenamento.
     * @param text O texto a ser codificado no QR Code.
     * @return Um objeto de resposta com a URL do QR Code.
     * @throws WriterException Se ocorrer um erro durante a geração do QR Code.
     * @throws IOException Se ocorrer um erro de I/O durante a escrita da imagem.
     */
    public QrCodeGenerateResponse generateAndUploadQrCode(String text) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 250, 250);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix,"PNG", pngOutputStream);
        byte[] pngQrCodeData = pngOutputStream.toByteArray();

        String url = storage.uploadFile(pngQrCodeData, UUID.randomUUID().toString(), "image/png");

        return new QrCodeGenerateResponse(url);
    }
}
