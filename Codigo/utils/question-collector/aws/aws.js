const AWS = require('aws-sdk');
const fs = require('fs');
const path = require('path');

require('dotenv').config();

// Configurações da AWS
AWS.config.update({
  accessKeyId: process.env.AWS_ACCESS_KEY_ID,        // Usa a variável de ambiente
  secretAccessKey: process.env.AWS_SECRET_ACCESS_KEY, // Usa a variável de ambiente
  region: process.env.AWS_REGION // Usa a variável de ambiente
});
const s3 = new AWS.S3();
const directoryPath = path.join(__dirname, '../imagens'); // Diretório onde as imagens estão armazenadas
const bucketName = 's1mulado'; // Substitua pelo nome do seu bucket

// Função para enviar uma imagem para o S3
async function uploadImageToS3(filePath, bucketName, key) {
  const fileContent = fs.readFileSync(filePath);
  
  const params = {
    Bucket: bucketName,
    Key: key, // Nome do arquivo no bucket
    Body: fileContent,
    ContentType: 'image/jpeg' // Você pode ajustar o tipo de conteúdo conforme necessário
  };

  try {
    const data = await s3.upload(params).promise();
    console.log(`Arquivo enviado com sucesso! URL: ${data.Location}`);
  } catch (err) {
    console.error(`Erro ao enviar o arquivo ${key}:`, err.message);
  }
}

// Função para percorrer o diretório e enviar as imagens para o S3
async function uploadImagesFromDirectory() {
  fs.readdir(directoryPath, (err, files) => {
    if (err) {
      console.error('Erro ao ler o diretório:', err);
      return;
    }

    files.forEach(async (file) => {
      const filePath = path.join(directoryPath, file);
      const fileExtension = path.extname(file).toLowerCase();

      // Verifica se o arquivo é uma imagem (você pode adicionar mais tipos de imagem)
      if (['.jpg', '.jpeg', '.png', '.gif'].includes(fileExtension)) {
        const key = `imagens/${file}`; // Caminho no S3 (diretório + nome do arquivo)
        await uploadImageToS3(filePath, bucketName, key);
      }
    });
  });
}

// Inicia o upload das imagens
module.exports = uploadImagesFromDirectory;


function delay(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }
  