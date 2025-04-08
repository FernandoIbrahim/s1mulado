const fs = require('fs');
const path = require('path');

function clearDirectory(directoryPath) {
    fs.readdir(directoryPath, (err, files) => {
        if (err) {
            console.error(`Erro ao ler o diretório: ${err}`);
            return;
        }
        files.forEach((file) => {
            const filePath = path.join(directoryPath, file);
            fs.unlink(filePath, (err) => {
                if (err) {
                    console.error(`Erro ao excluir o arquivo ${filePath}: ${err}`);
                } else {
                    console.log(`Arquivo excluído: ${filePath}`);
                }
            });
        });
    });
}

module.exports = clearDirectory;
