package com.ejemplo.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadUtil {
  public String saveFile(String fileName, MultipartFile multipartFile) throws IOException {
    Path uploadPath = Paths.get("files-upload");
    if (!Files.exists(uploadPath))
      Files.createDirectories(uploadPath);
    RandomStringGenerator generator = new RandomStringGenerator.Builder()
      .withinRange('0', 'z')
      .filteredBy(Character::isLetterOrDigit)
      .get();
    String fileCode = generator.generate(8);
    try (InputStream inputStream = multipartFile.getInputStream()) {
      Path destino = uploadPath.resolve(fileCode + "-" + uploadPath.toString());
      Files.copy(inputStream, destino, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      throw new IOException("Error guardando el archivo de imagen " + fileName, e);
    }
    return fileCode;
  }
}
