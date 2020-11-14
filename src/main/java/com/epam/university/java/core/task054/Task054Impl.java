package com.epam.university.java.core.task054;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Task054Impl implements Task054 {
    @Override
    public BufferedImage grayscaleFilter(String inputFilePath, String outputFilePath) {
        BufferedImage myColorImage;
        try {
            myColorImage = ImageIO.read(new File(inputFilePath));
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found:" + inputFilePath);
        }

        BufferedImage outImage = new BufferedImage(myColorImage.getWidth(),
                myColorImage.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

        for (int x = 0; x < myColorImage.getWidth(); x++) {
            for (int y = 0; y < myColorImage.getHeight(); y++) {
                int pixel = myColorImage.getRGB(x, y);
                int red = getRed(pixel);
                int green = getGreen(pixel);
                int blue = getBlue(pixel);
                int grey = (int) Math.round((red + green + blue) / 3.0);
                Color newColor = new Color(grey,
                        grey, grey);
                outImage.setRGB(x, y, newColor.getRGB());
            }
        }

        outImage.flush();
        File outFile = new File(outputFilePath);
        try {
            ImageIO.write(outImage, "jpg", outFile);
        } catch (IOException e) {
            throw new IllegalArgumentException("Output File not found:" + inputFilePath);
        }

        return outImage;
    }

    @Override
    public BufferedImage sepiaFilter(String inputFilePath, String outputFilePath) {
        BufferedImage myColorImage;
        try {
            myColorImage = ImageIO.read(new File(inputFilePath));
        } catch (IOException e) {
            throw new IllegalArgumentException("Input File not found:" + inputFilePath);
        }

        BufferedImage outImage = new BufferedImage(myColorImage.getWidth(),
                myColorImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < myColorImage.getWidth(); x++) {
            for (int y = 0; y < myColorImage.getHeight(); y++) {
                int pixel = myColorImage.getRGB(x, y);
                int redOrig = getRed(pixel);
                int greenOrig = getGreen(pixel);
                int blueOrig = getBlue(pixel);
                int redResult = (((int) (0.393 * redOrig + 0.769 * greenOrig + 0.189 * blueOrig)));
                int greenResult = ((int) (0.349 * redOrig + 0.686 * greenOrig + 0.168 * blueOrig));
                int blueResult = ((int) (0.272 * redOrig + 0.534 * greenOrig + 0.131 * blueOrig));
                if (redResult > 255) {
                    redResult = 255;
                }
                if (greenResult > 255) {
                    greenResult = 255;
                }
                if (blueResult > 255) {
                    blueResult = 255;
                }

                Color newColor = new Color(redResult, greenResult, blueResult);
                outImage.setRGB(x, y, newColor.getRGB());
            }
        }
        outImage.flush();

        File outFile = new File(outputFilePath);
        try {
            ImageIO.write(outImage, "jpg", outFile);
        } catch (IOException e) {
            throw new IllegalArgumentException("Output File not found:" + outputFilePath);
        }

        return outImage;
    }

    @Override
    public BufferedImage reflectFilter(String inputFilePath, String outputFilePath) {
        BufferedImage myColorImage;
        try {
            myColorImage = ImageIO.read(new File(inputFilePath));
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found:" + inputFilePath);
        }


        BufferedImage outImage = new BufferedImage(myColorImage.getWidth(),
                myColorImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < myColorImage.getWidth(); x++) {
            for (int y = 0; y < myColorImage.getHeight(); y++) {
                int pixel = myColorImage.getRGB(x, y);
                int positionX = myColorImage.getWidth() - x - 1;
                outImage.setRGB(positionX, y, pixel);
            }
        }
        outImage.flush();

        File outFile = new File(outputFilePath);
        try {
            ImageIO.write(outImage, "jpg", outFile);
        } catch (IOException e) {
            throw new IllegalArgumentException("Output File not found:" + outputFilePath);
        }

        return outImage;
    }

    @Override
    public BufferedImage originalImage(String inputFilePath) {
        BufferedImage myColorImage = null;
        try {
            myColorImage = ImageIO.read(new File(inputFilePath));
        } catch (IOException e) {
            System.out.println("Не смог прочитать файл. Путь: " + inputFilePath);
        }
        return myColorImage;
    }

    @Override
    public int getRed(int pixel) {
        Color color = new Color(pixel);
        return color.getRed();
    }

    @Override
    public int getGreen(int pixel) {
        Color color = new Color(pixel);
        return color.getGreen();
    }

    @Override
    public int getBlue(int pixel) {
        Color color = new Color(pixel);
        return color.getBlue();
    }
}
