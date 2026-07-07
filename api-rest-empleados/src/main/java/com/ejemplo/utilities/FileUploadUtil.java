package com.ejemplo.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadUtil {
  public String saveFile(String fileName, MultipartFile multipartFile) throws IOException {
    Path uploadPath = Paths.get("target", "files-upload");
    if (!Files.exists(uploadPath))
      Files.createDirectories(uploadPath);
    String caracteres = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    SecureRandom random = new SecureRandom();
    String fileCode = random.ints(8, 0, caracteres.length())
      .mapToObj(caracteres::charAt)
      .map(Object::toString)
      .collect(Collectors.joining());
    try (InputStream inputStream = multipartFile.getInputStream()) {
      Path destino = uploadPath.resolve(fileCode + "-" + multipartFile.getOriginalFilename());
      Files.copy(inputStream, destino, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      throw new IOException("Error guardando el archivo de imagen " + fileName, e);
    }
    return fileCode;
  }
}
