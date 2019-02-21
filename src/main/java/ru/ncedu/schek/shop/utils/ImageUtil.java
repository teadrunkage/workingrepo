package ru.ncedu.schek.shop.utils;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ImageUtil {

    public static byte[] loadImage(String filePath) {
        try {
            return Files.readAllBytes(Paths.get(ImageUtil.class.getResource(filePath).toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    
}
