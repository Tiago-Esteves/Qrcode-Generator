package com.tiago.qrcode.generator.ports;

/**
 * Interface (Port) que define o contrato para operações de armazenamento de arquivos.
 */
public interface StoragePort {
    /**
     * Realiza o upload de um arquivo para o serviço de armazenamento.
     * @param fileData Os dados do arquivo em bytes.
     * @param fileName O nome do arquivo.
     * @param contentType O tipo de conteúdo (MIME type) do arquivo.
     * @return A URL pública do arquivo após o upload.
     */
    String uploadFile(byte[] fileData, String fileName, String contentType);
}
