package com.tiago.qrcode.generator.dto;

/**
 * Representa a requisição para gerar um QR Code.
 * @param text O texto que será codificado no QR Code.
 */
public record QrCodeGenerateRequest(String text) {
}
