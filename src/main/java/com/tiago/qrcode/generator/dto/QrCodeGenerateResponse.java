package com.tiago.qrcode.generator.dto;

/**
 * Representa a resposta contendo a URL do QR Code gerado.
 * @param url A URL pública do arquivo de imagem do QR Code.
 */
public record QrCodeGenerateResponse(String url) {
}
