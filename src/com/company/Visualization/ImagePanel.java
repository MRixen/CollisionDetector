package com.company.Visualization;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Manuel.Rixen on 27.04.2016.
 */
public class ImagePanel extends JPanel {
    private final String[] coordinates;
    private String viewDescription;
    private BufferedImage img1;

    public ImagePanel(String imagePath, String viewDescription, String[] coordinates) {
        this.coordinates = coordinates;
        setLayout(new GridBagLayout());
        this.viewDescription = viewDescription;
        try {
            img1 = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {

        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(img1, 5, 5, 730, 473, this);
//        g.drawImage(drawSomething(), 5, 5, 740, 483, this);
        g.drawImage(drawSomething(), 5, 5, 1000, 1000, this);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 500);
    }

    private BufferedImage drawSomething(){
        int yOffset = 0;
        int xOffset = 0;
        float yCoordinate = Float.parseFloat(coordinates[0]);
        float xCoordinate = Float.parseFloat(coordinates[1]);
        float zCoordinate = Float.parseFloat(coordinates[2]);
        int lineLength = 20;
        int circDiameter = 40;
        float scaleFactorX, scaleFactorY, scaleFactorZ = 1f;

        // Calculate x / y offsets for the view (top, left, right, etc.)
        if(viewDescription.equals("top")) {
            yOffset = 630;
            xOffset = 380;
            if(xCoordinate<0) scaleFactorX = 0.48f;
            else scaleFactorX = 1f;
            scaleFactorY = -0.6f;

            xCoordinate = xCoordinate*scaleFactorX;
            yCoordinate = yCoordinate*scaleFactorY;
        }
        else if(viewDescription.equals("left")){
            yOffset = 500;
            xOffset = 360;
            // TODO Create new image without z-belt
            xCoordinate = -20;
            zCoordinate = 0;
        }
        else if(viewDescription.equals("right")){
            yOffset = 420;
            xOffset = 390;
            if(xCoordinate<0) scaleFactorX = 0.508f;
            else scaleFactorX = 1f;
            scaleFactorY = -0.356f;

            xCoordinate = xCoordinate*scaleFactorX;
            yCoordinate = yCoordinate*scaleFactorY;
        }
        else if(viewDescription.equals("front")){
            yOffset = 340;
            xOffset = 720;

            float xCoordinateTemp = xCoordinate;
            float yCoordinateTemp = yCoordinate;

            if(xCoordinate<0) scaleFactorX = -0.492f;
            else scaleFactorX = -0.36f;
            scaleFactorY = -0.66f;

            yCoordinate = xCoordinateTemp*scaleFactorX;
            xCoordinate = yCoordinateTemp*scaleFactorY;
        }
        // Draw marker on image to show gripper location
//        BufferedImage bi = new BufferedImage(730, 473, BufferedImage.TYPE_INT_ARGB);
        BufferedImage bi = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = bi.createGraphics();
        graphics.setStroke(new BasicStroke(8));
        graphics.setColor(Color.RED);
        graphics.drawLine(xOffset - (((int) xCoordinate) + (lineLength / 2)), yOffset - (((int) yCoordinate) + (lineLength / 2)), xOffset - (((int) xCoordinate) - (lineLength / 2)), yOffset - (((int) yCoordinate) - (lineLength / 2)));
        graphics.drawLine(xOffset - (((int) xCoordinate) + (lineLength / 2) - 20), yOffset - (((int) yCoordinate) + (lineLength / 2)), xOffset - (((int) xCoordinate) - (lineLength / 2)) - 20, yOffset - (((int) yCoordinate) - (lineLength / 2)));
        graphics.drawOval(xOffset - ((int) xCoordinate) - lineLength, yOffset - ((int) yCoordinate) - lineLength, circDiameter, circDiameter);
        return bi;
    }
}
