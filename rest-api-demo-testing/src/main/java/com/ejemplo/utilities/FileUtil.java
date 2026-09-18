package com.ejemplo.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

@Component
public class FileUtil {
  public void eliminarArchivo(String fileName) {
    Path filenamePath = Paths.get("target", "files-upload", fileName);
      try {
          Files.deleteIfExists(filenamePath);
      } catch (IOException ex) {
          System.getLogger(FileUtil.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
      }
  }
}
