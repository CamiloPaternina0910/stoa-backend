package com.backend.stoa.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Setter
@Getter
public class SubidaArchivosUtil {

    private MultipartFile file;

    private String copyFileToUploads(String ubicacion) throws IOException {
        String nameFile = getStandardNameFile() + "." + getExtensionFile();
        Path path = Paths.get(ubicacion).resolve(nameFile).toAbsolutePath();

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

    public String subirImagen() throws IOException {
        if(!(getExtensionFile().equals("jpg") || getExtensionFile().equals("png") || getExtensionFile().equals("jpeg")))
            throw new IOException("Archivo no soportado : Formatos disponibles (png, jpg, jpeg)");
        return copyFileToUploads("uploads/imagenes");
    }

    public String subirPdf() throws IOException {
        if(!getExtensionFile().equals("pdf"))
            throw new IOException("Archivo no soportado : Formato pdf requerido");
        return copyFileToUploads("uploads/pdfs");
    }

    public void eliminarImagen(String imagen){
        Path path = Paths.get("uploads/imagenes").resolve(imagen).toAbsolutePath();
        File fileToRemove = new File(path.toUri());
        fileToRemove.delete();
    }

    public void eliminarPdf(String pdf){
        Path path = Paths.get("uploads/imagenes").resolve(pdf).toAbsolutePath();
        File fileToRemove = new File(path.toUri());
        fileToRemove.delete();
    }
}
