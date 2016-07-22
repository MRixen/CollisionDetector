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
        int zOffset = 0;
        float xCoordinate_tcp = Float.parseFloat(coordinates[0]);
        float yCoordinate_tcp = Float.parseFloat(coordinates[1]);
        float zCoordinate_tcp = Float.parseFloat(coordinates[2]);
        float xCoordinate_base = Float.parseFloat(coordinates[0]);
        float yCoordinate_base = Float.parseFloat(coordinates[1]);
        float zCoordinate_base = Float.parseFloat(coordinates[2]);
        int lineLength_tcp = 20;
        int circDiameter_tcp = 40;
        int lineLength_base = 20;
        int circDiameter_base = 30;
        float scaleFactorX, scaleFactorY, scaleFactorZ;

        // Calculate x / y offsets for the view (top, left, right, etc.)
        if(viewDescription.equals("top")) {
            yOffset = 630;
            xOffset = 380;
            if(xCoordinate_tcp<0) scaleFactorX = 0.48f;
            else scaleFactorX = 1f;
            scaleFactorY = -0.6f;

            xCoordinate_tcp = xCoordinate_tcp*scaleFactorX;
            yCoordinate_tcp = yCoordinate_tcp*scaleFactorY;
        }
        else if(viewDescription.equals("left")){
            yOffset = 500;
            xOffset = 360;
            // TODO Create new image without z-belt
            xCoordinate_tcp = -20;
            zCoordinate_tcp = 0;
        }
        else if(viewDescription.equals("right")){
            yOffset = 420;
            xOffset = 390;
            zOffset = -100;

            // Calculate positions for tcp
            if(xCoordinate_tcp<0) scaleFactorX = 0.508f;
            else scaleFactorX = 1f;
            scaleFactorY = -0.356f;
            scaleFactorZ = 0.5f;

            xCoordinate_tcp = xCoordinate_tcp*scaleFactorX;
            yCoordinate_tcp = (yCoordinate_tcp*scaleFactorY)+(zCoordinate_tcp*scaleFactorZ)+zOffset;

            // Calculate positions for z-base
            xCoordinate_base = xCoordinate_base*scaleFactorX;
            yCoordinate_base = yCoordinate_base*scaleFactorY;
        }
        else if(viewDescription.equals("front")){
            yOffset = 340;
            xOffset = 720;
            zOffset = 150;
            float zOffset2 = 270;

            float xCoordinateTemp = xCoordinate_tcp;
            float yCoordinateTemp = yCoordinate_tcp;

            if(xCoordinate_tcp<0) scaleFactorX = -0.492f;
            else scaleFactorX = -0.36f;
            scaleFactorY = -0.66f;
            scaleFactorZ = 0.55f;

//            yCoordinate_tcp = xCoordinateTemp*scaleFactorX;
            xCoordinate_tcp = yCoordinateTemp*scaleFactorY;

            yCoordinate_tcp = (xCoordinate_tcp*scaleFactorX)+(zCoordinate_tcp*scaleFactorZ)+zOffset;

            // Calculate positions for z-base
            xCoordinate_base = yCoordinate_base*scaleFactorY;
            yCoordinate_base = xCoordinate_base*scaleFactorX+zOffset2;
        }
        // Draw marker on image to show gripper location
//        BufferedImage bi = new BufferedImage(730, 473, BufferedImage.TYPE_INT_ARGB);
        BufferedImage bi = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);

        // Draw circle for tcp
        Graphics2D graphics1 = bi.createGraphics();
        graphics1.setStroke(new BasicStroke(8));
        graphics1.setColor(Color.RED);
        graphics1.drawLine(xOffset - (((int) xCoordinate_tcp) + (lineLength_tcp / 2)), yOffset - (((int) yCoordinate_tcp) + (lineLength_tcp / 2)), xOffset - (((int) xCoordinate_tcp) - (lineLength_tcp / 2)), yOffset - (((int) yCoordinate_tcp) - (lineLength_tcp / 2)));
        graphics1.drawLine(xOffset - (((int) xCoordinate_tcp) + (lineLength_tcp / 2) - 20), yOffset - (((int) yCoordinate_tcp) + (lineLength_tcp / 2)), xOffset - (((int) xCoordinate_tcp) - (lineLength_tcp / 2)) - 20, yOffset - (((int) yCoordinate_tcp) - (lineLength_tcp / 2)));
        graphics1.drawOval(xOffset - ((int) xCoordinate_tcp) - lineLength_tcp, yOffset - ((int) yCoordinate_tcp) - lineLength_tcp, circDiameter_tcp, circDiameter_tcp);

        // Draw circle for z-zero base on belt
        Graphics2D graphics2 = bi.createGraphics();
        graphics2.setStroke(new BasicStroke(2));
        graphics2.setColor(Color.RED);
        graphics2.drawLine(xOffset - (((int) xCoordinate_base) + (lineLength_base / 2)), yOffset - (((int) yCoordinate_base) + (lineLength_base / 2)), xOffset - (((int) xCoordinate_base) - (lineLength_base / 2)), yOffset - (((int) yCoordinate_base) - (lineLength_base / 2)));
        graphics2.drawLine(xOffset - (((int) xCoordinate_base) + (lineLength_base / 2) - 20), yOffset - (((int) yCoordinate_base) + (lineLength_base / 2)), xOffset - (((int) xCoordinate_base) - (lineLength_base / 2)) - 20, yOffset - (((int) yCoordinate_base) - (lineLength_base / 2)));

        yOffset = yOffset+((circDiameter_tcp-circDiameter_base)/2);
        xOffset = xOffset+((circDiameter_tcp-circDiameter_base)/2);
        graphics2.drawOval(xOffset - ((int) xCoordinate_base) - lineLength_base, yOffset - ((int) yCoordinate_base) - lineLength_base, circDiameter_base, circDiameter_base);

        // Draw line between the circles
        Graphics2D graphics3 = bi.createGraphics();
        graphics3.setStroke(new BasicStroke(2));
        graphics3.setColor(Color.RED);
        graphics3.drawLine(xOffset - ((int) xCoordinate_base) - lineLength_base + circDiameter_base/2, yOffset - ((int) yCoordinate_base) - lineLength_base + circDiameter_base/2, xOffset - ((int) xCoordinate_base) - lineLength_base + circDiameter_base/2, yOffset - ((int) yCoordinate_tcp) - lineLength_tcp + circDiameter_base/2);

        return bi;
    }
}
