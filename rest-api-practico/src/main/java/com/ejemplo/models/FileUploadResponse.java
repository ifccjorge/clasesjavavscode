package com.ejemplo.models;

public record FileUploadResponse(
  String fileName,
  String downloadURI,
  long fileSize
) {

}
