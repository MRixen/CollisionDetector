package com.company.Visualization;

import com.company.DataSet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Manuel.Rixen on 28.04.2016.
 */
public class GripperLocation {

    public byte[][] generateImages(DataSet dataSet, String[] coordinates) {
        byte[][] imageArray = new byte[4][];
        JFrame frame = new JFrame("Gripper Location");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(new Dimension(730, 510));

        // Generate images one after the other to show gripper location
        //for (int i=0;i<=dataSet.getImageNames().length-1;i++) {
        for (int i=3;i<=3;i++) {

            frame.getContentPane().removeAll();
            frame.add(new ImagePanel(dataSet.getImagePathName()+dataSet.getImageNames()[i]+".png", dataSet.getImageNames()[i], coordinates));
            frame.setVisible(true);

            // Get image from JFrame
            Container content = frame.getContentPane();
            BufferedImage img = new BufferedImage(content.getWidth(), content.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = img.createGraphics();
            content.printAll(g2d);
            g2d.dispose();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                // Save images in array and return this to calling function
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(img, "jpg", byteArrayOutputStream);
                imageArray[i] = byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Generating images failed in Server-Thread > GripperLocation. Exception thrown: " + e);
            }
        }
        return imageArray;
    }
}
