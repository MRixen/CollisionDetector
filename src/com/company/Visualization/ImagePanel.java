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
    private String viewDescription;
    BufferedImage img1;

    public ImagePanel(String imagePath, String viewDescription) {
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

        g.drawImage(img1, 5, 5, 500, 400, this);
        g.drawImage(drawSomething(), 5, 5, 510, 410, this);
    }

    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }

    private BufferedImage drawSomething(){
        int yOffset = 0;
        int xOffset = 0;
        int xCoordinate = 0;
        int yCoordinate = 0;
        int zCoordinate = 0;
        int lineLength = 20;
        int circDiameter = 40;

        // Calculate x / y offsets for the view (top, left, right, etc.)
        if(viewDescription.equals("top")) {
            yOffset = 230;
            xOffset = 290;
            // TODO Remove this (its for simulation only)
            xCoordinate = 150;
            yCoordinate = 75;
        }
        else if(viewDescription.equals("left")){
            yOffset = 300;
            xOffset = 320;
            // TODO Remove this (its for simulation only)
            xCoordinate = 70;
            zCoordinate = 75;
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
        BufferedImage bi = new BufferedImage(500, 400, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = bi.createGraphics();
        graphics.setStroke(new BasicStroke(8));
        graphics.setColor(Color.BLUE);
        graphics.drawLine(xOffset - (xCoordinate + (lineLength / 2)), yOffset - (yCoordinate + (lineLength / 2)), xOffset - (xCoordinate - (lineLength / 2)), yOffset - (yCoordinate - (lineLength / 2)));
        graphics.drawLine(xOffset - (xCoordinate + (lineLength / 2) - 20), yOffset - (yCoordinate + (lineLength / 2)), xOffset - (xCoordinate - (lineLength / 2)) - 20, yOffset - (yCoordinate - (lineLength / 2)));
        graphics.drawOval(xOffset - xCoordinate - lineLength, yOffset - yCoordinate - lineLength, circDiameter, circDiameter);
        return bi;
    }
}
