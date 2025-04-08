package com.example.S1mulado.domain.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Value("${image.upload-dir}")
    public String uploadDir;

    @PostMapping("/")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file){

        try{

            String filePath = saveImage(file);
            return ResponseEntity.ok("Image uploaded successfully: " + filePath);

        }catch (IOException e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");

        }

    }


    private String saveImage(MultipartFile file) throws IOException {

        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath)){

            Files.createDirectory(uploadPath);

        }

        String tempFileName = LocalDateTime.now() + file.getOriginalFilename();
        Path tempFilePath = uploadPath.resolve(tempFileName);
        Files.copy(file.getInputStream(), tempFilePath, StandardCopyOption.REPLACE_EXISTING);


        //Files.delete(tempFilePath);
        return tempFilePath.toString();
    }


    public String saveAndResizeImage(MultipartFile file) throws IOException {

        // Verifica se o arquivo é uma imagem
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IOException("O arquivo enviado não é uma imagem válida.");
        }

        System.out.println("OI");

        // Lê a imagem original a partir do arquivo recebido
        BufferedImage originalImage = ImageIO.read(file.getInputStream());
        if (originalImage == null) {
            throw new IOException("Falha ao ler a imagem. O arquivo pode estar corrompido ou não ser suportado.");
        }

        int targetWidth = originalImage.getWidth() / 2;
        System.out.println(targetWidth);
        int targetHeight = originalImage.getHeight() / 2;

        // Redimensiona a imagem
        BufferedImage resizedImage = resizeImage(originalImage, targetWidth, targetHeight);

        // Define o nome e o caminho para salvar a imagem
        String fileName = LocalDateTime.now() + "_" + file.getOriginalFilename();
        Path imageUploadPath = Paths.get(uploadDir);
        if (!Files.exists(imageUploadPath)) {
            Files.createDirectories(imageUploadPath);
        }
        Path filePath = imageUploadPath.resolve(fileName);

        // Salva a imagem redimensionada
        String formatName = getFileExtension(fileName); // Obtém a extensão para salvar no formato correto
        ImageIO.write(resizedImage, formatName, filePath.toFile());

        return filePath.toString();
    }



    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {

        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g2d.dispose();

        return resizedImage;
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            return "jpg"; // Define jpg como padrão caso não haja extensão
        }
        return fileName.substring(dotIndex + 1).toLowerCase();
    }



}
