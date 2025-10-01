# Gerador de QR Code - Backend

Projeto de backend para gerar QR Codes de forma rápida e eficiente, desenvolvido para fins de portfólio.

## 🔹 Funcionalidades

- Geração de QR Codes a partir de textos ou URLs via API REST.
- Salvamento de QR Codes como arquivos de imagem no **Amazon S3**.
- Código modular, limpo e pronto para integração com outros sistemas.

## 🔹 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Maven
- Biblioteca ZXing para geração de QR Codes
- Amazon S3 para armazenamento de imagens
- Docker para empacotamento da aplicação

## 🔹 Como Rodar

### 1. Clone o repositório
```bash
git clone <URL_DO_REPOSITORIO>
cd qrcode.generator
2. Crie um arquivo .env na raiz do projeto
Esse arquivo conterá suas credenciais e configurações da AWS:

env
Copiar código
AWS_ACCESS_KEY_ID=seu_access_key
AWS_SECRET_ACCESS_KEY=sua_secret_key

⚠️ Importante: Nunca versionar este arquivo. Certifique-se de adicioná-lo ao .gitignore.

3. Rodando localmente com Maven
bash
Copiar código
mvn clean package
java -jar target/qrcode-generator.jar
4. Rodando com Docker
Crie a imagem:

bash
Copiar código
docker build -t qrcode-generator .
Rode o container passando as variáveis de ambiente:

bash
Copiar código
docker run --env-file .env -p 8080:8080 qrcode-generator
Agora a API estará disponível em http://localhost:8080.

5. Uso da API
Envie uma requisição POST para o endpoint configurado (ex.: /qrcode) com o texto ou URL.

O QR Code será gerado e salvo automaticamente no Amazon S3.
