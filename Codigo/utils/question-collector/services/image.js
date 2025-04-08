const https = require('https');
const http = require('http');
const fs = require('fs');
const path = require('path');  // Para manipular caminhos de arquivos
const { URL } = require('url');
const { Model } = require('sequelize');

const clearDirectory = require('./dirCleaner');

const uploadImagesFromDirectory = require('../aws/aws');

async function downloadImage(url, filename) {
  try {
    const parsedUrl = new URL(url);
    
    // Verifica se o protocolo é válido
    if (!['http:', 'https:'].includes(parsedUrl.protocol)) {
      throw new Error('Protocolo inválido. Use HTTP ou HTTPS.');
    }

    // Define o cliente adequado (HTTP/HTTPS)
    const client = parsedUrl.protocol === 'https:' ? https : http;

    // Extrai o nome do arquivo se não for fornecido
    if (!filename) {
      filename = parsedUrl.pathname.split('/').pop() || 'downloaded_image';
      filename = filename.split('?')[0]; // Remove query parameters
    }

    // Caminho para a pasta 'imagens'
    const dir = path.join(__dirname, '../imagens');
    
    // Garante que a pasta 'imagens' exista, caso contrário, cria a pasta
    if (!fs.existsSync(dir)) {
      fs.mkdirSync(dir);
    }

    // Caminho completo para o arquivo
    const filePath = path.join(dir, filename);
    const file = fs.createWriteStream(filePath);

    // Configura o request com headers para evitar bloqueios
    const options = {
      hostname: parsedUrl.hostname,
      path: parsedUrl.pathname + parsedUrl.search,
      port: parsedUrl.port,
      headers: {
        'User-Agent': 'Mozilla/5.0 (Node.js)'
      }
    };

    return new Promise((resolve, reject) => {
      client.get(options, (response) => {
        // Verifica resposta válida
        if (response.statusCode === 301 || response.statusCode === 302) {
          return reject(new Error('Redirecionamento não suportado'));
        }
        
        if (response.statusCode !== 200) {
          return reject(new Error(`Falha no download. Código: ${response.statusCode}`));
        }

        // Pipe do stream de resposta para o arquivo
        response.pipe(file);

        file.on('finish', () => {
          file.close();
          resolve(`Imagem salva em ${filePath}`);
        });

        file.on('error', (err) => {
          fs.unlink(filePath, () => reject(err));
        });
      }).on('error', (err) => {
        fs.unlink(filePath, () => reject(err));
      });
    });

  } catch (err) {
    throw new Error(`Erro: ${err.message}`);
  }
}



async function downloadAndUploadAllImages(imageUrls) {
    for (let url of imageUrls) {
      try {
        const message = await downloadImage(url);
        console.log(message); // Exibe a mensagem de sucesso
      } catch (err) {
        console.error(`Erro ao baixar a imagem de ${url}: ${err.message}`);
      }
      
    }

    await uploadImagesFromDirectory();
    await delay(1000);
    await clearDirectory('./imagens');

}

module.exports = {
  downloadAndUploadAllImages
}

function delay(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}




