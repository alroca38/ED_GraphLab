package core.views;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {

    Image image;

    public BackgroundPanel(String path) {
        image = new ImageIcon(getClass().getResource(path)).getImage();
        
    }
    
    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
