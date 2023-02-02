package com.backend.stoa.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.InputStreamResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Getter
@Setter
public class DescargaArchivosUtil {

    File file;

    public DescargaArchivosUtil(String file){
        Path path = Paths.get("uploads").resolve(file).toAbsolutePath();
        this.file = new File(path.toUri());
    }

    public InputStreamResource getInputStreamFile() throws FileNotFoundException {
        InputStreamResource resource = new InputStreamResource(new FileInputStream(getFile()));
        return resource;
    }
}
