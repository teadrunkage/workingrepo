package ru.ncedu.schek.shop.utils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileSystemProvider;
import java.util.Collections;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ImageUtil {

    public static byte[] loadImage(String filePath) {
        try {
          //  return Files.readAllBytes(Paths.get(ImageUtil.class.getResource(filePath).toURI()));

            URI uri = ImageUtil.class.getResource(filePath).toURI();
            
            Path source = Paths.get(uri);
           
            return Files.readAllBytes(source);
            
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    
}
