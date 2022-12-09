package com.springflutter.demo.util;

import com.springflutter.demo.entity.ImageModel;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Component
public class ImageModelUtil {

    /**
     *This method converts an image to an array of bytes
     */
    public byte[] convertImageToBytes(String name, String type) throws IOException {

        BufferedImage bImage = ImageIO.read(new File("C:\\Users\\user\\Desktop\\Poze Hoteluri\\"+ name));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, type, bos );
        byte [] data = bos.toByteArray();

        return data;
    }
}
