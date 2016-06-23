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
        float scaleFactorXtop = 0.35f, scaleFactorYtop = 0.018f, scaleFactorZtop = 1f;

        // Calculate x / y offsets for the view (top, left, right, etc.)
        if(viewDescription.equals("top")) {
            yOffset = 620;
            xOffset = 290;
//            xCoordinate = xCoordinate*(-scaleFactorXtop);
            xCoordinate = 105*(-0.1f);
//            yCoordinate = yCoordinate*(-scaleFactorYtop);
            yCoordinate = -469*(-0.57f);
        }
        else if(viewDescription.equals("left")){
            yOffset = 500;
            xOffset = 360;
            // TODO Remove this (its for simulation only)
            xCoordinate = -20;
            zCoordinate = 0;
        }
        else if(viewDescription.equals("right")){
            yOffset = 230;
            xOffset = 290;
            // TODO Remove this (its for simulation only)
            xCoordinate = 150;
            yCoordinate = 75;
        }
        else if(viewDescription.equals("front")){
            yOffset = 230;
            xOffset = 290;
            // TODO Remove this (its for simulation only)
            xCoordinate = 150;
            yCoordinate = 75;
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
