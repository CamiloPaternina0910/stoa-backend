package com.backend.stoa.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class SubidaArchivosUtil {

    private MultipartFile file;

    public SubidaArchivosUtil(MultipartFile file){
        this.file = file;
    }

    public String copyFileToUploads() throws IOException {
        String nameFile = getStandardNameFile() + "." + getExtensionFile();
        Path path = Paths.get("uploads").resolve(nameFile).toAbsolutePath();

        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        return nameFile;
    }

    public String getExtensionFile(){
        int positionExtension =file.getOriginalFilename().lastIndexOf(".");
        String extension =file.getOriginalFilename().substring(positionExtension + 1);
        return extension;
    }

    public String getStandardNameFile(){
        return System.currentTimeMillis() + "";
    }
}
